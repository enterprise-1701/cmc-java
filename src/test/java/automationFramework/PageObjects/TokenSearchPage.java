package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TokenSearchPage extends BasePage {


	// Element Locators
	private static final String TOKENTYPE = ".//*[@id='searchToken_tokenType_sel']";
	private static final String SUBSYSTEM = ".//*[@id='searchToken_subsystem_sel']";
	private static final String BANKCARD_NUMBER = ".//*[@id='searchToken_bankCardNumber_txt']";	
	private static final String EXPIRATION_MONTH = ".//*[@id='searchToken_bankCardExpiryMM_sel']";
	private static final String EXPIRATION_YEAR = ".//*[@id='searchToken_bankCardExpiryYYYY_sel']";
	private static final String SEARCH_TOKEN = ".//*[@id='searchToken_searchToken_btn']";
	private static final String NEW_SEARCH = ".//*[@id='searchToken_newSearch_btn']";
	private static final String ACCOUNT_NICKNAME = ".//*[@id='searchTokenResults_accountNickname_txt']";
	private static final String REGISTER = ".//*[@id='searchTokenResults_registerCustomer_btn']";
	private static final String RESULTS_NEW_SEARCH = ".//*[@id='searchTokenResults_newSearch_btn']";
	private static final String NO_RECORD_FOUND = "/html/body/my-app/customer/div/search/fieldset/div[2]/div/div[2]/search-token-results/div/div/form/div[1]/p[1]";
	private static final String TOKEN_VERIFICATION_NICKNAME = ".//*[@id='searchTokenResults_accountNickname_txt']";
	private static final String TOKEN_WITHBALANCE_VERIFICATION_NICKNAME = "//*[@id='tokenVerification_nickname_text']";	
	private static final String TOKEN_VERIFICATION_REGISTER = ".//*[@id='searchTokenResults_registerCustomer_btn']";
	private static final String VIEW_DETAILS = "//*[@id='tokenVerification_viewDetails_button']";
	
	private static final String INVALID_TOKEN = "/html/body/my-app/customer/div/search/fieldset/div[2]/div/div[2]/search-token-results/div/div/div[1]/p[1]";
	private static final String INVALID_TOKEN_NEWSEARCH = ".//*[@id='accountCreationFailedMessageBox_newSearch_btn']";
	
	//UI ID has been wiped out 
	//private static final String REQUIRED_FIELD_ERROR = ".//*[@id='v-required-alert']";
	private static final String REQUIRED_FIELD_ERROR_MONTH = "/html/body/my-app/customer/div/search/fieldset/div[2]/div/div[1]/search-form/div/search-token-form/div/form/div[3]/div[2]/div/div[1]/div/validation-msg/div";
	private static final String REQUIRED_FIELD_ERROR_YEAR =  "/html/body/my-app/customer/div/search/fieldset/div[2]/div/div[1]/search-form/div/search-token-form/div/form/div[3]/div[2]/div/div[2]/div/validation-msg/div";
	
	private static final String LINK_ACCOUNT_CONFIRMATION = ".//*[@id='search_legend']/div";
	private static final String INVALID_TOKEN_REGISTER = ".//*[@id='accountCreationFailedMessageBox_registerCustomer_btn']";
	private static final String BALANCE_HISTORY = "/html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/balance-history/p-panel/div/div[1]/a/span";
	private static final String BALANCE_HISTORY_SUBACCOUNT = "//*[@id='subsystem_balance_history_pnl']/div/div[1]/a/span";
	private static final String TRAVEL_HISTORY = "//*[@id='subsystem_travel_history_pnl']/div/div[1]/a/span";
	private static final String TOKEN_VERIFICATION_DATE = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[1]/span";
	private static final String TOKEN_VERIFICATION_TRANSACTION = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[2]/span";
	private static final String TOKEN_VERIFICATION_PRODUCT = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[3]/span";
	private static final String TOKEN_VERIFICATION_AMOUNT = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[4]/span";
	private static final String TOKEN_VERIFICATION_DATE2 = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[2]/td[1]/span";
	private static final String TOKEN_VERIFICATION_TRANSACTION2 = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[2]/td[2]/span";
	private static final String TOKEN_VERIFICATION_PRODUCT2 = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[2]/td[3]/span";
	private static final String TOKEN_VERIFICATION_AMOUNT2 = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[2]/td[4]/span";

	public TokenSearchPage(WebDriver driver) {
		super(driver);
	}

	public void selectTokenType(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(TOKENTYPE)));
		mySelect.selectByValue(value);
	}

	public void selectSubsystem(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(SUBSYSTEM)));
		mySelect.selectByValue(value);
	}

	public void selectExpMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_MONTH)));
		mySelect.selectByIndex(1);
	}

	public void selectInvalidExpMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_MONTH)));
		mySelect.selectByIndex(0);
	}

	public void selectExpYear(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_YEAR)));
		mySelect.selectByIndex(2);
	}

	public void selectInvalidExpYear(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_YEAR)));
		mySelect.selectByIndex(0);
	}

	public void enterBankNumber(WebDriver driver, String number) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BANKCARD_NUMBER)));
		//driver.findElement(By.xpath(BANKCARD_NUMBER)).click();
		driver.findElement(By.xpath(BANKCARD_NUMBER)).sendKeys(number);
	}

	public void clickSearchToken(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_TOKEN)));
		driver.findElement(By.xpath(SEARCH_TOKEN)).click();
	}

	public Boolean isClickSearchTokenEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SEARCH_TOKEN)).isEnabled();
	}

	public void clickNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NEW_SEARCH)));
		driver.findElement(By.xpath(NEW_SEARCH)).click();
	}

	public void clickInvalidTokenNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_TOKEN_NEWSEARCH)));
		driver.findElement(By.xpath(INVALID_TOKEN_NEWSEARCH)).click();
	}

	public void clickBalanceHistory(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BALANCE_HISTORY)));
		driver.findElement(By.xpath(BALANCE_HISTORY)).click();
	}

	public void clickBalanceHistorySubAccount(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BALANCE_HISTORY_SUBACCOUNT)));
		driver.findElement(By.xpath(BALANCE_HISTORY_SUBACCOUNT)).click();
	}

	public void clickTravelHistory(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TRAVEL_HISTORY)));
		driver.findElement(By.xpath(TRAVEL_HISTORY)).click();
	}

	public void enterNickName(WebDriver driver, String name) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_NICKNAME)));
		driver.findElement(By.xpath(ACCOUNT_NICKNAME)).click();
		driver.findElement(By.xpath(ACCOUNT_NICKNAME)).sendKeys(name);
	}

	public void enterTokenVerificationNickName(WebDriver driver, String name)
			throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOKEN_VERIFICATION_NICKNAME)));
		driver.findElement(By.xpath(TOKEN_VERIFICATION_NICKNAME)).click();
		driver.findElement(By.xpath(TOKEN_VERIFICATION_NICKNAME)).sendKeys(name);
	}

	public void enterTokenWithBalanceVerificationNickName(WebDriver driver, String name)
			throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOKEN_WITHBALANCE_VERIFICATION_NICKNAME)));
		//driver.findElement(By.xpath(TOKEN_WITHBALANCE_VERIFICATION_NICKNAME)).click();
		driver.findElement(By.xpath(TOKEN_WITHBALANCE_VERIFICATION_NICKNAME)).sendKeys(name);
		//driver.findElement(By.xpath(TOKEN_WITHBALANCE_VERIFICATION_NICKNAME)).sendKeys(Keys.RETURN);
	}

	public void clickRegisterCustomer(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(REGISTER)));
		driver.findElement(By.xpath(REGISTER)).click();
	}

	public void clickViewDetails(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VIEW_DETAILS)));
		driver.findElement(By.xpath(VIEW_DETAILS)).click();
	}

	public void clickTokenVerificationRegisterCustomer(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOKEN_VERIFICATION_REGISTER)));
		driver.findElement(By.xpath(TOKEN_VERIFICATION_REGISTER)).click();
	}

	public void clickResultsNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESULTS_NEW_SEARCH)));
		driver.findElement(By.xpath(RESULTS_NEW_SEARCH)).click();
	}

	public void clickInvalidTokenRegister(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_TOKEN_REGISTER)));
		driver.findElement(By.xpath(INVALID_TOKEN_REGISTER)).click();
	}

	public String getNoRecordFoundError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NO_RECORD_FOUND)).getText();
	}

	public boolean isNoRecordFoundErrorDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NO_RECORD_FOUND)).isDisplayed();
	}

	public boolean isSearchTokenEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SEARCH_TOKEN)).isEnabled();
	}

	public boolean isLinkAccountConfirmationDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINK_ACCOUNT_CONFIRMATION)).isDisplayed();
	}

	public String getLinkAccountConfirmationNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINK_ACCOUNT_CONFIRMATION)).getText();
	}

	public String getInavlidTokenError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(INVALID_TOKEN)).getText();
	}

	public String getBankNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(BANKCARD_NUMBER)).getText();
	}

	public String getTokenVerificationDate(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_DATE)).getText();
	}

	public String getTokenVerificationDate2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_DATE2)).getText();
	}

	public String getTokenVerificationTransaction(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_TRANSACTION)).getText();
	}

	public String getTokenVerificationTransaction2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_TRANSACTION2)).getText();
	}

	public String getTokenVerificationProduct(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_PRODUCT)).getText();
	}

	public String getTokenVerificationProduct2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_PRODUCT2)).getText();
	}

	public String getTokenVerificationAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_AMOUNT)).getText();
	}

	public String getTokenVerificationAmount2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION_AMOUNT2)).getText();
	}

	public String getRequiredFieldErrorMonth(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(REQUIRED_FIELD_ERROR_MONTH)).getText();
	}
	
	public String getRequiredFieldErrorYear(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(REQUIRED_FIELD_ERROR_YEAR)).getText();
	}

}