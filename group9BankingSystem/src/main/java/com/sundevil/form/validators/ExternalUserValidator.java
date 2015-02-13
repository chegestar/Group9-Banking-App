/**
 * 
 */
package com.sundevil.form.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sundevil.domain.implementation.ExternalUser;

/**
 * @author satyaswaroop
 *
 */
@Service
public class ExternalUserValidator extends AbstractValidator {
	
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.isAssignableFrom(ExternalUser.class);
	}
	
	@Override
	public void validate(Object obj, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exLastName", "lastName.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exFirstName", "firstName.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exSsn", "ssn.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exEmailId", "emailId.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exAddress", "address.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exZipCode", "zipCode.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exGender", "gender.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exContactNumber", "contactNumber.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "exUserName", "userName.required", "Field name is required.");
		ExternalUser user = (ExternalUser)obj;
		if(!err.hasErrors())
		validateUserName(user.getExUserName(),err,"exUserName");
		
		validateEmailAddress(user.getExEmailId(), err,"exEmailId");
		validatestrings(user.getExAddress(), err,"exAddress");
		validateContactNumber(user.getExContactNumber(), err,"exContactNumber");
		validateSSN(user.getExSsn(), err,"exSsn");
		validateGender(user.getExGender(), err,"exGender");
		
		validatestrings(user.getExFirstName(), err, "exFirstName");
		validatestrings(user.getExLastName(), err, "exLastName");
		validateNumber(user.getExZipCode(),err,"exZipCode");
	}
}
