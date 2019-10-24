package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {
  private static final String GET_ALL_RECORDS="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		int count=0;
	  try {
		  //regsiter JDBC driver 
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create Statement object
		   if(con!=null)
			   st=con.createStatement();
			 
		   
          //send and execute SQL Query
		   if(st!=null)
			   rs=st.executeQuery(GET_ALL_RECORDS);
		   //get ResultSetMetaData obj
		   if(rs!=null) {
			   rsmd=rs.getMetaData();
			   //get column count
			   count=rsmd.getColumnCount();
		   }
		   //print col names
		   if(rsmd!=null) {
			   for(int i=1;i<=count;++i) {
				   System.out.print(rsmd.getColumnLabel(i)+"("+rsmd.getColumnTypeName(i)+")    ");
			   }//for
			   System.out.println();
		   }//if
		   
		   //process the ResultSet
		   while(rs.next()) {
			   for(int i=1;i<=rsmd.getColumnCount();++i) {
				   System.out.print(rs.getString(i)+"                  ");
			   }
			   System.out.println();
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
