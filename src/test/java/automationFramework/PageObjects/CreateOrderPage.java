package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrderPage extends BasePage {

	// Element Locators
	private static final String PURSE = "//*[@id='orderCreationFrom_purseId_sel']";
	private static final String ORDERTYPE = "//*[@id='orderCreationFrom_productType_sel']";
	private static final String ORDERAMOUNT = "//*[@id='orderCreationFrom_orderAmount_sel']";
	private static final String SUBMIT = "//*[@id='orderCreationFrom_submit_button']/span[1]";
	private static final String CANCEL = "//*[@id='orderCreationFrom']/div/button[2]";
	private static final String BALANCE_HISTORY_EXPAND = "//*[@id='balanceHistory_list_panel']/div/div[1]/a/span";								
	private static final String T_PURSE = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr/td[3]/span";
	private static final String T_ENTRY_TYPE = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr/td[4]/span";
	private static final String T_TRANSACTION_AMOUNT = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr/td[6]/span/span";	
	private static final String T_AVAILABLE_BALANCE = "//*[@id='balanceHistory_list_tbl']/div/div/table/tbody/tr/td[7]";
	private static final String NEW_FUNDING_SOURCE = "//*[@id='orderCreationFrom_createFundingSource_button']";

	public CreateOrderPage(WebDriver driver) {
		super(driver);
	}

	public void selectPurseType(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(PURSE)));
		mySelect.selectByIndex(1);
	}

	public void selectOrderType(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(ORDERTYPE)));
		mySelect.selectByIndex(1);
	}

	public void selectOrderAmount(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(ORDERAMOUNT)));
		mySelect.selectByIndex(1);
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}

	public void clickAddtoCart(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUBMIT)));
		driver.findElement(By.xpath(SUBMIT)).click();
	}

	public void clickBalanceHistoryExpand(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BALANCE_HISTORY_EXPAND)));
		driver.findElement(By.xpath(BALANCE_HISTORY_EXPAND)).click();
	}

	public void clickNewFundingSource(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NEW_FUNDING_SOURCE)).click();
	}

	public String getPurse(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(T_PURSE)));
		return driver.findElement(By.xpath(T_PURSE)).getText();
	}

	public String getEntryType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_ENTRY_TYPE)).getText();
	}

	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_TRANSACTION_AMOUNT)).getText();
	}

	public String getAvaiableBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_AVAILABLE_BALANCE)).getText();
	}
}