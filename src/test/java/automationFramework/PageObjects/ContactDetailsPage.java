package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import automationFramework.Utilities.Utils;
import org.openqa.selenium.Keys;

public class ContactDetailsPage extends BasePage {

	// Element Locators
	private static final String FNAME = "customerContact_firstname_txt";
	private static final String LNAME = "customerContact_lastname_txt";
	private static final String CONTACT_TYPE = "customerContact_contactType_sel";
	private static final String ADDRESS = "customerContact_address_sel";
	private static final String NEWADDRESS = "customerContact_newAddress_btn";
	private static final String PHONE = "customerContact_phone1_txt";
	private static final String PHONETYPE = "customerContact_primryPhoneType_sel";
	private static final String PHONE2 = "customerContact_phone2_txt";
	private static final String PHONETYPE2 = "customerContact_secondaryPhoneType_sel";
	private static final String PHONE3 = "customerContact_phone3_txt";
	private static final String PHONETYPE3 = "customerContact_tertiaryPhoneType_sel";
	private static final String EMAIL = "customerContact_email_txt";
	private static final String USERNAME = "createCustomer_username_tst";
	private static final String PIN = "customerContact_pin_txt";
	private static final String SECURITYQ = "customerContact_sequrityQ_sel";
	private static final String SECURITYA = "customerContact_sequrityA_txt";
	private static final String DOB = "//*[@id='customerContact_dob_txt']";
	private static final String SUBMIT = "customerContact_submit_btn";
	private static final String CANCEL = "customerContact_cancel_btn";
	private static final String CLOSE = "//*[@id='contactsDialog']/div/div[1]/a/span";
	private static final String REQUIREDF_ERROR = "//*[@id='v-required-alert']";
	private static final String REQUIREDF_MUTUAL_ERROR = "#customerContact > div:nth-child(3) > div:nth-child(2) > div:nth-child(3)";
	private static final String COUNTRY = "customerContact_country_sel";
	private static final String ADDRESSLINE1 = "customerContact_address1_txt";
	private static final String ADDRESSLINE2 = "customerContact_address2_txt";
	private static final String CITY = "customerContact_city_txt";
	private static final String STATE = "customerContact_state_sel";
	private static final String POSTALCODE = "customerContact_postalCode_txt";
	
	//ID overwritten in app
	//private static final String INVALID_EMAIL_ERROR = "v-email-alert";
	private static final String INVALID_EMAIL_ERROR = "//*[@id='customerContact']/div[5]/div[1]/validation-msg/div";
	
	private static final String INVALID_FORMAT_ERROR = "v-format-alert";
	private static final String MIN_LENGTH_ERROR = "v-minlength-alert";
	private static final String NEW_DOB = "//*[@id='customerContact_dob_txt']/span/div/table/tbody/tr[3]/td[4]/a";
	private static final String DOB_YEAR = ".//*[@id='customerContact_dob_txt']/span/div/div/div/select[2]";
	private static final String RESET_PASSWORD = "customerContact_pwReset_btn";
	
	private static final String DATE_PICKER = "//div[@class='ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-shadow']";
	private static final String PHONE_ERROR = "//*[@id='errorMessage_div']/div/ul/li/span[2]";

