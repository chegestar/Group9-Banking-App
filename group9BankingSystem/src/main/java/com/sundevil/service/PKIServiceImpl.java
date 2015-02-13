package com.sundevil.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sundevil.db.usermanagement.IDBConnectionUserManager;
import com.sundevil.domain.IUser;
import com.sundevil.domain.implementation.CertificateFormBean;
import com.sundevil.email.EmailNotificationSender;






@Service
public class PKIServiceImpl implements PKIService {
	@Autowired
	IDBConnectionUserManager dbconnectionuserManager;
	
	@Autowired
	EmailNotificationSender sender;
	
	@Override
	@Transactional
	public com.sundevil.domain.implementation.CertificateFormBean generateCertificate(String userName) throws Exception{
		CertificateFormBean certificateFormBean = null;
		String userId = userName;
		try {
			KeyStore ks = loadKeyStore();
			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
			boolean isInKeyStore = isUserCertificateInKeyStore(userId);
			if(isInKeyStore){
				X509Certificate certRetrieved = (X509Certificate)ks.getCertificate(userId+"Certificate");
				KeyStore.PrivateKeyEntry pkEntryRetrieved =  (PrivateKeyEntry) ks.getEntry(userId+"KeyEntry", passwordProtection);
				PrivateKey userPrivateKeyRetrieved =  pkEntryRetrieved.getPrivateKey();
				certificateFormBean = new CertificateFormBean();
				certificateFormBean.setSerialNumber(certRetrieved.getSerialNumber().toString());
				certificateFormBean.setIssuerName(certRetrieved.getIssuerDN().toString());
				certificateFormBean.setValidFrom("09/02/2013 00:00:00 MST");
				certificateFormBean.setValidTo("09/01/2015 23:59:59 MST");
				certificateFormBean.setUserPublicKey(certRetrieved.getPublicKey().toString());
				certificateFormBean.setVersion(new Integer(certRetrieved.getVersion()).toString());
				certificateFormBean.setCaSignature(certRetrieved.getSignature().toString());
			}
			else{
				GenerateCertificate generateCertificate = new GenerateCertificate();
				SecureRandom random = null;
				random = SecureRandom.getInstance("SHA1PRNG", "SUN");
				KeyPairGenerator keyGen = null;
				keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
				keyGen.initialize(1024, random);
				KeyPair keyPairUser = keyGen.generateKeyPair();
				KeyStore.PrivateKeyEntry SBSKeyEntryRetrieved =  (PrivateKeyEntry) ks.getEntry("SBSKeyEntry", passwordProtection);
				X509Certificate[] certArr = generateCertificate.generateCertificate(SBSKeyEntryRetrieved, keyPairUser);
				KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(keyPairUser.getPrivate(),certArr);
			    ks.setEntry(userId+"KeyEntry", privateKeyEntry, passwordProtection);
				for (int i = 0; i < certArr.length; i++) { 
					 X509Certificate c 	= (X509Certificate) certArr[i];
					 ks.setCertificateEntry(userId + "Certificate" ,c); 
				}
				setValuesInKeystore(ks);
				certificateFormBean = new CertificateFormBean();
				certificateFormBean.setSerialNumber(certArr[0].getSerialNumber().toString());
				certificateFormBean.setIssuerName(certArr[0].getIssuerDN().toString());
				certificateFormBean.setValidFrom("09/02/2013 00:00:00 MST");
				certificateFormBean.setValidTo("09/01/2015 23:59:59 MST");
				certificateFormBean.setUserPublicKey(certArr[0].getPublicKey().toString());
				certificateFormBean.setVersion(new Integer(certArr[0].getVersion()).toString());
				certificateFormBean.setCaSignature(certArr[0].getSignature().toString());
			}
		} catch (Exception e) {
			System.out.println("In else exception : "+e );// TODO: handle exception
		}
		
		
		return certificateFormBean;
	}
	
