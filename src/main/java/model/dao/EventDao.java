package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Event;
import oracle.jdbc.datasource.impl.OracleDataSource;


public class EventDao {
	public boolean save(Event event) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO EVENTS VALUES(EVENTS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, event.getTitle());
			stmt.setString(2, event.getDescription());
			stmt.setString(3, event.getTag());
			stmt.setInt(4, event.getGymId());
			stmt.setString(5, event.getHostId());
			stmt.setDate(6, event.getOpenDate());
			stmt.setInt(7, event.getCapacity());
			stmt.setInt(8, event.getAttendee());
			stmt.setDate(9, event.getRegisterAt());
			
			
			int r =  stmt.executeUpdate();
			return r == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
	}
	
	public Event findById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS WHERE ID=?");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Event(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), 
						rs.getString(6), rs.getDate(7),rs.getInt(8),rs.getInt(9),rs.getDate(10));
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public List<Event> findAllOrderByOpenAt() throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS ORDER BY OPEN_AT ASC");
			
			
			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<>();
			
			while(rs.next()) {
				Event one =  new Event(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), 
						rs.getString(6), rs.getDate(7),rs.getInt(8),rs.getInt(9),rs.getDate(10));
				events.add(one);
			}
			return events;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Event findByTag(String tag) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS WHERE TAG=? ORDER BY OPEN_AT ASC");
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Event(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), 
						rs.getString(6), rs.getDate(7),rs.getInt(8),rs.getInt(9),rs.getDate(10));
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean increaseAttendeeById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("UPDATE EVENTS SET ATTENDEE = ATTENDEE+1 WHERE ID=?");
			stmt.setInt(1, id);
			
			int  r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
