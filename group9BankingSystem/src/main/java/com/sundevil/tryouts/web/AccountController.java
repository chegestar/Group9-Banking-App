/**
 * 
 */
package com.sundevil.tryouts.web;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.sundevil.domain.IAccount;
import com.sundevil.domain.ITransaction;
import com.sundevil.domain.factory.IAccountFactory;
import com.sundevil.domain.implementation.Account;
import com.sundevil.form.validators.AccountValidator;
import com.sundevil.service.accountmanagement.IAccountManager;


/**
 * @author apurva
 * 
 */
@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IAccountFactory accountFactory;
	
	@Autowired
	private IAccountManager accountManager;
	
	@Autowired
	private AccountValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.setValidator(validator);
	}
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_SALES')")
	@RequestMapping(value = "/sales/addAccount", method = RequestMethod.POST)
	public ModelAndView addAccount(@Validated @ModelAttribute("account") Account account,  BindingResult result, Model model) throws Exception{
		if (result.hasErrors()) {
			ModelAndView modelmap = new ModelAndView("createAccount");
			return modelmap;
		}
		logger.info("Account Creation Submitted successfully");
		accountManager.createAccount(account);
		model.addAttribute("UserName", account.getUserId());
		return new ModelAndView("useraddsuccess");
	}
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_SALES')")
	@RequestMapping(value = "/sales/addAccountForm", method = RequestMethod.GET)
	public ModelAndView addAccountForm(ModelMap map) throws Exception{
		
		logger.info("Account Creation Page");
		map.addAttribute("account", accountFactory.createAccountObject());
		ModelAndView model = new ModelAndView("createAccount");
		return model;
	}
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/listAccounts", method = RequestMethod.GET)
	public ModelAndView listAccounts(ModelMap map, Principal p) throws Exception {
		
		logger.info("Showing bank details");
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(p.getName());
		System.out.println(listOfAccounts);
		map.addAttribute("listOfAccounts", listOfAccounts);
		ModelAndView model = new ModelAndView("viewAccounts");
		return model;
	}
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/accountDetails/{accountid}/{username}", method = RequestMethod.GET)
	public ModelAndView accountDetails(@PathVariable("accountid") String accountid,@PathVariable("username") String username, ModelMap map, Principal p) throws Exception{
		logger.info("Opening account details"+username+"::"+p.getName());
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(p.getName());
		IAccount acc = new Account(); acc.setAccountId(accountid);
		if(!listOfAccounts.contains(acc))
		{
			map.addAttribute("error","Please give your accounts not others account ids");
			return new ModelAndView("redirect:/ext/listAccounts");
		}
		if(username.equals(p.getName())){
		logger.info("Opening account details");
		List<ITransaction> listOfTrans = accountManager.getAllTransactions(accountid);
		map.addAttribute("listOfTrans", listOfTrans);
		ModelAndView model = new ModelAndView("showTrans");
		return model;
	}
		else
		{
			return null;
		}
	}
}
