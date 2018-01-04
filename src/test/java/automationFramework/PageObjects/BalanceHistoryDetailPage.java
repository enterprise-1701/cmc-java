package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BalanceHistoryDetailPage extends BasePage {

	// Element Locators
	private static final String PURSE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[2]/span";
	private static final String ENTRY_TYPE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[3]/span";
	private static final String TRANSACTION_AMOUNT = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[4]/span/span";
	private static final String END_BALANCE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[5]";
	private static final String CLOSE = "/html/body/div[1]/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/balance-history/p-dialog/div/div[1]/a/span";

	public BalanceHistoryDetailPage(WebDriver driver) {
		super(driver);
	}

	public String getPurse(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PURSE)).getText();
	}

	public String getEntryType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ENTRY_TYPE)).getText();
	}

	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRANSACTION_AMOUNT)).getText();
	}

	public String getEndingBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(END_BALANCE)).getText();
	}

	public void clickClose(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE)).click();
	}

}