package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest6WithPS {
  private static final String  COUNT_STUDS="SELECT COUNT(*) FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		ResultSet rs=null;
		try {
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root", "root");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(COUNT_STUDS);
			
			//send and execute SQL Query in DB s/w
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs!=null) {
				rs.next();
				System.out.println("Count of records"+rs.getInt(1));
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
			if(rs!=null)
				rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				if(con!=null)
					con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			
		}//finally
	}//main
}//class
