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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sundevil.domain.IAccount;
import com.sundevil.domain.ITransaction;
import com.sundevil.domain.implementation.Account;
import com.sundevil.security.transactionmanagement.ITransactionManager;
import com.sundevil.service.accountmanagement.IAccountManager;
import com.sundevil.service.requestmanagement.IRequestManagement;

/**
 * @author satyaswaroop
 * 
 */
@Controller
public class RequestsController {

	@Autowired
	IRequestManagement reqManager;
	
	@Autowired
	IAccountManager accManager;
	
	@Autowired
	ITransactionManager tranManager;

	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS')")
	@RequestMapping(value = "/transaction/placeRequest", method = RequestMethod.POST)
	public String placeRequest(Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		String accId = req.getParameter("account");
		if (!reqManager.chkExistance(prin.getName(), accId)) {
			model.addAttribute("accounterror",
					"Request is already placed for this account");
			return "redirect:/transaction/placeRequestForm";
		}
		if (!(reqManager.placeRequest(prin.getName(), accId))) {
			model.addAttribute("accounterror", "Please choose a valid account");
			return "redirect:/transaction/placeRequestForm";
		}
		return "redirect:/transaction/showRequests";
	}

	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS')")
	@RequestMapping(value = "/transaction/placeRequestForm", method = RequestMethod.GET)
	public String placeRequestForm(Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		List<String> accounts = reqManager.getAllAccounts();
		model.addAttribute("accounts", accounts);
		return "placeRequestForm";
	}
	
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS')")
	@RequestMapping(value = "/transaction/showRequests", method = RequestMethod.GET)
	public String showRequests(Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		model.addAttribute("requests",reqManager.getAllRequests(prin.getName()));
		return "showRequests";
	}
	
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/showRequests", method = RequestMethod.POST)
	public String showExtRequests(Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		model.addAttribute("account",req.getParameter("account"));
		model.addAttribute("requests",reqManager.getAccountRequests(req.getParameter("account")));
		return "requests/showAccesses";
	}
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/showRequestsForm", method = RequestMethod.GET)
	public String showExtRequestsForm(Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		model.addAttribute("accounts",accManager.getAllBankAccounts(prin.getName()));
		return "requests/showAccessForm";
	}
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/approveRequest/{accountId}/{username}", method = RequestMethod.GET)
	public String approveRequest(@PathVariable("accountId") String accountId, @PathVariable("username") String userName,  Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		List<IAccount> account = accManager.getAllBankAccounts(prin.getName());
		IAccount acc=new Account();acc.setAccountId(accountId);
		if(!account.contains(acc) || !(reqManager.approveRequest(accountId,userName)))
		{
			model.addAttribute("error","Please enter a valid account number and username");
			
		}
		
		return "redirect:/ext/showRequestsForm";
	}
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/rejectRequest/{accountId}/{username}", method = RequestMethod.GET)
	public String rejectRequest(@PathVariable("accountId") String accountId, @PathVariable("username") String userName,  Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		List<IAccount> account = accManager.getAllBankAccounts(prin.getName());
		IAccount acc=new Account();acc.setAccountId(accountId);
		if(!account.contains(acc) || !(reqManager.rejectRequest(accountId,userName)))
		{
			model.addAttribute("error","Please enter a valid account number and username");
			
		}
		
		return "redirect:/ext/showRequestsForm";
	}
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/revokeRequest/{accountId}/{username}", method = RequestMethod.GET)
	public String revokeRequest(@PathVariable("accountId") String accountId, @PathVariable("username") String userName,  Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		List<IAccount> account = accManager.getAllBankAccounts(prin.getName());
		IAccount acc=new Account();acc.setAccountId(accountId);
		if(!account.contains(acc) || !(reqManager.revokeRequest(accountId,userName)))
		{
			model.addAttribute("error","Please enter a valid account number and username");
			
		}
		
		return "redirect:/ext/showRequestsForm";
	}

	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS')")
	@RequestMapping(value = "/transaction/viewTransactions/{accountId}", method = RequestMethod.GET)
	public String viewTransactions(@PathVariable("accountId") String accountId,  Model model, Principal prin,
			HttpServletRequest req) throws Exception {
		
		if( !(reqManager.isAccessable(accountId, prin.getName())))
		{
			model.addAttribute("error","Please enter a valid account number and username");
			return "redirect:/transaction/showRequests";
		}
		List<ITransaction> listOfTrans = accManager.getAllTransactions(accountId);
		model.addAttribute("listOfTrans", listOfTrans);
		return "showTrans";
	}
}
