package com.sundevil.db.accountmanagement;

import java.util.List;

import com.sundevil.domain.IAccount;
/**
 * @author priyankarupani
 *
 */
public interface IDBConnectionAccountManager {
	public abstract boolean createAccount(IAccount account);
	
	public abstract String getType(String account);
	public abstract String getBalance(String account);
	public abstract boolean setBalance(String accountid, String balance);
	public abstract String getAccountId(String account);
	public abstract boolean deleteAccount(String account);

	public abstract List<IAccount> getAllAccounts(String name);
}

