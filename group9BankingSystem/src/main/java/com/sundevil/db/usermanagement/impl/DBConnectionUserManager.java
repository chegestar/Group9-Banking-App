/**
 * 
 */
package com.sundevil.db.usermanagement.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sundevil.db.ADBConnectionManager;
import com.sundevil.db.usermanagement.IDBConnectionUserManager;
import com.sundevil.domain.IUser;
import com.sundevil.domain.implementation.User;

/**
 * @author satyaswaroop
 * 
 */
@Service
public class DBConnectionUserManager extends ADBConnectionManager implements
		IDBConnectionUserManager {

	private HashMap<String, String> roleAndRoleID;
	private HashMap<String, String> deptAndDeptID;

	@PostConstruct
	public void initRoles() {
		roleAndRoleID = new HashMap<String, String>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select role_id,authority from roles";
		List<Map<String, Object>> result = select.queryForList(query);
		for (Map<String, Object> m : result) {
			String id = (String) m.get("role_id");
			String auth = (String) m.get("authority");
			roleAndRoleID.put(id, auth);
		}
	}
	
	@Override
	public boolean checkValidDepartmentName(String departmentName) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select count(DEPARTMENT_NAME) from bank.department where DEPARTMENT_NAME = ?";
		int i = select.queryForInt(query, new Object[] { departmentName });
		if (i == 0)
			return false;
		else
			return true;
  }

	@PostConstruct
	public void initDepts() {
		deptAndDeptID = new HashMap<String, String>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME FROM `department`";
		List<Map<String, Object>> result = select.queryForList(query);
		for (Map<String, Object> m : result) {
			String id = (String) m.get("DEPARTMENT_ID");
			String auth = (String) m.get("DEPARTMENT_NAME");
			deptAndDeptID.put(id, auth);
		}
	}

	@Transactional
	@Override
	public boolean addUser(IUser user) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		user.setUserID("USER_" + UUID.randomUUID());
		String getRoleId = "Select role_id from roles where AUTHORITY = ?";
		String roleId = insert.queryForObject(getRoleId,
				new Object[] { user.getRoleId() }, String.class);
		String updateStatement = "INSERT INTO bank.users (USER_ID, LASTNAME, FIRSTNAME, SSN, EMAILID, ADDRESS, ZIPCODE, GENDER, CONTACT_NUMBER, USERNAME, PASSWORD, ENABLED, ROLE_ID) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";
		insert.update(
				updateStatement,
				new Object[] { user.getUserID(), user.getLastName(),
						user.getFirstName(), user.getSsn(), user.getEmailId(),
						user.getAddress(), user.getZipCode(), user.getGender(),
						user.getContactNumber(), user.getUserName(),
						user.getPassword(), new Integer(0), roleId });
		assignDepartment(user.getUserName(), user.getDepartment(),
				user.getSalary());
		return true;

	}

	@Override
	public boolean chkDuplicateUserName(String userName) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select count(USERNAME) from bank.users where USERNAME = ?";
		int result = select.queryForInt(query, new Object[] { userName });
		if (result == 0)
			return true;
		return false;
	}

	@Override
	public String getDepartment(String userName) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUserId = "select user_id from users where username=?";
		String userid = select.queryForObject(getUserId,
				new Object[] { userName }, String.class);
		String query = "select dept.department_name from bank.internal_user usr, bank.department dept where usr.user_id = ? AND usr.department_id = dept.department_id";
		String ret = select.queryForObject(query, new Object[] { userid },
				String.class);

		return ret;
	}

	@Override
	public boolean assignDepartment(String userName, String department_name,
			long l) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String getUserId = "select user_id from users where username=?";
		String getDeptId = "select DEPARTMENT_ID from department where DEPARTMENT_NAME = ?";

		String userid = insert.queryForObject(getUserId,
				new Object[] { userName }, String.class);
		String departmentid = insert.queryForObject(getDeptId,
				new Object[] { department_name }, String.class);

		String updateStatement = "INSERT INTO bank.internal_user (USER_ID, DEPARTMENT_ID, SALARY) VALUES (?, ?, ?)";
		insert.update(updateStatement, new Object[] { userid, departmentid, l });
		return true;
	}

	@Override
	public List<String> getPendingUsers() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUsers = "select username from users where ENABLED=0";
		List<String> ret = select.queryForList(getUsers, String.class);
		return ret;
	}

	@Override
	public void approveUser(String username) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		String updateStatement = "UPDATE users SET ENABLED=1 where users.username=?";
		update.update(updateStatement, new Object[] { username });
	}

	@Override
	public String getEmailId(String username) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUserId = "select EMAILID from users where username=?";
		String emailId = select.queryForObject(getUserId,
				new Object[] { username }, String.class);
		return emailId;
	}

	@Override
	public void deleteUser(String username) throws Exception {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		String role= getRole(username);
		if(!role.equalsIgnoreCase("ROLE_ADMIN")||!role.contains("ROLE_ADMIN"))
		{
		String updateStatement = "DELETE from users where users.username=?";
		update.update(updateStatement, new Object[] { username });
		}
		else
			throw new Exception("cannot delete an admin");
			

	}

	@Override
	public IUser getUser(String username) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String selectStatement = "select username, emailid, contact_number, role_id from users where username=?";
		return select.queryForObject(selectStatement,
				new Object[] { username }, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setContactNumber(rs.getString("contact_number"));
						user.setUserName(rs.getString("username"));
						user.setEmailId(rs.getString("emailid"));
						user.setRoleId(roleAndRoleID.get(rs
								.getString("role_id")));
						return user;
					}
				});
	}

	/**
	 * This method returns all the internal users list of a particular
	 * department
	 */
	@Override
	public List<IUser> getAllEmployees(String username) {
		List<IUser> ret = new ArrayList<IUser>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String department_name = getDepartment(username);
		String getDeptId = "select DEPARTMENT_ID from department where DEPARTMENT_NAME = ?";
		String departmentid = select.queryForObject(getDeptId,
				new Object[] { department_name }, String.class);
		String query = "select usr.firstname,usr.lastname, usr.emailid, usr.contact_number, usr.role_id, intusr.salary,usr.username from bank.internal_user intusr, bank.users usr where usr.user_id = intusr.user_id AND intusr.department_id = ?";
		List<Map<String, Object>> l = select.queryForList(query,
				new Object[] { departmentid });
		for (Map<String, Object> m : l) {
			IUser u = new User();
			u.setFirstName((String) (m.get("firstname")));
			u.setContactNumber((String) (m.get("contact_number")));
			u.setRoleId((roleAndRoleID.get((String) m.get("role_id"))));
			u.setSalary((Integer) ((m.get("salary"))));
			u.setUserName((String) (m.get("username")));
			u.setEmailId((String) (m.get("emailid")));
			u.setLastName((String) (m.get("lastname")));
			ret.add(u);
		}
		return ret;

	}

	@Override
	public boolean chkExUserName(String userName) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String getUserRole = "select role_id from users where username=?";
		if (chkDuplicateUserName(userName)) {
			return false;
		}
		String roleId = select.queryForObject(getUserRole,
				new Object[] { userName }, String.class);
		if (roleAndRoleID.get(roleId).equals("EXTERNAL_USER"))
			return true;
		return false;
	}

	@Override
	public boolean setRole(String userName, String role) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String updateUserRole = "UPDATE users SET role_id=? where users.username=?";
		for (String id : roleAndRoleID.keySet()) {
			if ((roleAndRoleID.get(id)).equals(role)) {
				select.update(updateUserRole, new Object[] { id, userName });
				return true;
			}

		}
		return false;

	}

	@Override
	public boolean removeUser(String username) {
		boolean ret = false;
		JdbcTemplate update = new JdbcTemplate(dataSource);
		String updateStatement = "UPDATE  `internal_user` SET DEPARTMENT_ID = 'DEPT_5' WHERE `USER_ID` = ( SELECT user_id FROM users WHERE username = ? ) ";
		update.update(updateStatement, new Object[] { username });

		return ret;
	}

	@Override
	public List<IUser> getUnassignedEmployees() {
		List<IUser> ret = new ArrayList<IUser>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select usr.firstname,usr.lastname, usr.emailid, usr.contact_number, intusr.salary,usr.username from bank.internal_user intusr, bank.users usr where usr.user_id = intusr.user_id AND intusr.department_id = 'DEPT_5'";
		List<Map<String, Object>> l = select.queryForList(query,
				new Object[] {});
		for (Map<String, Object> m : l) {
			IUser u = new User();
			u.setFirstName((String) (m.get("firstname")));
			u.setContactNumber((String) (m.get("contact_number")));
			u.setRoleId((roleAndRoleID.get((String) m.get("role_id"))));
			u.setSalary((Integer) ((m.get("salary"))));
			u.setUserName((String) (m.get("username")));
			u.setEmailId((String) (m.get("emailid")));
			u.setLastName((String) (m.get("lastname")));
			ret.add(u);
		}
		return ret;
	}

	@Override
	public boolean setDepartment(String username, String department) {

		JdbcTemplate select = new JdbcTemplate(dataSource);

		String getDeptId = "select DEPARTMENT_ID from department where DEPARTMENT_NAME = ?";
		String departmentid = select.queryForObject(getDeptId,
				new Object[] { department }, String.class);
		String updateStatement = "UPDATE  `internal_user` SET DEPARTMENT_ID = ? WHERE `USER_ID` = ( SELECT user_id FROM users WHERE username = ? ) ";
		select.update(updateStatement, new Object[] { departmentid, username });
		return true;
	}

	@Override
	public List<IUser> getManagers() {
		List<IUser> ret = new ArrayList<IUser>();
		JdbcTemplate select = new JdbcTemplate(dataSource);

		String query = "select usr.firstname,usr.lastname, usr.emailid, usr.contact_number, usr.role_id, intusr.salary,usr.username ,intusr.DEPARTMENT_ID from bank.internal_user intusr, bank.users usr where usr.user_id = intusr.user_id AND usr.role_id='role_4'";
		List<Map<String, Object>> l = select.queryForList(query,
				new Object[] {});
		for (Map<String, Object> m : l) {
			IUser u = new User();
			u.setFirstName((String) (m.get("firstname")));
			u.setContactNumber((String) (m.get("contact_number")));
			u.setRoleId((roleAndRoleID.get((String) m.get("role_id"))));
			u.setSalary((Integer) ((m.get("salary"))));
			u.setUserName((String) (m.get("username")));
			u.setEmailId((String) (m.get("emailid")));
			u.setLastName((String) (m.get("lastname")));
			u.setDepartment(deptAndDeptID.get((String) (m.get("DEPARTMENT_ID"))));
			ret.add(u);
		}
		return ret;
	}

	@Override
	public boolean validatePassword(String username, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String query = "select password from users where username=? ";
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String hashed = select.queryForObject(query, new Object[] { username },
				String.class);
		if (encoder.matches(password, hashed))
			return true;
		else
			return false;
	}

	@Override
	public boolean updatePassword(String username, String password) {
		String query = "update users SET password = ? where username = ?";
		JdbcTemplate select = new JdbcTemplate(dataSource);
		select.update(query, new Object[] { password, username });
		return true;
	}

	@Override
	public boolean chkEmail(String emailaddress) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String query = "select count(USERNAME) from bank.users where EMAILID = ?";
		int i = insert.queryForInt(query, new Object[] { emailaddress });
		if (i != 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean chkSSN(String ssn) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String query = "select count(USERNAME) from bank.users where SSN = ?";
		int i = insert.queryForInt(query, new Object[] { ssn });
		if (i != 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean chkContactNumber(String contactNumber) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String query = "select count(USERNAME) from bank.users where contact_number = ?";
		int i = insert.queryForInt(query, new Object[] { contactNumber });
		if (i != 0)
			return false;
		else
			return true;
	}

	@Override
	public List<IUser> getAllUsers() {
		List<IUser> ret = new ArrayList<IUser>();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select usr.emailid, usr.contact_number, usr.username from  bank.users usr, bank.internal_user iusr where usr.role_id!='role_1' and usr.user_id=iusr.user_id";
		List<Map<String, Object>> l = select.queryForList(query);
		for (Map<String, Object> m : l) {
			IUser u = new User();
			u.setContactNumber((String) (m.get("contact_number")));
			u.setUserName((String) (m.get("username")));
			u.setEmailId((String) (m.get("emailid")));
			ret.add(u);
		}
		return ret;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean checkLoginOTP(String name, String parameter) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String statusUpdate ="UPDATE onetimelogin SET verified=1 where username=?"; 
		String query = "select password  from onetimelogin where username =  ?  and  StartTime >= (SELECT date_sub( now( ) , INTERVAL '5:0' MINUTE_SECOND ) )";
		String hashed = select.queryForObject(query, new Object[] { name },
				String.class);
		if (encoder.matches(parameter, hashed))
		{
			select.update(statusUpdate,new Object[]{name});
			return true;
		}
		else
			return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveOTP(String name, String parameter) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		String delete = "DELETE FROM bank.onetimelogin where username=? ";
		insert.update(delete, new Object[] { name });
		String saveKey = "INSERT INTO bank.onetimelogin (username, password) VALUES (?, ?)";
		insert.update(saveKey, new Object[] { name, parameter });
		return true;
	}
	
	@Override
	public boolean checkOTPValidity(String name) {
		
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String query = "select count(*)  from onetimelogin where username =  ? and verified = 1 and StartTime >= (SELECT date_sub( now( ) , INTERVAL '30:0' MINUTE_SECOND ) )";
		int i = select.queryForInt(query, new Object[] { name });
		if (i==1)
			return true;
		else
			return false;
	}

	@Override
	public String getRole(String user) {
		String query = "select r.authority from users u, roles r  where r.role_id = u.role_id and u.username =?  ";
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert
				.queryForObject(query, new Object[] { user }, String.class);

	}
	
	@Override
	public IUser getUserDTOFor(String username,String emailId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		String selectStatement = "select username, emailid, contact_number, role_id from users where username=? and emailid=?";
		return select.queryForObject(selectStatement,
				new Object[] { username, emailId}, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setContactNumber(rs.getString("contact_number"));
						user.setUserName(rs.getString("username"));
						user.setEmailId(rs.getString("emailid"));
						user.setRoleId(roleAndRoleID.get(rs
								.getString("role_id")));
						return user;
					}
				});
	}
}
