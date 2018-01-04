package automationFramework.TestCases;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automationFramework.PageObjects.*;
import automationFramework.Utilities.*;

//#################################################################################
//Quality Center Test IDs: 74589, 74653, 75705, 74590, 74594, 74593, 75706
//#################################################################################

public class TokenSearchTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String DATE = "2017-05-25T20:43:42.000Z";
	private static final String TRANSACTION = "1000, 1000";
	private static final String AMOUNT = "$7.60";
	private static final String TRANSACTION2 = "Load value for Unrestricted";
	private static final String AMOUNT2 = "$28.00";
	private static final String NO_RECORD_FOUND = "No Records Found.";

	static WebDriver driver;
	static String browser;
	CoreTest coreTest = new CoreTest();
	UserData userData = new UserData();
	CreditCardNumberGenerator ccGen = new CreditCardNumberGenerator();

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

	
	// Search token invalid month
	@Test(priority = 1, enabled = true)
	public void searchTokenInvalidMonth() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectInvalidExpMonth(driver);
		tPage.selectExpYear(driver);
		Assert.assertEquals(tPage.getRequiredFieldErrorMonth(driver), Global.REQUIREDFIELD_ERROR);
		Assert.assertFalse(tPage.isSearchTokenEnabled(driver));
		driver.close();

	}


	// Search token invalid year
	@Test(priority = 2, enabled = true)
	public void searchTokenInvalidYear() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.selectInvalidExpYear(driver);
		Assert.assertEquals(tPage.getRequiredFieldErrorYear(driver), Global.REQUIREDFIELD_ERROR);
		Assert.assertFalse(tPage.isSearchTokenEnabled(driver));
		driver.close();

	}

	// Search token missing bank card number
	@Test(priority = 3, enabled = true)
	public void searchTokenMissingBankCard() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		Assert.assertFalse(tPage.isSearchTokenEnabled(driver));
		driver.close();

	}

	
	// Search token new search
	@Test(priority = 4, enabled = true)
	public void searchTokenNewSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickNewSearch(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	// Search token no record found
	@Test(priority = 5, enabled = true)
	public void searchTokenNoRecordFound() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), NO_RECORD_FOUND);
		driver.close();

	}

	// Search token no record found new search
	@Test(priority = 6, enabled = true)
	public void searchTokenNoRecordFoundNewSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), NO_RECORD_FOUND);
		tPage.clickResultsNewSearch(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	// Search token invalid token search
	@Test(priority = 7, enabled = true)
	public void searchTokenInvalidTokenSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, Global.INVALID_CC);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), NO_RECORD_FOUND);
		tPage.enterNickName(driver, "joe");
		tPage.clickRegisterCustomer(driver);
		Assert.assertEquals(tPage.getInavlidTokenError(driver), "Invalid Token");
		driver.close();

	}

	// Search token invalid token search new search
	@Test(priority = 8, enabled = true)
	public void searchTokenInvalidTokenNewSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, Global.INVALID_CC);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), NO_RECORD_FOUND);
		tPage.enterNickName(driver, "joe");
		tPage.clickRegisterCustomer(driver);
		Assert.assertEquals(tPage.getInavlidTokenError(driver), "Invalid Token");
		tPage.clickInvalidTokenNewSearch(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	// STA-698 - Search and view unregistred customer
	@Test(priority = 9, enabled = true)
	public void searchUnregistredCustomer() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "4887150737923832");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Utils.waitTime(5000);
		tPage.clickViewDetails(driver);
		Utils.waitTime(5000);
		UnregistredCustomerPage uPage = new UnregistredCustomerPage(driver);
		Assert.assertEquals(uPage.getAccountNumber(driver), Global.UNREGISTRED_ACCOUNT);
		Assert.assertEquals(uPage.getStatus(driver), "Active");
		Assert.assertEquals(uPage.getTokenType(driver), "Bankcard");
		Assert.assertEquals(uPage.getPanNo(driver), "Bankcard: ****3832");
		driver.close();

	}

	
	// STA-698 - Search and view registred customer
	// Fail due to CCBO-8318
	@Test(priority = 10, enabled = true)
	public void searchRegistredCustomer() throws Exception {

		// Register a new customer using token
		// First generate transit account by using no records found
		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();

		// generate a new CC number that is not in the system
		CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
		String validCCNumber = ccGenerator.generate("4", 16);

		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		tPage.enterTokenVerificationNickName(driver, Global.NICKNAME);
		tPage.clickTokenVerificationRegisterCustomer(driver);

		// get the subsystem account id to use for verification later
		Assert.assertTrue(tPage.isLinkAccountConfirmationDisplayed(driver));
		String accountID = tPage.getLinkAccountConfirmationNumber(driver);
		Log.info("Link Account confirmation message is:  " + tPage.getLinkAccountConfirmationNumber(driver));
		accountID = accountID.substring(16, 28);
		Log.info("Account ID is:  " + accountID);
		coreTest.createCustomerTokenSearch(driver);
		tPage.clickHome(driver);

		// search for new registred customer using token
		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);

		// Assertions on the registered customer based on token search
		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains(Global.FNAME));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains(accountID));
		vPage.clickSecurityBox(driver);
		vPage.clickContinue(driver);

		TokenSearchCustomerDetailsPage dPage = new TokenSearchCustomerDetailsPage(driver);
		Assert.assertEquals(dPage.getFirstName(driver), Global.FNAME);
		Assert.assertEquals(dPage.getEmail(driver), userData.getEmail());
		Assert.assertTrue(dPage.getAccount(driver).contains(accountID));
		driver.close();

	}

	// Fail due to CCBO-8318
	@Test(priority = 11, enabled = true)
	public void searchRegistredCustomerVerifiedThreeInfo() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();

		// generate a new CC number that is not in the system
		CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
		String validCCNumber = ccGenerator.generate("4", 16);

		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		tPage.enterTokenVerificationNickName(driver, "joe");
		tPage.clickTokenVerificationRegisterCustomer(driver);

		// get the subsystem account id to use for verification later
		Assert.assertTrue(tPage.isLinkAccountConfirmationDisplayed(driver));
		String accountID = tPage.getLinkAccountConfirmationNumber(driver);
		Log.info("Link Account confirmation message is:  " + tPage.getLinkAccountConfirmationNumber(driver));
		accountID = accountID.substring(16, 28);
		Log.info("Account ID is:  " + accountID);
		coreTest.createCustomerTokenSearch(driver);
		tPage.clickHome(driver);

		// search for new registred customer using token
		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		Utils.waitTime(5000);
		tPage.clickSearchToken(driver);

		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains(Global.FNAME));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains(accountID));
		vPage.clickAddressBox(driver);
		vPage.clickDobBox(driver);
		vPage.clickNameBox(driver);
		vPage.clickContinue(driver);
		TokenSearchCustomerDetailsPage dPage = new TokenSearchCustomerDetailsPage(driver);
		Assert.assertEquals(dPage.getFirstName(driver), Global.FNAME);
		Assert.assertEquals(dPage.getEmail(driver), userData.getEmail());
		Assert.assertTrue(dPage.getAccount(driver).contains(accountID));
		driver.close();

	}

	// Fail due to CCBO-8318
	// Search and view subsystem page for registred customer
	@Test(priority = 12, enabled = true)
	public void viewRegistredCustomerSubsystemPage() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();

		// generate a new CC number that is not in the system
		CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
		String validCCNumber = ccGenerator.generate("4", 16);

		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		tPage.enterTokenVerificationNickName(driver, "joe");
		tPage.clickTokenVerificationRegisterCustomer(driver);

		// get the subsystem account id to use for verification later
		Assert.assertTrue(tPage.isLinkAccountConfirmationDisplayed(driver));
		String accountID = tPage.getLinkAccountConfirmationNumber(driver);
		Log.info("Link Account confirmation message is:  " + tPage.getLinkAccountConfirmationNumber(driver));
		accountID = accountID.substring(16, 28);
		Log.info("Account ID is:  " + accountID);
		coreTest.createCustomerTokenSearch(driver);
		tPage.clickHome(driver);

		// search for new registered customer using token
		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);

		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains(Global.FNAME));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains(accountID));
		vPage.clickSecurityBox(driver);
		vPage.clickContinue(driver);

		TokenSearchCustomerDetailsPage dPage = new TokenSearchCustomerDetailsPage(driver);
		Assert.assertTrue(dPage.getAccount(driver).contains(accountID));
	
		driver.close();

	}

	//fail
	// token verification test
	@Test(priority = 13, enabled = true)
	public void searchTokenVerification() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, "4887150737923832");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Utils.waitTime(5000);

		Assert.assertEquals(tPage.getTokenVerificationDate(driver), DATE);
		Assert.assertEquals(tPage.getTokenVerificationTransaction(driver), TRANSACTION);
		Assert.assertEquals(tPage.getTokenVerificationAmount(driver), AMOUNT);
		Assert.assertEquals(tPage.getTokenVerificationTransaction2(driver), TRANSACTION2);
		Assert.assertEquals(tPage.getTokenVerificationAmount2(driver), AMOUNT2);
		driver.close();

	}

	// CCD-839
	// Fail due to CCBO-8318
	@Test(priority = 15, enabled = true)
	public void tokenSearchWithSpace() throws Exception {

		// Register a new customer using token with space
		// First generate transit account by using no records found
		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();

		// generate a new CC number that is not in the system
		CreditCardNumberGenerator ccGenerator = new CreditCardNumberGenerator();
		String validCCNumber = ccGenerator.generate("4", 16);

		// Add space at the end of CC number
		validCCNumber = validCCNumber + "  " + "!";
		Log.info("CC number is: " + validCCNumber);

		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		tPage.enterTokenVerificationNickName(driver, "joe");
		tPage.clickTokenVerificationRegisterCustomer(driver);

		// get the subsystem account id to use for verification later
		Assert.assertTrue(tPage.isLinkAccountConfirmationDisplayed(driver));
		String accountID = tPage.getLinkAccountConfirmationNumber(driver);
		Log.info("Link Account confirmation message is:  " + tPage.getLinkAccountConfirmationNumber(driver));
		accountID = accountID.substring(16, 28);
		Log.info("Account ID is:  " + accountID);
		coreTest.createCustomerTokenSearch(driver);
		tPage.clickHome(driver);

		// search for new registred customer using token with space
		tPage.enterBankNumber(driver, validCCNumber);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);

		// Assertions on the registered customer based on token search
		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains(Global.FNAME));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains(accountID));
		driver.close();

	}

	// CCD-320
	@Test(priority = 16, enabled = true)
	public void searchTokenClickHome() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.enterBankNumber(driver, ccGen.generate("4", 16));
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), NO_RECORD_FOUND);
		tPage.clickHome(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");

		// repeat test for header search button
		tPage.enterBankNumber(driver, ccGen.generate("4", 16));
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), NO_RECORD_FOUND);
		tPage.clickSearchHeader(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	private TokenSearchPage getTokenSearchPage() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		TokenSearchPage tPage = new TokenSearchPage(driver);
		return tPage;
	}

	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}