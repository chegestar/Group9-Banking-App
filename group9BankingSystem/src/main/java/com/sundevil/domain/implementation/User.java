/**
 * 
 */
package com.sundevil.domain.implementation;

import com.sundevil.domain.IUser;

/**
 * @author satyaswaroop
 * 
 */
public class User implements IUser {
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	private String userID;
	private String lastName;
	private String firstName;
	private String ssn;
	private String emailId;
	private String address;
	private String zipCode;
	/**
	 * @return the userID
	 */
	@Override
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	@Override
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the lastName
	 */
	@Override
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the firstName
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the ssn
	 */
	@Override
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	@Override
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the emailId
	 */
	@Override
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	@Override
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the address
	 */
	@Override
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the zipCode
	 */
	@Override
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	@Override
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the gender
	 */
	@Override
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the contactNumber
	 */
	@Override
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	@Override
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @return the userName
	 */
	@Override
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the roleId
	 */
	@Override
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	@Override
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	private String gender;
	private String contactNumber;
	private String userName;
	private String password;
	
	
	private String roleId;
	private String department;
	/**
	 * @return the department
	 */
	@Override
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	@Override
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the salary
	 */
	@Override
	public long getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	@Override
	public void setSalary(long salary) {
		this.salary = salary;
	}
	private long salary;

}
