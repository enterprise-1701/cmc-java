package automationFramework.TestCases;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automationFramework.PageObjects.*;
import automationFramework.RestApi.ApiCustomerPost;
import automationFramework.Utilities.*;

//#################################################################################
//Quality Center Test IDs: 75817, 72101
// Testing GIT 
//#################################################################################

public class BalanceHistoryTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String TRANSACTION_AMOUNT2 = "$28.00";
	private static final String ENTRY_TYPE2 = "Purse Load";
	private static final String PURSE_RC = "Default Purse";
	private static final String NO_RECORD_FOUND = "No records found";
	private static final String TOKEN_TYPE = "Bankcard";
	private static final String STATUS = "Active";
	private static final String DEVICE = "SAAJ-Auto";

	static WebDriver driver;
	static String browser;
	private static String phoneNumber;
	private static String email;
	private static CustomerData cData;
	CoreTest coreTest = new CoreTest();
	CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
	String validCCNumber;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		Logging.setLogConsole();
		Logging.setLogFile();
		Log.info("Setup Started");
		Log.info("Current OS: " + WindowsUtils.readStringRegistryValue(Global.OS));
		Log.info("Current Browser: " + browser);
		driver = Utils.openBrowser(browser);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Log.info("Setup Completed");
	}

	// STA-721 - view unregistered customer balance history dynamic
	@Test(enabled = true)
	public void viewBalanceHistoryUnregisteredDynamic() throws Exception {

		// create travel balance via soap call
		SOAPClientSAAJ sClient = new SOAPClientSAAJ();
		CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
		String validCCNumber = ccGenerator.generate("4", 16);
		Log.info("New CC Number is:  " + validCCNumber);
		String accountID = sClient.createABPAccountSOAPCall(validCCNumber);
		Log.info("cc number being used is " + validCCNumber);
		Log.info("account id being returned is " + accountID);

		Log.info("wait time for balnce history to display on cmc");
		Utils.waitTime(120000);

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();

		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Utils.waitTime(5000);

		tPage.clickViewDetails(driver);
		BalanceHistoryPage bdPage = new BalanceHistoryPage(driver);
		Assert.assertEquals(bdPage.getTokenType(driver), TOKEN_TYPE);
		Assert.assertEquals(bdPage.getStatus(driver), STATUS);
		Assert.assertEquals(bdPage.getDevice(driver), DEVICE);
		driver.close();

	}

	// view registered customer balance history dynamic
	@Test(enabled = true)
	public void viewBalanceHistoryRegisteredDynamic() throws Exception {

		registerCustomerAndCreateBalance(driver);
		BalanceHistoryPage bdPage = new BalanceHistoryPage(driver);
		Assert.assertEquals(bdPage.getPurse(driver), PURSE_RC);
		Assert.assertEquals(bdPage.getEntryType(driver), ENTRY_TYPE2);
		Assert.assertEquals(bdPage.getTransactionAmount(driver), TRANSACTION_AMOUNT2);
		driver.close();

	}

	// view registered customer balance history details
	@Test(enabled = true)
	public void viewBalanceHistoryDetailsRegisteredDynamic() throws Exception {

		registerCustomerAndCreateBalance(driver);
		BalanceHistoryPage bhPage = new BalanceHistoryPage(driver);
		Utils.waitTime(5000);
		bhPage.clickRow(driver);
		BalanceHistoryDetailPage bdPage = new BalanceHistoryDetailPage(driver);
		Assert.assertEquals(bdPage.getPurse(driver), PURSE_RC);
		Assert.assertEquals(bdPage.getEntryType(driver), ENTRY_TYPE2);
		driver.close();

	}

	// CCD-851 - balance history filter
	@Test(enabled = true)
	public void viewBalanceHistoryFilterTest() throws Exception {

		registerCustomerAndCreateBalance(driver);
		BalanceHistoryPage bdPage = new BalanceHistoryPage(driver);
		Utils.waitTime(5000);
		bdPage.selectTransactionType(driver, "Charge");
		bdPage.clickBalanceHistoryFilter(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(bdPage.getNoRecordFound(driver), NO_RECORD_FOUND);
		bdPage.selectTransactionType(driver, "PurseLoad");
		bdPage.clickBalanceHistoryFilter(driver);
		Utils.waitTime(5000);

		Assert.assertEquals(bdPage.getPurse(driver), PURSE_RC);
		Assert.assertEquals(bdPage.getEntryType(driver), ENTRY_TYPE2);
		driver.close();

	}

	// failing balance history not getting updated
	@Test(enabled = true)
	public void viewUpdatedBalanceHistoryTest() throws Exception {
		Boolean balanceUpdated = false;

		// create a tap, create oneaccount and link token to oneaccount
		loadValueAndRegisterCustomer(driver);
		BalanceHistoryPage bdPage = new BalanceHistoryPage(driver);
		bdPage.clickBalanceHistory(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);

		// create another tap with the original CC
		SOAPClientSAAJ sClient = new SOAPClientSAAJ();
		Log.info("cc number being used the second time is " + validCCNumber);
		sClient.postTapSOAPCall(validCCNumber);
		Log.info("wait 1 minute for trip to get generated");
		Utils.waitTime(60000);
		String initialBalance = (nPage3.getAccountBalances(driver).substring(1));
		Log.info("initial balance is: " + Double.valueOf(initialBalance));

		// Go back and do another search on the customer to see the updated
		// account balance
		nPage3.clickHome(driver);
		SearchPage sPage = new SearchPage(driver);
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterEmail(driver, email);
		sPage.clickSearch(driver);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -275)", "");
		Utils.waitTime(5000);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);

		Log.info("updated balance is: " + Double.valueOf(nPage3.getAccountBalances(driver).substring(1)));

		// Assertion based on updated account balance less than initial account
		// balance
		if (Double.valueOf(nPage3.getAccountBalances(driver).substring(1)) < Double.valueOf(initialBalance)) {
			balanceUpdated = true;
		}

		Assert.assertTrue(balanceUpdated);
		driver.close();

	}

	// private methods
	private WebDriver registerCustomerAndCreateBalance(WebDriver driver) throws Exception {

		// create balance via soap api
		SOAPClientSAAJ sClient = new SOAPClientSAAJ();
		CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
		String validCCNumber = ccGenerator.generate("4", 16);
		String accountID = sClient.createABPAccountSOAPCall(validCCNumber);
		Log.info("cc number being used is " + validCCNumber);
		Log.info("account id being returned is " + accountID);

		// Create customer test data via rest api
		cData = ApiCustomerPost.apiPostSuccess();
		email = cData.getEmail();
		phoneNumber = cData.getPhone();
		Log.info("Email and phone number from API:  " + email + " " + phoneNumber);

		// return to selenium testing
		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterEmail(driver, email);
		sPage.clickSearch(driver);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -275)", "");
		Utils.waitTime(8000);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);

		BasePage bPage = new BasePage(driver);
		Utils.waitTime(5000);
		bPage.clickLinkAccount(driver);
		LinkAccountPage lPage = new LinkAccountPage(driver);

		// use cc number from soap call to link account
		lPage.enterBankAccount(driver, validCCNumber);
		lPage.selectExpMonth(driver);
		lPage.selectExpYear(driver, 2);
		lPage.clickSearchToken(driver);
		lPage.enterNickName(driver, Global.NICKNAME);
		lPage.clickLinkAccount(driver);

		// check balance history
		bPage.clickBalanceHistory(driver);
		return driver;
	}

	// load value on CC and register customer and link
	private WebDriver loadValueAndRegisterCustomer(WebDriver driver) throws Exception {

		// load value on token via soap api
		SOAPClientSAAJ sClient = new SOAPClientSAAJ();
		validCCNumber = ccGenerator.generate("4", 16);
		sClient.loadValueSOAPCall(validCCNumber);
		Log.info("cc number being used is " + validCCNumber);

		// Create customer test data via rest api
		cData = ApiCustomerPost.apiPostSuccess();
		email = cData.getEmail();
		phoneNumber = cData.getPhone();
		Log.info("Email and phone number from API:  " + email + " " + phoneNumber);

		// return to selenium testing
		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterEmail(driver, email);
		sPage.clickSearch(driver);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -275)", "");
		Utils.waitTime(5000);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);

		// use cc number from soap call to link account
		BasePage bPage = new BasePage(driver);
		Utils.waitTime(8000);
		bPage.clickLinkAccount(driver);
		LinkAccountPage lPage = new LinkAccountPage(driver);
		lPage.enterBankAccount(driver, validCCNumber);
		lPage.selectExpMonth(driver);
		lPage.selectExpYear(driver, 2);
		lPage.clickSearchToken(driver);
		lPage.enterNickName(driver, Global.NICKNAME);
		lPage.clickLinkAccount(driver);

		return driver;
	}

	private TokenSearchPage getTokenSearchPage() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		TokenSearchPage tPage = new TokenSearchPage(driver);
		return tPage;
	}

	private SearchPage getSearchPage() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		SearchPage sPage = new SearchPage(driver);
		return sPage;
	}

	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}