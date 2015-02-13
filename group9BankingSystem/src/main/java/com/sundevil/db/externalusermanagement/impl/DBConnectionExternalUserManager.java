package com.sundevil.db.externalusermanagement.impl;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author priyankarupani
 *
 */
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sundevil.db.ADBConnectionManager;
import com.sundevil.db.externalusermanagement.IDBConnectionExternalUserManager;
import com.sundevil.domain.IExternalUser;
import com.sundevil.security.Cryptographer;
@Service
public class DBConnectionExternalUserManager extends ADBConnectionManager implements IDBConnectionExternalUserManager {

	@Autowired
	Cryptographer cryptic;
	
	@Transactional
	@Override
	public boolean externaluser(IExternalUser externalUser) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		externalUser.setExUserID("USER_"+UUID.randomUUID());
		String encrypd_ssn = cryptic.encrpyt(externalUser.getExSsn());
		System.out.println("-----------"+encrypd_ssn+"----------");
		String getExRoleId = "Select role_id from roles where AUTHORITY = ?";
		String exRoleId = insert.queryForObject(getExRoleId, new Object[]{"EXTERNAL_USER"}, String.class);
		String updateStatement ="INSERT INTO bank.users (USER_ID, LASTNAME, FIRSTNAME, SSN, EMAILID, ADDRESS, ZIPCODE, GENDER, CONTACT_NUMBER, USERNAME, PASSWORD, ENABLED, ROLE_ID) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";
		insert.update(updateStatement,
		        new Object[] { externalUser.getExUserID(), externalUser.getExLastName(), externalUser.getExFirstName(), encrypd_ssn, externalUser.getExEmailId(), externalUser.getExAddress(), externalUser.getExZipCode(), externalUser.getExGender(), externalUser.getExContactNumber(),externalUser.getExUserName(), externalUser.getExPassword(), new Integer(0),exRoleId });
		return true;
	}

}
