package com.sundevil.domain.implementation;

public class CertificateFormBean {

	

	
	private String serialNumber;
	private String issuerName;
	private String validFrom;
	private String validTo;
	private String userPublicKey;
	private String version;
	private String caSignature;
	
	
	private String destinationUserName;
	private String message;
	private String destinationEmailId;
	private String senderUserName;
	private String statusMessage;
	private String signature;
	
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getSenderUserName() {
		return senderUserName;
	}
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
	public String getDestinationEmailId() {
		return destinationEmailId;
	}
	public void setDestinationEmailId(String destinationEmailId) {
		this.destinationEmailId = destinationEmailId;
	}
	public String getDestinationUserName() {
		return destinationUserName;
	}
	public void setDestinationUserName(String destinationUserName) {
		this.destinationUserName = destinationUserName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getValidTo() {
		return validTo;
	}
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
	public String getUserPublicKey() {
		return userPublicKey;
	}
	public void setUserPublicKey(String userPublicKey) {
		this.userPublicKey = userPublicKey;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCaSignature() {
		return caSignature;
	}
	public void setCaSignature(String caSignature) {
		this.caSignature = caSignature;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}	
}
