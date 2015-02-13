package com.sundevil.domain.implementation;

import com.sundevil.domain.IExternalUser;
/**
 * @author priyankarupani
 *
 */
public class ExternalUser implements IExternalUser {

	private String exUserID;
	private String exLastName;
	private String exFirstName;
	private String exSsn;
	private String exEmailId;
	private String exAddress;
	private String exZipCode;
	private String exGender;
	private String exContactNumber;
	private String exUserName;
	private String exPassword;
	
	private String exRoleId;
	
	@Override
	public void setExRoleId(String exRoleId) {
		this.exRoleId = exRoleId;
	}
	@Override
	public String getExRoleId() {
		return exRoleId;
	}
	
	@Override
	public void setExPassword(String exPassword) {
		this.exPassword = exPassword;
	}
	@Override
	public String getExPassword() {
		// TODO Auto-generated method stub
		return exPassword;
	}
	@Override
	public void setExUserName(String exUserName) {
		this.exUserName = exUserName;
		
	}
	@Override
	public String getExUserName() {
		// TODO Auto-generated method stub
		return exUserName;
	}
	@Override
	public void setExContactNumber(String exContactNumber) {
		this.exContactNumber=exContactNumber;
		
	}
	@Override
	public String getExContactNumber() {
		// TODO Auto-generated method stub
		return exContactNumber;
	}
	@Override
	public void setExGender(String exGender) {
		this.exGender=exGender;
		
	}
	@Override
	public String getExGender() {
		// TODO Auto-generated method stub
		return exGender;
	}
	@Override
	public void setExZipCode(String exZipCode) {
		this.exZipCode=exZipCode;
		
	}
	@Override
	public String getExZipCode() {
		// TODO Auto-generated method stub
		return exZipCode;
	}
	@Override
	public void setExAddress(String exAddress) {
		this.exAddress=exAddress;
		
	}
	@Override
	public String getExAddress() {
		// TODO Auto-generated method stub
		return exAddress;
	}
	@Override
	public void setExEmailId(String exEmailId) {
		this.exEmailId=exEmailId;
		
	}
	@Override
	public String getExEmailId() {
		// TODO Auto-generated method stub
		return exEmailId;
	}
	@Override
	public void setExSsn(String exSsn) {
		this.exSsn=exSsn;
		
	}
	@Override
	public String getExSsn() {
		// TODO Auto-generated method stub
		return exSsn;
	}
	@Override
	public void setExFirstName(String exFirstName) {
		this.exFirstName=exFirstName;
		
	}
	@Override
	public String getExFirstName() {
		// TODO Auto-generated method stub
		return exFirstName;
	}
	@Override
	public void setExLastName(String exLastName) {
		this.exLastName=exLastName;
		
	}
	@Override
	public String getExLastName() {
		// TODO Auto-generated method stub
		return exLastName;
	}
	@Override
	public void setExUserID(String exUserID) {
		this.exUserID=exUserID;
		
	}
	@Override
	public String getExUserID() {
		// TODO Auto-generated method stub
		return exUserID;
	}
	
	
}
