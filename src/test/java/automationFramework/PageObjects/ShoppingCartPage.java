package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

	// Element Locators
	private static final String CHECKOUT = "/html/body/my-app/customer/div/cart/fieldset/div[2]/div/div[2]/sale-order-summary/div/div[2]/div[4]/button";
	private static final String PLACE_ORDER = "/html/body/my-app/customer/div/checkout/fieldset/div[2]/div/div[2]/sale-order-summary/div/div[2]/div[5]/button/span[1]";
	
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}

	public void clickCheckOut(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CHECKOUT)).click();
	}
	
	public void clickPlaceOrder(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PLACE_ORDER)).click();
	}
	
}