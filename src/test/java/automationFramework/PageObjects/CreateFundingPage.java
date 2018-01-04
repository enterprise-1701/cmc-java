package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateFundingPage extends BasePage {

	// Element Locators
	private static final String PAYMENTTYPE = "//*[@id='fundingSourceForm_paymentType_sel']";
	private static final String NAMEONCARD = ".//*[@id='fundingSourceForm_nameOnCard_txt']";
	private static final String CARDNUMBER = ".//*[@id='fundingSourceForm_cardNumber_txt']";
	private static final String MONTH_EXP = "//*[@id='fundingSourceForm_cardExpiryMM_sel']";
	private static final String YEAR_EXP = "//*[@id='fundingSourceForm_cardExpiryYYYY_sel']";
	private static final String SET_PRIMARY = "#fundingSourceForm_setAsPrimary_chk";
	private static final String SET_PRIMARY2 = ".fa.fa-check-square";
	private static final String YES_WARNING = "#fundingSourceForm_dialog_yes_btn";
	private static final String NO_WARNING = "#fundingSourceForm_dialog_no_btn";
	private static final String ISPRIMARY = "//*[@id='fundingSourceForm_isPrimary_chk']";
	private static final String ISRECURRING = "//*[@id='fundingSourceForm_isRecurring_chk']";
	private static final String SUBMIT = "#fundingSource > footer:nth-child(5) > div:nth-child(1) > button:nth-child(2)";
	private static final String CANCEL = "#fundingSource > footer:nth-child(5) > div:nth-child(1) > button:nth-child(1)";
	private static final String T_NAMEONCARD = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr/td[1]/span";
	private static final String T_NAMEONCARD2 = "//*[@id='fundingSources_list_tbl']/div/div/table/tbody/tr[2]/td[1]/span";
	private static final String T_CARDTYPE = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr/td[2]";							
	private static final String T_CARDTYPE2 = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr[2]/td[2]/span";
	private static final String T_CARDNUMBER = ".//*[@id='fundingSources_list_tbl']/div/div/table/tbody/tr/td[3]/span/span";
	private static final String T_CARDNUMBER2 = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr[2]/td[3]/span/span";
	private static final String T_CARDEXPIRATION = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr/td[4]/span/span";
	private static final String T_CARDEXPIRATION2 = ".//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr[2]/td[4]/span/span";
	private static final String T_PRIMARY2 = ".//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr[2]/td[6]/span/span/i";
	private static final String T_CARDSTATUS = "//*[@id='fundingSources_list_tbl']/div/div/table/tbody/tr/td[7]/span/span";
	private static final String T_CARDSTATUS2 = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr[2]/td[7]/span/span";
	private static final String T_BILLING_ADDRESS = "//*[@id='fundingSources_list_tbl']/div/div[1]/table/tbody/tr/td[8]/span/span";
	private static final String COUNTRY = "//*[@id='fundingSourceForm_countryId_sel']";
	private static final String NEW_BILLINGADDRESS = "//*[@id='fundingSourceForm_address1_txt']";
	private static final String CITY = "//*[@id='fundingSourceForm_city_txt']";
	private static final String STATE = "//*[@id='fundingSourceForm_state_sel']";
	private static final String POSTAL = "//*[@id='fundingSourceForm_postalCode_txt']";
	private static final String T_NEWBILLINGADDRESS = ".//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[2]/td[1]/span";
	private static final String T_NEWCITY = ".//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[2]/td[3]/span";
	private static final String T_NEWSTATE = ".//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[2]/td[4]/span";
	private static final String T_NEWPOSTAL = ".//*[@id='addresses_list_tbl']/div/div/table/tbody/tr[2]/td[5]/span";
	private static final String CREATE_CONTACT = ".//*[@id='viewCustomer_createContact_lnk']";
	private static final String CC_ERROR = "funding-sources.customer-primeng > p-dialog:nth-child(2) > div:nth-child(1) > div:nth-child(2) > funding-source-form:nth-child(1) > error-message:nth-child(1) > p-messages:nth-child(1) > div:nth-child(1) > ul:nth-child(3) > li:nth-child(1) > span:nth-child(2)";
	private static final String REQUIRED_FIELD_ERROR = "//*[@id='fundingSource']/div[1]/div[1]/validation-msg/div";
	//private static final String CLOSE = "#oneaccount_customer_panel > div > div.ui-panel-content-wrapper.ng-trigger.ng-trigger-panelContent > div > div:nth-child(4) > div:nth-child(1) > funding-sources > p-dialog.ng-tns-c3-38 > div > div.ui-dialog-titlebar.ui-widget-header.ui-helper-clearfix.ui-corner-top.ng-tns-c3-38 > a > span";
	private static final String CLOSE = "//*[@id='oneaccount_customer_panel']/div/div[2]/div/div[4]/div[1]/funding-sources/p-dialog[1]/div/div[1]/a/span";
	private static final String BILLING_ADDRESS = "//*[@id='fundingSourceForm_billingAddressId_sel']";
	private static final String NEW_ADDRESS = "#fundingSource > div:nth-child(4) > div:nth-child(2) > button:nth-child(1)";
	private static final String CC_PAN_ERROR = "#errorMessage_div > div > ul > li > span.ui-messages-detail";


	public CreateFundingPage(WebDriver driver) {
		super(driver);
	}

	public void selectPaymentType(WebDriver driver, int index) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(PAYMENTTYPE)));
		mySelect.selectByIndex(index);
	}

	public void clearName(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NAMEONCARD)).click();
		driver.findElement(By.xpath(NAMEONCARD)).clear();
	}

	public void enterName(WebDriver driver, String name) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NAMEONCARD)));
		driver.findElement(By.xpath(NAMEONCARD)).click();
		driver.findElement(By.xpath(NAMEONCARD)).sendKeys(name);
	}

	public void enterCC(WebDriver driver, String cc) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CARDNUMBER)).click();
		driver.findElement(By.xpath(CARDNUMBER)).sendKeys(cc);
	}

	public void enterCCwithSpace(WebDriver driver, String cc) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CARDNUMBER)).click();
		driver.findElement(By.xpath(CARDNUMBER)).sendKeys(Keys.SPACE);
		driver.findElement(By.xpath(CARDNUMBER)).sendKeys(cc);
	}

	public void enterNewBillingAddress(WebDriver driver, String newAddress) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NEW_BILLINGADDRESS)).click();
		driver.findElement(By.xpath(NEW_BILLINGADDRESS)).sendKeys(newAddress);
	}

	public void enterCity(WebDriver driver, String city) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CITY)).sendKeys(city);
	}

	public void enterPostalCode(WebDriver driver, String postal) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(POSTAL)).click();
		driver.findElement(By.xpath(POSTAL)).sendKeys(postal);
	}

	public void selectInvalidMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(MONTH_EXP)));
		mySelect.selectByIndex(1);
	}

	public void selectInvalidYear(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(YEAR_EXP)));
		mySelect.selectByIndex(1);
	}

	public void selectMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(MONTH_EXP)));
		mySelect.selectByIndex(1);
	}

	public void selectYear(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(YEAR_EXP)));
		mySelect.selectByIndex(5);
	}

	public void selectCountry(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(COUNTRY)));
		Select mySelect = new Select(driver.findElement(By.xpath(COUNTRY)));
		mySelect.selectByIndex(1);
	}

	public void selectState(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(STATE)));
		mySelect.selectByIndex(6);
	}

	public void clickIsPrimary(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ISPRIMARY)).click();
	}

	public void clickIsRecurring(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ISRECURRING)).click();
	}

	public void clickBillingAddress(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(BILLING_ADDRESS)).click();
	}

	public void clickNewAddress(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.cssSelector(NEW_ADDRESS)).click();
	}

	public void clickSubmit(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SUBMIT)));
		driver.findElement(By.cssSelector(SUBMIT)).click();
	}

	public void clickSetPrimary(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SET_PRIMARY)));
		driver.findElement(By.cssSelector(SET_PRIMARY)).click();
	}

	public boolean isSetPrimaryEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.cssSelector(SET_PRIMARY2)).isEnabled();
	}

	public void clickYesWarning(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.cssSelector(YES_WARNING)).click();
	}
	
	public void clickNoWarning(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.cssSelector(NO_WARNING)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.cssSelector(CANCEL)).click();
	}
	
	public void clickClose(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE)).click();
	}

	public String getCCname(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(T_NAMEONCARD)));
		return driver.findElement(By.xpath(T_NAMEONCARD)).getText();
	}

	public String getCCname2(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(T_NAMEONCARD2)));
		return driver.findElement(By.xpath(T_NAMEONCARD2)).getText();
	}

	public String getCardType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDTYPE)).getText();
	}

	public String getCardType2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDTYPE2)).getText();
	}

	public String getCardNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDNUMBER)).getText();
	}

	public void clickCardNumber(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(T_CARDNUMBER)));
		driver.findElement(By.xpath(T_CARDNUMBER)).click();
	}

	public String getCardNumber2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDNUMBER2)).getText();
	}

	public void clickCardNumber2(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(T_CARDNUMBER2)));
		driver.findElement(By.xpath(T_CARDNUMBER2)).click();
	}

	public String getCardExpiration(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDEXPIRATION)).getText();
	}

	public String getCardExpiration2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDEXPIRATION2)).getText();
	}

	public String getCardStatus(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDSTATUS)).getText();
	}

	public String getCardStatus2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_CARDSTATUS2)).getText();
	}

	public String getBillingAddress(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_BILLING_ADDRESS)).getText();
	}

	public String getNewBillingAddress(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_NEWBILLINGADDRESS)).getText();
	}

	public String getNewCity(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_NEWCITY)).getText();
	}

	public String getNewState(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_NEWSTATE)).getText();
	}

	public String getNewPostalCode(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_NEWPOSTAL)).getText();
	}

	public String getCCError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.cssSelector(CC_ERROR)).getText();
	}

	public String getCCPanError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.cssSelector(CC_PAN_ERROR)).getText();
	}

	public String getFieldError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(REQUIRED_FIELD_ERROR)).getText();
	}

	public boolean isCreateContactDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CREATE_CONTACT)).isDisplayed();
	}

	public boolean isSubmitEnabled(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SUBMIT)));
		return driver.findElement(By.cssSelector(SUBMIT)).isEnabled();
	}

	public boolean isSecondCardPrimaryDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_PRIMARY2)).isDisplayed();
	}

	public boolean isNameOnCardDisplayed(WebDriver driver)
			throws InterruptedException, AWTException, NoSuchElementException {
		try {
			return driver.findElement(By.xpath(T_NAMEONCARD)).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}