/**
 * 
 */
package com.sundevil.db.usermanagement;

import java.util.List;

import com.sundevil.domain.IUser;

/**
 * @author satyaswaroop
 *
 */
public interface IDBConnectionUserManager {

	public abstract boolean addUser(IUser user);

	public abstract boolean chkDuplicateUserName(String userName);
	
	public abstract String getDepartment(String userName);
	
	
	public abstract List<String> getPendingUsers();

	public abstract void approveUser(String username);

	public abstract void deleteUser(String username) throws Exception;

	
	boolean assignDepartment(String userName, String department_name, long l);

	String getEmailId(String username);

	IUser getUser(String username);

	

	List<IUser> getAllEmployees(String username);

	public abstract boolean chkExUserName(String userName);

	boolean setRole(String userName, String role);

	public abstract boolean removeUser(String username);

	List<IUser> getUnassignedEmployees();

	public abstract boolean setDepartment(String username, String department);

	public abstract List<IUser> getManagers();

	boolean validatePassword(String name, String pass);

	boolean updatePassword(String username, String password);

	public abstract boolean chkEmail(String emailaddress);

	public abstract boolean chkSSN(String ssn);

	public abstract boolean chkContactNumber(String contactNumber);

	public abstract List<IUser> getAllUsers();

	public abstract boolean checkLoginOTP(String name, String parameter);

	boolean saveOTP(String name, String parameter);

	public abstract String getRole(String user);

	boolean checkOTPValidity(String name);

	IUser getUserDTOFor(String username, String emailId);

	boolean checkValidDepartmentName(String departmentName);

}
