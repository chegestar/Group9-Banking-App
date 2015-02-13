package com.sundevil.service.accountmanagement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundevil.db.accountmanagement.IDBConnectionAccountManager;
import com.sundevil.db.transactionmanagement.IDBConnectionTransactionManager;
import com.sundevil.domain.IAccount;
import com.sundevil.domain.ITransaction;
import com.sundevil.service.accountmanagement.IAccountManager;

@Service
public class AccountManager implements IAccountManager{

@Autowired
IDBConnectionAccountManager dbConnectionAccountManager;

@Autowired
IDBConnectionTransactionManager  dBConnectionTransactionManager;

@Override
public boolean createAccount(IAccount account){
	dbConnectionAccountManager.createAccount(account);
	return true;
}



@Override
public String getType(String account) {
	return dbConnectionAccountManager.getType(account);
}

@Override
public String getBalance(String account) {
	return dbConnectionAccountManager.getBalance(account);
}

@Override
public boolean deleteAccount(String account) {
	return dbConnectionAccountManager.deleteAccount(account);
	
}




@Override
public boolean setBalance(String account, String balance) {
	// TODO Auto-generated method stub
	return false;
}



@Override
public List<IAccount> getAllBankAccounts(String name) {
	return dbConnectionAccountManager.getAllAccounts(name);
	
}

@Override
public List<ITransaction> getAllTransactions(String accid)
{
	return dBConnectionTransactionManager.getAllTransactions(accid);
}

}
