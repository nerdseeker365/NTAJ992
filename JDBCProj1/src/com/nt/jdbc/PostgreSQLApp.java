package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLApp {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			//register JDBc driver
			Class.forName("org.postgresql.Driver");
			//establish the connection
			//con=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres","tiger");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ992DB","postgres","tiger");
			//create STatement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query
			if(st!=null)
				rs=st.executeQuery("SELECT PID,PNAME,PRICE FROM PRODUCT");
			//process the ResultSEt object
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getDouble(3));
					flag=true;
				}//while
			}//if
			if(flag==false)
				System.out.println("Records not found");
				
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
				if(st!=null)
					st.close();
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
