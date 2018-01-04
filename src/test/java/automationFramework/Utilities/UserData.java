package automationFramework.Utilities;

import org.apache.log4j.Logger;

public class UserData {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String name;
	private static String email;
	private static String phone;

	public void setUsername(String userName) {
		name = userName;
		Log.info("username being stored is " + name);

	}

	public void setEmail(String userEmail) {
		email = userEmail;
		Log.info("email being stored is " + email);
	}

	public void setPhone(String userPhone) {
		phone = userPhone;
		Log.info("phone being stored is " + phone);
	}

	public String getUsername() {
		Log.info("username being returned is " + name);
		return name;
	}

	public String getEmail() {
		Log.info("email being returned is " + email);
		return email;
	}

	public String getPhone() {
		Log.info("phone being returned is " + phone);
		return phone;
	}

}
