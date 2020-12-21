package ex1;

import java.sql.*;

public class Program1 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbid = "newlec";
		String dbpw = "1234";
		
		String title = "TEST4";
		int id = 258;
		String content = "HOHOHO";
		String files = "";
		
		
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=?, FILES=? WHERE ID=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, files);
		pstmt.setInt(4, 258);
		
		int result = pstmt.executeUpdate();
		
		System.out.println(result);
	}	
}
