package com.sundevil.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.sundevil.exceptions.BankException;




@ControllerAdvice
public class BankExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(BankExceptionHandler.class);
	
	
	@ExceptionHandler(BankException.class)
	public ModelAndView handleBankException(BankException ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("errorpage");
		modelAndView.addObject("ex_name", ex.getClass().getName());
		modelAndView.addObject("ex_message", "Unexpected Error Occured We will get back to you");
		logger.error(ex.getMessage(), ex);
		return modelAndView;
	}
	
}
