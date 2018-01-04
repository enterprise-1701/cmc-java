package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelHistoryPage extends BasePage {

	// Element Locators
	private static final String TIMING = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[1]/span/span[1]";
	private static final String LOCATION = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[2]/span/span[1]";
	private static final String TRAVEL_MODE = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String TOKEN = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[4]/span/span[1]";
	private static final String PRODUCT = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[6]/span";
	private static final String FARE = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[6]/span/span";
	private static final String UNPAID_FARE = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[7]/span/span";
	private static final String OPERATOR = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[5]/span";
	private static final String SOURCE = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[7]/span/span";
	private static final String DESCRIPTION = "//*[@id='subsystem_travelHistory_list_tbl']/div/div/table/tbody/tr/td[7]/span/span";
	private static final String TIMING_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[1]/span/span[1]";
	private static final String LOCATION_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[2]/span/span[1]";
	private static final String AGENCY_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[3]/span/span[1]";
	private static final String TRAVEL_MODE_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[4]/span";
	private static final String TOKEN_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[5]/span/span[1]";
	private static final String FARE_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[7]/span/span";
	private static final String UNPAID_FARE_RC = "//*[@id='travelHistory_list_tbl']/div/div/table/tbody/tr[1]/td[8]/span/span";

	public TravelHistoryPage(WebDriver driver) {
		super(driver);
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

	public String getProduct(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PRODUCT)).getText();
	}

	public String getFare(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FARE)).getText();
	}

	public String getUnpaidFare(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UNPAID_FARE)).getText();
	}

	public String getOperator(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(OPERATOR)).getText();
	}

	public String getSource(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SOURCE)).getText();
	}

	public String getDescription(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DESCRIPTION)).getText();
	}

	public void clickTiming(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(TIMING)).click();
	}

	public String getTimingRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TIMING_RC)).getText();
	}

	public String getLocationRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LOCATION_RC)).getText();
	}

	public String getTravelModeRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRAVEL_MODE_RC)).getText();
	}

	public String getTokenRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_RC)).getText();
	}

	public String getAgencyRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(AGENCY_RC)).getText();
	}

	public String getFareRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FARE_RC)).getText();
	}

	public String getUnpaidFareRc(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(UNPAID_FARE_RC)).getText();
	}

	public void clickTimingRc(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(TIMING_RC)).click();
	}

}