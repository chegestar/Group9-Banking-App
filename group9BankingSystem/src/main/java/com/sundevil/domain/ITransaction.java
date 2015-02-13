package com.sundevil.domain;
/**
 * @author priyankarupani
 *
 */
public interface ITransaction {

	public abstract String getTransId();
	public abstract void setTransId(String transId);
	public abstract String getAmount();
	public abstract void setAmount(String amount);
	public abstract String getDestAccount();
	public abstract void setDestAccount(String destAccount);
	public abstract String getOriginAccount();
	public abstract void setOriginAccount(String originAccount);
	public abstract String getType();
	public abstract void setType(String type);
	public abstract int getStatus();
	public abstract void setStatus(int status);
}
