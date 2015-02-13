package com.sundevil.security;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Cryptographer {

	private static final Logger logger = LoggerFactory
			.getLogger(Cryptographer.class);
	
	
	private Cipher aes;
	
	public String encrpyt(String data)
	{
		String passphrase = "protecting the SSN";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA");
		
		digest.update(passphrase.getBytes());
		SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		aes.init(Cipher.ENCRYPT_MODE, key);
		String ciphertext = aes.doFinal(data.getBytes()).toString();
		return ciphertext;
		}catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			logger.error("encryption alg error "+e); 
			return null;
		} catch (NoSuchPaddingException e) {
			logger.error("encryption padding error "+e);
			return null;
		} catch (InvalidKeyException e) {
			logger.error("encryption key error "+e);
			return null;
		} catch (IllegalBlockSizeException e) {
			logger.error("encryption block error "+e);
			return null;
			
		} catch (BadPaddingException e) {
			logger.error("encryption bad padding error "+e);
			return null;
		}
	}
	
	public String decrypt(String data)
	{
		try {
		String passphrase = "protecting the SSN";
		MessageDigest digest = MessageDigest.getInstance("SHA");
		
		digest.update(passphrase.getBytes());
		SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		aes.init(Cipher.DECRYPT_MODE, key);
		String ciphertext = aes.doFinal(data.getBytes()).toString();
		return ciphertext;
	}
	catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		logger.error("encryption alg error "+e); 
		return null;
	} catch (NoSuchPaddingException e) {
		logger.error("encryption padding error "+e);
		return null;
	} catch (InvalidKeyException e) {
		logger.error("encryption key error "+e);
		return null;
	} catch (IllegalBlockSizeException e) {
		logger.error("encryption block error "+e);
		return null;
		
	} catch (BadPaddingException e) {
		logger.error("encryption bad padding error "+e);
		return null;
	}
	}
}
