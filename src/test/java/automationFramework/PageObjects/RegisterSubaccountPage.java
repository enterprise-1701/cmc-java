package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

//regiser customer pop-up for subaccount landing page
public class RegisterSubaccountPage extends BasePage {


	// Element Locators
	private static final String TITLE = "/html/body/my-app/customer/div/workspace/fieldset/div[3]/div/div[1]/subsystem-actions-unregistered/div/p-dialog/div/div[1]/span";
	private static final String NICKNAME = "//*[@id='viewSubSystem_nickname_form_nickname_txt']";
	private static final String CANCEL = "//*[@id='viewSubSystem_nickname_form_cancel_btn']";
	private static final String REGISTER = "//*[@id='viewSubSystem_nickname_form_createCustomer_btn']";

	public RegisterSubaccountPage(WebDriver driver) {
		super(driver);
	}

	public void enterNickname(WebDriver driver, String name) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NICKNAME)).sendKeys(name);
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}

	public void clickRegister(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(REGISTER)).click();
	}

	public String getTitle(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE)));
		return driver.findElement(By.xpath(TITLE)).getText();
	}

}