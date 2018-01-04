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
import automationFramework.RestApi.ApiCustomerPost;
import automationFramework.Utilities.*;
import org.openqa.selenium.JavascriptExecutor;

//#################################################################################
//Quality Center Test IDs: 77676, 77679, 77680, 77681
//#################################################################################

public class UpdateFundingTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());

	static WebDriver driver;
	static String browser;
	CoreTest coreTest = new CoreTest();
	private static String phoneNumber;
	private static String email;
	private static CustomerData cData;

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

	// Test case for updating non-primary card
	@Test(priority = 1, enabled = true)
	public void updateNonPrimaryFundingTest() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);

		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);

		nPage3.clickFundingSource(driver);
		cPage.enterName(driver, Global.CCNAME2);
		cPage.enterCC(driver, Global.CC2);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSetPrimary(driver);
		cPage.clickYesWarning(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);
		cPage.clickCardNumber2(driver);
		Utils.waitTime(3000);
		cPage.clearName(driver);
		cPage.enterName(driver, Global.FULLNAME);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
	

		// Assertions on second card
		Assert.assertEquals(cPage.getCCname2(driver), Global.FULLNAME);
		driver.close();
	}

	@Test(priority = 2, enabled = true)
	public void updateNonPrimaryFundingCancelTest() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);

		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);

		nPage3.clickFundingSource(driver);
		cPage.enterName(driver, Global.CCNAME2);
		cPage.enterCC(driver, Global.CC2);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSetPrimary(driver);
		cPage.clickYesWarning(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);
		cPage.clickCardNumber2(driver);
		Utils.waitTime(3000);
		cPage.clearName(driver);
		cPage.enterName(driver, Global.FULLNAME);
		cPage.clickCancel(driver);
		Utils.waitTime(5000);

		// Assertions on second card
		Assert.assertEquals(cPage.getCCname2(driver), Global.CCNAME2);
		driver.close();
	}

	// Test case for updating primary card
	@Test(priority = 3, enabled = true)
	public void updatePrimaryFundingTest() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);

		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(5000);
		cPage.clickCardNumber(driver);
		Utils.waitTime(5000);
		
		cPage.clearName(driver);
		cPage.enterName(driver, Global.FULLNAME);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		

		// Assertions on first card
		Assert.assertEquals(cPage.getCCname(driver), Global.FULLNAME);
		driver.close();
	}

	// Test case for updating primary card
	@Test(priority = 4, enabled = true)
	public void updatePrimaryFundingCancelTest() throws Exception {
		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);
		cPage.clickCardNumber(driver);
		Utils.waitTime(3000);
		cPage.clearName(driver);
		cPage.enterName(driver, Global.FULLNAME);
		cPage.clickCancel(driver);
		Utils.waitTime(3000);

		// Assertions on first card
		Assert.assertEquals(cPage.getCCname(driver), Global.CCNAME);
		driver.close();
	}
	
	
		@Test(priority = 5, enabled = true)
		public void updatePrimaryFundingCloseTest() throws Exception {
			createNewCustomer(driver);
			NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
			Utils.waitTime(5000);
			nPage3.clickFundingSource(driver);
			CreateFundingPage cPage = new CreateFundingPage(driver);
			cPage.selectPaymentType(driver, 2);
			cPage.enterName(driver, Global.CCNAME);
			cPage.enterCC(driver, Global.CC);
			cPage.selectMonth(driver);
			cPage.selectYear(driver);
			cPage.clickSubmit(driver);
			Utils.waitTime(5000);
			nPage3.clickFundingSourceExpand(driver);
			Utils.waitTime(3000);
			cPage.clickCardNumber(driver);
			Utils.waitTime(3000);
			cPage.clearName(driver);
			cPage.enterName(driver, Global.FULLNAME);
			cPage.clickClose(driver);
			Utils.waitTime(3000);

			// Assertions on first card
			Assert.assertEquals(cPage.getCCname(driver), Global.CCNAME);
			driver.close();
		}

	private SearchPage getSearchPage() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		SearchPage sPage = new SearchPage(driver);
		return sPage;
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

	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}