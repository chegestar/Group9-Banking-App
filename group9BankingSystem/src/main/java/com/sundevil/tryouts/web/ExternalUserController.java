package com.sundevil.tryouts.web;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.sundevil.domain.factory.IExternalUserFactory;
import com.sundevil.domain.implementation.ExternalUser;
import com.sundevil.form.validators.ExternalUserValidator;
import com.sundevil.service.externalusermanagement.IExternalUserManager;
/**
 * @author priyankarupani
 *
 */
@Controller
public class ExternalUserController {
	@Autowired
	private IExternalUserFactory userFactory;
	@Autowired
	private IExternalUserManager userManager;
	@Autowired
	private ExternalUserValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.setValidator(validator);
	}
	

	@RequestMapping(value = "/sales/addExternalUserForm", method = RequestMethod.GET)
	public ModelAndView externalUser(ModelMap map) throws Exception{
		
		map.addAttribute("user", userFactory.createExtUserObject());
		ModelAndView model =  new ModelAndView("externalUser");
		return model;
	}
	
	@RequestMapping(value = "/sales/addExternalUser", method = RequestMethod.POST)
	public ModelAndView addExternalUser(@Validated @ModelAttribute("user") ExternalUser user,BindingResult result, Model model) throws Exception{
		if (result.hasErrors()) {
			ModelAndView modelmap = new ModelAndView("externalUser");
			return modelmap;
		}
		userManager.externaluser(user);
		model.addAttribute("UserName", user.getExUserName());
		return new ModelAndView("useraddsuccess");
	}
	
}
