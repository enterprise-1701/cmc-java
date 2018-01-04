package automationFramework.Utilities;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class SOAPClientSAAJ2 {

	// ######################################################################
	// ##### GLOBAL VARIABLES
	// ######################################################################
	public static final String FILE_PATH = "C:/TRASH/soapReq.xml";
	public static final boolean APPEND_TO_FILE = false;
	public static final int CREDIT_CARD_LENGTH = 16;
	public static final String VISA_BIN = "4";
	public static final String MASTERCARD_BIN = "51";
	public static final String ABP_HOST_NAME = "172.23.4.200";
	public static final String ACCT_MGMT_URI = "http://abp.cubic.com/api/account/interface";
	public static final String ACCT_MGMT_SVC = "/AcctMgmtWebService/Services.svc";
	public static final String TXN_MGMT_URI = "http://abp.cubic.com/api/txn/interface";
	public static final String TXN_MGMT_SVC = "/TxnWebService/Services.svc";
	public static final String DEVICE_ID = "SAAJ-Auto";

	/**
	 * Starting point for the SAAJ - SOAP Client Testing
	 */

	public String travelHistoryPostTab(String pan) {
		// ######################################################################
		// ##### REQUEST PARAMETERS
		// ######################################################################

		// update to 2020
		String expirationDate = "2018-01";

		// PostTap Parameters
		String stopId = "1";
		String routeId = "1";
		String zoneId = "1";
		String operatorId = "1";
		String directionId = "1";
		String sectorId = "1";
		String serviceType = "1";
		String tapPurpose = "Entry";
		int tapSequence = 0;
		String tokenExpirationDate = expirationDate + "-31" + "T08:00:00Z"; // Pacifict
																			// Midnight
																			// in
																			// UTC
																			// (-8
																			// hours)
		String tokenType = "Bankcard";
		int transactionId = 0;

		String request = "";
		@SuppressWarnings("restriction")
		SOAPMessage response = null;
		String responseCode = "";
		String soapAction = "";
		String reqURL = "";

		// ######################################################################
		// ##### POST (SOAP) TAP
		// ######################################################################
		System.out.println("\n######################################################################");
		System.out.println("PostTap (ENTRY)");

		reqURL = "http://" + ABP_HOST_NAME + TXN_MGMT_SVC;
		soapAction = TXN_MGMT_URI + "/IServices/PostTap";
		request = createReqPostTap(DEVICE_ID, stopId, routeId, zoneId, operatorId, directionId, sectorId, serviceType,
				tapPurpose, ++tapSequence, pan, tokenExpirationDate, tokenType, createNowZuluDTM(), ++transactionId);
		response = sendSOAPReq(request, soapAction, reqURL);
		responseCode = getRespValue(response, "ResponseCode");
		System.out.println("ResponseCode: " + responseCode);
		// assert(responseCode == "Successful");

		System.out.println("\n######################################################################");
		System.out.println("PostTap (EXIT)");
		stopId = "2";
		request = createReqPostTap(DEVICE_ID, stopId, routeId, zoneId, operatorId, directionId, sectorId, serviceType,
				"Exit", ++tapSequence, pan, tokenExpirationDate, tokenType, createNowZuluDTM(), ++transactionId);
		response = sendSOAPReq(request, soapAction, reqURL);
		responseCode = getRespValue(response, "ResponseCode");
		System.out.println("ResponseCode: " + responseCode);
		// assert(responseCode == "Successful");

		return responseCode;

	}

	public String travelHistorySOAPCall(String pan) {
		// ######################################################################
		// ##### REQUEST PARAMETERS
		// ######################################################################
		String transitAccountId;
		String loadAmount = "188";
		String multiPurseType = "StoredValue";
		String multiPurseUsage = "Unrestricted";

		String avsData = "";
		String expirationDate = "2018-01";
		String requestAutoActivate = "true";

		// PostTap Parameters
		String stopId = "1";
		String routeId = "1";
		String zoneId = "1";
		String operatorId = "1";
		String directionId = "1";
		String sectorId = "1";
		String serviceType = "1";
		String tapPurpose = "Entry";
		int tapSequence = 0;
		String tokenExpirationDate = expirationDate + "-31" + "T08:00:00Z"; // Pacifict
																			// Midnight
																			// in
																			// UTC
																			// (-8
																			// hours)
		String tokenType = "Bankcard";
		int transactionId = 0;

		String request = "";
		@SuppressWarnings("restriction")
		SOAPMessage response = null;
		String responseCode = "";
		String soapAction = "";
		String reqURL = "";

		// ######################################################################
		// ##### ISSUE BANKCARD
		// ######################################################################
		System.out.println("######################################################################");
		System.out.println("IssueBankcard");

		// Get new Credit Card number
		// pan = createRandomPAN(MASTERCARD_BIN, CREDIT_CARD_LENGTH);

		// Create new Transit Account with new Credit Card number
		reqURL = "http://" + ABP_HOST_NAME + ACCT_MGMT_SVC;
		soapAction = ACCT_MGMT_URI + "/IServices/IssueBankcard";
		request = createReqIssueBankcard(avsData, expirationDate, pan, requestAutoActivate);
		response = sendSOAPReq(request, soapAction, reqURL);
		responseCode = getRespValue(response, "ResponseCode");
		System.out.println("ResponseCode: " + responseCode);
		// assert(responseCode == "Successful");

		// Extract Transit Account ID
		transitAccountId = getRespValue(response, "a:AccountId");
		System.out.println("Transit Account ID: " + transitAccountId);

		// ######################################################################
		// ##### LOAD VALUE (V3)
		// ######################################################################
		System.out.println("\n######################################################################");
		System.out.println("LoadValueV3");

		reqURL = "http://" + ABP_HOST_NAME + ACCT_MGMT_SVC;
		soapAction = ACCT_MGMT_URI + "/IServices/LoadValueV3";
		request = createReqLoadValueV3(transitAccountId, loadAmount, multiPurseType, multiPurseUsage);
		response = sendSOAPReq(request, soapAction, reqURL);
		responseCode = getRespValue(response, "ResponseCode");
		System.out.println("ResponseCode: " + responseCode);
		// assert(responseCode == "Successful");

		// ######################################################################
		// ##### POST (SOAP) TAP
		// ######################################################################
		System.out.println("\n######################################################################");
		System.out.println("PostTap (ENTRY)");

		reqURL = "http://" + ABP_HOST_NAME + TXN_MGMT_SVC;
		soapAction = TXN_MGMT_URI + "/IServices/PostTap";
		request = createReqPostTap(DEVICE_ID, stopId, routeId, zoneId, operatorId, directionId, sectorId, serviceType,
				tapPurpose, ++tapSequence, pan, tokenExpirationDate, tokenType, createNowZuluDTM(), ++transactionId);
		response = sendSOAPReq(request, soapAction, reqURL);
		responseCode = getRespValue(response, "ResponseCode");
		System.out.println("ResponseCode: " + responseCode);
		// assert(responseCode == "Successful");

		System.out.println("\n######################################################################");
		System.out.println("PostTap (EXIT)");
		stopId = "2";
		request = createReqPostTap(DEVICE_ID, stopId, routeId, zoneId, operatorId, directionId, sectorId, serviceType,
				"Exit", ++tapSequence, pan, tokenExpirationDate, tokenType, createNowZuluDTM(), ++transactionId);
		response = sendSOAPReq(request, soapAction, reqURL);
		responseCode = getRespValue(response, "ResponseCode");
		System.out.println("ResponseCode: " + responseCode);
		// assert(responseCode == "Successful");

		return transitAccountId;

	}

	@SuppressWarnings("restriction")
	public static SOAPMessage sendSOAPReq(String reqString, String soapActionURI, String reqURL) {
		SOAPMessage response = null;

		try {
			// ######################################################################
			// ##### WRITE REQUEST TO XML FILE
			// ######################################################################
			FileWriter writer = new FileWriter(FILE_PATH, APPEND_TO_FILE);
			PrintWriter fileWriter = new PrintWriter(writer);

			fileWriter.print(reqString);
			fileWriter.close();

			// ######################################################################
			// ##### CREATE CONNECTION AND SET UP REQUEST
			// ######################################################################
			SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
			SOAPConnection conn = scf.createConnection();

			// Create message
			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage msg = mf.createMessage();

			// Object for message parts
			SOAPPart sp = msg.getSOAPPart();
			StreamSource prepMsg = new StreamSource(new FileInputStream(FILE_PATH));
			sp.setContent(prepMsg);

			// Headers (of full request)
			MimeHeaders headers = msg.getMimeHeaders();
			headers.addHeader("SOAPAction", soapActionURI);

			// ######################################################################
			// ##### SAVE XML MESSAGE
			// ######################################################################
			msg.saveChanges();

			// ######################################################################
			// ##### PRINT REQUEST XML
			// ######################################################################
			// System.out.println("\nSOAP Request:\n");
			// msg.writeTo(System.out);
			// System.out.println();

			// ######################################################################
			// ##### SEND REQUEST
			// ######################################################################
			// String urlval = "http://" + ABP_HOST_NAME + reqService;
			response = conn.call(msg, reqURL);

			// ######################################################################
			// ##### PRINT RESPONSE XML
			// ######################################################################
			// System.out.println("\nXML Response\n");

			// Create transformer
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();

			// Get reply content
			Source sc = response.getSOAPPart().getContent();

			// Set output transformation
			StreamResult result = new StreamResult(System.out);
			tf.transform(sc, result);
			// System.out.println();

			// ######################################################################
			// ##### CLOSE CONNECTION
			// ######################################################################
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	/**
	 * ######################################################################
	 * getRespValue() response - SOAP Response xmlTag - Name of the XML tag for
	 * which the value will be extracted This function takes an XML response and
	 * finds the value of the tag named "xmlTag" RETURNS: The string inside the
	 * XML tag requested
	 * ######################################################################
	 */
	public static String getRespValue(@SuppressWarnings("restriction") SOAPMessage response, String xmlTag) {
		String respVal = "";

		try {
			// Parse XML Body for Tag
			@SuppressWarnings("restriction")
			SOAPBody respBody = response.getSOAPBody();

			respVal = respBody.getElementsByTagName(xmlTag).item(0).getFirstChild().getNodeValue();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return respVal;
	}

	/**
	 * ######################################################################
	 * createReqHeader() requestUID - Unique Request UID This function creates a
	 * generic SOAP Request header (to use inside SOAP Body) RETURNS: Common
	 * SOAP Request header
	 * ######################################################################
	 */
	public static String createReqHeader(String requestUID) {
		String header = "";

		// Generate Today's timestamp (UTC/GMT)
		String timestamp = createNowZuluDTM();

		// Construct Header
		header = header + "<ns1:Header xmlns:ns1=\"http://abp.cubic.com/common\">\n<ns1:Device>" + DEVICE_ID
				+ "</ns1:Device>\n<ns1:Password>password</ns1:Password>\n";
		header = header + "<ns1:RequestId>" + requestUID + "</ns1:RequestId>\n";
		header = header + "<ns1:Timestamp>" + timestamp
				+ "</ns1:Timestamp>\n<ns1:UserName>web</ns1:UserName>\n</ns1:Header>\n";

		return header;
	}

	/**
	 * ######################################################################
	 * createNowZuluDTM() This function creates a ZULU (UTC) timestamp of "now"
	 * RETURNS: ZULU (UTC) timestamp of now
	 * ######################################################################
	 */
	public static String createNowZuluDTM() {
		// Timestamp settings
		SimpleDateFormat zuluSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		zuluSDF.setTimeZone(TimeZone.getTimeZone("GMT"));
		zuluSDF.setLenient(false);

		// Generate Today's timestamp (UTC/GMT)
		String timestamp = zuluSDF.format(new Date());

		return timestamp;
	}

	// ######################################################################
	// ##### Returns LoadValueV3 Request
	// ######################################################################
	public static String createReqLoadValueV3(String transitAccountId, String loadAmount, String multiPurseType,
			String multiPurseUsage) {
		/**
		 * THIS IS THE SAMPLE XML BEING CONSTRUCTED:
		 * <SOAP-ENV:Envelope xmlns:SOAP-ENV=
		 * "http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd=
		 * "http://www.w3.org/2001/XMLSchema" xmlns:xsi=
		 * "http://www.w3.org/2001/XMLSchema-instance"> <SOAP-ENV:Body>
		 * <LoadValueV3 xmlns="http://abp.cubic.com/api/account/interface">
		 * <request> <ns1:Header xmlns:ns1="http://abp.cubic.com/common">
		 * <ns1:Device>SOATest-Auto</ns1:Device>
		 * <ns1:Password>password</ns1:Password>
		 * <ns1:RequestId>88da93e3-3001-2017-0004-939478e94997</ns1:RequestId>
		 * <ns1:Timestamp>2017-01-30T22:37:51.136Z</ns1:Timestamp>
		 * <ns1:UserName>web</ns1:UserName> </ns1:Header>
		 * <ns2:ACHPayment xmlns:ns2="http://abp.cubic.com/api/account">0
		 * </ns2:ACHPayment>
		 * <ns3:AccountId xmlns:ns3="http://abp.cubic.com/api/account">
		 * 330000000027</ns3:AccountId>
		 * <ns4:BankcardPaymentType xmlns:ns4="http://abp.cubic.com/api/account"
		 * xsi:nil="true"/> <ns5:BypassAccountStatusCheck xmlns:ns5=
		 * "http://abp.cubic.com/api/account">false
		 * </ns5:BypassAccountStatusCheck>
		 * <ns6:CardDeposit xmlns:ns6="http://abp.cubic.com/api/account">0
		 * </ns6:CardDeposit>
		 * <ns7:CardFee xmlns:ns7="http://abp.cubic.com/api/account">0
		 * </ns7:CardFee>
		 * <ns8:CashPayment xmlns:ns8="http://abp.cubic.com/api/account">177
		 * </ns8:CashPayment> <ns9:ExternalBenefitPayment xmlns:ns9=
		 * "http://abp.cubic.com/api/account">0</ns9:ExternalBenefitPayment>
		 * <ns10:FarecardPayment xmlns:ns10="http://abp.cubic.com/api/account">0
		 * </ns10:FarecardPayment>
		 * <ns11:LoadFee xmlns:ns11="http://abp.cubic.com/api/account">0
		 * </ns11:LoadFee>
		 * <ns12:ReceiptId xmlns:ns12="http://abp.cubic.com/api/account">
		 * 88da93e3-3001-2017-0004-939478e94997</ns12:ReceiptId>
		 * <ns13:ShippingFee xmlns:ns13="http://abp.cubic.com/api/account">0
		 * </ns13:ShippingFee>
		 * <ns14:ThirdPartyPayment xmlns:ns14="http://abp.cubic.com/api/account"
		 * xsi:nil="true"/> <ns15:PALPayment xmlns:ns15=
		 * "http://abp.cubic.com/api/account" xsi:nil="true"/> <ns16:LoadAmount
		 * xmlns:ns16="http://abp.cubic.com/api/account">177</ns16:LoadAmount>
		 * <ns17:AllowLimitViolation xmlns:ns17=
		 * "http://abp.cubic.com/api/account">false</ns17:AllowLimitViolation>
		 * <ns18:MultiPurseType xmlns:ns18="http://abp.cubic.com/api/account">
		 * StoredValue</ns18:MultiPurseType>
		 * <ns19:MultiPurseUsage xmlns:ns19="http://abp.cubic.com/api/account">
		 * Unrestricted</ns19:MultiPurseUsage> </request> </LoadValueV3>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */
		String req = "";
		String requestUID = UUID.randomUUID().toString();
		int i = 1; // Namespace counter
		String nameSpc = "http://abp.cubic.com/api/account";

		// ##### START OF XML REQUEST
		req = req
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n";
		req = req + "<SOAP-ENV:Body>\n<LoadValueV3 xmlns=\"" + ACCT_MGMT_URI + "\">\n<request>\n";
		req = req + createReqHeader(requestUID);
		req = req + "<ns" + ++i + ":ACHPayment xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i + ":ACHPayment>\n";
		req = req + "<ns" + ++i + ":AccountId xmlns:ns" + i + "=\"" + nameSpc + "\">" + transitAccountId + "</ns" + i
				+ ":AccountId>\n";
		req = req + "<ns" + ++i + ":BankcardPaymentType xmlns:ns" + i + "=\"" + nameSpc + "\" xsi:nil=\"true\"/>\n";
		req = req + "<ns" + ++i + ":BypassAccountStatusCheck xmlns:ns" + i + "=\"" + nameSpc + "\">false</ns" + i
				+ ":BypassAccountStatusCheck>\n";
		req = req + "<ns" + ++i + ":CardDeposit xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i + ":CardDeposit>\n";
		req = req + "<ns" + ++i + ":CardFee xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i + ":CardFee>\n";
		req = req + "<ns" + ++i + ":CashPayment xmlns:ns" + i + "=\"" + nameSpc + "\">" + loadAmount + "</ns" + i
				+ ":CashPayment>\n";
		req = req + "<ns" + ++i + ":ExternalBenefitPayment xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i
				+ ":ExternalBenefitPayment>\n";
		req = req + "<ns" + ++i + ":FarecardPayment xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i
				+ ":FarecardPayment>\n";
		req = req + "<ns" + ++i + ":LoadFee xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i + ":LoadFee>\n";
		req = req + "<ns" + ++i + ":ReceiptId xmlns:ns" + i + "=\"" + nameSpc + "\">" + requestUID + "</ns" + i
				+ ":ReceiptId>\n";
		req = req + "<ns" + ++i + ":ShippingFee xmlns:ns" + i + "=\"" + nameSpc + "\">0</ns" + i + ":ShippingFee>\n";
		req = req + "<ns" + ++i + ":ThirdPartyPayment xmlns:ns" + i + "=\"" + nameSpc + "\" xsi:nil=\"true\"/>\n";
		req = req + "<ns" + ++i + ":PALPayment xmlns:ns" + i + "=\"" + nameSpc + "\" xsi:nil=\"true\"/>\n";
		req = req + "<ns" + ++i + ":LoadAmount xmlns:ns" + i + "=\"" + nameSpc + "\">" + loadAmount + "</ns" + i
				+ ":LoadAmount>\n";
		req = req + "<ns" + ++i + ":AllowLimitViolation xmlns:ns" + i + "=\"" + nameSpc + "\">false</ns" + i
				+ ":AllowLimitViolation>\n";
		req = req + "<ns" + ++i + ":MultiPurseType xmlns:ns" + i + "=\"" + nameSpc + "\">" + multiPurseType + "</ns" + i
				+ ":MultiPurseType>\n";
		req = req + "<ns" + ++i + ":MultiPurseUsage xmlns:ns" + i + "=\"" + nameSpc + "\">" + multiPurseUsage + "</ns"
				+ i + ":MultiPurseUsage>\n";
		req = req + "</request>\n</LoadValueV3>\n</SOAP-ENV:Body>\n</SOAP-ENV:Envelope>\n";

		return req;
	}

	// ######################################################################
	// ##### Returns IssueBankcard Request
	// ######################################################################
	public static String createReqIssueBankcard(String avsData, String expirationDate, String pan,
			String requestAutoActivate) {
		/**
		 * THIS IS THE SAMPLE XML REQUEST BEING RETURNED <?xml version="1.0"
		 * encoding="UTF-8"?> <SOAP-ENV:Envelope xmlns:SOAP-ENV=
		 * "http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd=
		 * "http://www.w3.org/2001/XMLSchema" xmlns:xsi=
		 * "http://www.w3.org/2001/XMLSchema-instance"> <SOAP-ENV:Body>
		 * <IssueBankcard xmlns="http://abp.cubic.com/api/account/interface">
		 * <request> <ns1:Header xmlns:ns1="http://abp.cubic.com/common">
		 * <ns1:Device>${device}</ns1:Device>
		 * <ns1:Password>${soapHeaderPassword}</ns1:Password>
		 * <ns1:RequestId>${requestUID}</ns1:RequestId>
		 * <ns1:Timestamp>2016-12-12T18:18:10.721Z</ns1:Timestamp>
		 * <ns1:UserName>${soapHeaderUsername}</ns1:UserName> </ns1:Header>
		 * <ns2:AVSData xmlns:ns2="http://abp.cubic.com/api/account">${avsData}
		 * </ns2:AVSData>
		 * <ns3:Expiration xmlns:ns3="http://abp.cubic.com/api/account">${
		 * expirationDate}</ns3:Expiration>
		 * <ns4:PAN xmlns:ns4="http://abp.cubic.com/api/account">${pan}
		 * </ns4:PAN> <ns5:RequestAutoActivate xmlns:ns5=
		 * "http://abp.cubic.com/api/account">${requestAutoActivate}
		 * </ns5:RequestAutoActivate> </request> </IssueBankcard>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */
		String req = "";
		String requestUID = UUID.randomUUID().toString();
		int i = 1; // Namespace counter
		String nameSpc = "http://abp.cubic.com/api/account";

		// ##### START OF XML REQUEST
		req = req
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n";
		req = req + "<SOAP-ENV:Body>\n<IssueBankcard xmlns=\"" + ACCT_MGMT_URI + "\">\n<request>\n";
		req = req + createReqHeader(requestUID);
		if (avsData.isEmpty()) {
			req = req + "<ns" + ++i + ":AVSData xmlns:ns" + i + "=\"" + nameSpc + "\">" + avsData + "</ns" + i
					+ ":AVSData>\n";
		} else {
			req = req + "<ns" + ++i + ":AVSData xmlns:ns" + i + "=\"" + nameSpc + "\"/>\n";
		}

		req = req + "<ns" + ++i + ":Expiration xmlns:ns" + i + "=\"" + nameSpc + "\">" + expirationDate + "</ns" + i
				+ ":Expiration>\n";
		req = req + "<ns" + ++i + ":PAN xmlns:ns" + i + "=\"" + nameSpc + "\">" + pan + "</ns" + i + ":PAN>\n";
		req = req + "<ns" + ++i + ":RequestAutoActivate xmlns:ns" + i + "=\"" + nameSpc + "\">" + requestAutoActivate
				+ "</ns" + i + ":RequestAutoActivate>\n";
		req = req + "</request>\n</IssueBankcard>\n</SOAP-ENV:Body>\n</SOAP-ENV:Envelope>\n";

		return req;
	}

	// ######################################################################
	// ##### Returns PostTap Request
	// ######################################################################
	public static String createReqPostTap(String deviceId, String stopId, String routeId, String zoneId,
			String operatorId, String directionId, String sectorId, String serviceType, String tapPurpose,
			int tapSequence, String tokenNum, String tokenExpirationDate, String tokenType, String transactionDTM,
			int transactionId) {
		/**
		 * THIS IS THE SAMPLE XML REQUEST BEING RETURNED <?xml version="1.0"
		 * encoding="UTF-8"?> <SOAP-ENV:Envelope xmlns:SOAP-ENV=
		 * "http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd=
		 * "http://www.w3.org/2001/XMLSchema" xmlns:xsi=
		 * "http://www.w3.org/2001/XMLSchema-instance"> <SOAP-ENV:Body> <PostTap
		 * xmlns="http://abp.cubic.com/api/txn/interface"> <request> <ns1:Header
		 * xmlns:ns1="http://abp.cubic.com/common"> <ns1:Device>${device}
		 * </ns1:Device> <ns1:Password>${soapHeaderPassword}</ns1:Password>
		 * <ns1:RequestId>${requestUID}</ns1:RequestId>
		 * <ns1:Timestamp>2016-08-18T20:00:15.280Z</ns1:Timestamp>
		 * <ns1:UserName>${soapHeaderUsername}</ns1:UserName> </ns1:Header>
		 * <ns2:BusId xsi:nil="true" xmlns:ns2=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests"/>
		 * <ns3:DeviceId xmlns:ns3=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${device}</ns3:DeviceId> <ns4:FareDue xsi:nil="true" xmlns:ns4=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests"/>
		 * <ns5:Location xmlns:ns5=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${stopId},${routeId},${zoneId},${operatorId},${directionId},${
		 * sectorId},${serviceType}</ns5:Location>
		 * <ns6:RiderClassOverride xmlns:ns6=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * true</ns6:RiderClassOverride> <ns7:TapPurpose xmlns:ns7=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${tapPurpose}</ns7:TapPurpose> <ns8:TapSequence xmlns:ns8=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${tapSequence}</ns8:TapSequence> <ns9:TapStatus xmlns:ns9=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * DeviceApproved</ns9:TapStatus> <ns10:TokenClear xmlns:ns10=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${tokenNum}</ns10:TokenClear> <ns11:TokenExpiration xmlns:ns11=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${tokenExpDate}</ns11:TokenExpiration> <ns12:TokenType xmlns:ns12=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${tokenType}</ns12:TokenType> <ns13:TransactionDtm xmlns:ns13=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${timestamp}</ns13:TransactionDtm> <ns14:TransactionId xmlns:ns14=
		 * "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests">
		 * ${transactionId}</ns14:TransactionId> </request> </PostTap>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */
		String req = "";
		String requestUID = UUID.randomUUID().toString();
		int i = 1; // Namespace counter
		String nameSpc = "http://schemas.datacontract.org/2004/07/ABP.TxnWebService.DataTypes.Requests";
		String location = stopId + "," + routeId + "," + zoneId + "," + operatorId + "," + directionId + "," + sectorId
				+ "," + serviceType;

		// ##### START OF XML REQUEST
		req = req
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n";
		req = req + "<SOAP-ENV:Body>\n<PostTap xmlns=\"" + TXN_MGMT_URI + "\">\n<request>\n";
		req = req + createReqHeader(requestUID);
		req = req + "<ns" + ++i + ":BusId xsi:nil=\"true\" xmlns:ns" + i + "=\"" + nameSpc + "\"/>\n";
		req = req + "<ns" + ++i + ":DeviceId xmlns:ns" + i + "=\"" + nameSpc + "\">" + deviceId + "</ns" + i
				+ ":DeviceId>\n";
		req = req + "<ns" + ++i + ":FareDue xsi:nil=\"true\" xmlns:ns" + i + "=\"" + nameSpc + "\"/>\n";
		req = req + "<ns" + ++i + ":Location xmlns:ns" + i + "=\"" + nameSpc + "\">" + location + "</ns" + i
				+ ":Location>\n";
		req = req + "<ns" + ++i + ":RiderClassOverride xmlns:ns" + i + "=\"" + nameSpc + "\">true</ns" + i
				+ ":RiderClassOverride>\n";
		req = req + "<ns" + ++i + ":TapPurpose xmlns:ns" + i + "=\"" + nameSpc + "\">" + tapPurpose + "</ns" + i
				+ ":TapPurpose>\n";
		req = req + "<ns" + ++i + ":TapSequence xmlns:ns" + i + "=\"" + nameSpc + "\">" + tapSequence + "</ns" + i
				+ ":TapSequence>\n";
		req = req + "<ns" + ++i + ":TapStatus xmlns:ns" + i + "=\"" + nameSpc + "\">DeviceApproved</ns" + i
				+ ":TapStatus>\n";
		req = req + "<ns" + ++i + ":TokenClear xmlns:ns" + i + "=\"" + nameSpc + "\">" + tokenNum + "</ns" + i
				+ ":TokenClear>\n";
		req = req + "<ns" + ++i + ":TokenExpiration xmlns:ns" + i + "=\"" + nameSpc + "\">" + tokenExpirationDate
				+ "</ns" + i + ":TokenExpiration>\n";
		req = req + "<ns" + ++i + ":TokenType xmlns:ns" + i + "=\"" + nameSpc + "\">" + tokenType + "</ns" + i
				+ ":TokenType>\n";
		req = req + "<ns" + ++i + ":TransactionDtm xmlns:ns" + i + "=\"" + nameSpc + "\">" + transactionDTM + "</ns" + i
				+ ":TransactionDtm>\n";
		req = req + "<ns" + ++i + ":TransactionId xmlns:ns" + i + "=\"" + nameSpc + "\">" + transactionId + "</ns" + i
				+ ":TransactionId>\n";
		req = req + "</request>\n</PostTap>\n</SOAP-ENV:Body>\n</SOAP-ENV:Envelope>\n";

		return req;
	}

}
