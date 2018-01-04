package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TokenSearchCustomerVerifiPage extends BasePage {

	// Element Locators
	private static final String NAME = ".//*[@id='searchTokenResults_list_tbl']/div/div/table/tbody/tr/td[1]/span/span";
	private static final String CONTACT = ".//*[@id='searchTokenResults_list_tbl']/div/div/table/tbody/tr/td[2]/span/span";
	private static final String TOKEN = ".//*[@id='searchTokenResults_list_tbl']/div/div/table/tbody/tr/td[3]/span/span";
	private static final String ACCOUNT = ".//*[@id='searchTokenResults_list_tbl']/div/div/table/tbody/tr/td[4]";
	private static final String SQUESTION = ".//*[@id='verificationForm_securityQA_chk']";
	private static final String CONTINUE = ".//*[@id='verificationForm_verified_btn']";
	private static final String NICKNAME = ".//*[@id='tokenVerification_nickname_text']";
	private static final String REGISTER = ".//*[@id='tokenVerification_subsystemLinkSubmit_button']";
	public static final String NAME_CHECKBOX = ".//*[@id='verificationForm_name_chk']";
	public static final String ADDRESS_CHECKBOX = ".//*[@id='verificationForm_address_chk']";
	public static final String DOB_CHECKBOX = ".//*[@id='verificationForm_dob_chk']";

	public TokenSearchCustomerVerifiPage(WebDriver driver) {
		super(driver);
	}

	public String getName(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NAME)));
		return driver.findElement(By.xpath(NAME)).getText();
	}

	public String getContact(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CONTACT)).getText();
	}

	public String getStatus(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN)).getText();
	}

	public String getAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT)).getText();
	}

	public boolean isAccountNumberDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT)).isDisplayed();
	}

	public boolean isTokenInfoDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN)).isDisplayed();
	}

	public void clickSecurityBox(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SQUESTION)).click();
	}

	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}

	public void clickRegisterCustomer(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(REGISTER)).click();
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

	public void enterTokenVerificationNickName(WebDriver driver, String name)
			throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NICKNAME)));
		//driver.findElement(By.xpath(NICKNAME)).click();
		driver.findElement(By.xpath(NICKNAME)).sendKeys(name);
	}

}