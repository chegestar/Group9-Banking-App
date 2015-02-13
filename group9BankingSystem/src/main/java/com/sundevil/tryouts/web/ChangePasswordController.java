/**
 * 
 */
package com.sundevil.tryouts.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sundevil.domain.backing.ChangePasswordBean;
import com.sundevil.form.validators.ChangePasswordValidator;
import com.sundevil.service.changepassword.IChangePasswordManager;

/**
 * @author satyaswaroop
 * 
 */
@Controller
public class ChangePasswordController {
	@Autowired
	ChangePasswordValidator validator;

	@Autowired
	IChangePasswordManager pwManger;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.setValidator(validator);
	}

	@PreAuthorize("isFullyAuthenticated()")
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public ModelAndView changePassword(
			@Validated @ModelAttribute("change") ChangePasswordBean bean, BindingResult result,
			Model model, Principal prin, HttpServletRequest req)
			throws Exception {
		if (result.hasErrors()) {
			ModelAndView modelmap = new ModelAndView("/changePassword/changePassword");
			return modelmap;
		}
		if (!pwManger.validatePassword(prin.getName(), bean.getOldPass())) {
			model.addAttribute("error", "Please enter a valid old password");
			return new ModelAndView("/changePassword/changePassword");
		}
		pwManger.updatePassword(prin.getName(), bean.getNewPass());

		return new ModelAndView("redirect:j_spring_security_logout");
	}

	@PreAuthorize("isFullyAuthenticated()")
	@RequestMapping(value = "/changePassForm", method = RequestMethod.GET)
	public ModelAndView changePasswordForm(ModelMap map) throws Exception {
		map.addAttribute("change", new ChangePasswordBean());
		ModelAndView model = new ModelAndView("/changePassword/changePassword");
		return model;

	}

}
