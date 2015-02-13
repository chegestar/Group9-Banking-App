/**
 * 
 */
package com.sundevil.service.changepassword.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sundevil.db.usermanagement.IDBConnectionUserManager;
import com.sundevil.service.changepassword.IChangePasswordManager;

/**
 * @author satyaswaroop
 *
 */
@Service
public class ChangePasswordManager implements IChangePasswordManager{

	@Autowired
	IDBConnectionUserManager dbuserManger;
	
	@Override
	public boolean updatePassword(String username, String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return dbuserManger.updatePassword(username, hashedPassword);
	}

	@Override
	public boolean validatePassword(String username, String password) {
		return dbuserManger.validatePassword(username, password);
	}
	
	

}
