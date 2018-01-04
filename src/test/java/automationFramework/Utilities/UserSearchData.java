package automationFramework.Utilities;

import org.apache.log4j.Logger;

public class UserSearchData {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String fname;
	private static String lname;
	private static String email;

	public static void setUserFirstname(String userName) {
		fname = userName;
		Log.info("username being stored is " + userName);

	}

	public static void setEmail(String userEmail) {
		email = userEmail;
		Log.info("email being stored is " + email);
	}

	public static void setUserLastname(String lastName) {
		lname = lastName;
		Log.info("phone being stored is " + lastName);
	}

	public static String getUserFirstname() {
		Log.info("username being returned is " + fname);
		return fname;
	}

	public static String getEmail() {
		Log.info("email being returned is " + email);
		return email;
	}

	public static String getLastName() {
		Log.info("phone being returned is " + lname);
		return lname;
	}

}
