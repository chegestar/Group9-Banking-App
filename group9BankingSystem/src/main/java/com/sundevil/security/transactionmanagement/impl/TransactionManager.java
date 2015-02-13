/**
 * 
 */
package com.sundevil.security.transactionmanagement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sundevil.db.accountmanagement.IDBConnectionAccountManager;
import com.sundevil.db.transactionmanagement.IDBConnectionTransactionManager;
import com.sundevil.db.usermanagement.IDBConnectionUserManager;
import com.sundevil.domain.ITransaction;
import com.sundevil.domain.IUser;
import com.sundevil.domain.implementation.Transaction;
import com.sundevil.email.EmailNotificationSender;
import com.sundevil.security.transactionmanagement.ITransactionManager;
import com.sundevil.service.RandomNumberGenerator;

/**
 * @author satyaswaroop
 * 
 */
@Service
public class TransactionManager implements ITransactionManager {

	@Autowired
	IDBConnectionTransactionManager dbconnection;
	@Autowired
	EmailNotificationSender sender;
	@Autowired
	IDBConnectionUserManager userManager;

	@Autowired
	IDBConnectionAccountManager accConnection;

	@Override
	public boolean makeDeposit(ITransaction transaction) {
		transaction.setType("DEPOSIT");
		dbconnection.addTransaction(transaction);
		return true;
	}

	@Override
	public boolean makeWithdraw(Transaction trans) {
		String balance = accConnection.getBalance(trans.getDestAccount());
		if (Long.parseLong(balance) >= Long.parseLong(trans.getAmount())) {
			trans.setType("WITHDRAW");
			dbconnection.addTransaction(trans);
			return true;
		} else
			return false;
	}

	@Override
	public boolean checkExists(String destAccount) {
		return dbconnection.checkExists(destAccount);
	}

	@Override
	public boolean makeTransfer(Transaction trans, final String username) {
		String balance = accConnection.getBalance(trans.getOriginAccount());
		if (Long.parseLong(balance) >= Long.parseLong(trans.getAmount())) {
			if (Long.parseLong(trans.getAmount()) <= 5000) {
				final String randKey = new RandomNumberGenerator()
						.generatePassword();

				trans.setType("TRANSFER");
				new Thread(new Runnable() {

					@Override
					public void run() {
						sender.sendNotificationEmail(
								userManager.getEmailId(username), "IMPORTANT",
								randKey);

					}
				}).start();
				
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(randKey);
				dbconnection.addTransferTransaction(trans, hashedPassword);
				
			}
			else if(Long.parseLong(trans.getAmount()) > 5000 && Long.parseLong(trans.getAmount())<9999)
			{
				dbconnection.addTransferTransaction(trans, "");
			}
			
			return true;
		}
		return false;
	}

	@Override
	public ITransaction getTransaction(String transId) {
		return dbconnection.getTransaction(transId);
	}

	@Override
	public boolean checkKey(String parameter, String transId) {

		return dbconnection.finalizeByKey(parameter, transId);
	}
	
	@Override
	public List<ITransaction> getTransactionsToBeApproved(String username) {
		IUser user = userManager.getUser(username);
		String role = user.getRoleId();
		int status=0;
		if(role.equals("ROLE_REGULAREMPLOYEE"))
		{
			status = 0;
		}
		else if(role.equals("ROLE_MANAGER"))
		{
			status = 2;
		}
		else if(role.equals("ROLE_OFFICIAL"))
		{
			status = 3;
		}
		return dbconnection.toBeApproved(status);
	}

	@Override
	public boolean approve(String name, String transactionId) {
		IUser user = userManager.getUser(name);
		String role = user.getRoleId();
		int status=0;
		if(role.equals("ROLE_REGULAREMPLOYEE"))
		{
			status = 2;
		}
		else if(role.equals("ROLE_MANAGER"))
		{
			status = 3;
		}
		else if(role.equals("ROLE_OFFICIAL"))
		{
			status = 1;
		}
		return dbconnection.finalizeByApproval(transactionId, status);
	}
	
	@Override
	public boolean reject(String name, String transactionId) {
		IUser user = userManager.getUser(name);
		ITransaction trans = getTransaction(transactionId);
		String role = user.getRoleId();
		int status=0;
		if((role.equals("ROLE_REGULAREMPLOYEE") && trans.getStatus()==0) || (role.equals("ROLE_MANAGER") && trans.getStatus()==2) || (role.equals("ROLE_OFFICIAL") && trans.getStatus()==3) )
		{
			return dbconnection.reject(transactionId);
		}
		return false;
		
	}
	
	

}
