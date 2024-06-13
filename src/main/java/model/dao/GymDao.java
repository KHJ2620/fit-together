package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Gym;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class GymDao {
	public Gym distinctByType() throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT TYPE FROM GYMS");
			
			
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				return new Gym(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));
				
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	public List<Gym> findByType(String type) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GYMS WHERE TYPE=?");
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery();
			List<Gym> gyms = new ArrayList<>();
			
			while(rs.next()) {
				Gym one = new Gym(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));
				gyms.add(one);
			}
			return gyms;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
		
	public List<Gym> findById(int gymId) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GYMS WHERE GYM_ID=?");
			stmt.setInt(1, gymId);
			
			ResultSet rs = stmt.executeQuery();
			List<Gym> gyms = new ArrayList<>();
			
			while(rs.next()) {
				Gym one = new Gym(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));
				gyms.add(one);
			}
			return gyms;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
