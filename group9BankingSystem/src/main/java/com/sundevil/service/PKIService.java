package com.sundevil.service;





public interface PKIService {
	
	public com.sundevil.domain.implementation.CertificateFormBean generateCertificate(String userName) throws Exception;
	//public CertificateFormBean signCertificate(CertificateFormBean certificateFormBean) throws Exception;
	public boolean sendCertificate(String destinationUserName, String message, String emailId, String userName);
	public com.sundevil.domain.implementation.CertificateFormBean obtainCertificateForVerification(String senderUserName, String userName);
	public boolean verifyCertificate(String senderName, String userName);
	 boolean verifySignature(String senderUserName, String message, String signature, String userName);
}
