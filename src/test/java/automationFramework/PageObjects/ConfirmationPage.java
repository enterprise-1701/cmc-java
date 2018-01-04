package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

//confirmation for both delete funding source and reset password
public class ConfirmationPage extends BasePage {

	// Element Locators
	private static final String YES = "/html/body/p-confirmdialog/div/div[3]/button[2]/span[2]";
	private static final String NO = "/html/body/p-confirmdialog/div/div[3]/button[1]/span[1]";
	private static final String CLOSE = "/html/body/p-confirmdialog/div/div[1]/a/span";
	private static final String CONFIRMATION = "/html/body/p-confirmdialog/div/div[1]/span";
	//private static final String CLOSE_FUNDING_SOURCE = "/html/body/div[1]/div/div/customer/workspace/fieldset/div[3]/div/div[2]/customer-details/div[5]/div[1]/funding-sources/p-dialog[2]/div/div[1]/a/span";
	private static final String CLOSE_FUNDING_SOURCE = "//*[@id=\"oneaccount_customer_panel\"]/div/div[2]/div/div[4]/div[1]/funding-sources/p-dialog/div/div[1]/a/span";    
	                                                    
	
	private static final String CONFIRM_DELETE = "//*[@id='fundingSourceDelete_delete_btn']";
	private static final String CANCEL = "//*[@id='fundingSourceDelete_cancel_btn']";

	
	//Need to update webelement 
	private static final String CONFIRMATION_FS = "/html/body/div[1]/div/div/customer/workspace/fieldset/div[3]/div/div[2]/customer-details/div[5]/div[1]/funding-sources/p-dialog[2]/div/div[1]/span";

	public ConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public void clickYes(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(YES)).click();
	}

	public void clickNo(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NO)).click();
	}

	public void clickClose(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE)).click();
	}

	public void clickCloseFundingSource(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE_FUNDING_SOURCE)).click();
	}

	public void clickConfirmDelete(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONFIRM_DELETE)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}

	public String getConfirmation(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CONFIRMATION)).getText();
	}

	public String getConfirmationFundingSource(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONFIRMATION_FS)));
		return driver.findElement(By.xpath(CONFIRMATION_FS)).getText();
	}

}