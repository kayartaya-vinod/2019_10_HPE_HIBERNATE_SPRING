package com.hpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ProductDaoJdbcImpl implements ProductDao {

	// These member variables for this class are a.k.a dependencies.
	// Spring can inject values for these based on configuration.
	private String driver;
	private String url;
	private String username;
	private String password;
	
	private Connection createConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}
	

	@Override
	public long count() throws DaoException {
		try(
			Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement("select count(*) from products");
			ResultSet rs = stmt.executeQuery();
		){
			rs.next();
			return rs.getLong(1);
		}
		catch(Exception ex) {
			throw new DaoException(ex);
		}
	}

}







