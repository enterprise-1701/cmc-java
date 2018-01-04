package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PilLabPage extends BasePage{

	// Element Locators
	private static final String USERNAME = "//*[@id='username']";
	private static final String PASSWORD = "//*[@id='password']";
	private static final String LOGIN = "//*[@id='kc-login']";

	public PilLabPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(WebDriver driver, String name) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(USERNAME)).clear();
		driver.findElement(By.xpath(USERNAME)).sendKeys(name);
	}

	public void enterPassword(WebDriver driver, String passwd) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWORD)).clear();
		driver.findElement(By.xpath(PASSWORD)).sendKeys(passwd);
	}

	public void clickLogin(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LOGIN)).click();
	}

}