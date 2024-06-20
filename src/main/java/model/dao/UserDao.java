package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public Double findAvgBirth() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("select avg(birth) from users u join participants p on u.id = p.user_id");
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getDouble(1);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public double[] findGenderRatio() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
        ods.setUser("fit_together");
        ods.setPassword("ORACLE");
        
        try (Connection conn = ods.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT " +
                "    (COUNT(CASE WHEN u.gender = '남' THEN 1 END) / COUNT(*)) AS male_ratio, " +
                "    (COUNT(CASE WHEN u.gender = '여' THEN 1 END) / COUNT(*)) AS female_ratio " +
                "FROM " +
                "    users u " +
                "    JOIN participants p ON u.id = p.user_id"
            );
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                double maleRatio = rs.getDouble("male_ratio");
                double femaleRatio = rs.getDouble("female_ratio");
                // 여기서 필요에 따라 로직 추가
                // 예를 들어, DTO 객체를 만들어 결과를 저장할 수 있음
                return new double[] { maleRatio, femaleRatio }; // 예시로 반환되는 수치를 더해서 반환
            } else {
                return null; // 데이터가 없을 경우 null 반환
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
            
        }
		
    }
	
	}