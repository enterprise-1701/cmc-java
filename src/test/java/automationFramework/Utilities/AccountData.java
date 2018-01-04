package automationFramework.Utilities;

import org.apache.log4j.Logger;

public class AccountData {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String account;
	private static String cc;

	public static void setAccountId(String accountId) {
		account = accountId;
		Log.info("account id being stored is " + accountId);

	}

	public static void setCCNumber(String ccNumber) {
		cc = ccNumber;
		Log.info("account id being stored is " + ccNumber);

	}

	public static String getAccountId() {
		Log.info("account number being returned is " + account);
		return account;
	}

	public static String getCCNumber() {
		Log.info("cc number being returned is " + cc);
		return cc;
	}

}
