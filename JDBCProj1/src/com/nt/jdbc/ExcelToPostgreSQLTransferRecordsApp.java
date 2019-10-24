package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToPostgreSQLTransferRecordsApp {
   private static final String INSERT_PGS_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
   private static final String SELECT_EXCEL_QUERY="SELECT * FROM College.Sheet1";
	public static void main(String[] args) {
		Connection pgsCon=null,xlsCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		String name=null,add=null;
		try {
		  // register JDBC driver s/ws
		  Class.forName("org.postgresql.Driver");
		  Class.forName("com.hxtt.sql.excel.ExcelDriver");
		  //establish the connections
		  pgsCon=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres", "tiger");
		  xlsCon=DriverManager.getConnection("jdbc:excel:///F:\\Apps\\ExcelDB");
		  //create Statement objs
		  if(xlsCon!=null)
			  st=xlsCon.createStatement();
		  if(pgsCon!=null)
			  ps=pgsCon.prepareStatement(INSERT_PGS_QUERY);
		  //send and execute SQL Query in mysql DB s/w
		  if(st!=null)
			  rs=st.executeQuery(SELECT_EXCEL_QUERY);
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
				  if(xlsCon!=null)
					  xlsCon.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				  if(pgsCon!=null)
					  pgsCon.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
		}//finally
		  
	}//main
}//class
