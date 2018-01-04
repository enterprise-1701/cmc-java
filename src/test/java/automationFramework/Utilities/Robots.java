package automationFramework.Utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Robots {

	public void abpTestClientLoadValue(String accountID) throws AWTException, IOException {

		try {

			Robot robot = new Robot();

			Runtime.getRuntime().exec(
					"cmd /c start cmd.exe /K \"c: && cd c:/Program Files/CTS/ABP Test Client (03.01.0100) && AbpTestClient.exe \"");
			robot.delay(3000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_DELETE);

			// Change the server name
			StringSelection stringSelection = new StringSelection(Global.ABP_SERVER);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, stringSelection);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(3000);

			// Go to AcctMgmt Load Value
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_L);
			robot.keyPress(KeyEvent.VK_V);

			// Go to AccountId field
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);

			// Change the AccountId
			StringSelection stringSelection2 = new StringSelection(accountID);
			Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard2.setContents(stringSelection2, stringSelection2);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(8000);

			// Exit out of ABP test tool
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_E);
			robot.keyPress(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_X);
			robot.keyPress(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_I);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyPress(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void abpTestClient(String currentToken) throws AWTException,
	 * IOException {
	 * 
	 * try {
	 * 
	 * Robot robot = new Robot();
	 * 
	 * Runtime.getRuntime().exec(
	 * "cmd /c start cmd.exe /K \"c: && cd c:/Program Files/CTS/ABP Test Client (03.01.0100) && AbpTestClient.exe \""
	 * ); robot.delay(3000); robot.keyPress(KeyEvent.VK_CONTROL);
	 * robot.keyPress(KeyEvent.VK_A); robot.keyPress(KeyEvent.VK_DELETE);
	 * 
	 * // Change the server name StringSelection stringSelection = new
	 * StringSelection(Global.ABP_SERVER); Clipboard clipboard =
	 * Toolkit.getDefaultToolkit().getSystemClipboard();
	 * clipboard.setContents(stringSelection, stringSelection);
	 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
	 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
	 * robot.delay(3000);
	 * 
	 * // Go to Device Tap robot.keyPress(KeyEvent.VK_ALT);
	 * robot.keyPress(KeyEvent.VK_T); robot.keyRelease(KeyEvent.VK_ALT);
	 * robot.keyPress(KeyEvent.VK_DOWN); robot.keyPress(KeyEvent.VK_ENTER);
	 * robot.delay(3000);
	 * 
	 * // On Device Tap robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_ENTER); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_A);
	 * robot.keyPress(KeyEvent.VK_DELETE);
	 * robot.keyRelease(KeyEvent.VK_CONTROL); robot.delay(3000);
	 * 
	 * // Change the token data StringSelection stringSelection2 = new
	 * StringSelection(currentToken); Clipboard clipboard2 =
	 * Toolkit.getDefaultToolkit().getSystemClipboard();
	 * clipboard2.setContents(stringSelection2, stringSelection2);
	 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
	 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
	 * robot.delay(3000);
	 * 
	 * // tab to Post Tap robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_TAB); robot.delay(1000);
	 * robot.keyPress(KeyEvent.VK_ENTER); robot.delay(15000);
	 * 
	 * // Exit out of test tool robot.keyPress(KeyEvent.VK_ALT);
	 * robot.keyPress(KeyEvent.VK_F4); robot.keyPress(KeyEvent.VK_ALT);
	 * robot.keyPress(KeyEvent.VK_F4); robot.keyPress(KeyEvent.VK_ALT);
	 * robot.keyPress(KeyEvent.VK_F4); robot.keyRelease(KeyEvent.VK_ALT);
	 * robot.delay(1000); robot.keyPress(KeyEvent.VK_E);
	 * robot.keyRelease(KeyEvent.VK_E); robot.keyPress(KeyEvent.VK_X);
	 * robot.keyRelease(KeyEvent.VK_X); robot.keyPress(KeyEvent.VK_I);
	 * robot.keyRelease(KeyEvent.VK_I); robot.keyPress(KeyEvent.VK_T);
	 * robot.keyRelease(KeyEvent.VK_T); robot.keyPress(KeyEvent.VK_ENTER);
	 * 
	 * } catch (AWTException e) { e.printStackTrace(); } }
	 */
}
