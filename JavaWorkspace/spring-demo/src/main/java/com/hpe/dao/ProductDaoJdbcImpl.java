package com.hpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository("jdbc")
@NoArgsConstructor
@Setter
public class ProductDaoJdbcImpl implements ProductDao {

	// These member variables for this class are a.k.a dependencies.
	// Spring can inject values for these based on configuration.
	private String driver;
	private String url;
	private String username;
	private String password;

	// required=false --> spring does not throw exception when dependency is unsatisfied
	// required=true (default) -> spring throws UnsatisfiedDependencyException when no bean of DataSource found
	@Autowired(required = false)
	private DataSource dataSource;

	private Connection createConnection() throws ClassNotFoundException, SQLException {
		// if there is a pool available, lets returna  connection from the pool
		if (dataSource != null) {
			return dataSource.getConnection();
		}

		// if not, lets try creating a new one and return the same
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public long count() throws DaoException {
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement("select count(*) from products");
				ResultSet rs = stmt.executeQuery();) {
			rs.next();
			return rs.getLong(1);
		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}

}
