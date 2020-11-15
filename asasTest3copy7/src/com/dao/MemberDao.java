package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.MemberDto;

public class MemberDao {
private static MemberDao mDao;
private Connection con;
private PreparedStatement pstmt;
private ResultSet rs;
private int result;

private MemberDao() {
	
}

public static synchronized MemberDao getInstance() {
	if(mDao==null) {
		mDao=new MemberDao();
	}
	return mDao;
}

public Connection getConnection() {
	String url="jdbc:oracle:thin:@localhost:1521/orcl";
	String id="ASDF",pw="1234";
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		con=DriverManager.getConnection(url,id,pw);
		if(con==null) {
			System.out.println("con is null!!");
		}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return con;
}

public void close(Connection con,PreparedStatement pstmt,ResultSet rs) {
	if(rs!=null) {
		try {
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	if(pstmt!=null) {
		try {
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	if(con!=null) {
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

public int join(MemberDto mDto) {
	con=getConnection();
	StringBuffer query=new StringBuffer(); // if used to single thread, StringBuffer -> StringBuilder. 
	query.append("INSERT INTO member ").append("VALUES(?,?,?,?)");
	try {
		pstmt=con.prepareStatement(query.toString());
		pstmt.setString(1, mDto.getId());
		pstmt.setString(2, mDto.getPw());
		pstmt.setString(3, mDto.getName());
		pstmt.setString(4, mDto.getEmail());
		result=pstmt.executeUpdate();
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		this.close(con, pstmt, rs);
	}return result;
}

public int login(String id,String pw) {
	con=getConnection();
	 StringBuilder query=new StringBuilder();
	query.append("SELECT pw").append(" FROM member").append(" WHERE ID=?");
	try {
		pstmt=con.prepareStatement(query.toString());
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getString("pw").equals(pw)) {
				return 1;
			}else {return 0;
		}
	}
}catch(SQLException e) {
	e.printStackTrace();
}finally {
	this.close(con, pstmt, rs);
}
	return -1;
}
}