	@Override
	@Transactional
	public boolean sendCertificate(String destinationUserName, String message, String emailId,final String userName){
		
		try
		{
			//String userId = dbconnectionuserManager.getUser(userName).getUserID();
			final IUser userDTO = dbconnectionuserManager.getUser(destinationUserName);
			if(userDTO != null){
				KeyStore ks =loadKeyStore();
				KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
				KeyStore.PrivateKeyEntry pkEntryRetrieved =  (PrivateKeyEntry) ks.getEntry(userName+"KeyEntry", passwordProtection);
				final String signedMessage = new GenerateCertificate().signMessage(message, pkEntryRetrieved.getPrivateKey()); 
				new Thread( new Runnable() {
					
					@Override
					public void run() {
						sender.sendNotificationEmail(userDTO.getEmailId(), signedMessage, "Sender of payment"+userName);
						
					}
				}).start();
				
				KeyStore verifks = loadVerifKeyStore();
				if(verifks.getCertificate(userName+"to"+userDTO.getUserID()+"Certificate")==null ){ 
					java.security.cert.Certificate certRetrieved = ks.getCertificate(userName+"Certificate");
					verifks.setCertificateEntry(userName+"to"+userDTO.getUserID()+"Certificate", certRetrieved);
					setValuesInVerifKeystore(verifks);
				 }
				return true;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	@Transactional
	public boolean verifySignature(String senderUserName, String message, String signature, String userName){
		Boolean isVerified = false;
		try{
			//IUser senderUserDTO = dbconnectionuserManager.getUser(senderUserName);
			
				KeyStore ks = loadKeyStore();
				X509Certificate senderCertRetrieved = (X509Certificate)ks.getCertificate(senderUserName+"Certificate"); //hardcoded still left
				PublicKey senderPublicKey = senderCertRetrieved.getPublicKey();
				
				isVerified = new GenerateCertificate().verifySignature(senderPublicKey, message, signature);
			
			
		}
		catch(Exception e){
			System.out.println("Exception in verifySignature : "+e);
		}
		return isVerified;
		
		
	}
	@Override
	@Transactional
	public CertificateFormBean obtainCertificateForVerification(String senderUserName, String userName){
		CertificateFormBean certificateFormBean = null;
		
		try{
			String userId = dbconnectionuserManager.getUser(userName).getUserID();
			//IUser senderUserDTO = dbconnectionuserManager.getUser(senderUserName);
			KeyStore verifks = loadVerifKeyStore();
			//if(senderUserDTO != null){
				if(verifks.getCertificate(senderUserName+"to"+userId+"Certificate")!=null ){ //hardcoded still left
					
					X509Certificate certRetrieved = (X509Certificate)verifks.getCertificate(senderUserName+"to"+userId+"Certificate"); //hardcoded still left
					certificateFormBean = new CertificateFormBean();
					certificateFormBean.setSerialNumber(certRetrieved.getSerialNumber().toString());
					certificateFormBean.setIssuerName(certRetrieved.getIssuerDN().toString());
					certificateFormBean.setValidFrom("09/02/2013 00:00:00 MST");
					certificateFormBean.setValidTo("09/01/2015 23:59:59 MST");
					certificateFormBean.setUserPublicKey(certRetrieved.getPublicKey().toString());
					certificateFormBean.setVersion(new Integer(certRetrieved.getVersion()).toString());
					certificateFormBean.setCaSignature(certRetrieved.getSignature().toString());
				 }
			
			
		}
		catch(Exception e){
			System.out.println("Exception in obtainCertificateForVerification : "+e);
		}
		System.out.println("certificate form bean : "+certificateFormBean);
		return certificateFormBean;
	}
	
	
	@Override
	@Transactional
	public boolean verifyCertificate(String senderName, String userName){
		boolean isVerified = false;
		try{
			String userId =dbconnectionuserManager.getUser(userName).getUserID();
			if(senderName != null){
				KeyStore ks = loadKeyStore();
				X509Certificate sbsCertRetrieved = (X509Certificate)ks.getCertificate("SBSCertificate");
				PublicKey sbsPublicKey = sbsCertRetrieved.getPublicKey();
				
				KeyStore verifks = loadVerifKeyStore();
				X509Certificate senderCertRetrieved = (X509Certificate)verifks.getCertificate(senderName+"to"+userId+"Certificate"); 
				isVerified = new GenerateCertificate().verifyUserCertificateUsingBankPublicKey(sbsPublicKey, senderCertRetrieved);
			}
			
		}
		catch(Exception e){
			System.out.println("Exception in obtainCertificateForVerification : "+e);
		}
		return isVerified;
	}
	
	
	public void setValuesInKeystore(KeyStore ks){
		 java.io.FileOutputStream fos = null; 
		 try { 
			 fos = new java.io.FileOutputStream("C:/keystores/sbs.keystore"); 
		 ks.store(fos,"parsam".toCharArray());
		 
		
		 } 
		 
		 catch (Exception e) {
		// TODO: handle exception
	}
			return;
	}
	public void setValuesInVerifKeystore(KeyStore ks){
		 java.io.FileOutputStream fos = null; 
		 try { 
			 fos = new java.io.FileOutputStream("C:/keystores/verification.keystore"); 
		 ks.store(fos,"parsam".toCharArray());
		 
		
		 } 
		 
		 catch (Exception e) {
		// TODO: handle exception
	}
			return;
	}

	
	public boolean isUserCertificateInKeyStore(String userId){
		try{
		KeyStore ks = loadKeyStore();
		KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
	    if(ks.getCertificate(userId+"Certificate")!=null && ks.getEntry(userId+"KeyEntry", passwordProtection)!= null){
			 return true;
		 }
		
		
		}
		catch (Exception e) {
				System.out.println("In isUserCertificateInKeyStore exception");// TODO: handle exception
			}
		return false;
	}
	
	
	public KeyStore loadKeyStore(){
		 KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");
		
		 
		 java.io.FileInputStream fis = null; try { fis = new
		 java.io.FileInputStream("C:/keystores/sbs.keystore"); ks.load(fis,
		 "parsam".toCharArray()); }  
		 
		 finally {
			 if (fis != null) {
		 }
		 fis.close(); }	
	}catch(Exception e){
		System.out.println("In loadKeyStore exception : "+e);
	}
		return ks;
	}
	
	public KeyStore loadVerifKeyStore(){
		 KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");
		
		 
		 java.io.FileInputStream fis = null; try { fis = new
		 java.io.FileInputStream("C:/keystores/verification.keystore"); ks.load(fis,
		 "parsam".toCharArray()); }  
		 
		 finally {
			 if (fis != null) {
		 }
		 fis.close(); }	
	}catch(Exception e){
		System.out.println("In loadKeyStore exception : "+e);
	}
		return ks;
	}
	
	
}
