/**
 * 
 */
package com.sundevil.service.accountmanagement;

import java.util.List;

import com.sundevil.domain.IAccount;
import com.sundevil.domain.ITransaction;


/**
 * @author apurva
 *
 */
public interface IAccountManager {

	public abstract boolean createAccount(IAccount account);

	public abstract String getType(String account);
	
	public abstract String getBalance(String account);
	
	public abstract boolean deleteAccount(String account);
	public abstract boolean setBalance(String account, String balance);

	public abstract List<IAccount> getAllBankAccounts(String name);

	public abstract List<ITransaction> getAllTransactions(String accid);

}
