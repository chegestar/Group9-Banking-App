/**
 * 
 */
package com.sundevil.domain.backing;

/**
 * @author satyaswaroop
 *
 */
public class ChangePasswordBean {
	String oldPass;
	String newPass;
	String confirmPass;
	/**
	 * @return the oldPass
	 */
	public String getOldPass() {
		return oldPass;
	}
	/**
	 * @param oldPass the oldPass to set
	 */
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	/**
	 * @return the newPass
	 */
	public String getNewPass() {
		return newPass;
	}
	/**
	 * @param newPass the newPass to set
	 */
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	/**
	 * @return the confirmPass
	 */
	public String getConfirmPass() {
		return confirmPass;
	}
	/**
	 * @param confirmPass the confirmPass to set
	 */
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	

}
