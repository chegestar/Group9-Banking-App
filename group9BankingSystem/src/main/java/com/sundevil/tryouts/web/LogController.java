/**
 * 
 */
package com.sundevil.tryouts.web;

import java.io.File;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sundevil.service.ILoggerManager;

/**
 * @author satyaswaroop
 * 
 */
@Controller
public class LogController {

	@Autowired
	ILoggerManager logManager;

	@PreAuthorize("hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')")
	@RequestMapping(value = "/sysadmin/viewlogs", method = RequestMethod.GET)
	public String getLogs(Model model) throws Exception{
		Scanner scan = null;
		StringBuffer buffer;
		File logFile = logManager.getLatestLogs();
		try {
			buffer = new StringBuffer();
			scan = new Scanner(logFile, "UTF-8");
			String readdata = "";
			while (scan.hasNext() && (readdata = scan.nextLine()) != null) {
				buffer.append(readdata).append('\n');
			}
			model.addAttribute("logFile", buffer.toString());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (scan != null) {
				scan.close();
				scan = null;
			}
		}

		return "viewlogs";
	}

}
