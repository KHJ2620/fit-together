package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Event;
import model.vo.Feed;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class FeedDao {
	public boolean save(Feed newFeed) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO FEEDS VALUES(FEEDS_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?)");
			stmt.setString(1, newFeed.getWriterId());
			stmt.setString(2, newFeed.getTitle());
			stmt.setString(3, newFeed.getBody());
			stmt.setDate(4, newFeed.getWritedAt());
			stmt.setInt(5, newFeed.getReadCnt());
			stmt.setString(6, newFeed.getCategory());
			
			int r =  stmt.executeUpdate();
			return r == 1 ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public Feed findByNo(int no) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("select * from FEEDS where NO=?");
			stmt.setInt(1, no);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Feed(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6),rs.getString(7) );
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Feed> findAll() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("select * from FEEDS ORDER BY WRITED_AT DESC");
			
			
			ResultSet rs = stmt.executeQuery();
			List<Feed> feeds = new ArrayList<>();
			
			while(rs.next()) {
				Feed one = new Feed(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6), rs.getString(7));
				feeds.add(one);
			}
			return feeds;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Feed> findByWriterId(String writerId) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("select * from FEEDS WHERE WRITER_ID=? ORDER BY WRITED_AT DESC");
			stmt.setString(1, writerId);
			
			
			ResultSet rs = stmt.executeQuery();
			List<Feed> feeds = new ArrayList<>();
			
			while(rs.next()) {
				Feed one = new Feed(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6), rs.getString(7));
				feeds.add(one);
			}
			return feeds;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Feed> findByTitleLikeOrBodyLikeCategoryLike(String pattern) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//3.34.136.108:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("ORACLE");
		try (Connection conn = ods.getConnection()){
			
			//식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FEEDS WHERE TITLE LIKE ? OR BODY LIKE ? OR CATEGORY LIKE ? ORDER BY writed_At ASC");
			stmt.setString(1, "%"+pattern+"%");
			stmt.setString(2, "%"+pattern+"%");
			stmt.setString(3, "%"+pattern+"%");
			
			ResultSet rs = stmt.executeQuery();
			List<Feed> feeds = new ArrayList<Feed>();
			while (rs.next()) {
				Feed one = new Feed(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6), rs.getString(7));
				feeds.add(one);
			}
			return feeds;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	
}
