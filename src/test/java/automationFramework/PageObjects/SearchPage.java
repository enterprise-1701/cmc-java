package automationFramework.PageObjects;

import java.awt.AWTException;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends BasePage {

	// Element Locators
	private static final String CUSTOMERTYPE = "searchCustomer_customerType_sel";
	private static final String FIRSTNAME = "searchCustomer_firstname_txt";
	private static final String LASTNAME = "searchCustomer_last_txt";
	private static final String EMAIL = "searchCustomer_email_txt";
	private static final String PHONE = "searchCustomer_phone_txt";
	private static final String SEARCH = "searchCustomer_searchToken_btn";
	private static final String NORECORD = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td";
	private static final String FIRSTRECORD = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]/span";
	private static final String SECONDRECORD = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[2]/td[1]/span";
	private static final String LEGEND = "Create Customer_legend";
	private static final String FNAMETABLE = ".//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]/span";
	private static final String FNAMETABLE_ROW2 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[2]/td[1]/span";
	private static final String FNAMETABLE_ROW3 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[3]/td[1]/span";
	private static final String LNAMETABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[2]/span";
	private static final String LNAMETABLE_ROW2 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[2]/td[2]/span";
	private static final String LNAMETABLE_ROW3 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[3]/td[2]/span";
	private static final String EMAILTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[3]/span";
	private static final String PHONETABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[4]/span/span";
	private static final String ADDRESSTABLE = "//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[5]/span/span";
	private static final String DOBTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[6]/span/span";
	private static final String CUSTOMERTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[7]/span";
	private static final String CONTACTTABLE = ".//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[8]/span";
	private static final String CONTACTTABLE_TWO = ".//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[8]/span";
	private static final String SECURITYQBOX = "verificationForm_securityQA_chk";
	private static final String CONTINUE = "verificationForm_verified_btn";
	private static final String NOTVERIFIED = "verificationForm_notVerified_btn";
	private static final String EMAIL_ERROR = "//*[@id='searchCustomerForm']/div[4]/validation-msg/div";
	private static final String NEW_ADDRESS = "create_new_address_link";
	private static final String ADDRESS_LIST = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr/td[1]";
	private static final String NEW_ADDRESS_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[2]/td[1]/span";
	private static final String NEW_CITY_TABLE = "//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[2]/td[4]/span";
	private static final String NEW_ZIP_TABLE = "//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[2]/td[6]/span";
	private static final String UPDATED_ADDRESS_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]";
	private static final String UPDATED_CITY_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[1]/td[3]/span";
	private static final String UPDATED_ZIP_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[1]/td[5]/span";
	private static final String POSTAL_CODE_ERROR = "//*[@id='errorMessage_div']/div/ul/li[1]/span[2]";
	private static final String CLOSE_ERROR = "//*[@id='errorMessage_div']/div/a";
	private static final String CONTACT = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[1]";
	public static final String PHONE_ERROR = "//*[@id='errorMessage_div']/div/ul/li/span[2]";
	public static final String UNEXPECTED_ERROR = "//*[@id='errorMessage_div']/div/ul/li/span[2]";
	public static final String SEARCH_LEGEND = ".//*[@id='search_legend']/span";
	public static final String NAME_CHECKBOX = "//*[@id='verificationForm_name_chk']";
	public static final String ADDRESS_CHECKBOX = "//*[@id='verificationForm_address_chk']";
	public static final String DOB_CHECKBOX = "//*[@id='verificationForm_dob_chk']";
	public static final String BALANCE_HISTORY = "/html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/balance-history/p-panel/div/div[1]/a/span";
	public static final String POSTAL_CODE = "searchCustomer_postalCode_txt";

	public static final String NEW_SEARCH = "searchCustomer_newSearch_btn";
	public static final String DELETE_ADDRESS = "addresses_delete_btn";
	// public static final String DELETE_ADDRESS =
	// "//*[@id=\"oneaccount_customer_panel\"]/div/div[2]/div/div[1]/div[1]/customer-contacts/p-dialog/div/div[1]/a/span";
	public static final String NOTIFICATION_PREFERENCES = "viewCustomer_notificationPref_lnk";
	public static final String CITY_TABLE = "//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[1]/td[4]/span";
	public static final String ZIP_TABLE = "//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[1]/td[6]/span";
	// Need to update web element
	// public static final String EXPAND_ADDRESS =
	// "/html/body/div[1]/div/div/customer/workspace/fieldset/div[3]/div/div[2]/customer-details/div[3]/div[1]/customer-addresses/p-panel/div/div[1]/a/span";
	public static final String EXPAND_ADDRESS = "//*[@id=\"addresses_list_panel\"]/div/div[1]/a/span";

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	public void clickCustomerType(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(CUSTOMERTYPE)));
		mySelect.selectByValue(value);
	}

	public void clickCustomerInvalidType(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(CUSTOMERTYPE)));
		mySelect.selectByIndex(0);
	}

	public void enterFirstname(WebDriver driver, String firstname) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FIRSTNAME)));
		driver.findElement(By.id(FIRSTNAME)).click();
		driver.findElement(By.id(FIRSTNAME)).sendKeys(firstname);
	}

	public void enterLastname(WebDriver driver, String lastname) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LASTNAME)));
		driver.findElement(By.id(LASTNAME)).click();
		driver.findElement(By.id(LASTNAME)).sendKeys(lastname);
	}

	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL)));
		driver.findElement(By.id(EMAIL)).click();
		driver.findElement(By.id(EMAIL)).sendKeys(email);

	}

	public void enterPhone(WebDriver driver, String phone) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PHONE)));
		driver.findElement(By.id(PHONE)).click();
		driver.findElement(By.id(PHONE)).sendKeys(phone);
	}

	public void enterPostalCode(WebDriver driver, String postalCode) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(POSTAL_CODE)));
		driver.findElement(By.id(POSTAL_CODE)).click();
		driver.findElement(By.id(POSTAL_CODE)).sendKeys(postalCode);
	}

	public void clickRecord(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRSTRECORD)));
		driver.findElement(By.xpath(FIRSTRECORD)).click();
	}

	public void clickSecondRecord(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SECONDRECORD)));
		driver.findElement(By.xpath(SECONDRECORD)).click();
	}

	public void clickSecurityBox(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(SECURITYQBOX)).click();
	}

	public void clickNameBox(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NAME_CHECKBOX)).click();
	}

	public void clickAddressBox(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADDRESS_CHECKBOX)).click();
	}

	public void clickDobBox(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(DOB_CHECKBOX)).click();
	}

	public void clickNotVerified(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(NOTVERIFIED)).click();
	}

	public void clickSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(By.id(SEARCH)));
		driver.findElement(By.id(SEARCH)).click();
	}

	public void clickNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(By.id(NEW_SEARCH)));
		driver.findElement(By.id(NEW_SEARCH)).click();
	}

	public void clickContiune(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(CONTINUE)).click();
	}

	public void clickNewAddress(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(NEW_ADDRESS)));
		driver.findElement(By.id(NEW_ADDRESS)).click();
	}

	public void clickInsideAddress(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADDRESS_LIST)));
		driver.findElement(By.xpath(ADDRESS_LIST)).click();
	}

	public void clickCloseError(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE_ERROR)).click();
	}

	public void clickContact(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTACT)).click();
	}

	public void clickBalanceHistory(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(BALANCE_HISTORY)).click();
	}

	public void clickDeleteAddress(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(DELETE_ADDRESS)).click();
	}

	public void clickExpandAddress(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EXPAND_ADDRESS)).click();
	}

	public void clickNotificationPreferences(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(NOTIFICATION_PREFERENCES)).click();
	}

	public boolean isDeleteAddressDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(DELETE_ADDRESS)).isDisplayed();
	}

	public String getNoRecordFound(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NORECORD)).getText();
	}

	public String getFirstName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FNAMETABLE)).getText();
	}

	public String getFirstNameSecondRow(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FNAMETABLE_ROW2)).getText();
	}

	public boolean isFirstNameSecondRowDisplayed(WebDriver driver)
			throws InterruptedException, AWTException, NoSuchElementException {
		try {
			driver.findElement(By.xpath(FNAMETABLE_ROW2)).getText();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFirstNameThirdRowDisplayed(WebDriver driver)
			throws InterruptedException, AWTException, NoSuchElementException {
		try {
			driver.findElement(By.xpath(FNAMETABLE_ROW3)).getText();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getLastName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LNAMETABLE)).getText();
	}

	public String getLastNameSecondRow(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LNAMETABLE_ROW2)).getText();
	}

	public boolean isLastNameSecondRowDisplayed(WebDriver driver)
			throws InterruptedException, AWTException, NoSuchElementException {
		try {
			driver.findElement(By.xpath(LNAMETABLE_ROW2)).getText();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLastNameThirdRowDisplayed(WebDriver driver)
			throws InterruptedException, AWTException, NoSuchElementException {
		try {
			driver.findElement(By.xpath(LNAMETABLE_ROW3)).getText();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getEmail(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAILTABLE)).getText();
	}

	public String getPhone(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PHONETABLE)).getText();
	}

	public String getAddress(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ADDRESSTABLE)).getText();
	}

	public String getDob(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DOBTABLE)).getText();
	}

	public String getCustomerType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CUSTOMERTABLE)).getText();
	}

	public String getContactType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CONTACTTABLE)).getText();
	}

	public String getContactTypeTableTwo(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CONTACTTABLE_TWO)).getText();
	}

	public String getEmailError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAIL_ERROR)).getText();
	}

	public String getPostalCodeError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(POSTAL_CODE_ERROR)).getText();
	}

	public String getPhoneError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PHONE_ERROR)).getText();
	}

	public String getUnexpectedError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UNEXPECTED_ERROR)).getText();
	}

	public String getCity(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CITY_TABLE)).getText();
	}

	public String getZip(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ZIP_TABLE)).getText();
	}

	public String getPostalCode(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(POSTAL_CODE)).getText();
	}

	public String getNewAddress(WebDriver driver)
			throws InterruptedException, AWTException, org.openqa.selenium.NoSuchElementException {
		try {
			return driver.findElement(By.xpath(NEW_ADDRESS_TABLE)).getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return null;
		}
	}

	public String getNewCity(WebDriver driver) throws InterruptedException, AWTException, NoSuchElementException,
			org.openqa.selenium.NoSuchElementException {
		try {
			return driver.findElement(By.xpath(NEW_CITY_TABLE)).getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return null;
		}
	}

	public String getNewPostalCode(WebDriver driver) throws InterruptedException, AWTException, NoSuchElementException,
			org.openqa.selenium.NoSuchElementException {
		try {
			return driver.findElement(By.xpath(NEW_ZIP_TABLE)).getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return null;
		}
	}

	public String getUpdatedAddress(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UPDATED_ADDRESS_TABLE)));
		return driver.findElement(By.xpath(UPDATED_ADDRESS_TABLE)).getText();
	}

	public String getUpdatedCity(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UPDATED_CITY_TABLE)).getText();
	}

	public String getUpdatedPostalCode(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UPDATED_ZIP_TABLE)).getText();
	}

	public String getSearchLegend(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SEARCH_LEGEND)).getText();
	}

	public boolean isRecordPresent(WebDriver driver) throws InterruptedException, AWTException {
		try {
			driver.findElement(By.xpath(FIRSTRECORD)).isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isLegendPresent(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(LEGEND)).isDisplayed();
	}

	public boolean isSearchEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(SEARCH)).isEnabled();
	}

	public boolean isContinueEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(CONTINUE)).isEnabled();
	}

}