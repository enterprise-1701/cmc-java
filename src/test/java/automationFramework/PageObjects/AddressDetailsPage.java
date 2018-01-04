package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

public class AddressDetailsPage extends BasePage {

	// Element Locators
	private static final String COUNTRY = "//*[@id='customerAddress_country_sel']";
	private static final String ADDRESS = "//*[@id='customerAddress_address1_txt']";
	private static final String ADDRESS2 = "//*[@id='customerAddress_address2_txt']";
	private static final String CITY = "customerAddress_city_txt";
	private static final String STATE = "//*[@id='customerAddress_state_sel']";
	private static final String ZIPCODE = "//*[@id='customerAddress_postalCode_txt']";
	private static final String SUBMIT = "//*[@id='customerAddress_submit_btn']";
	private static final String CANCEl = "//*[@id='customerAddress_cancel_btn']";
	private static final String REQUIREDF_ERROR = "//*[@id='customerAddress']/div[2]/div/validation-msg/div";
	private static final String POSTAL_ERROR = "#customerAddress > div:nth-child(4) > div:nth-child(2) > validation-msg > div";
	private static final String ADDRESS_ERROR = "customer-addresses.customer-primeng > p-dialog:nth-child(2) > div:nth-child(1) > div:nth-child(2) > address-form:nth-child(1) > error-message:nth-child(1) > p-messages:nth-child(1) > div:nth-child(1) > ul:nth-child(3) > li:nth-child(1) > span:nth-child(2)";
	
	public AddressDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void selectCountry(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(COUNTRY)));
		mySelect.selectByIndex(1);
	}

	public void selectNoCountry(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(COUNTRY)));
		mySelect.selectByIndex(0);
	}

	public void enterAddress(WebDriver driver, String address) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADDRESS)).clear();
		driver.findElement(By.xpath(ADDRESS)).sendKeys(address);
	}

	public void deleteAddress(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADDRESS)).click();
		driver.findElement(By.xpath(ADDRESS)).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(ADDRESS)).sendKeys(Keys.ENTER);
	}

	public void enterAddress2(WebDriver driver, String address) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADDRESS2)).clear();
		driver.findElement(By.xpath(ADDRESS2)).sendKeys(address);
	}

	public void enterCity(WebDriver driver, String city) throws InterruptedException, AWTException {
		driver.findElement(By.id(CITY)).clear();
		driver.findElement(By.id(CITY)).sendKeys(city);
	}

	public void deleteCity(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.id(CITY)).click();
		driver.findElement(By.id(CITY)).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id(CITY)).sendKeys(Keys.ENTER);
	}

	public void selectState(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(STATE)));
		mySelect.selectByIndex(6);
	}

	public void enterPostalCode(WebDriver driver, String zip) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ZIPCODE)).clear();
		driver.findElement(By.xpath(ZIPCODE)).sendKeys(zip);
	}

	public void deletePostalCode(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ZIPCODE)).click();
		driver.findElement(By.xpath(ZIPCODE)).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(ZIPCODE)).sendKeys(Keys.ENTER);
	}

	public void clickSubmit(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SUBMIT)).click();
	}
	
	public boolean isSubmitEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SUBMIT)).isEnabled();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEl)).click();
	}

	public String getRequiredFieldError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.id(REQUIREDF_ERROR)).getText();
	}

	public String getPostalError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.cssSelector(POSTAL_ERROR)).getText();
	}
	
	public String getAddressError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.cssSelector(ADDRESS_ERROR)).getText();
	}


}