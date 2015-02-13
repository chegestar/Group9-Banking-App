package com.sundevil.domain;
/**
 * @author priyankarupani
 *
 */
public interface IAccount {

	public abstract String getType();
	public abstract void setType(String type);
	public abstract String getAccountId();
	public abstract void setAccountId(String accountId);
	public abstract String getBalance();
	public abstract void setBalance(String balance);
	public abstract String getUserId();
	public abstract void setUserId(String userId);
}
