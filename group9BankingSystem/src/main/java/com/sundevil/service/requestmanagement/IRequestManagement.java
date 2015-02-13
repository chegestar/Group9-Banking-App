/**
 * 
 */
package com.sundevil.service.requestmanagement;

import java.util.List;
import java.util.Map;

/**
 * @author satyaswaroop
 *
 */
public interface IRequestManagement {

	boolean placeRequest(String username, String accountId);

	boolean chkExistance(String name, String accId);

	List<String> getAllAccounts();

	Map<String, Integer> getAllRequests(String username);

	Map<String, Integer> getAccountRequests(String accountId);

	boolean approveRequest(String accountId, String userName);

	boolean rejectRequest(String accountId, String userName);

	boolean revokeRequest(String accountId, String userName);

	boolean isAccessable(String accountId, String userName);

}
