package com.sundevil.domain;
/**
 * @author priyankarupani
 *
 */
public interface IExternalUser {



	public abstract void setExPassword(String exPassword);

	public abstract String getExPassword();

	public abstract void setExUserName(String exUserName);
	
	public abstract String getExUserName();

	public abstract void setExContactNumber(String exContactNumber);

	public abstract String getExContactNumber();

	public abstract void setExGender(String exGender);

	public abstract String getExGender();

	public abstract void setExZipCode(String exZipCode);

	public abstract String getExZipCode();

	public abstract void setExAddress(String exAddress);

	public abstract String getExAddress();

	public abstract void setExEmailId(String exEmailId);

	public abstract String getExEmailId();

	public abstract void setExSsn(String exSsn);

	public abstract String getExSsn();

	public abstract void setExFirstName(String exFirstName);

	public abstract String getExFirstName();

	public abstract void setExLastName(String exLastName);

	public abstract String getExLastName();

	public abstract void setExUserID(String exUserID);

	public abstract String getExUserID();

	public abstract void setExRoleId(String exRoleId);

	public abstract String getExRoleId();

}
