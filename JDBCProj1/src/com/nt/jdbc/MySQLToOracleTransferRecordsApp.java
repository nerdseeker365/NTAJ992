package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLToOracleTransferRecordsApp {
   private static final String INSERT_ORA_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
   private static final String SELECT_MYSQL_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		String name=null,add=null;
		try {
		  // register JDBC driver s/ws
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  //establish the connections
		  oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		  mysqlCon=DriverManager.getConnection("jdbc:mysql:///NTAJ992DB1","root","root");
		  //create Statement objs
		  if(mysqlCon!=null)
			  st=mysqlCon.createStatement();
		  if(oraCon!=null)
			  ps=oraCon.prepareStatement(INSERT_ORA_QUERY);
		  //send and execute SQL Query in mysql DB s/w
		  if(st!=null)
			  rs=st.executeQuery(SELECT_MYSQL_QUERY);
		  //Process the ResultSEt obj and also copies the record OracleDB table
		  if(ps!=null && rs!=null) {
			  while(rs.next()) {
				  //get Each Record from  ResultSet obj
				  no=rs.getInt(1);
				  name=rs.getString(2);
				  add=rs.getString(3);
				  //set values to  insert query  params using ps 
				  ps.setInt(1,no);
				  ps.setString(2,name);
				  ps.setString(3,add);
				  //execute the Query
				  ps.executeUpdate();
			  }//while
			  System.out.println("Records are copied");
		  }//if
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
				  if(st!=null)
					  st.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				  if(oraCon!=null)
					  oraCon.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				  if(mysqlCon!=null)
					  mysqlCon.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
		}//finally
		  
	}//main
}//class
