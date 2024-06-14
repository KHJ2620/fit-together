package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import model.vo.Participant;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class ParticipantDao {
	public boolean save(Participant participant) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO PARTICIPANTS VALUE (PARTICIPANTS_SEQ.NEXTVAL,  ?, ?, ?)");
			stmt.setInt(1, participant.getId());
			stmt.setString(2, participant.getUserId());
			stmt.setInt(3, participant.getEventId());
			stmt.setDate(4, participant.getJoinAt());
			
			
			
			int r =  stmt.executeUpdate();
			return r == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
	}
	
	public List<Participant> findByEventId(int eventId) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * PARTICIPANTS WHERE EVENT_ID=?");
			stmt.setInt(1, eventId);
			
			ResultSet rs = stmt.executeQuery();
			List<Participant> participants = new ArrayList<>();
			
			while(rs.next()) {
				Participant one =  new Participant(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4));
				participants.add(one);
			}
			return participants;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public List<Participant> findByUserId(String userId) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * PARTICIPANTS WHERE USER_ID=?");
			stmt.setString(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			List<Participant> participants = new ArrayList<>();
			
			while(rs.next()) {
				Participant one =  new Participant(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4));
				participants.add(one);
			}
			return participants;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
