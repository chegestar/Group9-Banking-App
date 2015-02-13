package com.sundevil.db.transactionmanagement.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sundevil.db.ADBConnectionManager;
import com.sundevil.db.transactionmanagement.IDBConnectionTransactionManager;
import com.sundevil.domain.ITransaction;
import com.sundevil.domain.implementation.Transaction;

/**
 * @author priyankarupani
 * 
 */
@Service
public class DBConnectionTransactionManager extends ADBConnectionManager
		implements IDBConnectionTransactionManager {

	/**
	 * This is used for deposit only
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean addTransaction(ITransaction transaction) {
		// TODO Auto-generated method stub
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		if (transaction.getType().equals("DEPOSIT")) {
			transaction.setOriginAccount(transaction.getDestAccount());

			transaction.setTransId("TRANS_" + UUID.randomUUID());
			String updateStatement = "INSERT INTO bank.transaction (TRANS_ID, AMOUNT, TYPE, DESTACCOUNT,ORIGINACCOUNT) VALUES (?, ?, ?, ?, ?)";
			insert.update(
					updateStatement,
					new Object[] { transaction.getTransId(),
							transaction.getAmount(), transaction.getType(),
							transaction.getDestAccount(),
							transaction.getOriginAccount() });
			String updateAccount = "UPDATE account SET balance=balance+? where account_id = ?";
			insert.update(updateAccount, new Object[] {
					transaction.getAmount(), transaction.getDestAccount() });
			return true;
		} else if (transaction.getType().equals("WITHDRAW")) {
			transaction.setOriginAccount(transaction.getDestAccount());

			transaction.setTransId("TRANS_" + UUID.randomUUID());
			String updateStatement = "INSERT INTO bank.transaction (TRANS_ID, AMOUNT, TYPE, DESTACCOUNT,ORIGINACCOUNT) VALUES (?, ?, ?, ?, ?)";
			insert.update(
					updateStatement,
					new Object[] { transaction.getTransId(),
							transaction.getAmount(), transaction.getType(),
							transaction.getDestAccount(),
							transaction.getOriginAccount() });
			String updateAccount = "UPDATE account SET balance=balance-? where account_id = ?";
			insert.update(updateAccount, new Object[] {
					transaction.getAmount(), transaction.getDestAccount() });
			return true;
		}
		/*
		 * else if (transaction.getType().equals("TRANSFER")) {
		 * transaction.setTransId("TRANS_" + UUID.randomUUID()); String
		 * updateStatement =
		 * "INSERT INTO bank.transaction (TRANS_ID, AMOUNT, TYPE, DESTACCOUNT,ORIGINACCOUNT) VALUES (?, ?, ?, ?, ?)"
		 * ; insert.update( updateStatement, new Object[] {
		 * transaction.getTransId(), transaction.getAmount(),
		 * transaction.getType(), transaction.getDestAccount(),
		 * transaction.getOriginAccount() }); String updateAccount =
		 * "UPDATE account SET balance=balance-? where account_id = ?";
		 * insert.update(updateAccount, new Object[] { transaction.getAmount(),
		 * transaction.getOriginAccount() }); String updateDestAccount =
		 * "UPDATE account SET balance=balance+? where account_id = ?";
		 * insert.update(updateDestAccount, new Object[] {
		 * transaction.getAmount(), transaction.getDestAccount() }); return
		 * true; }
		 */

		return false;
	}

	@Override
	public ITransaction getTransaction(String transaction) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String selectStatement = "select trans_id,amount,type,destaccount,originaccount, status from transaction where trans_id=?";
		return select.queryForObject(selectStatement,
				new Object[] { transaction }, new RowMapper<Transaction>() {

					@Override
					public Transaction mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Transaction trans = new Transaction();
						trans.setTransId(rs.getString("trans_id"));
						trans.setAmount(rs.getString("amount"));
						trans.setType(rs.getString("type"));
						trans.setDestAccount(rs.getString("destaccount"));
						trans.setOriginAccount(rs.getString("originaccount"));
						trans.setStatus(Integer.parseInt(rs.getString("status")));
						return trans;
					}
				});
	}

	@Override
	public List<ITransaction> getAllTransactions(String accountid) {
		List<ITransaction> ret = new ArrayList<ITransaction>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select trans_id,amount,type,destaccount,originaccount,status from transaction where originaccount = ?";
		List<Map<String, Object>> l1 = select.queryForList(query,
				new Object[] { accountid });
		for (Map<String, Object> m : l1) {
			ITransaction i = new Transaction();
			i.setTransId((String) (m.get("trans_id")));
			i.setAmount((String) (m.get("amount")));
			i.setType((String) (m.get("type")));
			i.setDestAccount((String) (m.get("destaccount")));
			i.setOriginAccount((String) (m.get("originaccount")));
			i.setStatus((int) (m.get("status")));
			if (!ret.contains(i))
				ret.add(i);
		}

		query = "select trans_id,amount,type,destaccount,originaccount,status from transaction where destaccount = ?";
		List<Map<String, Object>> l2 = select.queryForList(query,
				new Object[] { accountid });
		for (Map<String, Object> m : l2) {
			ITransaction i = new Transaction();

			i.setTransId((String) (m.get("trans_id")));
			i.setAmount((String) (m.get("amount")));
			i.setType((String) (m.get("type")));
			i.setDestAccount((String) (m.get("destaccount")));
			i.setOriginAccount((String) (m.get("originaccount")));
			i.setStatus((int) (m.get("status")));
			if (!ret.contains(i))
				ret.add(i);
		}
		return ret;

	}

	@Override
	public boolean checkExists(String destAccount) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select count(*)  from account where ACCOUNT_ID=?";
		int result = select.queryForInt(query, new Object[] { destAccount });
		if (result >= 1)
			return true;
		else
			return false;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean addTransferTransaction(Transaction transaction,
			String randKey) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		transaction.setTransId("TRANS_" + UUID.randomUUID());
		String updateStatement = "INSERT INTO bank.transaction (TRANS_ID, AMOUNT, TYPE, DESTACCOUNT,ORIGINACCOUNT) VALUES (?, ?, ?, ?, ?)";
		insert.update(
				updateStatement,
				new Object[] { transaction.getTransId(),
						transaction.getAmount(), transaction.getType(),
						transaction.getDestAccount(),
						transaction.getOriginAccount() });
		if (Long.parseLong(transaction.getAmount()) <= 5000
				&& Long.parseLong(transaction.getAmount()) > 0) {
			String saveKey = "INSERT INTO bank.onetimepass (TransactionId, Password) VALUES (?, ?)";
			insert.update(saveKey, new Object[] { transaction.getTransId(),
					randKey });
		}
		return true;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean finalizeByKey(String key, String transactionId) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String query = "select password from onetimepass where  TransactionId=? and StartTime >= (SELECT date_sub( now( ) , INTERVAL '5:0' MINUTE_SECOND ) ) ";
		JdbcTemplate check = new JdbcTemplate(dataSource);
		ITransaction trans;
		//String query = "select count(*)  from onetimepass where TransactionId=? and Password = ? and  StartTime >= (SELECT date_sub( now( ) , INTERVAL '5:0' MINUTE_SECOND ) )";
		String hashed = check.queryForObject(query, new Object[] { transactionId },
				String.class);
		
		if (encoder.matches(key, hashed)) {
			trans = getTransaction(transactionId);
			String updateTransaction = "UPDATE transaction SET status=1 where TRANS_ID = ?";
			check.update(updateTransaction, new Object[] { trans.getTransId() });
			String updateAccount = "UPDATE account SET balance=balance-? where account_id = ?";
			check.update(
					updateAccount,
					new Object[] { trans.getAmount(), trans.getOriginAccount() });
			String updateDestAccount = "UPDATE account SET balance=balance+? where account_id = ?";
			check.update(updateDestAccount, new Object[] { trans.getAmount(),
					trans.getDestAccount() });
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean finalizeByApproval(String transactionId, int status) {
		JdbcTemplate check = new JdbcTemplate(dataSource);
		ITransaction trans = getTransaction(transactionId);
		String updateTransaction = "UPDATE transaction SET status=? where TRANS_ID = ?";
		check.update(updateTransaction,
				new Object[] { status, trans.getTransId() });
		if (status == 1) {
			String updateAccount = "UPDATE account SET balance=balance-? where account_id = ?";
			check.update(
					updateAccount,
					new Object[] { trans.getAmount(), trans.getOriginAccount() });
			String updateDestAccount = "UPDATE account SET balance=balance+? where account_id = ?";
			check.update(updateDestAccount, new Object[] { trans.getAmount(),
					trans.getDestAccount() });
		}

		return true;

	}

	@Override
	public List<ITransaction> toBeApproved(int status) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		List<ITransaction> ret = new ArrayList<ITransaction>();
		String query = "select trans_id,amount,type,destaccount,originaccount,status from transaction where status = ?";
		List<Map<String, Object>> l2 = select.queryForList(query,
				new Object[] { status });
		for (Map<String, Object> m : l2) {
			ITransaction i = new Transaction();
			i.setTransId((String) (m.get("trans_id")));
			i.setAmount((String) (m.get("amount")));
			i.setType((String) (m.get("type")));
			i.setDestAccount((String) (m.get("destaccount")));
			i.setOriginAccount((String) (m.get("originaccount")));
			i.setStatus((int) (m.get("status")));
			ret.add(i);
		}

		return ret;

	}

	@Override
	public boolean reject(String transactionId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "DELETE from  transaction where trans_id = ?";
		select.update(query,new Object[]{transactionId});
		return true;
	}

}
