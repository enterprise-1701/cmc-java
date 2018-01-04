package automationFramework.PageObjects;

import java.awt.AWTException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationPreferencesPage extends BasePage {

	// Element Locators
	private static final String SUBMIT = "//*[@id='notificationPreferences_submit_btn']";	
	private static final String CANCEL = "//*[@id='notificationPreferences_cancel_btn']";
	private static final String IFRAME_CUSTOMER = "//*[@id='portal_customer_iframe']";
	private static final String CATEGORY_ACCOUNTS_CHKBX = "//*[@id='category_Accounts']/input";
	
	//*[@id="portal_customer_iframe"]

	public NotificationPreferencesPage(WebDriver driver) {
		super(driver);
	}

	public void clickSubmit(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUBMIT)));
		driver.findElement(By.xpath(SUBMIT)).click();
	}
	
	/**
	 * clickSubCatAccountIssues()
	 * Click the Customer Contact Updates checkbox.
	 * 
	 * @param driver
	 */
	public void clickCategoryAccountsCheckbox(WebDriver driver) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CATEGORY_ACCOUNTS_CHKBX)));
		driver.findElement(By.xpath(CATEGORY_ACCOUNTS_CHKBX)).click();
	}
	
	/**
	 * Test that the Customer Contact Updates check-box is selected.
	 * @param driver
	 * @return True if the check-box is selected, else false.
	 */
	public boolean isSelectedSubCatAccountIssues(WebDriver driver) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CATEGORY_ACCOUNTS_CHKBX)));
		return driver.findElement(By.xpath(CATEGORY_ACCOUNTS_CHKBX)).isSelected();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CANCEL)));
		driver.findElement(By.xpath(CANCEL)).click();
	}

	public boolean isCancelsDisplayed(WebDriver driver) throws InterruptedException, AWTException,
			NoSuchElementException, org.openqa.selenium.NoSuchElementException {

		List<WebElement> mlList = driver.findElements(By.xpath(CANCEL));
		if (mlList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean isCancelDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(CANCEL)).isDisplayed();
	}
	
	public void switchToFrame(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IFRAME_CUSTOMER)));
		driver.switchTo().frame(driver.findElement(By.xpath(IFRAME_CUSTOMER)));
	}

}