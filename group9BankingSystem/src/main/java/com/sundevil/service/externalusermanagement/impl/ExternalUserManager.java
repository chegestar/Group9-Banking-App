package com.sundevil.service.externalusermanagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sundevil.db.externalusermanagement.IDBConnectionExternalUserManager;
import com.sundevil.domain.IExternalUser;
import com.sundevil.email.EmailNotificationSender;
import com.sundevil.service.RandomNumberGenerator;
import com.sundevil.service.externalusermanagement.IExternalUserManager;

/**
 * @author priyankarupani
 *
 */
@Service
public class ExternalUserManager implements IExternalUserManager {

	@Autowired
	IDBConnectionExternalUserManager dbconnectionexternaluserManager;
	
	@Autowired
	EmailNotificationSender emailNotify;
	
	@Override
	public boolean externaluser(final IExternalUser externalUser) {
		RandomNumberGenerator rnd = new RandomNumberGenerator();
		final String password = rnd.generatePassword();
		new Thread(new Runnable() {
			@Override
			public void run() {
		emailNotify.sendNotificationEmail(externalUser.getExEmailId(), "IMPORTANT", "Please change the password as early as possible \n Password:\t"+password);
		}
	}).start();
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String hashedPassword = passwordEncoder.encode(password);
	    externalUser.setExPassword(hashedPassword);
	    dbconnectionexternaluserManager.externaluser(externalUser);
		return true;
	}

}
