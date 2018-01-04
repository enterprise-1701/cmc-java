package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import automationFramework.Utilities.Utils;

public class BalanceHistoryPage extends BasePage {

	// Element Locators
	private static final String DATE = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr/td[1]/span/span";
	private static final String PURSE = "//*[@id='balanceHistory_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String PURSE_RESTRICTION = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[4]/td[3]/span";
	private static final String ENTRY_TYPE = "//*[@id='balanceHistory_list_tbl']/div/div/table/tbody/tr/td[4]/span";
	private static final String TAMOUNT = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr/td[7]/span/span";
	private static final String END_BALANCE = "//*[@id='balanceHistory_list_tbl']/div/div/table/tbody/tr/td[6]/span/span";
	private static final String ACCT_BALANCE = "/html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[3]/subsystem-purse-details/div/p-panel/div/div[1]/header/div/span[2]";
	private static final String ACCT_BALANCE_RC = "/html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[3]/external-data/div/account-balance/p-panel/div/div[1]/header/div/div[2]";
	private static final String DATE_RC = "//*[@id='balanceHistory_list_tbl']/div/div/table/tbody/tr[1]/td[2]/span/span";
	private static final String PURSE_SUBACCOUNT = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[4]/td[2]/span";
	private static final String ENTRY_TYPE_SUBACCOUNT = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[4]/td[4]/span";
	private static final String TAMOUNT_SUBACCOUNT = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[4]/td[6]/span/span";
	private static final String END_BALANCE_SUBACCOUNT = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[4]/td[7]/span/span";
	private static final String FROM_DATE = "//*[@id='balanceHistory_fromDate_txt']/span/input";
	private static final String NEW_DATE = "//*[@id='balanceHistory_fromDate_txt']/span/div/table/tbody/tr[1]/td[4]/a";
	private static final String FILTER = "//*[@id='subsystem_balance_history_pnl']/div/div[2]/div/div/form/div[3]/button";
	private static final String BALANCE_HISTORY_FILTER = "//*[@id='balanceHistory_filter_btn']";
	private static final String BALANCE_HISTORY_TRANSACTION_TYPE = "//*[@id='balanceHistory_financialTxnType_sel']";
	private static final String TOKEN_TYPE = "//*[@id='tokens_list_tbl']/div/div[1]/table/tbody/tr/td[1]/span";
	private static final String STATUS = "//*[@id='tokens_list_tbl']/div/div[1]/table/tbody/tr/td[3]/span";
	private static final String DEVICE = "//*[@id='tokens_list_tbl']/div/div[1]/table/tbody/tr/td[7]/span";
	
	private static final String NO_RECORD_FOUND = "//*[@id='balanceHistory_list_tbl']/div/div/table/tbody/tr/td";

	public BalanceHistoryPage(WebDriver driver) {
		super(driver);
	}

	public String getDate(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DATE)).getText();
	}

	public String getDateRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DATE_RC)).getText();
	}

	public String getPurse(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PURSE)).getText();
	}

	public String getPurseSubAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PURSE_SUBACCOUNT)).getText();
	}

	public String getPurseRestriction(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PURSE_RESTRICTION)).getText();
	}

	public String getEntryType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ENTRY_TYPE)).getText();
	}

	public String getEntryTypeSubAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ENTRY_TYPE_SUBACCOUNT)).getText();
	}

	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TAMOUNT)).getText();
	}

	public String getTransactionAmountSubAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TAMOUNT_SUBACCOUNT)).getText();
	}

	public String getEndingBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(END_BALANCE)).getText();
	}

	public String getEndingBalanceSubAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(END_BALANCE_SUBACCOUNT)).getText();
	}

	public String getAcctBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCT_BALANCE)).getText();
	}

	public String getAcctBalanceRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCT_BALANCE_RC)).getText();
	}
	
	public String getTokenType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_TYPE)).getText();
	}
	

	public String getStatus(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(STATUS)).getText();
	}
	
	public String getDevice(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DEVICE)).getText();
	}

	public String getNoRecordFound(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NO_RECORD_FOUND)).getText();
	}

	public void clickRow(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(DATE_RC)).click();
	}

	public void clickFromDate(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(FROM_DATE)).click();
		driver.findElement(By.xpath(NEW_DATE)).click();
	}

	public void clickFilter(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(FILTER)).click();
	}

	public void clickBalanceHistoryFilter(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(BALANCE_HISTORY_FILTER)).click();
	}

	public void selectTransactionType(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(BALANCE_HISTORY_TRANSACTION_TYPE)));
		Utils.waitTime(3000);
		mySelect.selectByValue(value);
	}

}