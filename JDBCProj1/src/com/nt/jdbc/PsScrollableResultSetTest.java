package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PsScrollableResultSetTest {
  private static final String GET_ALL_RECORDS="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
	  try {
		  //regsiter JDBC driver 
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create Statement object
		   if(con!=null)
			   ps=con.prepareStatement(GET_ALL_RECORDS, 
					                   ResultSet.TYPE_SCROLL_SENSITIVE,
					                   ResultSet.CONCUR_UPDATABLE);
					   
		   
          //send and execute SQL Query
		   if(ps!=null)
			   rs=ps.executeQuery();
		   //process the ResultSet
		   System.out.println("Records (top--bottom)");
		   while(rs.next()) {
			   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		   }
		   rs.afterLast();
		   System.out.println("Records (bottom-- top)");
		   while(rs.previous()) {
			   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		   }
		   System.out.println("=================");
		   //Display records randomly
		   rs.first();
		   System.out.println(rs.getRow()+"---> "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
		   rs.last();
		   System.out.println(rs.getRow()+"---> "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
		   rs.absolute(3);
		   System.out.println(rs.getRow()+"--> "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
		   rs.absolute(-4);
		   System.out.println(rs.getRow()+"--> "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
		   rs.relative(3);
		   System.out.println(rs.getRow()+"--> "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
		   rs.relative(-3);
		   System.out.println(rs.getRow()+"--> "+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
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
