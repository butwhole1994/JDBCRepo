package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService {
	
	private String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbid = "newlec";
	private String dbpw = "1234";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList(int page, String field, String query) throws Exception{
		
		int start = 1 + (page - 1)*10;//1, 11, 21, 31...
		int end = 10*page;//10 ,20 ,30 ...
		
		String sql = "SELECT * FROM NOTICE_VIEW WHERE "+
					field
					+" LIKE ? NUM BETWEEN ? AND ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+query+"%");
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		ResultSet rs = pstmt.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regDate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			
			Notice notice = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
					);
			list.add(notice);
		}
		rs.close();
		pstmt.close();
		con.close();
		return list;
	}
	
	//단위값을 얻는 것을 Scalar값을 얻는다고 말한다.
		public int getCount()throws Exception {
			int count = 0;
			
			String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("COUNT");
			}
			rs.close();
			pstmt.close();
			con.close();
			return count;
		}
	public int insert(Notice notice) throws Exception{
		
		String title = notice.getTitle();
		int id = notice.getId();
		String content = notice.getContent();
		String files = notice.getFiles();
		String writerId = notice.getWriterId();
		
		
		String sql = "INSERT INTO notice (title, writer_id, content, files) VALUES(?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, writerId);
		pstmt.setString(3, content);
		pstmt.setString(4, files);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return result;
	}
	public int update(Notice notice) throws Exception {
		
		String title = notice.getTitle();
		int id = notice.getId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=?, FILES=? WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, files);
		pstmt.setInt(4, id);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return result;
	}
	public int delete(int id) throws Exception {
		
		String sql = "DELETE NOTICE WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return result;
	}
	
}
	
