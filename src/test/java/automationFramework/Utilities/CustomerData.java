package automationFramework.Utilities;

import org.apache.log4j.Logger;

public class CustomerData {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String fname;
	private static String lname;
	private static String email;
	private static String phone;
	private static String address;
	private static String contactType;
	private static String state;
	private static String postal;
	private static String city;

	public void setFname(String userFName) {
		fname = userFName;
		Log.info("username being stored is " + fname);

	}

	public void setLname(String userLName) {
		lname = userLName;
		Log.info("username being stored is " + lname);

	}

	public void setEmail(String userEmail) {
		email = userEmail;
		Log.info("email being stored is " + email);
	}

	public void setPhone(String userPhone) {
		phone = userPhone;
		Log.info("phone being stored is " + phone);
	}

	public void setAddress(String userAddress) {
		address = userAddress;
		Log.info("username being stored is " + userAddress);

	}

	public void setContactType(String userContactType) {
		contactType = userContactType;
		Log.info("contact type being stored is " + contactType);
	}

	public void setCity(String userCity) {
		city = userCity;
		Log.info("city being stored is " + city);

	}

	public void setState(String userState) {
		state = userState;
		Log.info("state being stored is " + state);

	}

	public void setPostal(String userPostal) {
		postal = userPostal;
		Log.info("postal being stored is " + postal);

	}

	public String getFname() {
		Log.info("first name being returned is " + fname);
		return fname;
	}

	public String getLname() {
		Log.info("last name being returned is " + lname);
		return lname;
	}

	public String getEmail() {
		Log.info("email being returned is " + email);
		return email;
	}

	public String getPhone() {
		Log.info("phone being returned is " + phone);
		return phone;
	}

	public String getAddress() {
		Log.info("address being returned is " + address);
		return address;
	}

	public String getContactType() {
		Log.info("contact type being returned is " + contactType);
		return contactType;
	}

	public String getCity() {
		Log.info("city being returned is " + city);
		return city;
	}

	public String getState() {
		Log.info("state being returned is " + state);
		return state;
	}

	public String getPostal() {
		Log.info("postal being returned is " + postal);
		return postal;
	}

}
