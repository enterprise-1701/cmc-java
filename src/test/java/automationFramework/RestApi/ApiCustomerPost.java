package automationFramework.RestApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.json.JSONException;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import automationFramework.DAO.PostgresAutomation;
import automationFramework.Utilities.CustomerData;
import automationFramework.Utilities.Global;
import automationFramework.Utilities.Logging;
import org.apache.log4j.Logger;
import automationFramework.Utilities.Utils;

@JsonIgnoreProperties(ignoreUnknown = true)
// API Test customer POST - NIS NextWave v2.0
public class ApiCustomerPost {

	//private static String ENDPOINT = "http://172.23.4.196:8201/nis/nwapi/v2/customer/";
	
	//PIL endpoint
	private static String ENDPOINT = "http://10.252.1.21:8201/nis/nwapi/v2/customer/";
	private static String ENDPOINT2b = "?returnCustomerInfo=true";
	

	private static String customerId;
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static CustomerData cData = new CustomerData();

	@BeforeMethod
	public void setUp() throws InterruptedException {
		RestAssured.defaultParser = Parser.JSON;
		Logging.setLogConsole();
		Logging.setLogFile();
		Log.info("Setup Started");
		Log.info("Current OS: " + WindowsUtils.readStringRegistryValue(Global.OS));
		Log.info("Starting Tests");

	}

	// API post success return customer data
	@Test(enabled = true, priority = 1)
	public static CustomerData apiPostSuccess() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody(getContact());
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		printResponse(response);
		assertResponse(response);

