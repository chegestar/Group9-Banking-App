/**
 * 
 */
package com.sundevil.form.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sundevil.domain.implementation.Transaction;
import com.sundevil.security.transactionmanagement.ITransactionManager;

/**
 * @author satyaswaroop
 *
 */
@Service
public class TransactionValidator extends AbstractValidator {
	
	@Autowired
	ITransactionManager transaction;
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Transaction.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Transaction t = (Transaction)target;
		validateNumber(t.getAmount(), errors, "amount");
		ValidationUtils.rejectIfEmpty(errors, "amount", "generic.required");
		if(t.getType().equals("DEPOSIT") || t.getType().equals("WITHDRAW"))
		{
			ValidationUtils.rejectIfEmpty(errors, "destAccount", "generic.required");
		}
		if(t.getType().equals("TRANSFER"))
		{
			ValidationUtils.rejectIfEmpty(errors, "destAccount", "generic.required");
			ValidationUtils.rejectIfEmpty(errors, "originAccount", "generic.required");
			if((t.getDestAccount()).equals(t.getOriginAccount()))
			{
				errors.rejectValue("originAccount", "generic.required", "Enter a positive number");
			}
			if(!(transaction.checkExists(t.getDestAccount())))
			{
				errors.rejectValue("destAccount", "generic.required", "Enter a correct number");
			}
			
		}
		if((Long.parseLong(t.getAmount()))>=10000)
		{
			
			errors.rejectValue("amount", "negative.ammount", "Enter ammount lessthan 10000 number");
		}
	}


}
