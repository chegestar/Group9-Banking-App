/**
 * 
 */
package com.sundevil.security.transactionmanagement;

import java.util.List;

import com.sundevil.domain.ITransaction;
import com.sundevil.domain.implementation.Transaction;

/**
 * @author satyaswaroop
 *
 */
public interface ITransactionManager {
	
	
	

	

	boolean makeDeposit(ITransaction transaction);

	boolean makeWithdraw(Transaction trans);

	boolean checkExists(String destAccount);


	ITransaction getTransaction(String transId);

	boolean checkKey(String parameter, String transId);

	boolean makeTransfer(Transaction trans, String username);

	List<ITransaction> getTransactionsToBeApproved(String username);

	boolean approve(String name, String transactionId);

	boolean reject(String name, String transactionId);

}
