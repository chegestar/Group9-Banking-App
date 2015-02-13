/**
 * 
 */
package com.sundevil.form.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sundevil.domain.backing.ChangePasswordBean;

/**
 * @author satyaswaroop
 * 
 */
@Service
public class ChangePasswordValidator extends AbstractValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(ChangePasswordBean.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPass",
				"generic.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPass",
				"generic.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPass",
				"generic.required", "Field name is required.");
		ChangePasswordBean bean = (ChangePasswordBean) target;
		validatePassword(bean.getNewPass(), errors, "newPass");
		if (!(bean.getConfirmPass()).equals(bean.getNewPass())) {
			errors.rejectValue("newPass", "notmatch.password");
		}
		if ((bean.getOldPass()).equals(bean.getNewPass())) {
			errors.rejectValue("newPass", "notprevious.password");
		}
		

	}

}
