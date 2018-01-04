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
//Quality Center Test IDs: 71941, 72029, 72028, 71947, 71946 
//#################################################################################

public class CreateFundingTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String EXPIRATION = "01/2021";
	private static final String CARDTYPE = "Active";
	private static final String POSTAL = "92122";
	private static CustomerData cData;
	private static String phoneNumber;
	private static String email;
	private static boolean address = true;
	static WebDriver driver;
	static String browser;
	CoreTest coreTest = new CoreTest();

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

	@Test(priority = 1, enabled = true)
	public void createFundingSource() throws Exception {

		createNewCustomer(driver);
		// create funding source using UIf
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);
		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardExpiration(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);
		driver.close();

	}

	@Test(priority = 2, enabled = true)
	public void createFundingSourceNewBillingAddress() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickNewAddress(driver);
		cPage.selectCountry(driver);
		cPage.enterNewBillingAddress(driver, Global.NEWADDRESS);
		cPage.enterCity(driver, Global.NEWCITY);
		cPage.selectState(driver);
		cPage.enterPostalCode(driver, POSTAL);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);

		Assert.assertEquals(cPage.getCCname(driver), Global.CCNAME);
		Log.info("Actual results " + cPage.getCCname(driver) + " matches expected results " + Global.CCNAME);
		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardExpiration(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);
		driver.close();

	}

	@Test(priority = 3, enabled = true)
	public void createFundingSourceCancel() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickCancel(driver);
		Utils.waitTime(7000);
		Assert.assertFalse(cPage.isNameOnCardDisplayed(driver), "name on card should not be displayed!");
		driver.close();

	}


	@Test(priority = 4, enabled = true)
	public void createFundingSourceTypeNotSelected() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 0);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Assert.assertEquals(cPage.getFieldError(driver), Global.REQUIREDFIELD_ERROR);
		driver.close();
	}

	@Test(priority = 5, enabled = true)
	public void createCustomerInvalidEmail() throws Exception {

		coreTest.signIn(driver);
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.getLandingPage(Global.URL1);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		CreateCustomerPage nPage = new CreateCustomerPage(driver);
		nPage.clickNewCustomer(driver);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.enterLastname(driver, Global.LNAME);
		nPage.enterEmail(driver, Utils.randomUsernameString());
		Assert.assertFalse(nPage.isContinueEnabled(driver), "Continue button should not be enabled!");
		Assert.assertEquals(nPage.getEmailError(driver), Global.INVALID_EMAIL);
		nPage.enterEmail(driver, Utils.randomEmailString());
		nPage.enterPhone(driver, Utils.randomPhoneNumber());
		nPage.clickContinue(driver);
		NewCustomerPage nPaget = new NewCustomerPage(driver);
		nPaget.selectContactType(driver, Global.CONTACTTYPE);
		nPaget.selectCountry(driver);
		nPaget.enterAddress(driver, Global.ADDRESS);
		nPaget.enterCity(driver, Global.CITY);
		nPaget.enterPostalCode(driver, Global.POSTAL);
		nPaget.enterEmail(driver, Utils.randomUsernameString());
		Assert.assertFalse(nPaget.isSubmitEnabled(driver), "Submit button should not be enabled!");
		Assert.assertEquals(nPaget.getEmailError(driver), Global.INVALID_EMAIL);
		driver.close();

	}

	@Test(priority = 6, enabled = true)
	public void createCustomerFundingInvalidCC() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.INVALID_CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		Assert.assertEquals(cPage.getCCError(driver), "Card Number does not match with credit card type.");
		driver.close();
	}

	@Test(priority = 7, enabled = true)
	public void createCustomerFundingInvalidName() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		Assert.assertFalse(cPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
		driver.close();

	}

	@Test(priority = 8, enabled = true)
	public void createFundingSourceInvalidPanTooLong() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, "41111112222222222222");
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		Assert.assertEquals(cPage.getCCPanError(driver), Global.PAN_ERROR);
		driver.close();

	}

	
	@Test(priority = 9, enabled = true)
	public void createFundingSourceInvalidCCType() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		Utils.waitTime(3000);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCCwithSpace(driver, "411111111111111");
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		Assert.assertEquals(cPage.getCCPanError(driver), Global.PAN_ERROR2);
		driver.close();

	}

	@Test(priority = 10, enabled = true)
	public void createFundingSourceInvalidExpirationDate() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectInvalidMonth(driver);
		cPage.selectInvalidYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		Assert.assertEquals(cPage.getCCPanError(driver), Global.PAN_ERROR3);
		driver.close();

	}

	// Test case for second funding source with a different cc
	@Test(priority = 11, enabled = true)
	public void createFundingSourceTwoCards() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(7000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);

		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardExpiration(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);

		nPage3.clickFundingSource(driver);
		cPage.enterName(driver, Global.CCNAME2);
		cPage.enterCC(driver, Global.CC2);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);

		// Assertions on second card
		Assert.assertEquals(cPage.getCardType2(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType2(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber2(driver), Global.CCMASKED2);
		Log.info("Actual results " + cPage.getCardNumber2(driver) + " matches expected results " + Global.CCMASKED2);
		Assert.assertEquals(cPage.getCardExpiration2(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration2(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus2(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus2(driver) + " matches expected results " + CARDTYPE);
		driver.close();

	}

	// Test case for changing primary card
	@Test(priority = 12, enabled = true)
	public void createFundingSourceChangePrimaryCard() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
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
		Utils.waitTime(7000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);
		cPage.clickCardNumber2(driver);

		// Assertions on second card
		Assert.assertTrue(cPage.isSetPrimaryEnabled(driver), "Set primary should not be enabled");
		driver.close();
	}

	// Test case for attempting to change primary card and canceling
	@Test(priority = 13, enabled = true)
	public void createFundingSourceChangePrimaryCardCancel() throws Exception {

		createNewCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(3000);

		nPage3.clickFundingSource(driver);
		cPage.enterName(driver, Global.CCNAME2);
		cPage.enterCC(driver, Global.CC2);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSetPrimary(driver);
		cPage.clickNoWarning(driver);
		cPage.clickCancel(driver);
		Utils.waitTime(7000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);

		// Assertions on set as primary check box
		Assert.assertTrue(cPage.isSetPrimaryEnabled(driver), "Set primary should not be enabled");
	

		driver.close();
	}

	// fail due to CCBO-7819
	// #################################################################################
	// Fix verification for CCD-853
	// #################################################################################
	@Test(priority = 14, enabled = true)
	public void createFundingNoAddress() throws Exception {

		address = false;
		createNewCustomer(driver, address);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(3000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickNewAddress(driver);
		cPage.selectCountry(driver);
		cPage.enterNewBillingAddress(driver, Global.NEWADDRESS);
		cPage.enterCity(driver, Global.NEWCITY);
		cPage.selectState(driver);
		cPage.enterPostalCode(driver, POSTAL);
		cPage.clickSubmit(driver);
		Utils.waitTime(7000);
		nPage3.clickFundingSourceExpand(driver);
		Utils.waitTime(3000);

		Assert.assertEquals(cPage.getCCname(driver), Global.CCNAME);
		Log.info("Actual results " + cPage.getCCname(driver) + " matches expected results " + Global.CCNAME);
		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);
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

	private WebDriver createNewCustomer(WebDriver driver, boolean address) throws Exception {

		// Create customer test data via api rest call
		if (address) {
			cData = ApiCustomerPost.apiPostSuccess();
		} else {
			cData = ApiCustomerPost.apiPostSuccessNoAddress();
			Log.info("API No Address is being called!");
		}
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