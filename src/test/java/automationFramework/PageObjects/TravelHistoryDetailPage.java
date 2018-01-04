package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelHistoryDetailPage extends BasePage {

	// Element Locators
	private static final String TIMING = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[2]/span/span";
	private static final String LOCATION = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[3]/span/span";
	private static final String DEVICE = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[4]/span";
	private static final String OPERATOR = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[5]/span";
	private static final String TRANSACTION = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[6]/span";
	private static final String TRANSACTION_STATUS = "//*[@id='travelHistoryDetail_journey_table']/div/div/table/tbody/tr/td[7]/span";
	private static final String FARE = "//*[@id='travelHistoryDetail_paidByEntry_table']/div/div/table/tbody/tr/td[1]/span/span";
	private static final String SOURCE = "//*[@id='travelHistoryDetail_paidByEntry_table']/div/div/table/tbody/tr/td[2]/span";
	private static final String DESCRIPTION = "//*[@id='travelHistoryDetail_paidByEntry_table']/div/div/table/tbody/tr/td[3]/span/span[1]/span[1]/div";
	private static final String CLOSE = "//*[@id='travelHistoryDetail_submit_btn']";
	private static final String AGENCY = "//*[@id='travelHistoryDetail_agencyEntry_table']/div/div/table/tbody/tr/td[1]/span";
	private static final String TRAVEL_MODE = "//*[@id='travelHistoryDetail_agencyEntry_table']/div/div/table/tbody/tr/td[2]/span";
	private static final String TOKEN = "//*[@id='travelHistoryDetail_agencyEntry_table']/div/div/table/tbody/tr/td[3]/span/span";

	public TravelHistoryDetailPage(WebDriver driver) {
		super(driver);
	}

	public String getTiming(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TIMING)).getText();
	}

	public String getLocation(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LOCATION)).getText();
	}

	public String getDevice(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DEVICE)).getText();
	}

	public String getOperator(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(OPERATOR)).getText();
	}

	public String getTransaction(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRANSACTION)).getText();
	}

	public String getTransactionStatus(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRANSACTION_STATUS)).getText();
	}

	public String getFare(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FARE)).getText();
	}

	public String getSource(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SOURCE)).getText();
	}

	public String getDescription(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(DESCRIPTION)).getText();
	}

	public String getAgency(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(AGENCY)).getText();
	}

	public String getTravelMode(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TRAVEL_MODE)).getText();
	}

	public String getToken(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN)).getText();
	}

	public void clickClose(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CLOSE)).click();
	}

}