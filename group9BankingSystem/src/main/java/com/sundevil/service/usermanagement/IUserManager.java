/**
 * 
 */
package com.sundevil.service.usermanagement;

import java.util.List;

import com.sundevil.domain.IUser;


/**
 * @author satyaswaroop
 *
 */
public interface IUserManager {

	public abstract boolean addUser(IUser user);

	public abstract String getRole(String user);

	public abstract String getDepartment(String user);
	
	public abstract List<String> getPendingUsers();

	public abstract void approveUser(String username);

	public abstract void deleteUser(String username) throws Exception;

	boolean assignDepartment(String userName, String department_name,
			long salary);

	public List<IUser> getEmployees(String username);

	

	IUser getInternalUser(String username, String managerName);

	boolean setRole(String username, String managerName, String role);

	public abstract boolean removeEmployee(String username, String name);

	List<IUser> getUnassignedEmployees();

	public abstract boolean setDepartment(String parameter, String parameter2);

	public abstract List<IUser> getManagers();

	boolean removeManager(String username);

	public abstract List<IUser> getAllUsers();

	public abstract void resetPassword(String username);

	public abstract boolean checkLoginOTP(String name, String parameter);

	

	boolean saveOTP(String name);

	public abstract boolean chkOnetimeLogin(String user);


}
