package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.User;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class UserDao {
	
	public boolean save(User user) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ? , ?, ? , ?)");
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getGender());
			stmt.setInt(5, user.getBirth());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getInterest());
			
			int r =  stmt.executeUpdate();
			return r == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
		



	public User findById(String id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE ID=?");
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new User(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getString(6), rs.getString(7));
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
		
	}