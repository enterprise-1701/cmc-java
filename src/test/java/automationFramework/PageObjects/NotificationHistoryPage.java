package automationFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import automationFramework.Utilities.Utils;

/**
 * Page Object class designed to allow handling of the
 * Notification History list.
 * 
 * @author 203402
 *
 */
public class NotificationHistoryPage extends BasePage {

	public static final String NOT_HISTORY_ICON = "//*[@id='notificationHistory_list_panel']/div/div[1]/a/span";     
	public static final String SUBJECT_XPATH = "//*[@id='notificationHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[3]/span/span";
	
	/**
	 * Constructor of the NotificationHistoryPage class.
	 * 
	 * @param driver
	 */
	public NotificationHistoryPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click the Notification History icon to open or close the
	 * Notification History List.
	 * @throws InterruptedException 
	 */
	public void clickIcon() throws InterruptedException {
		Utils.waitTime(6000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NOT_HISTORY_ICON)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(NOT_HISTORY_ICON))).click();
	}
	
	/**
	 * Get the text of the Subject of the most recent message.
	 * 
	 * @return the text of the Subject of the most recent message.
	 */
	public String getSubject() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUBJECT_XPATH)));
		return driver.findElement(By.xpath(SUBJECT_XPATH)).getText();
	}
}
