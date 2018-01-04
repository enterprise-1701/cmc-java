package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import automationFramework.Utilities.Utils;

public class NewCustomerPageTwo extends BasePage {

	// Element Locators
	private static final String CONTACTTYPE = "//*[@id='createCustomer_contactType_sel']";
	private static final String COUNTRY = "//*[@id='createCustomer_country_sel']";
	private static final String CITY = "//*[@id='createCustomer_city_txt']";
	private static final String ADDRESS = "//*[@id='createCustomer_address1_txt']";
	private static final String STATE = "//*[@id='createCustomer_state_sel']";
	private static final String POSTAL = "//*[@id='createCustomer_postalCode_txt']";
	private static final String PHONETYPE = "//*[@id='createCustomer_primryPhoneType_sel']";
	private static final String SUBMIT = ".//*[@id='createCustomer_submit_btn']";
	private static final String SECURITYQ = "//*[@id='createCustomer_sequrityQ_sel']";
	private static final String SECURITYA = "//*[@id='createCustomer_sequrityA_txt']";
	private static final String USERNAME = "//*[@id='createCustomer_username_tst']";
	private static final String PIN = "//*[@id='createCustomer_pin_txt']";
	private static final String DOB = "//*[contains(@id, 'dp')]";
	private static final String DOBLABEL = ".//*[@id='createCustomer']/div[2]/div[7]/label";
	private static final String CANCEL = ".//*[@id='createCustomer_cancel_btn']";
	private static final String EMAIL = "//*[@id='createCustomer_email_txt']";
	private static final String EMAIL_ERROR = "//*[@id='v-email-alert']";
	private static final String PIN_ERROR = ".//*[@id='v-minlength-alert']";
	private static final String USER_ERROR = "//*[@id='v-format-alert']";

	public NewCustomerPageTwo(WebDriver driver) {
		super(driver);
	}

	public void selectContactType(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(CONTACTTYPE)));
		Utils.waitTime(3000);
		mySelect.selectByValue(value);
	}

	public void selectCountry(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(COUNTRY)));
		mySelect.selectByIndex(7);
	}

	public void enterAddress(WebDriver driver, String address) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADDRESS)).sendKeys(address);
	}

	public void enterCity(WebDriver driver, String city) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CITY)).sendKeys(city);
	}

	public void selectState(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(STATE)));
		mySelect.selectByIndex(6);
	}

	public void enterPostalCode(WebDriver driver, String postalCode) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(POSTAL)).sendKeys(postalCode);
	}

	public void selectPhoneType(WebDriver driver, String phoneType) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(PHONETYPE)));
		mySelect.selectByValue(phoneType);
	}

	public void selectSecurityQ(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(SECURITYQ)));
		mySelect.selectByIndex(1);
	}

	public void enterSecuirtyA(WebDriver driver, String securityA) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SECURITYA)).sendKeys(securityA);
	}

	public void enterUserName(WebDriver driver, String userName) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(USERNAME)).sendKeys(userName);
	}

	public void enterPin(WebDriver driver, String pin) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PIN)).sendKeys(pin);
	}

	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL)).click();
		driver.findElement(By.xpath(EMAIL)).clear();
		driver.findElement(By.xpath(EMAIL)).sendKeys(email);
	}

	public void clickSubmit(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SUBMIT)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}

	public void enterDob(WebDriver driver, String dob) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(DOB)).click();
		driver.findElement(By.xpath(DOB)).sendKeys(dob);
		driver.findElement(By.xpath(DOBLABEL)).click();
	}

	public boolean isSubmitEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SUBMIT)).isEnabled();
	}

	public String getEmailError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAIL_ERROR)).getText();
	}

	public String getPinError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PIN_ERROR)).getText();
	}

	public String getUserNameError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(USER_ERROR)).getText();
	}
}