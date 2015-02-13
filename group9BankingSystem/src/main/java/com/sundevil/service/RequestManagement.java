/**
 * 
 */
package com.sundevil.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundevil.db.requestmanagement.IDBRequestManagement;
import com.sundevil.db.transactionmanagement.IDBConnectionTransactionManager;
import com.sundevil.service.requestmanagement.IRequestManagement;

/**
 * @author satyaswaroop
 * 
 */
@Service
public class RequestManagement implements IRequestManagement {

	@Autowired
	IDBRequestManagement dbManager;

	@Autowired
	IDBConnectionTransactionManager tranManager;

	@Override
	public boolean placeRequest(String username, String account_id) {
		if (tranManager.checkExists(account_id))
			return dbManager.placeRequest(username, account_id);
		else
			return false;
	}

	@Override
	public boolean chkExistance(String name, String accId) {
		return dbManager.chkExistance(name, accId);
	}

	@Override
	public List<String> getAllAccounts() {
		
		return dbManager.getAllAccounts();
	}
	
	
	@Override
	public Map<String, Integer> getAllRequests(String username) {
		
		return dbManager.getRequests(username);
	}
	
	@Override
	public Map<String, Integer> getAccountRequests(String accountId) {
		
		return dbManager.getAccountRequest(accountId);
	}

	@Override
	public boolean approveRequest(String accountId, String userName) {
	if(!chkExistance(userName,accountId))
		return dbManager.approveRequest(userName, accountId);
		return false;
	}

	@Override
	public boolean rejectRequest(String accountId, String userName) {
		if(!chkExistance(userName,accountId))
			return dbManager.rejectRequest(userName, accountId);
		return false;
	}

	@Override
	public boolean revokeRequest(String accountId, String userName) {
		if(!chkExistance(userName,accountId))
			return dbManager.revokeRequest(userName, accountId);
		return false;
	}
	
	@Override
	public boolean isAccessable(String accountId, String userName) {
		if(!chkExistance(userName,accountId))
			return dbManager.isAccessible(userName, accountId);
		return false;
	}

}
