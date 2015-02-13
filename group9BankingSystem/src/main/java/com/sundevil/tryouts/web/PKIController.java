package com.sundevil.tryouts.web;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sundevil.security.transactionmanagement.ITransactionManager;
import com.sundevil.service.PKIService;
import com.sundevil.service.accountmanagement.IAccountManager;

import com.sundevil.domain.IAccount;
import com.sundevil.domain.implementation.Account;
import com.sundevil.domain.implementation.CertificateFormBean;





@Controller
public class PKIController {

	@Autowired
	private PKIService pkiService;
	
	@Autowired
	private IAccountManager accManager;
	
	@Autowired
	private ITransactionManager transManager;

	@RequestMapping(value = "/ext/pki/generateCertificate", method = RequestMethod.GET)
	public ModelAndView generateCertificate(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal)   {
		String userName = principal.getName();
		try{
			 certificateFormBean = pkiService.generateCertificate(userName);
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
		}
		
		model.addAttribute("certificateStatusMessage", "Your genuine certificate provided by SBS");
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/certificate",model);
		
	}
	
	@RequestMapping(value = "/ext/pki/obtainCertificateForVerification", method = RequestMethod.GET)
	public ModelAndView obtainCertificateForVerification(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal)   {
		String userName = principal.getName();
		if(certificateFormBean != null){
			if(certificateFormBean.getSenderUserName()!=null){
				String senderUserName = certificateFormBean.getSenderUserName();
				try{
					 certificateFormBean = pkiService.obtainCertificateForVerification(senderUserName,userName);
				}
				catch(Exception e){
					System.out.println("Exception : "+e);
				}
			}
			
		
		
			
		}
		String returnPage = "pki/certificateVerification";
		if(certificateFormBean == null){
			certificateFormBean = new CertificateFormBean();
			model.addAttribute("errorMessage", "The User You Just Mentioned Has Not Sent Any Certificate");
			returnPage = "pki/enterMessageDetails2";
		}
		
		
		
		
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView(returnPage,model);
		
	}
	
	@RequestMapping(value = "/ext/pki/enterMessageDetails", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal)   {
		String userName = principal.getName();
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/makePayments",model);
	}
	
	@RequestMapping(value = "/ext/pki/enterMessageDetail2", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails2(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal) throws Exception  {
		String userName = principal.getName();
		//String userName = "mohanraj1"; 
		System.out.println("enterMessageDetails2");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails2",model);
	}
/*	
	@RequestMapping(value="/enterMessageDetails", method = RequestMethod.GET)
	public String login(@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception {
		System.out.println("enterMessageDetails");
		return "pki/enterMessageDetails1";
	}*/
	
	
	
	@RequestMapping(value = "/ext/pki/sendPayment", method = RequestMethod.POST)
	public ModelAndView sendCertificate(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal)   {
		
		String userName = principal.getName();
		boolean emailSent = false;
		String merchantName = certificateFormBean.getDestinationUserName();
		List<IAccount> acc = accManager.getAllBankAccounts(merchantName);
		boolean b = false;
		for(IAccount account: acc)
		{
			if(account.getType().equals("MERCHANT_ACCOUNT"))
			{
				b=true;
				break;
			}
		}
		String sourceAccountId = certificateFormBean.getDestinationEmailId();
		String ammount = certificateFormBean.getMessage(); 
		List<IAccount> listOfAccounts = accManager.getAllBankAccounts(principal.getName());
		Account a = new Account();a.setAccountId(sourceAccountId);
		String regex = "^[0-9]+$";
		if(!listOfAccounts.contains(a) && ammount.matches(regex) && !b)
		{
			return new ModelAndView("/ext/pki/enterMessageDetails");
		}
		try{
			 String paymentId = ("PAYMENT_" + UUID.randomUUID());
			 certificateFormBean = pkiService.generateCertificate(paymentId);
			emailSent =  pkiService.sendCertificate(merchantName,ammount,sourceAccountId,paymentId);
			System.out.println("enter\n"+paymentId);
			//swaroop
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
		}
		
		if(emailSent){
			model.addAttribute("certificateStatusMessage", "Certificate Sent To The Desired User. An E-Mail Is Sent To Notify The Desired User");
		}
		else{
			model.addAttribute("errorMessage", "Certificate And E-Mail Could Not Be Sent. You Might Have Entered Incorrect E-Mail-Id/User-Name Combination. Try Again");
			
		}
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails1",model);
		
	}
	
	@RequestMapping(value = "/ext/pki/enterMessageDetails3", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails3(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal) throws Exception  {
		String userName = principal.getName();
		//String userName = "mohanraj1"; 
		System.out.println("enterMessageDetails3");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails3",model);
	}
	
	@RequestMapping(value = "/ext/pki/verifyCertificate", method = RequestMethod.GET)
	public ModelAndView verifyCertificate(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal)   {
		String userName = principal.getName();
		//String userName = "mohanraj1"; 
		boolean isVerified = false;
		System.out.println("in verifyCertificate");
		System.out.println("paymentId  :" +certificateFormBean.getSenderUserName());
		String senderUserName = certificateFormBean.getSenderUserName();
		try{
			certificateFormBean = pkiService.obtainCertificateForVerification(senderUserName,userName);
			isVerified =  pkiService.verifyCertificate(senderUserName,userName);
			//swaroop code for updating db
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
		}
		System.out.println(isVerified);
		if(isVerified){
			model.addAttribute("certificateStatusMessage", "Certificate Of The Sender You Just Mentioned Is A Genuine Certificate. You Can Now Safely Transact With The Sender.");
		}
		else{
			model.addAttribute("errorMessage", "Certificate Not Verified !! Certificate Of The Sender Not Genuine Or You Might Have Entered Incorrect User-Name For The Sender. Try Again.");
		}
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails3",model);
		
	}
	@RequestMapping(value = "/ext/pki/verifySignature", method = RequestMethod.GET)
	public ModelAndView decryptMessage(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model, Principal principal) throws Exception  {
		String userName = principal.getName();
		//String userName = "mohanraj1";
		System.out.println("verifySignature");
		//pkiService.signCertificate(certificateFormBean);
		String senderUserName = certificateFormBean.getSenderUserName();
		String signature = certificateFormBean.getSignature();
		String message = certificateFormBean.getMessage();
		
		 boolean isVerified = pkiService.verifySignature(senderUserName, message, signature,userName);
		 if(certificateFormBean == null){
				certificateFormBean =  new CertificateFormBean();
			}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/signatureVerification",model);
	}
}