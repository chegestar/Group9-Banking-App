/**
 * 
 */
package com.sundevil.tryouts.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sundevil.service.usermanagement.IUserManager;

/**
 * @author satyaswaroop
 * 
 */
@Controller
public class LoginController {

	@Autowired
	IUserManager userManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws Exception {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:j_spring_security_logout";
	}

	@RequestMapping(value = "/loginerror", method = RequestMethod.GET)
	public String loginerror(ModelMap model) throws Exception {

		model.addAttribute("error", "true");
		return "login";

	}

	/*@RequestMapping(value = "/forbidden", method = RequestMethod.GET)
	public String forbidden(ModelMap model) throws Exception {

		model.addAttribute("error",
				"Logged out as you tried to access a page which u dont have access to");
		return "forward:/logout";

	}*/

	/*@RequestMapping(value = "/loginotp", method = RequestMethod.GET)
	public String loadOTPForm(ModelMap model, Principal prin) throws Exception {

		userManager.saveOTP(prin.getName());
		return "loginotp";

	}

	@RequestMapping(value = "/checkLoginOTP", method = RequestMethod.POST)
	public String checkOTP(ModelMap model, Principal p, HttpServletRequest req)
			throws Exception {
		if (req.getParameter("otp") != null) {
			if (req.getParameter("otp").length() == 10
					&& userManager.checkLoginOTP(p.getName(),
							req.getParameter("otp"))) {
				return "redirect:/";
			} else {
				model.addAttribute("error", "Please enter a valid pin");
				return "loginotp";
			}
		} else {
			model.addAttribute("error", "Please enter a valid pin");
			return "loginotp";
		}
	}*/

}
