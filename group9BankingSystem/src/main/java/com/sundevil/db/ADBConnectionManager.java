/**
 * 
 */
package com.sundevil.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author satyaswaroop
 * 
 */
public abstract class ADBConnectionManager {
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;
	
	
	@Autowired
	protected DataSource dataSource;

	/**
	 * @Description: Assigns the data source
	 * 
	 * @param : dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected int closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
			return 0;
		} catch (SQLException se) {

		}
		return -1;
	}

	protected Connection getConnection() {
		try {
			return  dataSource.getConnection();
		} catch (SQLException e) {

		}
		return null;
	}

}
