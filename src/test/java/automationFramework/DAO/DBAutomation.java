package automationFramework.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//New class to connect to Oracle DB
@Test
public class DBAutomation {

	static WebDriver driver;
	static String browser;
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	Connection connection;
	Connection connectionOAM;
	int accountID;
	int customerID;
	int count;
	int fundingID;
	int addressID;
	int securityID;
	int contactID;
	int phoneID;
	int ccID;
	int subSystemID;
	int purseID;
	int journalID;
	String userName;
	String phoneNo;

	@BeforeMethod
	public void setUp() throws InterruptedException {

		Log.info("set up started");

	}

	public void dbCmcConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			Log.error("JDBC driver not found" + e);
		}

		try {

			//EIL Connection
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@172.23.4.198:1521:nextfare", "cmc_main",
			//		"cmc_main");
			
			//PIL Connection 
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.252.2.41:1521:cmc", "cmc_main",
					"cmc_main");
			
			Log.info("CMC DB connection success");

		} catch (Exception e) {
			Log.error("Connection failed" + e);
		}

	}

	public void dbCmsConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			Log.error("JDBC driver not found" + e);
		}

		try {

			//EIL
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@172.23.4.198:1521:nextfare", "cms_main",
			//		"cms_main");
			
			//PIL 
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.252.2.41:1521:oamcms", "cms_main",
					"cms_main");
			Log.info("CMS DB connection success");

		} catch (Exception e) {
			Log.error("Connection failed" + e);
		}

	}

	public void dbOamConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			Log.error("JDBC driver not found" + e);
		}

		try {

			//EIL 
			//connectionOAM = DriverManager.getConnection("jdbc:oracle:thin:@172.23.4.198:1521:nextfare", "oam_main",
			//		"oam_main");
			
			//PIL 
			connectionOAM = DriverManager.getConnection("jdbc:oracle:thin:@10.252.2.41:1521:oamcms", "oam_main",
					"oam_main");
			
			Log.info("OAM DB connection success");

		} catch (Exception e) {
			Log.error("Connection failed" + e);
		}

	}

	// query the databases
	// check cms_main.contact
	public boolean dbFindCustomer(String userEmail) {

		boolean recordFound = false;

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT * FROM cms_main.contact where email ='" + userEmail + "'");
			while (results.next()) {
				String ID = results.getString("contact_id");
				Log.info("contact id in the database: " + ID);
				recordFound = true;
			}

		} catch (Exception e) {
			Log.error("Not able to retrieve record from oracle DB");
		}

		return recordFound;
	}

	// check oam_main.account
	public boolean dbFindAccount(String email) {

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");

			while (results.next()) {
				customerID = results.getInt("customer_id");
				Log.info("contact id in the database: " + customerID);

			}

			// Second connection to OAM database
			Statement statement2 = connectionOAM.createStatement();
			ResultSet results2 = statement2
					.executeQuery("SELECT account_id FROM oam_main.account where customer_id like '" + customerID + "'");

			while (results2.next()) {
				accountID = results2.getInt("account_id");
				Log.info("account id in the database: " + accountID);
				return true;

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle oam_main.account");
		}

		return false;

	}

	// check cms_main.customer_credential
	public boolean dbFindUniqueUserName(String userId) {

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT count (user_name) FROM cms_main.customer_credential where user_name = '" + userId + "'");

			while (results.next()) {
				count = results.getInt("count");
				Log.info("number of userids for this account in the database: " + count);
				if (count == 1) {
					return true;
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle cms_main.customer_credential");
		}

		return false;
	}

	// check cms_main.address
	public boolean dbFindAddress(String email) {

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");

			while (results.next()) {
				customerID = results.getInt("customer_id");
				Log.info("contact id in the database: " + customerID);

			}

			Statement statement2 = connection.createStatement();
			ResultSet results2 = statement2
					.executeQuery("SELECT address_id FROM cms_main.address where customer_id = '" + customerID + "'");

			while (results2.next()) {
				addressID = results2.getInt("address_id");
				Log.info("address id in the database: " + addressID);
				return true;

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle cms_main.address");
		}

		return false;

	}

	// check cms_main.security_answer
	public boolean dbFindSecurityAnswer(String email) {

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT contact_id FROM cms_main.contact where email ='" + email + "'");
			if(results == null){
				Log.info("NO RESULTS WERE FOUND");
			}

			while (results.next()) {
				contactID = results.getInt("contact_id");
				Log.info("contact id in the database: " + contactID);

			}

			Statement statement2 = connection.createStatement();
			ResultSet results2 = statement2.executeQuery(
					"SELECT security_answer_id FROM cms_main.security_answer where contact_id = '" + contactID + "'");

			while (results2.next()) {
				securityID = results2.getInt("security_answer_id");
				Log.info("security id in the database: " + securityID);
				return true;

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle cms_main.security_answer");
		}

		return false;

	}

	// check cms_main.phone
	public boolean dbFindPhone(String email) {

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT phone_id_1 FROM cms_main.contact where email ='" + email + "'");

			while (results.next()) {
				phoneID = results.getInt("phone_id_1");
				Log.info("phone id in the database: " + phoneID);

			}

			Statement statement2 = connection.createStatement();
			ResultSet results2 = statement2
					.executeQuery("SELECT phone_number FROM cms_main.phone where phone_id = '" + phoneID + "'");

			while (results2.next()) {
				phoneNo = results2.getString("phone_number");
				Log.info("phone number in the database: " + phoneNo);
				return true;

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle cms_main.phone");
		}

		return false;

	}

	// check oam_main.subsystem_account
	public boolean dbFindSubSystem(int customerID) {

		try {

			Statement statement = connectionOAM.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT subsystem_account_id from oam_main.subsystem_account inner join oam_main.account on oam_main.subsystem_account.account_id = oam_main.account.account_id where oam_main.account.customer_id = '"
							+ customerID + "'");

			while (results.next()) {
				subSystemID = results.getInt("subsystem_account_id");
				Log.info("subsystem id in the database: " + subSystemID);
				return true;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle oam_main.account");
		}

		return false;

	}

	// get customer id number
	public int dbFindCustomerId(String email) {

		try {

			Statement statement = connection.createStatement();
			ResultSet results = statement
					.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");

			while (results.next()) {
				customerID = results.getInt("customer_id");
				Log.info("customer id in the database: " + customerID);

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle cms_main.contact");
		}

		return customerID;
	}

	// check oam_main.journal_entry
	public boolean dbFindJournalEntry(int customerID) {

		try {

			Log.info("oam_main Statement started!");
			Statement statement = connectionOAM.createStatement();

			ResultSet results = statement
					.executeQuery("SELECT account_id FROM oam_main.account where customer_id like '" + customerID + "'");

			if (results != null) {

				while (results.next()) {
					accountID = results.getInt("ACCOUNT_ID");
					Log.info("account id in the database: " + accountID);

				}

			} else {
				Log.info("results are null from oam_main.account");
			}

			Statement statement2 = connectionOAM.createStatement();
			ResultSet results2 = statement2.executeQuery(
					"SELECT journal_entry_id from oam_main.journal_entry inner join oam_main.purse on oam_main.purse.purse_id = oam_main.journal_entry.purse_id where oam_main.purse.account_id = '"
							+ accountID + "'");

			while (results2.next()) {
				journalID = results2.getInt("journal_entry_id");
				Log.info("journal entry id in the database: " + journalID);
				return true;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from oracle oam_main");
			Log.error(e);
		}

		return false;

	}

	public void dbDisconnectOAM() {
		try {
			connectionOAM.close();
			Log.info("DB disconnected");
		} catch (SQLException e) {
			Log.error("Not able to disconnect from DB");
		}

	}

	public void dbDisconnect() {
		try {
			connection.close();
			Log.info("DB disconnected");
		} catch (SQLException e) {
			Log.error("Not able to disconnect from DB");
		}

	}

}
