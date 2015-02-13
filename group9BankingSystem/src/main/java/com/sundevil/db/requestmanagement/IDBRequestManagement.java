package com.sundevil.db.requestmanagement;

import java.util.List;
import java.util.Map;

public interface IDBRequestManagement {

	boolean placeRequest(String username, String account_id);

	Map<String, Integer> getRequests(String username);

	Map<String, Integer> getAccountRequest(String account_id);

	boolean rejectRequest(String username, String account_id);

	boolean approveRequest(String username, String account_id);

	boolean isAccessible(String username, String account_id);

	boolean chkExistance(String username, String account_id);

	List<String> getAllAccounts();

	boolean revokeRequest(String username, String account_id);

}