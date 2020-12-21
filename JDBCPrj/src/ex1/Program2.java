package ex1;

import java.sql.*;

public class Program2 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbid = "newlec";
		String dbpw = "1234";
		
		String title = "TEST2";
		String writerId = "newlec";
		String content = "hahaha";
		String files = "";
		
		
		String sql = "INSERT INTO notice ( "+
					    "title,"+
					    "writer_id,"+
					    "content,"+
					    "files"+
					    ") VALUES (?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, writerId);
		pstmt.setString(3, content);
		pstmt.setString(4, files);
		
		int result = pstmt.executeUpdate();
		
		System.out.println(result);
	}	
}
