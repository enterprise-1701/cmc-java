package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteAddressDetailsPage extends BasePage {

	// Element Locators
	private static final String CANCEl = "addressDelete_cancel_btn";
	private static final String CONFIRM_DELETE = "addressDelete_delete_btn";
	private static final String HEADER = "/html/body/div[1]/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/customer-addresses/p-dialog[2]/div/div[1]/span";
	private static final String CANCEl2 = "addressDepn_cancel_btn";

	public DeleteAddressDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void clickConfirmDelete(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(CONFIRM_DELETE)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(CANCEl)).click();
	}
	
	public void clickSecondCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(CANCEl2)).click();
	}

	public boolean isHeaderDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(HEADER)).isDisplayed();
	}

}