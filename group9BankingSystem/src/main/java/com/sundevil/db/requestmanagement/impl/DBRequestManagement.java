/**
 * 
 */
package com.sundevil.db.requestmanagement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sundevil.db.ADBConnectionManager;
import com.sundevil.db.requestmanagement.IDBRequestManagement;
import com.sundevil.db.usermanagement.IDBConnectionUserManager;

/**
 * @author satyaswaroop
 *
 */
@Service
public class DBRequestManagement extends ADBConnectionManager implements IDBRequestManagement{
	
	@Autowired
	IDBConnectionUserManager userManager;
	
	@Override
	public 	boolean placeRequest(String username, String account_id)
	{
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		
		String getUserId = "select user_id from users where username=?";
		String userId = insert.queryForObject(getUserId, new Object[]{username}, String.class);
		String query = "insert into requests(user_id, account_id) values(?,?)";
		insert.update(query, new Object[]{ userId, account_id});
		return true;
		
	}
	
	@Override
	public Map<String,Integer> getRequests(String username)
	{
		HashMap<String,Integer> ret = new HashMap<String , Integer>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getAccounts = "select account_id,status from requests where user_id=(select user_id from users where username=?)";
		List<Map<String, Object>> l = select.queryForList(getAccounts, new Object[]{username});
		for (Map<String, Object> m : l) {
			ret.put((String)m.get("account_id"),(Integer) m.get("status"));
		}
		return ret;
	}
	
	@Override
	public Map<String,Integer> getAccountRequest(String account_id)
	{
		HashMap<String,Integer> ret = new HashMap<String , Integer>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getRequests = "select u.username,req.status from users u, requests req where u.user_id = req.user_id and req.account_id = ?";
		List<Map<String, Object>> l = select.queryForList(getRequests, new Object[]{account_id});
		for (Map<String, Object> m : l) {
			ret.put((String)m.get("username"),(Integer) m.get("status"));
		}
		return ret;
	}
	
	@Override
	public boolean rejectRequest(String username,String account_id)
	{
		String query = "delete from requests where user_id = (select user_id from users where username = ?) and account_id = ?";
		JdbcTemplate select = new JdbcTemplate(dataSource);
		select.update(query,new Object[]{username, account_id});
		return true;
	}
	
	@Override
	public boolean approveRequest(String username,String account_id)
	{
		String query = "update requests SET status = 1 where account_id=? and user_id = (select user_id from users where username = ?)";
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(query,new Object[]{account_id, username});
		return true;
	}
	
	@Override
	public boolean revokeRequest(String username,String account_id)
	{
		String query = "update requests SET status = 0 where account_id=? and user_id = (select user_id from users where username = ?)";
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(query,new Object[]{account_id, username});
		return true;
	}
	
	@Override
	public boolean isAccessible(String username, String account_id)
	{
		String query = "select status from  requests where user_id = (select user_id from users where username = ?) and account_id = ?";
		JdbcTemplate select = new JdbcTemplate(dataSource);
		Integer integer = select.queryForObject(query,new Object[]{username,account_id},Integer.class);
		if(integer==0)
			return false;
		else if(integer == 1)
		return true;
		return false;
	}
	
	@Override
	public boolean chkExistance(String username, String account_id)
	{
		String query = "select count(*) from  requests where user_id = (select user_id from users where username = ?) and account_id = ?";
		JdbcTemplate select = new JdbcTemplate(dataSource);
		int i = select.queryForInt(query,new Object[]{username,account_id});
		if(i==0)
			return true;
		else
			return false;
	}

	@Override
	public List<String> getAllAccounts() {
		String query = "select account_id from account";
		JdbcTemplate select = new JdbcTemplate(dataSource);
		List<String> list = select.queryForList(query, String.class);
		return list;
	}
}
