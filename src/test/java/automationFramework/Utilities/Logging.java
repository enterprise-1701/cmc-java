package automationFramework.Utilities;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.os.WindowsUtils;


public class Logging {

	public static Logger Log = Logger.getLogger(Logger.class.getName());

	public static Logger setLogConsole() {

		// Logging Configuration
		Log.setLevel(Level.INFO);
		DOMConfigurator.configure("log4j.xml");
		ConsoleAppender ConsoleAppender = new ConsoleAppender();
		String pattern = "%nCode Location: %l %n";
		ConsoleAppender.setLayout(new PatternLayout(pattern));
		Log.addAppender(ConsoleAppender);
		ConsoleAppender.activateOptions();
		Log.info("Current OS: " + WindowsUtils.readStringRegistryValue(Global.OS));
		//Log.info("Current Browser: " + browser);
		return Log;

	}

	public static void setLogFile() {
		Log.setLevel(Level.INFO);
		DOMConfigurator.configure("log4j.xml");
		FileAppender fileAppender = new FileAppender();
		fileAppender.setFile(Global.LOG_FILE);
		String pattern = "%nCode Location: %l %n";;
		fileAppender.setLayout(new PatternLayout(pattern));
		Log.addAppender(fileAppender);
		fileAppender.activateOptions();
	}
}
