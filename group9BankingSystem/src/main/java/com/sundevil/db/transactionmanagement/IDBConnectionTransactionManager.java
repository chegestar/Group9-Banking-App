package com.sundevil.db.transactionmanagement;

import java.util.List;

import com.sundevil.domain.ITransaction;
import com.sundevil.domain.implementation.Transaction;

public interface IDBConnectionTransactionManager {
	public abstract boolean addTransaction(ITransaction transaction);
	public abstract ITransaction getTransaction(String transaction);
	List<ITransaction> getAllTransactions(String username);
	public abstract boolean checkExists(String destAccount);
	public abstract boolean addTransferTransaction(Transaction trans,
			String randKey);
	boolean finalizeByKey(String key, String transactionId);
	
	boolean finalizeByApproval(String transactionId, int status);
	List<ITransaction> toBeApproved( int status);
	public abstract boolean reject(String transactionId); 
	
}
