package automationFramework.PageObjects;

import java.awt.AWTException;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchPage2 extends BasePage {

	// Element Locators
	private static final String CUSTOMERTYPE = ".//*[@id='searchCustomer_customerType_sel']";
	private static final String FIRSTNAME = ".//*[@id='searchCustomer_firstname_txt']";
	private static final String LASTNAME = ".//*[@id='searchCustomer_last_txt']";
	private static final String EMAIL = ".//*[@id='searchCustomer_email_txt']";
	private static final String PHONE = ".//*[@id='searchCustomer_phone_txt']";
	private static final String SEARCH = ".//*[@id='searchCustomer_searchToken_btn']";
	private static final String NORECORD = "//*[@id='customerContacts_list_tbl']/div/div[1]/header";
	private static final String FIRSTRECORD = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]/span";
	private static final String SECONDRECORD = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[2]/td[1]/span";
	private static final String LEGEND = "//*[@id='Create Customer_legend']";
	private static final String FNAMETABLE = ".//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]/span";
	private static final String FNAMETABLE_ROW2 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[2]/td[1]/span";
	private static final String FNAMETABLE_ROW3 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[3]/td[1]/span";
	private static final String LNAMETABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[2]/span";
	private static final String LNAMETABLE_ROW2 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[2]/td[2]/span";
	private static final String LNAMETABLE_ROW3 = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[3]/td[2]/span";
	private static final String EMAILTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[3]/span";
	private static final String PHONETABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[4]/span/span";
	private static final String ADDRESSTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[5]/span/span";
	private static final String DOBTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[6]/span/span";
	private static final String CUSTOMERTABLE = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[7]/span";
	private static final String CONTACTTABLE = ".//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr[1]/td[8]/span";
	private static final String CONTACTTABLE_TWO = ".//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[7]/span";
	private static final String SECURITYQBOX = "//*[@id='verificationForm_securityQA_chk']";
	private static final String CONTINUE = "//*[@id='verificationForm_verified_btn']";
	private static final String NOTVERIFIED = "//*[@id='verificationForm_notVerified_btn']";
	private static final String EMAIL_ERROR = "//*[@id='v-email-alert']";
	private static final String NEW_ADDRESS = "//*[@id='create_new_address_link']";
	private static final String ADDRESS_LIST = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr/td[1]";
	private static final String NEW_ADDRESS_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[2]/td[1]/span";
	private static final String NEW_CITY_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[2]/td[3]/span";
	private static final String NEW_ZIP_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[2]/td[5]/span";
	private static final String UPDATED_ADDRESS_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]";
	private static final String UPDATED_CITY_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[1]/td[3]/span";
	private static final String UPDATED_ZIP_TABLE = "//*[@id='addresses_list_tbl']/div/div[1]/table/tbody/tr[1]/td[5]/span";
	private static final String POSTAL_CODE_ERROR = "//*[@id='errorMessage_div']/div/ul/li/span[2]";
	private static final String CLOSE_ERROR = "//*[@id='errorMessage_div']/div/a";
	private static final String CONTACT = "//*[@id='customerContacts_list_tbl']/div/div[1]/table/tbody/tr/td[1]";
	public static final String PHONE_ERROR = "//*[@id='errorMessage_div']/div/ul/li/span[2]";
	public static final String UNEXPECTED_ERROR = "//*[@id='errorMessage_div']/div/ul/li/span[2]";
	public static final String SEARCH_LEGEND = ".//*[@id='search_legend']/span";
	public static final String NAME_CHECKBOX = ".//*[@id='verificationForm_name_chk']";
	public static final String ADDRESS_CHECKBOX = ".//*[@id='verificationForm_address_chk']";
	public static final String DOB_CHECKBOX = ".//*[@id='verificationForm_dob_chk']";
	public static final String BALANCE_HISTORY = "/html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/balance-history/p-panel/div/div[1]/a/span";

	public SearchPage2(WebDriver driver) {
		super(driver);
	}

	public void clickCustomerType(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(CUSTOMERTYPE)));
		mySelect.selectByValue(value);
	}

	public void enterFirstname(WebDriver driver, String firstname) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRSTNAME)));
		driver.findElement(By.xpath(FIRSTNAME)).click();
		driver.findElement(By.xpath(FIRSTNAME)).sendKeys(firstname);
	}

	public void enterLastname(WebDriver driver, String lastname) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LASTNAME)));
		driver.findElement(By.xpath(LASTNAME)).click();
		driver.findElement(By.xpath(LASTNAME)).sendKeys(lastname);
	}

	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL)));
		driver.findElement(By.xpath(EMAIL)).click();
		driver.findElement(By.xpath(EMAIL)).sendKeys(email);

	}

	public void enterPhone(WebDriver driver, String phone) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PHONE)));
		driver.findElement(By.xpath(EMAIL)).click();
		driver.findElement(By.xpath(PHONE)).sendKeys(phone);
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
		driver.findElement(By.xpath(SECURITYQBOX)).click();
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
		driver.findElement(By.xpath(NOTVERIFIED)).click();
	}

	public void clickSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH)));
		driver.findElement(By.xpath(SEARCH)).click();
	}

	public void clickContiune(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}

	public void clickNewAddress(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NEW_ADDRESS)));
		driver.findElement(By.xpath(NEW_ADDRESS)).click();
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

	public String getNoRecordFound(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NORECORD)).getText();
	}

	public String getFirstName(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FNAMETABLE)));
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
		return driver.findElement(By.xpath(LEGEND)).isDisplayed();
	}

	public boolean isSearchEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SEARCH)).isEnabled();
	}

	public boolean isContinueEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CONTINUE)).isEnabled();
	}

}