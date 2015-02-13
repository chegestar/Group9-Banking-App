/**
 * 
 */
package com.sundevil.form.validators;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sundevil.db.usermanagement.IDBConnectionUserManager;

/**
 * @author satyaswaroop
 *
 */
public abstract class AbstractValidator implements Validator{

	@Autowired
	IDBConnectionUserManager dbConnect;
	
	protected void validateDepartment(String department, Errors err, String field) {
		String regex = "^[a-zA-Z_-]+$";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(department).matches())
		{
			err.rejectValue(field,"department.expression");
		}
		if(!dbConnect.checkValidDepartmentName(department))
		{
		    err.rejectValue(field,"department.expression");
		}
	}

	protected void validateGender(String gender, Errors err, String field) {
		String regex = "^(?:m|M|male|Male|f|F|female|Female)$";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(gender).matches())
		{
			err.rejectValue(field,"gender.expression");
		}
	}

	protected void validateUserName(String userName,Errors err, String field)
	{
		String regex = "^[a-z0-9_-]{3,15}$";
		Pattern pattern = Pattern.compile(regex);
		boolean isDuplicate;
		//Verifying if the Unix name already exists
		isDuplicate = dbConnect.chkDuplicateUserName(userName);
		if(!pattern.matcher(userName).matches()|| !isDuplicate)
		{
			err.rejectValue(field,"UserName.expression");
		}
	}
	
	protected void validateUserNameExists(String userName,Errors err, String field)
	{
		String regex = "^[a-z0-9_-]{3,15}$";
		Pattern pattern = Pattern.compile(regex);
		boolean isDuplicate;
		//Verifying if the Unix name already exists
		isDuplicate = dbConnect.chkDuplicateUserName(userName);
		if(!pattern.matcher(userName).matches()|| isDuplicate)
		{
			err.rejectValue(field,"UserName.expression");
		}
	}
	protected void validatePassword(String password, Errors err, String field){
		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(password).matches())
		{
			err.rejectValue(field,"password.expression");
		}
	}
	
	protected void validatestrings(String input, Errors err,String field){
		String regex = "[a-zA-Z0-9~@#\\^\\$&\\*\\(\\)-_\\+=\\[\\]\\{\\}\\|\\\\,\\.\\?\\s]*";
		Pattern pattern = Pattern.compile(regex);
		if(input.length()>50 && !pattern.matcher(input).matches() && !field.equals("address") )
		{
			err.rejectValue(field,"string.expression");
		}
		else if(input.length()>200)
		{
			err.rejectValue(field,"string.expression");
		}
		
	}
	
	protected void validateAccountType(String accountType, Errors err, String field)
	{
		String regex = "^(?:PERSONAL_ACCOUNT|MERCHANT_ACCOUNT)$";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(accountType).matches())
		{
			err.rejectValue(field,"type.expression");
		}
	}
	
	protected void validateContactNumber(String contactNumber, Errors err, String field){
		String regex = "(^[0-9]{10}$)";
		Pattern pattern = Pattern.compile(regex);
		if(!dbConnect.chkContactNumber(contactNumber))
		{
			err.rejectValue(field,"contact.unique");
		}
		if(!pattern.matcher(contactNumber).matches())
		{
			err.rejectValue(field,"contactNumber.expression");
		}
	}
	
	
	protected void validateEmailAddress(String emailaddress, Errors err, String field){
		String regex = "(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)";
		Pattern pattern = Pattern.compile(regex);
		if(!dbConnect.chkEmail(emailaddress))
		{
			err.rejectValue(field,"emailaddress.unique");
		}
		if(!pattern.matcher(emailaddress).matches())
		{
			err.rejectValue(field,"emailId.expression");
		}
		if((!emailaddress.contains("@gmail.com"))&&(!emailaddress.contains("@asu.edu")))
			err.rejectValue(field, "emailId.expression");
	}
	
	protected void validateSSN(String ssn, Errors err, String field){
		String regex = "(^[0-9]{9}$)";
		Pattern pattern = Pattern.compile(regex);
		if(!dbConnect.chkSSN(ssn))
		{
			err.rejectValue(field,"ssn.unique");
		}
		if(!pattern.matcher(ssn).matches())
		{
			err.rejectValue(field,"ssn.expression");
		}
	}
	
	protected void validateNumber(String input, Errors err,String field){
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(input).matches())
		{
			err.rejectValue(field,"number.expression");
		}
	}

	protected void validateExUser(String userName,Errors err, String field)
	{
		String regex = "^[a-z0-9_-]{3,15}$";
		Pattern pattern = Pattern.compile(regex);
		boolean isExternalUser;
		//Verifying if the Unix name already exists
		isExternalUser = dbConnect.chkExUserName(userName);
		if(!pattern.matcher(userName).matches()|| !isExternalUser)
		{
			err.rejectValue(field,"UserName.notexternaluser");
		}
	}

}
