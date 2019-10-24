package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAgeCalculatorUsingJava {
	 private static final String GET_DOB_BY_PERSONID="SELECT DOB FROM PERSON_TAB WHERE PID=?";

	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		java.sql.Date sqdob=null;
		java.util.Date udob=null,sysDate=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("enter person id");
			  pid=sc.nextInt();
	    	}
		/*  //register JDBC driver s/w
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager"); */
		  
		/*   //register JDBC driver s/w
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  //establish the connection
	
		  con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root", "root"); */
		  
		  
		  //register JDBC driver s/w
		  Class.forName("org.postgresql.Driver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres", "tiger");
		  
		  
		  
		  //create PreparedStatement object
		  if(con!=null)
			  ps=con.prepareStatement(GET_DOB_BY_PERSONID);
		  //set input value to Query params
		  if(ps!=null)
			  ps.setInt(1,pid);
		  //execute the SQL Query
		  if(ps!=null)
			  rs=ps.executeQuery();
		  //process the ResultSet
		  if(rs!=null) {
			if(rs.next()) {
				sqdob=rs.getDate(1);
				//convert java.sql.DAte class obj to java.util.Date class obj
				udob=sqdob;
				//get System DAte
				sysDate=new java.util.Date();
				System.out.println("Person age"+(sysDate.getTime()-udob.getTime())/(1000*60*60*24*365.0));
			}
			else {
				System.out.println("record not found");
			}
		  }//if
		  }//try
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		  catch(ClassNotFoundException cnf) {
			  cnf.printStackTrace();
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
			  
			  try {
					 if(sc!=null)
						 sc.close();
				  }
				  catch(Exception e) {
					  e.printStackTrace();
				  }
		  }//finally
	}//main
}//class
