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
//Quality Center Test IDs: 74589, 74653, 75705, 74590, 74594, 74593, 75706
//#################################################################################

public class TokenSearchAPITest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String CC1 = "4887150737923832";
	private static final String LINK_ACCOUNT_TRANSACTION = "1000, 1000";
	private static final String LINK_ACCOUNT_AMOUNT = "$7.60";
	private static String phoneNumber;
	private static String email;
	private static CustomerData cData;
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

	// token verification based on linking account
	@Test(priority = 1, enabled = true)
	public void tokenVerificationLinkAccount() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		nPage3.clickLinkAccount(driver);
		LinkAccountPage lPage = new LinkAccountPage(driver);
		lPage.enterBankAccount(driver, CC1);
		lPage.selectExpMonth(driver);
		lPage.selectExpYear(driver, 2);
		lPage.clickSearchToken(driver);
		Utils.waitTime(10000);
		Assert.assertEquals(lPage.getTokenTransaction(driver), LINK_ACCOUNT_TRANSACTION);
		Assert.assertEquals(lPage.getTokenAmount(driver), LINK_ACCOUNT_AMOUNT);
		driver.close();

	}

	// token verification based on linking account - new search
	@Test(priority = 2, enabled = true)
	public void tokenVerificationLinkAccountNewSearch() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		nPage3.clickLinkAccount(driver);
		LinkAccountPage lPage = new LinkAccountPage(driver);
		lPage.enterBankAccount(driver, CC1);
		lPage.selectExpMonth(driver);
		lPage.selectExpYear(driver, 2);
		lPage.clickSearchToken(driver);
		lPage.clickNewSearch(driver);
		Assert.assertFalse(lPage.isSearchTokenEnabled(driver), "Search Token should not be enabled");
		Assert.assertEquals(lPage.getBankNumber(driver), "");
		driver.close();

	}

	private WebDriver createNewCustomer(WebDriver driver) throws Exception {

		// Create customer test data via api rest call
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
		return driver;
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