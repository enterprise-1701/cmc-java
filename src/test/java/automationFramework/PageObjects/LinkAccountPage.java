package automationFramework.PageObjects;

import java.awt.AWTException;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class LinkAccountPage extends BasePage {

	// Element Locators
	// Elements need to be updated to have IDs
	private static final String SUBSYSTEM = "/html/body/div[1]/div/div/customer/workspace/fieldset/div[3]/div/div[2]/customer-details/div[2]/div[1]/linked-account/p-panel/div/div[2]/div/p-datatable/div/div[1]/table/tbody/tr/td[1]/span/div/span";
	private static final String AGENCY = "/html/body/div[1]/div/div/customer/workspace/fieldset/div[3]/div/div[2]/customer-details/div[2]/div[1]/linked-account/p-panel/div/div[2]/div/p-datatable/div/div[1]/table/tbody/tr/td[2]/span/div/span";
	private static final String ACCOUNT_NUMBER = "//*[@id='linkedSubsystemAccounts_list_tbl']/div/div[1]/table/tbody/tr/td[3]/span/div/span";
	
	private static final String TRAVELMODE = "//*[@id='linkAccountForm_travelMode_select']";
	private static final String TOKENTYPE = "//*[@id='linkAccountForm_tokenType_select']";
	private static final String CANCEL = ".//*[@id='searchToken_newSearch_btn']";
	private static final String BANKNUMBER = ".//*[@id='searchToken_bankCardNumber_txt']";
	private static final String BANKMONTH = ".//*[@id='searchToken_bankCardExpiryMM_sel']";
	private static final String BANKYEAR = ".//*[@id='searchToken_bankCardExpiryYYYY_sel']";

	private static final String CONTINUE = "//*[@id='linkAccountForm_formSubmit_button']";
	private static final String NICKNAME = "//*[@id='tokenVerification_nickname_text']";
	private static final String NICKNAME_ACCOUNT_NOTFOUND = "//*[@id='registerTokenVerification_nickname_text']";
	private static final String REGISTER_AND_LINK = "//*[@id='linkAccountVerify_accountNotFoundRetry_button']";

	private static final String LINKYES = "//*[@id='linkAccountForm_subsystemLinkSubmit_button']";
	private static final String LINKNO = "//*[@id='linkAccountForm_cancelSubsystemLink_button']";
	private static final String BALANCE = "//*[@id='accountBalance_totalBalance_div']";

	private static final String DATETIME = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[1]/span";
	private static final String TRANSACTION = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[2]/span";
	private static final String LOCATION = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[3]/span";
	private static final String PRODUCT = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[4]/span";
	private static final String AMOUNT = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[5]/span";
	private static final String LINKPANEL = "//*[@id='linkAccountVerify_verify_panel']/div/div[1]/span";
	private static final String T_LINKACCOUNT_AUTHORITY = "//*[@id='linkedAccount_list_div']/div/div/strong";
	private static final String T_LINKACCOUNT_ACCOUNT = "//*[@id='linkedAccount_list_div']/div/div/span";
	private static final String VIEW_LINKED = "//*[@id='linkAccountVerify_verify_button']";
	private static final String ALREADY_LINKED = "//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	private static final String RETRY = "//*[@id='linkAccountVerify_alreadyLinkedRetry_button']";
	private static final String RETRY_NO = "//*[@id='linkAccountVerify_alreadyLinkedCancel_button']";
	private static final String ACCT_NOT_ELGIBLE = "//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	private static final String RETRY_NO_ACCT_NOT_ELGIBLE = "//*[@id='linkAccountVerify_accountClosedCancel_button']";
	private static final String RETRY_ACCT_NOT_ELGIBLE = "//*[@id='linkAccountVerify_accountClosedRetry_button']";
	private static final String UPDATED_BALANCE = "//*[@id='accountBalance_totalBalance_div']";
	private static final String SEARCH_TOKEN = ".//*[@id='searchToken_searchToken_btn']";
	private static final String LINKACCOUNT_FAILURE = ".//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	private static final String TOKEN_VERIFICATION = "//*[@id='tokenVerification_verify_panel']/div/div[1]/span";
	private static final String TOKEN_TRANSACTION = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[2]/span";
	private static final String TOKEN_PRODUCT = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[3]/span";
	private static final String TOKEN_AMOUNT = "//*[@id='tokenVerification_verification_table']/div/div/table/tbody/tr[1]/td[4]/span";
	private static final String NEW_SEARCH = "//*[@id='tokenVerification_newSearchSubsystemLink_button']";
	private static final String LINK_ACCOUNT = "//*[@id='tokenVerification_subsystemLinkSubmit_button']";
	private static final String ACCOUNT_NOT_FOUND_ERROR = "//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	private static final String LINK_ACCOUNT_VERIFICATION_PANEL = "//*[@id='tokenVerification_verify_panel']/div/div[1]/span";

	public LinkAccountPage(WebDriver driver) {
		super(driver);
	}

	public void selectAgency(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(AGENCY)));
		mySelect.selectByIndex(1);
	}

	public void selectTravelMode(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(TRAVELMODE)));
		mySelect.selectByIndex(1);
	}

	public void selectTokenType(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(TOKENTYPE)));
		mySelect.selectByIndex(1);
	}

	public void clearBankAccount(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BANKNUMBER)));
		driver.findElement(By.xpath(BANKNUMBER)).clear();
	}

	public void enterBankAccount(WebDriver driver, String number) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BANKNUMBER)));
		driver.findElement(By.xpath(BANKNUMBER)).sendKeys(number);
	}

	public String getBankNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(BANKNUMBER)).getText();
	}

	public void selectExpMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(BANKMONTH)));
		mySelect.selectByIndex(1);
	}

	public void selectExpYear(WebDriver driver, int year) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(BANKYEAR)));
		mySelect.selectByIndex(year);
	}

	public void enterNickName(WebDriver driver, String nname) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NICKNAME)).sendKeys(nname);
	}

	public void enterNickNameAccountNotFound(WebDriver driver, String nname) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NICKNAME_ACCOUNT_NOTFOUND)).sendKeys(nname);
	}

	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}

	public void clickYes(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LINKYES)).click();
	}

	public void clickNo(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LINKNO)).click();
	}

	public void clickView(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(VIEW_LINKED)).click();
	}

	public void clickRetry(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(RETRY)).click();
	}

	public void clickNoRetry(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(RETRY_NO)).click();
	}

	public void clickRetryAcctNotElgible(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(RETRY_ACCT_NOT_ELGIBLE)).click();
	}

	public void clickNoRetryAcctNotElgible(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(RETRY_NO_ACCT_NOT_ELGIBLE)).click();
	}

	public void clickSearchToken(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SEARCH_TOKEN)).click();
	}

	public void clickNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NEW_SEARCH)).click();
	}

	public void clickLinkAccount(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LINK_ACCOUNT)).click();
	}

	public void clickRegisterAndLink(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(REGISTER_AND_LINK)).click();
	}

	public String getBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(BALANCE)).getText();
	}

	public String getUpdatedBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UPDATED_BALANCE)).getText();
	}

	public String getDateTime(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DATETIME)).getText();
	}

	public String getTransaction(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRANSACTION)).getText();
	}

	public String getLocation(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LOCATION)).getText();
	}

	public String getProduct(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PRODUCT)).getText();
	}

	public String getAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(AMOUNT)).getText();
	}

	public String getAlreadyLinkedError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ALREADY_LINKED)).getText();
	}

	public String getAccountNotElgible(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCT_NOT_ELGIBLE)).getText();
	}

	public String getLinkAccountAuthority(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_LINKACCOUNT_AUTHORITY)).getText();
	}

	public String getExternalAccountId(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(T_LINKACCOUNT_ACCOUNT)).getText();
	}

	public String getTokenVerification(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION)).getText();
	}

	public Boolean isTokenVerificationDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_VERIFICATION)).isDisplayed();
	}

	public String getTokenTransaction(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_TRANSACTION)).getText();
	}

	public String getTokenProduct(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_PRODUCT)).getText();
	}

	public String getTokenAmount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_AMOUNT)).getText();
	}

	public boolean isLinkPanelDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINKPANEL)).isDisplayed();
	}

	public boolean isViewLinkDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(VIEW_LINKED)).isDisplayed();
	}

	public boolean isAgencyDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(AGENCY)).isDisplayed();
	}

	public boolean isLinkFailureDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINKACCOUNT_FAILURE)).isDisplayed();
	}

	public String getLinkFailureDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINKACCOUNT_FAILURE)).getText();
	}
	
	public String getSubsystem(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SUBSYSTEM)).getText();
	}
	
	public String getAccountNo(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT_NUMBER)).getText();
	}


	public String getAccountNotFoundError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT_NOT_FOUND_ERROR)).getText();
	}

	public boolean isAccountNotFoundErrorDisplayed(WebDriver driver) throws InterruptedException, AWTException,
			NoSuchElementException, org.openqa.selenium.NoSuchElementException {

		List<WebElement> mlList = driver.findElements(By.xpath(ACCOUNT_NOT_FOUND_ERROR));
		if (mlList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	/*
	 * public boolean isAccountNotFoundErrorDisplayed(WebDriver driver) throws
	 * InterruptedException, AWTException, NoSuchElementException,
	 * org.openqa.selenium.NoSuchElementException { try { return
	 * driver.findElement(By.xpath(ACCOUNT_NOT_FOUND_ERROR)).isDisplayed(); }
	 * catch (org.openqa.selenium.NoSuchElementException e) { return false; } }
	 */

	public boolean isLinkAccountVerificationPanelDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINK_ACCOUNT_VERIFICATION_PANEL)).isDisplayed();
	}

	public boolean isContinueEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CONTINUE)).isEnabled();
	}

	public boolean isSearchTokenEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SEARCH_TOKEN)).isEnabled();
	}
}