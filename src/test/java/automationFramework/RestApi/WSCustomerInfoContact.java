package automationFramework.RestApi;

import java.util.Date;
import java.util.List;

public class WSCustomerInfoContact {

	private String contactId;
	private String contactType;
	private String credentialStatus;
	private WSName name;
	private WSAddressExt address;
	private List<WSPhone> phone;
	private String email;
	private Date dateOfBirth;
	private WSPersonalIdentifier personalIdentifierInfo;
	private String username;
	private String password;
	private String pin;
	private List<WSSecurityQA> securityQAs;

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public String getCredentialStatus() {
		return credentialStatus;
	}

	public void setCredentialStatus(String credentialStatus) {
		this.credentialStatus = credentialStatus;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public WSName getName() {
		return name;
	}

	public void setName(WSName name) {
		this.name = name;
	}

	public WSAddressExt getAddress() {
		return address;
	}

	public void setAddress(WSAddressExt address) {
		this.address = address;
	}

	public List<WSPhone> getPhone() {
		return phone;
	}

	public void setPhone(List<WSPhone> phone) {
		this.phone = phone;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public List<WSSecurityQA> getSecurityQAs() {
		return securityQAs;
	}

	public void setSecurityQAs(List<WSSecurityQA> securityQAs) {
		this.securityQAs = securityQAs;
	}

	public WSPersonalIdentifier getPersonalIdentifierInfo() {
		return personalIdentifierInfo;
	}

	public void setPersonalIdentifierInfo(WSPersonalIdentifier personalIdentifierInfo) {
		this.personalIdentifierInfo = personalIdentifierInfo;
	}

}
