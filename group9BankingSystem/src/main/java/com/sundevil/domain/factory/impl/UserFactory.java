package com.sundevil.domain.factory.impl;

import org.springframework.stereotype.Service;

import com.sundevil.domain.IUser;
import com.sundevil.domain.factory.IUserFactory;
import com.sundevil.domain.implementation.User;

/**
 * 
 */

/**
 * @author satyaswaroop
 *
 */
@Service
public class UserFactory implements IUserFactory {

	/* (non-Javadoc)
	 * @see com.sundevil.domain.factory.IUserFactory#createUserObject()
	 */
	@Override
	public IUser createUserObject() {
		IUser u = new User();
		
		return u;
	}

}
