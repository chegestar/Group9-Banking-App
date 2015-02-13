/**
 * 
 */
package com.sundevil.security;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.sundevil.service.usermanagement.IUserManager;
import com.sundevil.tryouts.web.HomeController;

/**
 * @author satyaswaroop
 *
 */
public class BankPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	IUserManager usermanager;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/*public BankPermissionEvaluator(IUserManager manager)
	{
		usermanager = manager;
	}*/
	
	/* (non-Javadoc)
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		boolean ret = false;
		if ( authentication != null &&  permission instanceof String ){
			
			try{
				
			String user =  authentication.getName();
			/*if(!usermanager.chkOnetimeLogin(user))
			{
				return false;
			}*/
			Collection<? extends GrantedAuthority> c = authentication.getAuthorities();
			String list[] = ((String)permission).split(":");
			for(String perm:list){
			String roles[] = ((String)perm).split(";");
			String dept=null;
			if(roles[1].equals("EXTERNAL_USER"))
				dept = "EXTERNAL_USER";
			else if(roles[0].equals("ROLE_OFFICIAL"))
			{
				dept="*";
			}
			else
			 dept = usermanager.getDepartment(user).trim();
			
			if(roles[0].equals("*") && dept.equals(roles[1]))
			{
				return true;
			}
			for(GrantedAuthority g:c)
			{
				if(roles[0].equals(g.getAuthority()) && roles[1].equals("*"))
					return true;
				if((roles[0].equals(g.getAuthority())) && (roles[1].equals(dept))){
				return true;
				}
			}
			}
			}
			catch(Exception e)
			{
				 logger.error("In Bank permision evaluater", e.getMessage());
				 ret=false;
			}
		}
		return ret;
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication, java.io.Serializable, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication arg0, Serializable arg1,
			String arg2, Object arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
