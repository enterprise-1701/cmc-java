package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TokenSearchCustomerDetailsPage extends BasePage {

	// Element Locators

	private static final String FNAMETABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[1]/span";
	private static final String LNAMETABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[2]/span";
	private static final String EMAILTABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String PHONETABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[4]/span/span";
	
	//Webelement needs to get updated 
	private static final String ACCOUNT = "//*[@id='linkedSubsystemAccounts_list_tbl']/div/div[1]/table/tbody/tr/td[3]/span/div/span";
	private static final String LINKED_ACCOUNT = "//*[@id='linkedAccount_list_div']/div/div[1]";

	public TokenSearchCustomerDetailsPage(WebDriver driver) {
		super(driver);
	}

	public String getFirstName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FNAMETABLE)).getText();
	}

	public String getLastName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LNAMETABLE)).getText();
	}

	public String getEmail(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAILTABLE)).getText();
	}

	public String getPhone(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PHONETABLE)).getText();
	}

	public String getAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT)).getText();
	}

	public void clickLinkedAccount(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LINKED_ACCOUNT)).click();
	}

}