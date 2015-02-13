/**
 * 
 */
package com.sundevil.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.sundevil.domain.implementation.Transaction;
import com.sundevil.form.validators.TransactionValidator;
import com.sundevil.security.transactionmanagement.ITransactionManager;
import com.sundevil.service.accountmanagement.IAccountManager;

/**
 * @author satyaswaroop
 *
 */
@Controller
public class TransactionController {
	@Autowired
	ITransactionManager transManage;
	
	@Autowired
	IAccountManager accountManager;
	
	@Autowired
	TransactionValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.setValidator(validator);
	}
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/makeDepositForm", method = RequestMethod.GET)
	public ModelAndView makeDepositForm(Model model, Principal principal) throws Exception{
		ITransaction trans = new Transaction();
		model.addAttribute("transaction", trans);
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		model.addAttribute("listOfAccounts", listOfAccounts);
		ModelAndView view = new ModelAndView("makeDeposit");
		return view;
		
	}
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/makeDeposit", method = RequestMethod.POST)
	public String makeDeposit(@Validated @ModelAttribute("transaction") Transaction trans,BindingResult result, Model model, Principal principal) throws Exception{
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		boolean check =false;
		for(IAccount acc : listOfAccounts)
		{
			if(acc.getAccountId().equals(trans.getDestAccount()))
				check = true;
		}
		if (result.hasErrors() || !check ) {
			model.addAttribute("listOfAccounts", listOfAccounts);
			return "makeDeposit";
		}
		transManage.makeDeposit(trans);
		return "redirect:/ext/accountDetails/"+trans.getDestAccount()+"/"+principal.getName();
	}
	
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/makeWithdrawalForm", method = RequestMethod.GET)
	public ModelAndView makeWithdrawalForm(Model model, Principal principal) throws Exception{
		ITransaction trans = new Transaction();
		model.addAttribute("transaction", trans);
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		model.addAttribute("listOfAccounts", listOfAccounts);
		ModelAndView view = new ModelAndView("makeWithdraw");
		return view;
		
	}
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/makeWithdraw", method = RequestMethod.POST)
	public String makeWithdraw(@Validated @ModelAttribute("transaction") Transaction trans,BindingResult result, Model model, Principal principal) throws Exception{
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		boolean check =false;
		for(IAccount acc : listOfAccounts)
		{
			if(acc.getAccountId().equals(trans.getDestAccount()))
				check = true;
		}
		if (result.hasErrors() || !check ) {
			model.addAttribute("listOfAccounts", listOfAccounts);
			return "makeDeposit";
		}
		boolean res = transManage.makeWithdraw(trans);
		if(!res)
		{
			result.rejectValue("amount", "amount.withdraw");
			model.addAttribute("listOfAccounts", listOfAccounts);
			return "makeDeposit";
		}
		return "redirect:/ext/accountDetails/"+trans.getDestAccount()+"/"+principal.getName();
	}
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/makeTransferForm", method = RequestMethod.GET)
	public ModelAndView makeTransferForm(Model model, Principal principal) throws Exception {
		ITransaction trans = new Transaction();
		model.addAttribute("transaction", trans);
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		model.addAttribute("listOfAccounts", listOfAccounts);
		ModelAndView view = new ModelAndView("makeTransfer");
		return view;
		
	}
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/makeTransfer", method = RequestMethod.POST)
	public String makeTransfer(@Validated @ModelAttribute("transaction") Transaction trans,BindingResult result, Model model, Principal principal) throws Exception {
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		boolean check =false;
		for(IAccount acc : listOfAccounts)
		{
			if(acc.getAccountId().equals(trans.getOriginAccount()))
				check = true;
		}
		if (result.hasErrors() || !check ) {
			model.addAttribute("listOfAccounts", listOfAccounts);
			return "makeTransfer";
		}
		
		boolean res = transManage.makeTransfer(trans,principal.getName());
		if(!res)
		{
			result.rejectValue("amount", "amount.withdraw");
			model.addAttribute("listOfAccounts", listOfAccounts);
			return "makeTransfer";
		}
		if(Long.parseLong(trans.getAmount())<=5000 && Long.parseLong(trans.getAmount())>0)
		return "redirect:/ext/confrimTransaction/"+trans.getTransId();
		else
			return "redirect:/ext/listAccounts";
	}
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "/ext/confrimTransaction/{TransactionID}", method = RequestMethod.GET)
	public String confirmTransaction(@PathVariable("TransactionID") String transId, Model model, Principal principal) throws Exception {
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		ITransaction t = transManage.getTransaction(transId);
		boolean check =false;
		for(IAccount acc : listOfAccounts)
		{
			if(acc.getAccountId().equals(t.getOriginAccount())){
				check = true;break;}
		}
		model.addAttribute("transId",transId);
		if(check)
			return "enterOTP";
		return "";
	}
	
	
	
	@PreAuthorize("hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')")
	@RequestMapping(value = "ext/checkOTP/{transId}", method = RequestMethod.POST)
	public String checkKey(@PathVariable("transId") String transId, Model model, Principal principal, HttpServletRequest req) throws Exception{
		List<IAccount> listOfAccounts = accountManager.getAllBankAccounts(principal.getName());
		ITransaction t = transManage.getTransaction(transId);
		boolean check =false;
		for(IAccount acc : listOfAccounts)
		{
			if(acc.getAccountId().equals(t.getOriginAccount())){
				check = true;break;}
		}
		
		if(check)
		{
			if(transManage.checkKey(req.getParameter("key"),t.getTransId())){
				return "redirect:/ext/accountDetails/"+t.getDestAccount()+"/"+principal.getName();
			}
				
				
			
		}
		return "ext/checkOTP/"+transId;
	}
	
	
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS:ROLE_OFFICIAL;*')")
	@RequestMapping(value = "transaction/getRequests", method = RequestMethod.GET)
	public String getRequests(Model model, Principal principal, HttpServletRequest req) throws Exception{
		
		List<ITransaction> t = transManage.getTransactionsToBeApproved(principal.getName());
		model.addAttribute("listOfTrans",t);
		return "transactionsApproval";
	}
	
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS:ROLE_OFFICIAL;*')")
	@RequestMapping(value = "transaction/approve/{transactionId}", method = RequestMethod.GET)
	public String approveTransaction(@PathVariable("transactionId") String transactionId, Model model, Principal principal, HttpServletRequest req) throws Exception{
		
		if(! transManage.approve(principal.getName(),transactionId))
				model.addAttribute("error","Please choose correct accounts");
		return "redirect:/transaction/getRequests";
	}
	
	
	@PreAuthorize("hasPermission(#user, '*;DEPT_TRANS:ROLE_OFFICIAL;*')")
	@RequestMapping(value = "transaction/reject/{transactionId}", method = RequestMethod.GET)
	public String rejectTransaction(@PathVariable("transactionId") String transactionId, Model model, Principal principal, HttpServletRequest req) throws Exception{
		
		if(! transManage.reject(principal.getName(),transactionId))
				model.addAttribute("error","Please choose correct accounts");
		return "redirect:/transaction/getRequests";
	}
}
