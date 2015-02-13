/**
 * 
 */
package com.sundevil.form.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sundevil.domain.implementation.Account;

/**
 * @author satyaswaroop
 *
 */
@Service
public class AccountValidator extends AbstractValidator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Account.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "generic.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "balance", "generic.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "generic.required", "Field name is required.");
		
		validateNumber(((Account)target).getBalance(), errors, "balance");
		validatestrings(((Account)target).getType(), errors, "type");
		validateExUser(((Account)target).getUserId(), errors, "userId");
		validateAccountType(((Account)target).getType(), errors, "type");
		
	}

}
