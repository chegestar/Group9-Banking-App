package com.sundevil.service.changepassword;

public interface IChangePasswordManager {
	public boolean updatePassword(String username, String password);
	public boolean validatePassword(String username, String password);

}
