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
//Quality Center Test IDs: 
//#################################################################################
//Failure due to existing notification bug.

public class NotificationPrefTest {

	private static String phoneNumber;
	private static String email;
	private static CustomerData cData;
	private static Logger Log = Logger.getLogger(Logger.class.getName());
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

	/**
	 * changeNotificationPrefSubmit()
	 * 
	 * <p>existing bug - CCBO-7831
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, enabled = true)
	public void changeNotificationPrefSubmit() throws Exception {

		createNewCustomer(driver);
		Utils.waitTime(6000);
		SearchPage sPage = new SearchPage(driver);
		Utils.waitTime(6000);
		sPage.clickNotificationPreferences(driver);
		Utils.waitTime(6000);
		NotificationPreferencesPage notiPage = new NotificationPreferencesPage(driver);
		notiPage.clickSubmit(driver);
		Utils.waitTime(6000);
		Assert.assertTrue(notiPage.isCreateAddressLinkDisplayed(driver));
		driver.close();

	}

	
	@Test(priority = 2, enabled = true)
	public void changeNotificationPrefCancel() throws Exception {

		createNewCustomer(driver);
		Utils.waitTime(6000);
		SearchPage sPage = new SearchPage(driver);
		Utils.waitTime(6000);
		sPage.clickNotificationPreferences(driver);
		Utils.waitTime(6000);
		NotificationPreferencesPage notiPage = new NotificationPreferencesPage(driver);
		notiPage.clickCancel(driver);
		Utils.waitTime(6000);
		Assert.assertTrue(notiPage.isCreateAddressLinkDisplayed(driver));
		driver.close();

	}
	
	/**
	 * <p>
	 * Test method to make a change on the Notification Preferences
	 * update page and then save the change, and then verify the
	 * change has been saved by verifying that no "Customer Contact Update"
	 * message is delivered following a change to the Contact Details.
	 * 
	 * <pre>
	 * Quality Center test ID: 82527
	 * External ID: RSC-58
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, enabled = true)
	public void accountNotificationPreferences_updateCategory() throws Exception {

		createNewCustomer(driver);
		Utils.waitTime(6000);
		SearchPage sPage = new SearchPage(driver);
		Utils.waitTime(6000);
		
		// open Notification Preferences Update panel
		sPage.clickNotificationPreferences(driver);
		Utils.waitTime(6000);
		
		// Disable Customer Contact messaging
		NotificationPreferencesPage notiPage = new NotificationPreferencesPage(driver);
		
		// Click the Customer Contact Updates check-box twice to deselect it.
		notiPage.clickCategoryAccountsCheckbox( driver );
		Utils.waitTime(1000);
		notiPage.clickCategoryAccountsCheckbox( driver );
		Utils.waitTime(6000);
		
		// Save the change
		notiPage.clickSubmit(driver);
		Utils.waitTime(6000);
		Assert.assertTrue(notiPage.isCreateAddressLinkDisplayed(driver));
		Utils.waitTime(6000);
		
		// Update Customer Contact Info
		updateCustomerContact( "jones" );

		NotificationHistoryPage notHistory = new NotificationHistoryPage( driver );
		// Open Notification History list
		notHistory.clickIcon();
		
		// Verify no Customer Contact Update message is sent
		Assert.assertFalse("Customer Contact Update".equals( notHistory.getSubject()),
				"MOST RECENT MESSAGE IS 'Customer Contact Update' BUT IT SHOULD NOT BE");
		
		// close Notification History list
		notHistory.clickIcon();
		Utils.waitTime(6000);
		
		// Re-enable Customer Contact messaging
		sPage.clickNotificationPreferences(driver);
		Utils.waitTime(6000);
		
		// Click the Customer Contact Updates check-box twice to reselect it
		notiPage.clickCategoryAccountsCheckbox( driver );
		Utils.waitTime(1000);
		
		// Save the change
		notiPage.clickSubmit(driver);
		Utils.waitTime(6000);
		Assert.assertTrue(notiPage.isCreateAddressLinkDisplayed(driver));
		Utils.waitTime(6000);
		
		// Update Customer Contact Info again to cause a
		// 'Customer Contact Update' message to be sent
		updateCustomerContact( "smith" );
		
		// wait for list update
		//Utils.waitTime(240000);
		
		// open Notification History list
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -275)", "");
		notHistory.clickIcon();
		Utils.waitTime(6000);
		
		// Verify a Customer Contact Update was sent
		Assert.assertTrue("Customer Contact Update".equals( notHistory.getSubject()),
				"MOST RECENT MESSAGE IS NOT 'Customer Contact Update' BUT IT SHOULD BE");	
		
		// close Notification History list
		notHistory.clickIcon();		
		
		driver.close();
	}
	
	/**
     * This test case verifies that clicking the Cancel button in
     * the Notification Preferences page doesn't save the changes
     * made to the Notification Preferences page.
	 * 
	 * It does this by updating the Contact Details of the test
	 * account and then verifying that a "Customer Contact Update"
	 * message is received.
	 * 
	 * <pre>
	 * QC Test ID: 84683
	 * External ID: RSC-58
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, enabled = true)
	public void accountNotificationPreferences_noUpdateCategory() throws Exception {
		createNewCustomer(driver);
		Utils.waitTime(6000);
		SearchPage sPage = new SearchPage(driver);
		Utils.waitTime(6000);
		
		// open Notification Preferences Update panel
		sPage.clickNotificationPreferences(driver);
		Utils.waitTime(6000);
		
		// Disable Customer Contact messaging
		NotificationPreferencesPage notiPage = new NotificationPreferencesPage(driver);
		
		// Click the Customer Contact Updates check-box twice to deselect it.
		notiPage.clickCategoryAccountsCheckbox( driver );
		Utils.waitTime(1000);
		notiPage.clickCategoryAccountsCheckbox( driver );
		Utils.waitTime(6000);
		
		// Don't save the change
		notiPage.clickCancel(driver);
		Utils.waitTime(6000);
		Assert.assertTrue(notiPage.isCreateAddressLinkDisplayed(driver));
		Utils.waitTime(6000);
		
		// Update Customer Contact Info
		updateCustomerContact( "jones" );

		// wait for list update
		//Utils.waitTime(240000);		
		
		NotificationHistoryPage notHistory = new NotificationHistoryPage( driver );
		// Open Notification History list
		notHistory.clickIcon();
	
		// Verify a Customer Contact Update was sent
		Assert.assertTrue("Customer Contact Update".equals( notHistory.getSubject()),
				"MOST RECENT MESSAGE IS NOT 'Customer Contact Update' BUT IT SHOULD BE");		
		
		// close Notification History list
		notHistory.clickIcon();
		Utils.waitTime(6000);
		
		driver.close();
	}
	
	/**
	 * A helper method to update the Last Name of a set of Contact Details.
     *
	 * @param lastName The new last name.
	 * @throws Exception Thrown if something goes wrong.
	 */
	private void updateCustomerContact( final String lastName ) throws Exception {
		SearchPage sPage = new SearchPage(driver);
		// open the Contact Details panel
		sPage.clickContact(driver);
		Utils.waitTime(7000);		
		ContactDetailsPage contactPage = new ContactDetailsPage(driver);
		// enter the new Last Name
		contactPage.enterLname(driver, lastName );
		Utils.waitTime(7000);
		// save the change
		contactPage.clickSubmit(driver);
		Utils.waitTime(7000);
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
		Utils.waitTime(6000);
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterEmail(driver, email);
		sPage.clickSearch(driver);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -275)", "");
		Utils.waitTime(10000);
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