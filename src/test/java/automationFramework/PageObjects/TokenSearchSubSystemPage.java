package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TokenSearchSubSystemPage extends BasePage {

	// Element Locators
	// CCD-551
	private static final String SUBSYSTEM = "//*[@id='subsystem_customer-panel']/div/div[1]/span";
	private static final String STATUS = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String ACCOUNT_NUMBER = "//*[@id='transitAccountDetails_accountNumber_td']";
	private static final String TOKEN_INFORMATION = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[2]/span/div[1]";
	private static final String TOKEN_TYPE = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[1]/span";
	private static final String TRAVEL_HISTORY = "//*[@id='travelHistory_list_panel']/div/div[1]/a/span";
	
	private static final String TIMING = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[1]/span/span[1]";
	private static final String LOCATION = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[2]/span/span[1]";
	                                      
	
	private static final String TRAVEL_MODE = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[4]/span";
	private static final String TOKEN = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[5]/span/span[1]";
	private static final String FARE = "///*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[7]/span/span";
	private static final String UNPAID_FARE = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[8]/span/span";

	private static final String BALANCE_HISTORY = "//*[@id='subsystem_balance_history_pnl']/div/div[1]/a/span";
	private static final String TRANSACTION_AMOUNT = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[1]/td[6]/span/span";
	private static final String ENDING_BALANCE = "//*[@id='subsystem_balanceHistory_list_tbl']/div/div/table/tbody/tr[1]/td[7]";
	private static final String REGISTER_CUSTOMER = "//*[@id='viewSubSystem_registerCustomer_lnk']";
	private static final String NICKNAME =  "//*[@id='viewSubSystem_nickname_form_nickname_txt']";

	public TokenSearchSubSystemPage(WebDriver driver) {
		super(driver);
	}

	public String getSubsystem(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SUBSYSTEM)).getText();
	}

	public String getStatus(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(STATUS)).getText();
	}

	public String getAccountNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT_NUMBER)).getText();
	}

	public String getTokenInfo(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_INFORMATION)).getText();
	}

	public String getTokenType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_TYPE)).getText();
	}

	public String getTiming(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TIMING)).getText();
	}

	public String getLocation(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LOCATION)).getText();
	}

	public String getTravelMode(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRAVEL_MODE)).getText();
	}

	public String getToken(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN)).getText();
	}

	public String getFare(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FARE)).getText();
	}

	public String getUnpaidFare(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UNPAID_FARE)).getText();
	}

	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRANSACTION_AMOUNT)).getText();
	}

	public String getEndingBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ENDING_BALANCE)).getText();
	}

	public void clickTravelHistory(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TRAVEL_HISTORY)));
		driver.findElement(By.xpath(TRAVEL_HISTORY)).click();
	}

	public void clickTravelHistoryRow(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(TIMING)).click();
	}

	public void clickBalanceHistory(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(BALANCE_HISTORY)).click();
	}

	public void clickRegisterCustomer(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(REGISTER_CUSTOMER)).click();
	}
	
	public void enterNickname(WebDriver driver, String nickName) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NICKNAME)).sendKeys(nickName);
	}

}