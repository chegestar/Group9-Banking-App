package com.sundevil.service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V1CertificateGenerator;

public class GenerateCertificate {

	public java.security.cert.X509Certificate[] generateCertificate(
			KeyStore.PrivateKeyEntry SBSpkEntryRetrieved, KeyPair keyPairUser) {
		java.security.cert.X509Certificate[] certArr = null;
		try {
			Date startDate = new Date(2013, 10, 29); // time from which
														// certificate is valid
			Date expiryDate = new Date(27 - 10 - 2013); // time after which
														// certificate is not
														// valid

			BigInteger serialNumber = BigInteger.valueOf(1); // serial number
																// for
																// certificate

			X509V1CertificateGenerator certGen = new X509V1CertificateGenerator();
			X500Principal dnName = new X500Principal(
					"CN=SBS Certification Authority");

			certGen.setSerialNumber(serialNumber);
			certGen.setIssuerDN(dnName); // Bank name
			certGen.setNotBefore(startDate);
			certGen.setNotAfter(expiryDate);
			certGen.setSubjectDN(dnName); // user name
			certGen.setPublicKey(keyPairUser.getPublic());// users public key
			certGen.setSignatureAlgorithm("SHA1withDSA");

			Security.addProvider(new BouncyCastleProvider());

			@SuppressWarnings("deprecation")
			java.security.cert.X509Certificate cert = certGen.generate(
					SBSpkEntryRetrieved.getPrivateKey(), "BC"); // Banks private
																// key

			certArr = new java.security.cert.X509Certificate[] { cert };

			/*
			 * for(int i=0;i<certArr.length;i++){
			 * System.out.println(certArr[i].toString());
			 * System.out.println("Initial Signature : "
			 * +certArr[i].getSignature());//getSignature gives users public key
			 * }
			 */

			System.out.println(cert.toString());

			System.out.println("done");

		} catch (Exception e) {
			// TODO: handle exception

		}

		return certArr;
	}

	public String signMessage(String message, PrivateKey privateKey) {
		String signarureForMessage = null;

		try {

			// Verify the message that is sent
			Signature sig = Signature.getInstance("SHA1withDSA");
			sig.initSign(privateKey);
			sig.update(message.getBytes());
			byte[] signature = sig.sign();
			System.out.println(sig.getProvider().getInfo());
			System.out.println("\nSignature:");
			System.out.println(new String(signature, "UTF8"));
			//

			signarureForMessage = new String(signature);

			// verify the signature with the public key

		} catch (SignatureException se) {
			System.out.println("Signature failed");
		} catch (Exception e) {
			System.out.println("Other Exception");
			// return "Other Exception";
		}
		return ("Message : " + message);
	}

	public boolean verifySignature(PublicKey senderPublicKey, String message,
			String signature) {
		// verify the signature with the public key

		try {
			System.out.println("\nStart signature verification : " + signature);
			Signature sig = Signature.getInstance("SHA1withDSA");
			sig.initVerify(senderPublicKey);
			sig.update(message.getBytes());

			if (sig.verify(signature.getBytes())) {
				System.out.println("Signature verified");
				return true;
			} else
				System.out.println("Signature failed");
			return false;
		} catch (Exception e) {
			System.out.println("Other Exception : " + e);
			// return "Other Exception";
			return false;
		}

	}

	public void verifyUserSignatureUsingUserPublicKey(KeyPair keyPairUser) {

		try {
			System.out.println("User Public key : " + keyPairUser.getPublic());

			// Verify the message that is sent
			Signature sig = Signature.getInstance("SHA1withDSA");
			sig.initSign(keyPairUser.getPrivate());
			sig.update("Hello I am a genuine user".getBytes());
			byte[] signature = sig.sign();
			System.out.println(sig.getProvider().getInfo());
			System.out.println("\nSignature:");
			System.out.println(new String(signature, "UTF8"));
			//
			// verify the signature with the public key
			System.out.println("\nStart signature verification");
			sig.initVerify(keyPairUser.getPublic());
			sig.update("Hello I am a genuine user".getBytes());

			if (sig.verify(signature)) {
				System.out.println("Signature verified");
			} else
				System.out.println("Signature failed");
		} catch (SignatureException se) {
			System.out.println("Signature failed");
		} catch (Exception e) {
			System.out.println("Other Exception");
			// return "Other Exception";
		}
	}

	public boolean verifyUserCertificateUsingBankPublicKey(
			PublicKey sbsPublicKey, X509Certificate senderCertRetrieved) {

		try {
			senderCertRetrieved.verify(sbsPublicKey); // Banks public key
			System.out.println("Certificate Genuine");
			return true;

		} catch (CertificateException e) {

			return false;

		} catch (Exception e) {
			System.out
					.println("Other Exception in verifyUserCertificateUsingBankPublicKey : "
							+ e);
		}
		return false;
	}

	public void verifyCertificate(KeyPair keyPairBank, KeyPair keyPairUser,
			X509Certificate cert) {

		try {
			cert.verify(keyPairBank.getPublic()); // Banks public key
			System.out.println("Certificate Genuine");

			verifyUserSignatureUsingUserPublicKey(keyPairUser);

		} catch (CertificateException e) {
			System.out.println("Certificate not genuine");
		} catch (Exception e) {
			System.out.println("Other Exception in verifyCertificate");
		}

	}

}