	public ContactDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void selectContactType(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(CONTACT_TYPE)));
		mySelect.selectByIndex(1);
	}

	public void selectAddress(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(ADDRESS)));
		mySelect.selectByIndex(1);
	}

	public void enterFname(WebDriver driver, String fname) throws InterruptedException, AWTException {
		driver.findElement(By.id(FNAME)).clear();
		driver.findElement(By.id(FNAME)).sendKeys(fname);
	}

	public void deleteFname(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(FNAME)).click();
		driver.findElement(By.id(FNAME)).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id(FNAME)).sendKeys(Keys.ENTER);
	}

	public void enterLname(WebDriver driver, String lname) throws InterruptedException, AWTException {
		driver.findElement(By.id(LNAME)).clear();
		driver.findElement(By.id(LNAME)).sendKeys(lname);
	}

	public void deleteLname(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(LNAME)).clear();
		driver.findElement(By.id(FNAME)).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id(FNAME)).sendKeys(Keys.ENTER);

	}

	public void enterPhone(WebDriver driver, String phone) throws InterruptedException, AWTException {
		driver.findElement(By.id(PHONE)).clear();
		driver.findElement(By.id(PHONE)).sendKeys(phone);
	}

	public void selectPhoneType(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(PHONETYPE)));
		mySelect.selectByIndex(2);
	}

	public void enterPhone2(WebDriver driver, String phone2) throws InterruptedException, AWTException {
		driver.findElement(By.id(PHONE2)).clear();
		driver.findElement(By.id(PHONE2)).sendKeys(phone2);
	}

	public void selectPhoneType2(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(PHONETYPE2)));
		mySelect.selectByIndex(2);
	}

	public void enterPhone3(WebDriver driver, String phone3) throws InterruptedException, AWTException {
		driver.findElement(By.id(PHONE3)).clear();
		driver.findElement(By.id(PHONE3)).sendKeys(phone3);
	}

	public void selectPhoneType3(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(PHONETYPE3)));
		mySelect.selectByIndex(2);
	}

	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		driver.findElement(By.id(EMAIL)).clear();
		driver.findElement(By.id(EMAIL)).sendKeys(email);
	}

	public void enterUsername(WebDriver driver, String username) throws InterruptedException, AWTException {
		driver.findElement(By.id(USERNAME)).clear();
		driver.findElement(By.id(USERNAME)).sendKeys(username);
	}

	public void enterPin(WebDriver driver, String pin) throws InterruptedException, AWTException {
		driver.findElement(By.id(PIN)).click();
		driver.findElement(By.id(PIN)).clear();
		driver.findElement(By.id(PIN)).sendKeys(pin);
	}

	public void selectSequirtyQuestion(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(SECURITYQ)));
		mySelect.selectByIndex(2);
	}

	public void enterSecurityAnswer(WebDriver driver, String answer) throws InterruptedException, AWTException {
		driver.findElement(By.id(SECURITYA)).clear();
		driver.findElement(By.id(SECURITYA)).sendKeys(answer);
	}

	public void enterDob(WebDriver driver, String dob) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(DOB)).click();
		driver.findElement(By.xpath(DOB)).clear();
		driver.findElement(By.xpath(DOB)).sendKeys(dob);
	}

	public void updateDob(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(DOB)).click();
		//driver.findElement(By.xpath(DOB)).click();
		Select mySelect = new Select(driver.findElement(By.xpath(DOB_YEAR)));
		mySelect.selectByIndex(1);
		driver.findElement(By.xpath(NEW_DOB)).click();
		Utils.waitTime(3000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER);
	}

	public void enterAddress(WebDriver driver, String address) throws InterruptedException, AWTException {
		driver.findElement(By.id(ADDRESSLINE1)).clear();
		driver.findElement(By.id(ADDRESSLINE1)).sendKeys(address);
	}

	public void enterAddress2(WebDriver driver, String address2) throws InterruptedException, AWTException {
		driver.findElement(By.id(ADDRESSLINE2)).clear();
		driver.findElement(By.id(ADDRESSLINE2)).sendKeys(address2);
	}

	public void enterCity(WebDriver driver, String city) throws InterruptedException, AWTException {
		driver.findElement(By.id(CITY)).clear();
		driver.findElement(By.id(CITY)).sendKeys(city);
	}

	public void enterPostalCode(WebDriver driver, String postalCode) throws InterruptedException, AWTException {
		driver.findElement(By.id(POSTALCODE)).clear();
		driver.findElement(By.id(POSTALCODE)).sendKeys(postalCode);
	}

	public void clickNewAddress(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(NEWADDRESS)).click();
	}

	public void clickSubmit(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(SUBMIT)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(CANCEL)).click();
	}
	
	public void clickClose(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE)).click();
	}

	public void clickResetPassword(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(RESET_PASSWORD)).click();
	}

	public void clickDob(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(DOB)).click();
	}

	public String getRequiredFieldError(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(REQUIREDF_ERROR)));
		return driver.findElement(By.xpath(REQUIREDF_ERROR)).getText();
	}

	public String getRequiredFieldMutualError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.cssSelector(REQUIREDF_MUTUAL_ERROR)).getText();
	}

	public String getInvalidFormatError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(INVALID_FORMAT_ERROR)).getText();
	}

	public String getMinLengthError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(MIN_LENGTH_ERROR)).getText();
	}

	public String getInvalidEmailError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(INVALID_EMAIL_ERROR)).getText();
	}

	public String getDatePicker(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DATE_PICKER)).getAttribute("style");
	}

	public String getPhoneError(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PHONE_ERROR)));
		return driver.findElement(By.xpath(PHONE_ERROR)).getText();
	}

	public void selectCountry(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(COUNTRY)));
		mySelect.selectByIndex(1);
	}

	public void selectState(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.id(STATE)));
		mySelect.selectByIndex(6);
	}

	public boolean isSubmitEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(SUBMIT)).isEnabled();
	}

	public boolean isSubmitDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(SUBMIT)).isDisplayed();
	}
	
	public boolean isCalanderDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(DATE_PICKER)).isDisplayed();
	}


}