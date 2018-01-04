package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automationFramework.Utilities.Global;

public class StatusPage extends BasePage {

	// Element Locators
	private static final String VERSION = "html/body/div[1]/table/tbody/tr[2]/td[4]";
	private static final String BUILD = "html/body/div[1]/table/tbody/tr[3]/td[3]";
	private static final String MACHINE = "html/body/div[1]/table/tbody/tr[4]/td[3]";

	public StatusPage(WebDriver driver) {
		super(driver);
	}

	public String getVersion(WebDriver driver) throws Exception {
		getLandingPage(Global.URL3);
		return driver.findElement(By.xpath(VERSION)).getText();
	}

	public String getBuild(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(BUILD)).getText();
	}

	public String getMachine(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(MACHINE)).getText();
	}

}