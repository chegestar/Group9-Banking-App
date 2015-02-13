/**
 * 
 */
package com.sundevil.tryouts.web;

import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sundevil.domain.IUser;
import com.sundevil.domain.factory.IUserFactory;
import com.sundevil.domain.implementation.User;
import com.sundevil.form.validators.RegistrationValidator;
import com.sundevil.service.usermanagement.IUserManager;

/**
 * @author satyaswaroop
 * 
 */
@Controller
public class UserController {
	@Autowired
	private IUserFactory userFactory;
	@Autowired
	private IUserManager userManager;
	@Autowired
	private RegistrationValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.setValidator(validator);
	}

	@PreAuthorize("hasPermission(#user, '*;DEPT_HR')")
	@RequestMapping(value = "/hrdept/addEmployeeForm", method = RequestMethod.GET)
	public ModelAndView user(ModelMap map) {
		map.addAttribute("user", userFactory.createUserObject());
		ModelAndView model = new ModelAndView("registerUser");
		return model;
	}

	@PreAuthorize("hasPermission(#user, '*;DEPT_HR')")
	@RequestMapping(value = "/hrdept/addEmployee", method = RequestMethod.POST)
	public ModelAndView addUser(@Validated @ModelAttribute("user") User user,
			BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			ModelAndView modelmap = new ModelAndView("registerUser");
			return modelmap;
		}
		userManager.addUser(user);
		model.addAttribute("UserName", user.getUserName());
		return new ModelAndView("useraddsuccess");
	}

	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/showRequests", method = RequestMethod.GET)
	public ModelAndView showRequests(ModelMap model) throws Exception {

		List<String> listOfUsers = userManager.getPendingUsers();
		System.out.println("" + listOfUsers.toString());
		model.addAttribute("listOfUsers", listOfUsers);
		return new ModelAndView("userRequests");
	}

	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/approveusers", method = RequestMethod.POST)
	public String approveUser(ModelMap model, HttpServletRequest req)
			throws Exception {
		String[] values = req.getParameterValues("selected");
		for (String username : values) {
			userManager.approveUser(username);
		}
		return "redirect:/sysadmin/showRequests";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/rejectusers", method = RequestMethod.POST)
	public String rejectUser(ModelMap model, HttpServletRequest req)
			throws Exception {
		String[] values = req.getParameterValues("selected");
		for (String username : values) {
			userManager.deleteUser(username);
		}
		return "redirect:/sysadmin/showRequests";
	}

	@PreAuthorize("hasPermission(#user, 'ROLE_MANAGER;*')")
	@RequestMapping(value = "/manager/userManagement", method = RequestMethod.GET)
	public String getAllEmployees(ModelMap model, HttpServletRequest req, Principal prin)
			throws Exception {
		model.addAttribute("listOfUsers", userManager.getEmployees(prin.getName()));
		return "managerPage";
	}
	
	
	
	@PreAuthorize("hasPermission(#user, 'ROLE_MANAGER;*')")
	@RequestMapping(value = "/manager/modifyUser/{userName}", method = RequestMethod.GET)
	public String modifyEmployee(@PathVariable("userName") String username, ModelMap model, HttpServletRequest req, Principal prin)
			throws Exception {
		IUser user = userManager.getInternalUser(username,prin.getName());
		model.addAttribute("user", user);
		return "manageEmployee";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_MANAGER;*')")
	@RequestMapping(value = "/manager/updateEmployee", method = RequestMethod.POST)
	public String updateEmployeeRole(@ModelAttribute("user") User user, Model model, Principal prin )
			throws Exception {
		if(!(userManager.setRole(user.getUserName(), prin.getName(), user.getRoleId())))
			model.addAttribute("errorUpdateRole", "Unable to update user role as the request is not proper please repeat the same");
		model.addAttribute("listOfUsers", userManager.getEmployees(prin.getName()));
		return "managerPage";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_MANAGER;*')")
	@RequestMapping(value = "/manager/removeEmployee/{userName}", method = RequestMethod.GET)
	public String removeEmployee( @PathVariable("userName") String username, Model model, Principal prin )
			throws Exception {
		userManager.removeEmployee(username,prin.getName());
		model.addAttribute("listOfUsers", userManager.getEmployees(prin.getName()));
		return "managerPage";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_OFFICIAL;*')")
	@RequestMapping(value = "/official/removeManager/{userName}", method = RequestMethod.GET)
	public String removeManager( @PathVariable("userName") String username, Model model, Principal prin )
			throws Exception {
		userManager.removeManager(username);
		return "redirect:/official/showManagers";
	}
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_HR')")
	@RequestMapping(value = "/hrdept/getUnassigned", method = RequestMethod.GET)
	public String getUnassignEmployee(Model model, Principal prin)
			throws Exception {
		
		model.addAttribute("listOfUsers", userManager.getUnassignedEmployees());
		return "assignEmployees";
	}
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_HR')")
	@RequestMapping(value = "/hrdept/assignEmployee", method = RequestMethod.GET)
	public String assignEmployee(Model model, Principal prin )
			throws Exception {
		
		model.addAttribute("listOfUsers", userManager.getUnassignedEmployees());
		return "assignDept";
	}
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_HR')")
	@RequestMapping(value = "/hrdept/updateDepartment", method = RequestMethod.POST)
	public String updateDepartment(Model model, Principal prin , HttpServletRequest req)
			throws Exception {
		
		if(!(userManager.setDepartment(req.getParameter("username"),req.getParameter("department"))))
		{
			model.addAttribute("errorUpdateDepartment", "Unable to update user department as the request is not proper please repeat the same");
		}
		return "redirect:/hrdept/assignEmployee";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_OFFICIAL;*')")
	@RequestMapping(value = "/official/setManager", method = RequestMethod.POST)
	public String setManager(Model model, Principal prin , HttpServletRequest req)
			throws Exception {
		List<IUser> list = userManager.getManagers();
		IUser u = userFactory.createUserObject();
		
		u.setUserName(req.getParameter("username"));
		if(list.contains(u))
		{
		if(!(userManager.setDepartment(req.getParameter("username"),req.getParameter("department"))))
		{
			model.addAttribute("errorUpdateDepartment", "Unable to update user department as the request is not proper please repeat the same");
		}
		}
		else
		{
			model.addAttribute("errorUpdateDepartment", "invalid username please provide a valid user");
		}
		return "redirect:/official/showManagers";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_OFFICIAL;*')")
	@RequestMapping(value = "/official/showManagers", method = RequestMethod.GET)
	public String showManager(Model model, Principal prin)
			throws Exception {
		
		model.addAttribute("listOfUsers", userManager.getManagers());
		return "showManagers";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_OFFICIAL;*')")
	@RequestMapping(value = "/official/setManagerForm", method = RequestMethod.GET)
	public String setManager(Model model, Principal prin)
			throws Exception {
		
		model.addAttribute("listOfUsers", userManager.getManagers());
		return "setManagerPage";
	}
	
	
	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/viewUsers", method = RequestMethod.GET)
	public String viewUsers(Model model, Principal prin)
			throws Exception {
		
		model.addAttribute("listOfUsers", userManager.getAllUsers());
		return "viewUsers";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/resetPassword/{username}", method = RequestMethod.GET)
	public String resetPassword( @PathVariable("username") String username, Model model, Principal prin)
			throws Exception {
		
		 userManager.resetPassword(username);
		return "redirect:/sysadmin/viewUsers";
	}
	
	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/deleteUser/{username}", method = RequestMethod.GET)
	public String deleteUser( @PathVariable("username") String username, Model model, Principal prin)
			throws Exception {
		userManager.deleteUser(username);
		return "redirect:/sysadmin/viewUsers";
	}

}
