/**
 * 
 */
package com.sundevil.service.usermanagement.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sundevil.db.usermanagement.IDBConnectionUserManager;
import com.sundevil.domain.IUser;
import com.sundevil.domain.factory.IUserFactory;
import com.sundevil.email.EmailNotificationSender;
import com.sundevil.service.RandomNumberGenerator;
import com.sundevil.service.usermanagement.IUserManager;

/**
 * @author satyaswaroop
 * 
 */
@Service
public class UserManager implements IUserManager {
	@Autowired
	IDBConnectionUserManager dbconnectionuserManager;

	@Autowired
	IUserFactory fact;
	@Autowired
	EmailNotificationSender emailNotify;

	@Override
	public boolean addUser(final IUser user) {
		RandomNumberGenerator rnd = new RandomNumberGenerator();
		final String password = rnd.generatePassword();
		new Thread(new Runnable() {

			@Override
			public void run() {
		emailNotify.sendNotificationEmail(user.getEmailId(), "IMPORTANT", "\n Please use this password once logged in \n PASSWORD: \t "+password);
			}
		}).start();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		dbconnectionuserManager.addUser(user);
		return true;

	}

	@Override
	public String getRole(String user) {
		return dbconnectionuserManager.getRole(user);
		
	}

	@Override
	public String getDepartment(String user) {
		return dbconnectionuserManager.getDepartment(user);
	}

	@Override
	public List<String> getPendingUsers() {
		return dbconnectionuserManager.getPendingUsers();
	}

	@Override
	public boolean assignDepartment(String userName, String department_name,
			long salary) {
		dbconnectionuserManager.assignDepartment(userName, department_name,
				salary);
		return true;

	}

	@Override
	public void approveUser(String username) {
		dbconnectionuserManager.approveUser(username);
		final IUser user = dbconnectionuserManager.getUser(username);
		new Thread(new Runnable() {

			@Override
			public void run() {
				emailNotify.sendNotificationEmail(user.getEmailId(),
						"ACCOUNT APPROVED",
						"Please login into your account and change password asap");

			}
		}).start();

	}

	@Override
	public void deleteUser(String username) throws Exception {
		final IUser user = dbconnectionuserManager.getUser(username);
		dbconnectionuserManager.deleteUser(username);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				emailNotify.sendNotificationEmail(user.getEmailId(),
						"ACCOUNT DELETED",
						"You no longer have access to this website \n Nice to have you\n");

			}
		}).start();
	}

	@Override
	public List<IUser> getEmployees(String username) {
		List<IUser> users = dbconnectionuserManager.getAllEmployees(username);
		Iterator<IUser> it = users.iterator();
		while (it.hasNext()) {
		    if (it.next().getRoleId().equals("ROLE_MANAGER")) {
		        it.remove();
		        // If you know it's unique, you could `break;` here
		    }
		}
		return users;

	}

	@Override
	public IUser getInternalUser(String username, String managerName) {
		IUser ret = null;
		String dept1 = dbconnectionuserManager.getDepartment(username);
		String dept2 = dbconnectionuserManager.getDepartment(managerName);
		if (dept1.equals(dept2)) {
			ret = dbconnectionuserManager.getUser(username);
			ret.setDepartment(dept1);
		}

		return ret;
	}

	@Override
	public boolean setRole(String username, String managerName, String role) {
		boolean ret = false;
		String dept1 = dbconnectionuserManager.getDepartment(username);
		String dept2 = dbconnectionuserManager.getDepartment(managerName);
		if (dept1.equals(dept2)) {
			ret = dbconnectionuserManager.setRole(username, role);
		}
		return ret;
	}

	@Override
	public boolean removeEmployee(String username, String name) {
		boolean ret = false;
		String dept1 = dbconnectionuserManager.getDepartment(username);
		String dept2 = dbconnectionuserManager.getDepartment(name);
		if (dept1.equals(dept2)) {
			ret = dbconnectionuserManager.removeUser(username);
		}
		return ret;
	}
	
	@Override
	public boolean removeManager(String username) {
		boolean ret = false;
		String role = dbconnectionuserManager.getUser(username).getRoleId();
		if (role.equals("ROLE_MANAGER")) {
			ret = dbconnectionuserManager.removeUser(username);
		}
		return ret;
	}
	
	@Override
	public List<IUser> getUnassignedEmployees() {
		List<IUser> users = dbconnectionuserManager.getUnassignedEmployees();
		return users;
	}

	@Override
	public boolean setDepartment(String username, String department) {
		if(dbconnectionuserManager.getDepartment(username).equals("DEPT_UNALLOC"))
		return dbconnectionuserManager.setDepartment(username,department);
		else
			return false;
	}

	@Override
	public List<IUser> getManagers() {
		List<IUser> users = dbconnectionuserManager.getManagers();
		return  users;
	}

	@Override
	public List<IUser> getAllUsers() {
		return dbconnectionuserManager.getAllUsers();
	}

	@Override
	public void resetPassword(String username) {
		RandomNumberGenerator rnd = new RandomNumberGenerator();
		final String pass = rnd.generatePassword();
		final IUser user = dbconnectionuserManager.getUser(username);
		new Thread(new Runnable() {

			@Override
			public void run() {
				emailNotify.sendNotificationEmail(user.getEmailId(),
						"PASSWORD RESET",
						"PASSWORD: \t"+pass);

			}
		}).start();
		String hashedPassword = (new BCryptPasswordEncoder()).encode(pass);
		
		dbconnectionuserManager.updatePassword(username, hashedPassword);
	}

	@Override
	public boolean checkLoginOTP(String name, String parameter) {
		return dbconnectionuserManager.checkLoginOTP(name,parameter);
	}
	
	@Override
	public boolean saveOTP(String name) {
		RandomNumberGenerator rnd = new RandomNumberGenerator();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 
		   Date date = new Date();
		   final String stdate = (dateFormat.format(date));
		final String pass = rnd.generatePassword();
		final IUser user = dbconnectionuserManager.getUser(name);
		new Thread(new Runnable() {

			@Override
			public void run() {
				emailNotify.sendNotificationEmail(user.getEmailId(),
						"ONE TIME PASSWORD FOR LOGIN AT "+stdate,
						"PASSWORD: \t"+pass);

			}
		}).start();
		String hashedPassword = (new BCryptPasswordEncoder()).encode(pass);
		return dbconnectionuserManager.saveOTP(name, hashedPassword);
	}

	@Override
	public boolean chkOnetimeLogin(String user) {
		
		return dbconnectionuserManager.checkOTPValidity(user);
	}
	
	
	
	
}
