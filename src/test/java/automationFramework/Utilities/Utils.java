package automationFramework.Utilities;

import static com.jayway.restassured.RestAssured.given;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.jayway.restassured.specification.RequestSpecification;

import automationFramework.Utilities.Global;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Utils {

	public static WebDriver driver = null;
	public static final String USERNAME = "cubic";
	public static final String ACCESS_KEY = "38f0a91e-f3a0-444b-ac89-d44140d1f18c";

	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitTime(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}

	public static void isPageLoaded(WebDriver driver, String title) {
		Assert.assertTrue(driver.getTitle().equals(title));
	}

	public static WebDriver openBrowser(String browser) {

		try {

			switch (browser) {
			case "firefox.exe":

				FirefoxProfile prof = new FirefoxProfile();
				prof.setPreference("browser.startup.homepage_override.mstone", "ignore");
				prof.setPreference("startup.homepage_welcome_url.additional", "about:blank");
				prof.setPreference("browser.cache.disk.enable", false);
				prof.setPreference("browser.cache.memory.enable", false);
				prof.setPreference("browser.cache.offline.enable", false);
				prof.setPreference("network.http.use-cache", false);
				prof.setPreference("browser.private.browsing.autostart", true);
				driver = new FirefoxDriver(prof);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				Reporter.log("firefox driver instantiated");
				break;

			case "chrome.exe":

				System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("incognito");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				break;

			case "saucelabs":

				String nodeUrl;
				nodeUrl = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
				DesiredCapabilities capbility = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL(nodeUrl), capbility);
				break;

			}

		} catch (Exception e) {
			Reporter.log("Utils.openBrowser failed");
		}

		return driver;
	}

	// Creating random email and password
	public static String randomEmailString() {
		String randomEmail = generateRandomString(12) + Global.MAILINATOR;
		return randomEmail;
	}

	public static String randomEmailStringGmailPlus() {
		String randomEmail = generateRandomString(12) + "+news" + Global.MAILINATOR;
		return randomEmail;
	}

	public static String randomPasswdString() {
		String randomPasswd = generateRandomString(12) + "1X!";
		return randomPasswd;
	}

	// Generate random user name and phone number
	public static String randomUsernameString() {
		String randomUsername = "USER_" + generateRandomString(7);
		return randomUsername;
	}

	public static String randomNameString() {
		String randomName = generateRandomString(5);
		return randomName;
	}

	public static String randomPhoneNumber() {
		return generatePhoneNumber();
	}

	// Generating random string
	private static String generateRandomString(int length) {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = getRandomNumber();
			char ch = Global.CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	// Generate random number
	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(Global.CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	// Generate random phone number
	private static String generatePhoneNumber() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		// first not 0 or 1
		sb.append(random.nextInt(7) + 2);
		// rest of 9 digits
		for (int i = 0; i < 9; i++) {
			sb.append(random.nextInt(8));
		}
		return sb.toString();
	}

	// capturing screenshots
	public static void getScreenShot() throws Exception {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File("C:/Automation/ScreenShots/" + System.currentTimeMillis() + "_screenshot.png"));
			Reporter.log("Screenshot Captured");
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot");
		}
	}

	// handling multiple windows and selecting the right one
	public static void handleMultipleWindows(WebDriver driver, String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}

	// Get index from external file
	public static String getIndex() {

		String index = null;
		try {
			Scanner s = new Scanner(new File(Global.INDEX_FILE));
			while (s.hasNext()) {
				index = s.next();
			}
			s.close();

		} catch (Exception e) {
			Reporter.log("Utils.getIndex failed");
			System.out.print("Utils.getIndex failed");
		}

		return index;
	}

	// Increment index in external file
	public static String incrementIndex() {

		// Get current index
		String currentIndex = Utils.getIndex();
		// Convert to int and increment
		int currentIndex1 = Integer.parseInt(currentIndex);
		int currentIndex2 = currentIndex1 + 1;
		// Convert incremented index back to String
		String nextIndex = Integer.toUnsignedString(currentIndex2);

		return nextIndex;
	}

	// Set index in external file
	public static void setIndex(String newIndex) {

		try {

			File file = new File(Global.INDEX_FILE);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(newIndex);
			bw.close();

		} catch (IOException e) {
			Reporter.log("Utils.setIndex failed");
			System.out.print("Utils.setIndex failed");
		}

	}

	// read text file and return row specified
	public static String readFile(int index) throws FileNotFoundException {

		ArrayList<String> list = new ArrayList<String>();
		String token = null;
		try {
			Scanner s = new Scanner(new File(Global.INPUT_FILE));
			while (s.hasNext()) {
				list.add(s.next());
			}

			token = list.get(index);
			s.close();

		} catch (Exception e) {
			Reporter.log("Utils.readFile failed");
			System.out.print("Utils.readFile failed");
		}

		return token;

	}

	// MAC generating code
	public String calcHmac(String src) throws Exception {
		String base64Key = "GaAodwiA6BREnloZYjOkONxCC//EKClXhzAuYoX91oU=";

		Mac mac = Mac.getInstance("HmacMD5");
		byte[] decodedBytes = Base64.decodeBase64(base64Key.getBytes("UTF-16LE"));
		SecretKeySpec sk = new SecretKeySpec(decodedBytes, mac.getAlgorithm());
		mac.init(sk);

		String srcStr = src;
		byte[] resultBase64 = Base64.encodeBase64(mac.doFinal(srcStr.getBytes("ASCII")));

		String sB64 = new String(resultBase64, "UTF-8");

		return sB64;
	}
	
	//API helper method
	public static RequestSpecification getRequestSpecifications() {

		return given().contentType("application/json").header("Authorization", "d2ViOnBhc3N3b3jk")
				.header("x-cub-uid", "11111111111").header("x-cub-device", "crm")
				.header("x-cub-audit", "{\"sourceIp\":\"123\",\"userId\":\"10\",\"firstName\":\"nick\",\"lastName\":\"dzwill\",\"channel\":\"cmc\",\"location\":\"usa\"}");
	}

}