package com.sundevil.domain.factory.impl;

import org.springframework.stereotype.Service;

import com.sundevil.domain.IAccount;
import com.sundevil.domain.IUser;
import com.sundevil.domain.factory.IAccountFactory;
import com.sundevil.domain.factory.IUserFactory;
import com.sundevil.domain.implementation.Account;
import com.sundevil.domain.implementation.User;

/**
 * 
 */

/**
 * @author apurva
 *
 */
@Service
public class AccountFactory implements IAccountFactory {

	@Override
	public IAccount createAccountObject() {
		IAccount acc = new Account();
		
		return acc;
	}

}
