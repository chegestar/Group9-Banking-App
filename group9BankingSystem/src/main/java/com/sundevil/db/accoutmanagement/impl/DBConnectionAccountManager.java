package com.sundevil.db.accoutmanagement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sundevil.db.ADBConnectionManager;
import com.sundevil.db.accountmanagement.IDBConnectionAccountManager;
import com.sundevil.domain.IAccount;
import com.sundevil.domain.implementation.Account;
/**
 * @author priyankarupani
 *
 */
@Service
public  class DBConnectionAccountManager extends ADBConnectionManager implements IDBConnectionAccountManager{

	@Override
	public boolean createAccount(IAccount account) {
		// TODO Auto-generated method stub
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String getUserId = "select user_id from users where username=?";
		String userid = insert.queryForObject(getUserId, new Object[]{account.getUserId()}, String.class);
		account.setAccountId("ACCOUNT_"+UUID.randomUUID().hashCode());
		String updateStatement ="INSERT INTO bank.account (ACCOUNT_ID, TYPE, BALANCE,USER_ID) VALUES (?, ?, ?, ?)";
		insert.update(updateStatement,
		        new Object[] { account.getAccountId(), account.getType(), account.getBalance(), userid });
		return true;
	}

	@Override
	public String getBalance(String accountId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select balance from account where account_id=?";
		String ret = select.queryForObject(query, new Object[]{accountId}, String.class);
		
		return ret;
	}
	
	@Override
	public boolean setBalance(String accountid, String balance) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "update account SET balance=? where account_id=?";
		select.queryForObject(query, new Object[]{balance,accountid}, String.class);
		
		return true;
	}

	@Override
	public String getType(String userName) {
		// TODO Auto-generated method stub
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUserId = "select user_id from users where username=?";
		String userid = select.queryForObject(getUserId, new Object[]{userName}, String.class);
		String query = "select type from account where user_id=?";
		String ret = select.queryForObject(query, new Object[]{userid}, String.class);
		
		return ret;
	}
	
	@Override
	public String getAccountId(String userName) {
		// TODO Auto-generated method stub
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUserId = "select user_id from users where username=?";
		String userid = select.queryForObject(getUserId, new Object[]{userName}, String.class);
		String query = "select account_id from account where user_id=?";
		String ret = select.queryForObject(query, new Object[]{userid}, String.class);
		
		return ret;
	}


	@Override
	public boolean deleteAccount(String account) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		String updateStatement ="DELETE from account where account_id=?";
		update.update(updateStatement, new Object[]{account});
		return true;
	}

	@Override
	public List<IAccount> getAllAccounts(String userName) {
		List<IAccount> ret = new ArrayList<IAccount>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUserId = "select user_id from users where username=?";
		String userid = select.queryForObject(getUserId, new Object[]{userName}, String.class);
		String query = "select type, balance, account_id  from account where user_id=?";
		List<Map<String, Object>> l = select.queryForList(query, new Object[]{userid});
		for(Map<String,Object> m : l)
		{
			IAccount acc = new Account();
			acc.setAccountId((String)(m.get("account_id")));
			acc.setBalance((String)(m.get("balance")));
			acc.setType((String)(m.get("type")));
			acc.setUserId(userName);
			ret.add(acc);
		}
		return ret;
	}

}