		// set customer data
		cData.setFname(requestBody.getContact().getName().getFirstName());
		cData.setLname(requestBody.getContact().getName().getLastName());
		cData.setEmail(requestBody.getContact().getEmail());
		cData.setPhone(requestBody.getContact().getPhone().get(0).getNumber());
		cData.setAddress(requestBody.getContact().getAddress().getAddress1());
		cData.setContactType(requestBody.getContact().getContactType());
		cData.setCity(requestBody.getContact().getAddress().getCity());
		cData.setState(requestBody.getContact().getAddress().getState());
		cData.setPostal(requestBody.getContact().getAddress().getPostalCode());
		return cData;
	}

	// API post success with no address for mobile customer return customer data
	@Test(enabled = false, priority = 2)
	public static CustomerData apiPostSuccessNoAddress() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody(getContactNoAddress());
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		printResponse(response);
		assertResponse(response);

		// set customer data
		cData.setFname(requestBody.getContact().getName().getFirstName());
		cData.setLname(requestBody.getContact().getName().getLastName());
		cData.setEmail(requestBody.getContact().getEmail());
		cData.setPhone(requestBody.getContact().getPhone().get(0).getNumber());
		cData.setContactType(requestBody.getContact().getContactType());
		return cData;
	}

	// API Get customer info to verify customer post success
	// This test should be executed right after apiPostSuccess()
	// Using WSAddressExt, WSCustomerInfo, WSCustomerInfoContact
	@Test(enabled = false, priority = 3)
	public static void apiGetCustomerInfo() throws JSONException {

		WSGetNewCustomerResponse response = Utils.getRequestSpecifications().get(ENDPOINT + customerId + ENDPOINT2b)
				.as(WSGetNewCustomerResponse.class);
		Assert.assertNotNull(response);

		// get the request object
		WSCreateNewCustomerRequest request = ApiCustomerPost.createRequestBody();

		// comparison of request object to the response object
		Assert.assertEquals(response.getCustomerInfo().getCustomerId(), customerId);
		Assert.assertEquals(response.getCustomerInfo().getCustomerType(), request.getCustomerType());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getContactId(), customerId);
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getContactType(),
				request.getContact().getContactType());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getName().getFirstName(),
				request.getContact().getName().getFirstName());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getName().getLastName(),
				request.getContact().getName().getLastName());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getName().getNameSuffixId(),
				request.getContact().getName().getNameSuffixId());

		// address
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getAddress().getAddress1(),
				request.getContact().getAddress().getAddress1());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getAddress().getAddress2(),
				request.getContact().getAddress().getAddress2());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getAddress().getCity(),
				request.getContact().getAddress().getCity());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getAddress().getState(),
				request.getContact().getAddress().getState());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getAddress().getCountry(),
				request.getContact().getAddress().getCountry());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getAddress().getPostalCode(),
				request.getContact().getAddress().getPostalCode());
		Assert.assertTrue(response.getCustomerInfo().getContacts().get(0).getAddress().getAddressId() != null);

		// phone
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPhone().get(0).getNumber(),
				request.getContact().getPhone().get(0).getNumber());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPhone().get(0).getType(),
				request.getContact().getPhone().get(0).getType());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPhone().get(1).getNumber(),
				request.getContact().getPhone().get(1).getNumber());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPhone().get(1).getType(),
				request.getContact().getPhone().get(1).getType());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPhone().get(2).getNumber(),
				request.getContact().getPhone().get(2).getNumber());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPhone().get(2).getType(),
				request.getContact().getPhone().get(2).getType());

		// email, dob, personal id
		// Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getEmail(),
		// request.getContact().getEmail());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getDateOfBirth(),
				request.getContact().getDateOfBirth());
		Assert.assertEquals(
				response.getCustomerInfo().getContacts().get(0).getPersonalIdentifierInfo().getPersonalIdentifierType(),
				request.getContact().getPersonalIdentifierInfo().getPersonalIdentifierType());
		Assert.assertEquals(
				response.getCustomerInfo().getContacts().get(0).getPersonalIdentifierInfo().getPersonalIdentifier(),
				request.getContact().getPersonalIdentifierInfo().getPersonalIdentifier());

		// username, pin, security question
		// Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getUsername(),
		// request.getContact().getUsername());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getPin(), request.getContact().getPin());
		Assert.assertEquals(
				response.getCustomerInfo().getContacts().get(0).getSecurityQAs().get(0).getSecurityQuestion(),
				request.getContact().getSecurityQAs().get(0).getSecurityQuestion());
		Assert.assertEquals(response.getCustomerInfo().getContacts().get(0).getSecurityQAs().get(0).getSecurityAnswer(),
				request.getContact().getSecurityQAs().get(0).getSecurityAnswer());

		// addresses
		Assert.assertEquals(response.getCustomerInfo().getAddresses().get(0).getAddress1(),
				request.getContact().getAddress().getAddress1());
		Assert.assertEquals(response.getCustomerInfo().getAddresses().get(0).getAddress2(),
				request.getContact().getAddress().getAddress2());
		Assert.assertEquals(response.getCustomerInfo().getAddresses().get(0).getCity(),
				request.getContact().getAddress().getCity());
		Assert.assertEquals(response.getCustomerInfo().getAddresses().get(0).getState(),
				request.getContact().getAddress().getState());
		Assert.assertEquals(response.getCustomerInfo().getAddresses().get(0).getCountry(),
				request.getContact().getAddress().getCountry());
		Assert.assertEquals(response.getCustomerInfo().getAddresses().get(0).getPostalCode(),
				request.getContact().getAddress().getPostalCode());
		Assert.assertTrue(response.getCustomerInfo().getAddresses().get(0).getAddressId() != null);

	}

	// API Get success - testing request is working with header success
	// request body is null without the extra parameter ?returnCustomerInfo=true
	@Test(enabled = false, priority = 4)
	public static void apiGetSuccess() throws JSONException {

		Response response = Utils.getRequestSpecifications().when().get(ENDPOINT + customerId).thenReturn();
		Assert.assertNotNull(response);
		System.out.println(response.asString());
		String header = response.getHeader("x-cub-hdr");
		Log.info(header);
		Assert.assertTrue(header.contains("Successful"), "API request failed!");

	}
	
	
    //Check all API erros 
	// API post error blank customer type
	@Test(enabled = false)
	public static void apiErrorBlankCustomerType() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.setCustomerType(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "customerType", "errors.general.value.required");
	}

	// API post error invalid customer type
	@Test(enabled = false)
	public static void apiErrorInvalidCustomerType() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.setCustomerType("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "customerType", "errors.general.value.unexpected");
	}

	// API post error blank contact
	@Test(enabled = false)
	public static void apiErrorBlankContact() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.setContact(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact", "errors.general.value.required");
	}

	// API post error blank contact type
	@Test(enabled = false)
	public static void apiErrorBlankContactType() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setContactType(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.contactType", "errors.general.value.required");
	}

	// API post error invalid contact type
	@Test(enabled = false)
	public static void apiErrorInvalidContactType() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setContactType("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.contactType", "errors.general.value.unexpected");

	}

	// API post error blank contact name
	@Test(enabled = false)
	public static void apiErrorBlankContactName() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setName(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name", "errors.general.value.required");
	}

	// API post error blank contact first name
	@Test(enabled = false)
	public static void apiErrorBlankContactFirstName() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getName().setFirstName(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name.firstName", "errors.general.value.required");
	}

	// Test special characters
	// API post error max length first name
	@Test(enabled = false)
	public static void apiErrorMaxLengthContactFirstName() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getName().setFirstName("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name.firstName", "errors.general.value.toolong");
	}

	// API post error max length middle name
	@Test(enabled = false)
	public static void apiErrorMaxLengthContactMiddleName() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getName().setMiddleInitial("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name.middleInitial", "errors.general.value.toolong");
	}

	// Test special characters
	// Test embeded sql
	// API post error blank contact last name
	@Test(enabled = false)
	public static void apiErrorBlankContactLastName() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getName().setLastName(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name.lastName", "errors.general.value.required");
	}

	// API post error maximum length contact last name
	@Test(enabled = false)
	public static void apiErrorMaxLengthContactLastName() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getName().setLastName("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name.lastName", "errors.general.value.toolong");
	}

	// API post error invalid suffix id
	@Test(enabled = false)
	public static void apiErrorInvalidSuffixId() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getName().setNameSuffixId(10);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.name.nameSuffixId", "errors.general.value.unexpected");
	}

	// API post error invalid address id
	@Test(enabled = false)
	public static void apiErrorAddressId() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setAddressId("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.addressId", "errors.general.value.unexpected");
	}

	// API post error blank address
	@Test(enabled = false)
	public static void apiErrorBlankAddress() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setAddress1(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.address1", "errors.general.value.required");
	}

	// API post error max length address 1
	@Test(enabled = false)
	public static void apiErrorMaxLengthAddress() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress()
				.setAddress1("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.address1", "errors.general.value.toolong");
	}

	// API post error max length address 2
	@Test(enabled = false)
	public static void apiErrorMaxLengthAddress2() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress()
				.setAddress2("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.address2", "errors.general.value.toolong");
	}

	// API post error blank city
	@Test(enabled = false)
	public static void apiErrorBlankCity() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setCity(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.city", "errors.general.value.required");
	}

	// API post error maximum length city
	@Test(enabled = false)
	public static void apiErrorMaxLengthCity() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress()
				.setCity("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.city", "errors.general.value.toolong");
	}

	// API post error blank country
	@Test(enabled = false)
	public static void apiErrorBlankCountry() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setCountry(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.country", "errors.general.value.required");
	}

	// API post error invalid country
	@Test(enabled = false)
	public static void apiErrorInvalidCountry() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setCountry("zz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.country", "errors.general.value.unexpected");
	}

	// API post error blank state
	@Test(enabled = false)
	public static void apiErrorBlankState() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setState(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.state", "errors.general.value.required");
	}

	// API post error invalid state
	@Test(enabled = false)
	public static void apiErrorInvalidState() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setState("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.state", "errors.general.value.unexpected");
	}

	// API post error blank postal code
	@Test(enabled = false)
	public static void apiErrorBlankPostalCode() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setPostalCode(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.postalCode", "errors.general.value.required");
	}

	// API post error invalid postal code
	@Test(enabled = false)
	public static void apiErrorInvalidPostalCode() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getAddress().setPostalCode("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.address.postalCode", "errors.general.invalid.postal.code");
	}

	// API post error blank phone number 1
	@Test(enabled = false)
	public static void apiErrorBlankPhoneNumber() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(0).setNumber(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[0].phoneNumber", "errors.general.value.required");
	}

	// API post error invalid phone number 1
	@Test(enabled = false)
	public static void apiErrorInvalidPhoneNumber() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(0).setNumber("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[0].phoneNumber", "errors.general.invalid.phone.number");
	}

	// API post error blank phone type 1
	@Test(enabled = false)
	public static void apiErrorBlankPhoneType() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(0).setType(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[0].phoneType", "errors.general.value.required");
	}

	// API post error invalid phone type 1
	@Test(enabled = false)
	public static void apiErrorInvalidPhoneType() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(0).setType("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[0].phoneType", "errors.general.value.unexpected");
	}

	// API post error blank phone number 2
	@Test(enabled = false)
	public static void apiErrorBlankPhoneNumber2() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(1).setNumber(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[1].phoneNumber", "errors.general.value.required");
	}

	// API post error invalid phone number 2
	@Test(enabled = false)
	public static void apiErrorInvalidPhoneNumber2() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(1).setNumber("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[1].phoneNumber", "errors.general.invalid.phone.number");

	}

	// API post error blank phone type 2
	@Test(enabled = false)
	public static void apiErrorBlankPhoneType2() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(1).setType(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[1].phoneType", "errors.general.value.required");

	}

	// API post error invalid phone type 2
	@Test(enabled = false)
	public static void apiErrorInvalidPhoneType2() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(1).setType("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[1].phoneType", "errors.general.value.unexpected");
	}

	// API post error blank phone number 3
	@Test(enabled = false)
	public static void apiErrorBlankPhoneNumber3() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(2).setNumber(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[2].phoneNumber", "errors.general.value.required");

	}

	// API post error invalid phone number 3
	@Test(enabled = false)
	public static void apiErrorInvalidPhoneNumber3() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(2).setNumber("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[2].phoneNumber", "errors.general.invalid.phone.number");

	}

	// API post error blank phone type 3
	@Test(enabled = false)
	public static void apiErrorBlankPhoneType3() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(2).setType(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[2].phoneType", "errors.general.value.required");

	}

	// API post error invalid phone type 3
	@Test(enabled = false)
	public static void apiErrorInvalidPhoneType3() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPhone().get(2).setType("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.phone[2].phoneType", "errors.general.value.unexpected");

	}

	// API post error blank email
	@Test(enabled = false)
	public static void apiErrorBlankEmail() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setEmail(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.email", "errors.general.value.required");
	}

	// API post error maximum length email
	@Test(enabled = false)
	public static void apiErrorMaximumLengthEmail() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setEmail(
				"zzadfkajddfkadfkjdfdfsffsadfassafasfasfsdadfadfassdfaadfadsfafadfadfafdafddasfdfffdesse213123123123131344455@yahoo.com");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.email", "errors.general.value.toolong");
	}

	// API post error special characters email
	@Test(enabled = false)
	public static void apiErrorSpecialCharactersEmail() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setEmail("@yahoo.com");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.email", "errors.general.email.cannot.start.with.special.characters");
	}

	// API post error invalid email format
	@Test(enabled = false)
	public static void apiErrorInvalidFormatEmail() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setEmail("test");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.email", "errors.general.email.invalid.format");
	}

	// API post error personal identifier blank
	@Test(enabled = false)
	public static void apiErrorBlankPersonalId() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPersonalIdentifierInfo().setPersonalIdentifier(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.personalIdentifierInfo.personalIdentifier", "errors.general.value.required");
	}

	// API post error personal identitifer maximum length
	@Test(enabled = false)
	public static void apiErrorMaxLengthPersonalId() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPersonalIdentifierInfo()
				.setPersonalIdentifier("dafdadffsdkjkldjadfakjads;fjkasd;fjka;dfjka;dfadf");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.personalIdentifierInfo.personalIdentifier", "errors.general.value.toolong");
	}

	// API post error blank personal identitifer type
	@Test(enabled = false)
	public static void apiErrorBlankPersonalIdType() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPersonalIdentifierInfo().setPersonalIdentifierType(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.personalIdentifierInfo.personalIdentifierType", "errors.general.value.required");
	}

	// API post error invalid personal identitifer type
	@Test(enabled = false)
	public static void apiErrorInvalidPersonalIdType() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getPersonalIdentifierInfo()
				.setPersonalIdentifierType("fadfakdfja;dfkaj;dfka;dfja;kafd;fkajd;f;f");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.personalIdentifierInfo.personalIdentifierType",
				"errors.general.value.unexpected");
	}

	// API post error blank username
	@Test(enabled = false)
	public static void apiErrorBlankUserName() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.username", "errors.general.value.required");

	}

	// API post error maximum length username
	@Test(enabled = false)
	public static void apiErrorMaxLengthUserName() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername(
				"test1358testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest@yahoo.com");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.username", "errors.general.value.toolong");
	}

	// API post error special characters username
	@Test(enabled = false)
	public static void apiErrorSpecialCharactersUserName() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername("@yahoo");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.username", "errors.general.email.cannot.start.with.special.characters");
	}

	// API post error invalid username
	@Test(enabled = false)
	public static void apiErrorInvalidUserName() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.username", "errors.general.email.invalid.format");
	}

	// API post error duplicate username
	@Test(enabled = false)
	public static void apiErrorDuplicateUserName() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername("Test8630@yahoo.com");
		sendAPIRequest(requestBody);
		WSCreateNewCustomerRequest requestBody2 = createRequestBody();
		requestBody2.getContact().setUsername("Test8630@yahoo.com");
		WSCreateNewCustomerResponse response2 = sendAPIRequest(requestBody2);
		checkError(response2, "contact.username", "errors.general.value.duplicate");
	}

	// API post error blank password
	@Test(enabled = false)
	public static void apiErrorBlankPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.general.value.required");
	}

	// API post error contain username in password
	@Test(enabled = false)
	public static void apiErrorContainUsernamePassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername("test@yahoo.com");
		requestBody.getContact().setPassword("test@yahoo.com");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.cannot.contain.username");
	}

	// API post error contain consecutively characters // There are two
	// different errors on this page 27 NIS_NextWave_v2.0_REST_API
	@Test(enabled = false)
	public static void apiErrorContainConsecutiveCharacterPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("testtt1234");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.cannot.contain.same.char.consecutively");
	}

	// API post error minimum lenght not met
	@Test(enabled = false)
	public static void apiErrorMinLengthPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("t");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.general.value.toosmall");
	}

	// API post error maximum length password
	@Test(enabled = false)
	public static void apiErrorMaxLengthPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("91245912459124591245912459124591245testtesttesttesttest");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.general.value.toolong");
	}

	// API post error contain digits password
	@Test(enabled = false)
	public static void apiErrorContainDigitsPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("invalidpassword");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.must.contain.atleast.digits");
	}

	// API post error mixed case letters password
	@Test(enabled = false)
	public static void apiErrorMixCasesPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("invalidpassword12");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.must.contain.mixed.case.chars");
	}

	// API post error must contain letters password
	@Test(enabled = false)
	public static void apiErrorMustContainLettersPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("45897586");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.must.contain.atleast.chars");
	}

	// API post error must contain special characters
	@Test(enabled = false)
	public static void apiErrorContainSpecialCharactersPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("Testx1869");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.must.contain.atleast.specials.chars");
	}

	// API post error must contain dictionary words
	@Test(enabled = false)
	public static void apiErrorContainDictionaryPassword() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPassword("Britain1");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.password", "errors.password.cannot.contain.dictionary.word");
	}

	// API post error pin can not contain username
	@Test(enabled = false)
	public static void apiErrorContainUsernamePin() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setUsername("Britain1@yahoo.com");
		requestBody.getContact().setPin("Britain1");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.pin.cannot.contain.username");
	}

	// API post error pin contain consecutive characters
	@Test(enabled = false)
	public static void apiErrorContainConsecutiveCharacterPin() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPin("1111");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.pin.cannot.contain.same.char.consecutively");
	}

	// API post error pin contain contigous characters?
	@Test(enabled = false)
	public static void apiErrorContainContigousCharacterPin() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPin("1234");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.pin.cannot.contain.contiguous.chars");
	}

	// API post error pin contain contigous characters?
	@Test(enabled = false)
	public static void apiErrorTooSmallPin() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPin("12");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.general.value.toosmall");
	}

	// API post error pin too long
	@Test(enabled = false)
	public static void apiErrorTooLongPin() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPin("1935692432498193432343");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.general.value.toolong");
	}

	// API post error pin contain non-numeric characters
	@Test(enabled = false)
	public static void apiErrorNonNumericPin() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPin("britain");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.general.numerics.only");
	}

	// API post error security question blank
	@Test(enabled = false)
	public static void apiErrorSecurityQuestionBlank() throws JSONException {

		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getSecurityQAs().get(0).setSecurityQuestion(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.securityQAs[0].securityQuestion", "errors.general.value.required");
	}

	// API post error security question invalid
	@Test(enabled = false)
	public static void apiErrorSecurityQuestionInvalid() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getSecurityQAs().get(0).setSecurityQuestion("invalid");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.securityQAs[0].securityQuestion", "errors.general.value.unexpected");
	}

	// API post error security answer blank
	@Test(enabled = false)
	public static void apiErrorSecurityAnswerBlank() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getSecurityQAs().get(0).setSecurityAnswer(null);
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.securityQAs[0].securityAnswer", "errors.general.value.required");
	}

	// API post error security answer max length
	@Test(enabled = false)
	public static void apiErrorSecurityAnswerMaxLength() throws JSONException {
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().getSecurityQAs().get(0).setSecurityAnswer(
				"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.securityQAs[0].securityAnswer", "errors.general.value.toolong");
	}

	// *******************************************************************************************************
	// These tests run individual updates for each field in the db and check the
	// api response for each update
	// *******************************************************************************************************

	// API post error pin contain consecutive characters
	@Test(enabled = false)
	public static void apiErrorContainConsecutiveCharacterPinUpdateDB()
			throws JSONException, InterruptedException, SQLException {

		// connect to db, set table to default settings
		PostgresAutomation db = new PostgresAutomation();
		db.dbConnect();
		db.dbSetDefaultSettings();

		// update table and wait for the cache to get refreshed
		db.dbUpdateSettings("security.pin.numeric.checkrepeats", "true");

		// response should return api error
		WSCreateNewCustomerRequest requestBody = createRequestBody();
		requestBody.getContact().setPin("1111");
		WSCreateNewCustomerResponse response = sendAPIRequest(requestBody);
		checkError(response, "contact.pin", "errors.pin.cannot.contain.same.char.consecutively");

		// update the field back to default and response should not return error
		// db.dbUpdateSettings("security.pin.numeric.checkrepeats", "false");
		// response = sendAPIRequest(requestBody);
		// checkError(response, null, null);

		// set back to defaults and disconnect
		// db.dbSetDefaultSettings();
		db.dbDisconnect();
	}

	// ****************************************************************************************************
	// start of private methods
	// ****************************************************************************************************
	// create contact object
	public static WSCustomerContact getContact() {

		// Declare, instantiate and initialize the object
		WSCustomerContact contact = getContactNoAddress();

		// set address
		WSAddress address = new WSAddress();
		address.setAddress1("123 broadway");
		address.setAddress2(null);
		address.setCity("san diego");
		address.setCountry("US");
		address.setState("CA");
		address.setPostalCode("92122");
		contact.setAddress(address);

		return contact;
	}

	public static WSCustomerContact getContactNoAddress() {

		// Declare, instantiate and initialize the object
		WSCustomerContact contact = new WSCustomerContact();

		// set name and contact type
		WSName name = new WSName();
		name.setFirstName("robert");
		name.setLastName("downton");
		name.setNameSuffixId(null);
		contact.setName(name);
		contact.setContactType("Primary");

		// don't set address
		WSAddress address = new WSAddress();
		address.setAddress1(null);
		address.setAddress2(null);
		address.setCity(null);
		address.setCountry(null);
		address.setState(null);
		address.setPostalCode(null);
		// contact.setAddress(address);

		// set phone
		WSPhone phone = new WSPhone();
		List<WSPhone> phoneList = new ArrayList<WSPhone>();
		phone.setCountry("US");
		phone.setType("M");
		phone.setNumber("8586140263");
		phoneList.add(phone);
		contact.setPhone(phoneList);

		// set email
		String email = Utils.randomEmailString();
		contact.setEmail(email);
		Log.info("New Customer email: " + email);

		// set dob
		String string = "January 2, 1980";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		LocalDate lDate = LocalDate.parse(string, formatter);
		Date date = java.sql.Date.valueOf(lDate);
		contact.setDateOfBirth(date);

		// set username, password and pin
		// contact.setUsername("indy1988@gmail.com");
		contact.setUsername(Utils.randomEmailString());
		contact.setPassword("testSample1x@");
		contact.setPin("1234");

		// set personalIdentifier
		WSPersonalIdentifier identifier = new WSPersonalIdentifier();
		identifier.setPersonalIdentifier("1");
		identifier.setPersonalIdentifierType("DriversLicense");
		contact.setPersonalIdentifierInfo(identifier);

		// Set securityQA
		WSSecurityQA security = new WSSecurityQA();
		List<WSSecurityQA> securityList = new ArrayList<WSSecurityQA>();
		security.setSecurityQuestion("What is your first pet's name?");
		security.setSecurityAnswer("forty");
		securityList.add(security);
		contact.setSecurityQAs(securityList);
		return contact;
	}

	// create request body
	public static WSCreateNewCustomerRequest createRequestBody(WSCustomerContact contact) {
		WSCreateNewCustomerRequest requestBody = new WSCreateNewCustomerRequest();
		requestBody.setCustomerType("Individual");
		requestBody.setContact(contact);
		return requestBody;
	}

	// create request body
	public static WSCreateNewCustomerRequest createRequestBody() {
		return createRequestBody(getContact());
	}

	// Send API request - mapper type GSON
	private static WSCreateNewCustomerResponse sendAPIRequest(WSCreateNewCustomerRequest requestBody) {
		WSCreateNewCustomerResponse response = Utils.getRequestSpecifications().body(requestBody).when().post(ENDPOINT)
				.as(WSCreateNewCustomerResponse.class);
		// .as(WSCreateNewCustomerResponse.class, ObjectMapperType.GSON);
		return response;
	}

	// Assertion on post success
	private static void assertResponse(WSCreateNewCustomerResponse response) {

		Assert.assertNotNull(response);
		Log.info("Response is:  ");
		printResponse(response);
		Assert.assertNotNull(response.getCustomerId());
		Log.info("New Customer Id: " + response.getCustomerId());
		Assert.assertNotNull(response.getContactId());
		Log.info("New Contact Id: " + response.getContactId());
		Assert.assertNotNull(response.getOneAccountId());
		Log.info("New OneAccount Id: " + response.getOneAccountId());
	}

	// Assertion on post errors
	private static void checkError(WSCreateNewCustomerResponse response, String field, String errorKey) {

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getHdr().getFieldName(), field);
		Log.info("Actual field " + response.getHdr().getFieldName() + " matches expected field " + field);
		Assert.assertEquals(response.getHdr().getErrorKey(), errorKey);
		Log.info("Actual error key " + response.getHdr().getErrorKey() + " matches expected errorkey " + errorKey);

	}

	// print response
	private static void printResponse(WSCreateNewCustomerResponse response) {
		System.out.print(response.prettyPrint());
	}

	@AfterMethod
	public void tearDown() {
		Log.info("Ending Tests");
		Log.info("TearDown Complete");

	}

}
