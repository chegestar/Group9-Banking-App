/**
 * 
 */
package com.sundevil.form.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sundevil.db.usermanagement.IDBConnectionUserManager;
import com.sundevil.domain.implementation.User;

/**
 * @author satyaswaroop boddu
 *
 */

@Service
public class RegistrationValidator extends AbstractValidator {

	@Autowired
	IDBConnectionUserManager dbConnect;
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.isAssignableFrom(User.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "lastName", "lastName.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "firstName", "firstName.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "ssn", "ssn.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "emailId", "emailId.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "address", "address.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "zipCode", "zipCode.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "gender", "gender.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "contactNumber", "contactNumber.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "userName", "userName.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "department", "department.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "salary", "salary.required", "Field name is required.");
		User user = (User)obj;
		
		if(!err.hasErrors())
		validateUserName(user.getUserName(),err,"userName");
		
		validateEmailAddress(user.getEmailId(), err,"emailId");
		validatestrings(user.getAddress(), err,"address");
		validateContactNumber(user.getContactNumber(), err,"contactNumber");
		validateSSN(user.getSsn(), err,"ssn");
		validateGender(user.getGender(), err,"gender");
		validateDepartment(user.getDepartment(), err,"department");
		validatestrings(user.getFirstName(), err, "firstName");
		validatestrings(user.getLastName(), err, "lastName");
		validateNumber(user.getZipCode(),err,"zipCode");
		validateNumber(user.getSalary()+"",err,"salary");
	}
	
}
