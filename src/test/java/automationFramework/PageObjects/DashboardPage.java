package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

	// Element Locators
	private static final String CUSTOMER_TAB = "//*[@id='header_customer_btn']";
	private static final String CUSTOMER = "//*[@id='launchPad_customer_btn']/h4";

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public void clickCustomerTab(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CUSTOMER_TAB)));
		driver.findElement(By.xpath(CUSTOMER_TAB)).click();
	}
	
	public void clickCustomer(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CUSTOMER)));
		driver.findElement(By.xpath(CUSTOMER)).click();
	}

}