package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest1 {

	public static void main(String[] args) {
		BasicDataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
		 //create DataSoruce obj pointing  to jdbc con pool
			ds=new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUsername("system");
			ds.setPassword("manager");
		   //get Pooled Connection obj
			if(ds!=null)
			  con=ds.getConnection();
		   //create Statement obj
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT");
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				}
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
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
