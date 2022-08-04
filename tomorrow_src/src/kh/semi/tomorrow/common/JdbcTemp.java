package kh.semi.tomorrow.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JdbcTemp {
	private static Connection conn = null;
	
	public static Connection getConnection()  {	
		try {
			Context initContext = new InitialContext();
			Context evnContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) evnContext.lookup("jdbc/tomorrowLocal");
//			DataSource ds = (DataSource) evnContext.lookup("jdbc/tomorrowPclass");
			conn = ds.getConnection();
		}  catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(conn==null) {
			System.out.println("DB연결 실패");
		} else {
			System.out.println("DB연결 성공");
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null) conn.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt!=null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setAutocommit(Connection conn, boolean onoff) {
		try {
			if(conn !=null)conn.setAutoCommit(onoff);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn !=null )conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn !=null )conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
