/**
 * 
 */
package com.sundevil.domain;


/**
 * @author satyaswaroop
 *
 */
public interface IUser {

	public abstract void setRoleId(String roleId);

	public abstract String getRoleId();

	public abstract void setPassword(String password);

	public abstract String getPassword();

	public abstract void setUserName(String userName);

	public abstract String getUserName();

	public abstract void setContactNumber(String contactNumber);

	public abstract String getContactNumber();

	public abstract void setGender(String gender);

	public abstract String getGender();

	public abstract void setZipCode(String zipCode);

	public abstract String getZipCode();

	public abstract void setAddress(String address);

	public abstract String getAddress();

	public abstract void setEmailId(String emailId);

	public abstract String getEmailId();

	public abstract void setSsn(String ssn);

	public abstract String getSsn();

	public abstract void setFirstName(String firstName);

	public abstract String getFirstName();

	public abstract void setLastName(String lastName);

	public abstract String getLastName();

	public abstract void setUserID(String userID);

	public abstract String getUserID();

	public abstract void setSalary(long salary);

	public abstract long getSalary();

	public abstract void setDepartment(String department);

	public abstract String getDepartment();
	
	

}
