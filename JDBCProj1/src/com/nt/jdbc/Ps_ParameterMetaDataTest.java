package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ps_ParameterMetaDataTest {
  private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	
  public static void main(String[] args) {
		int result=0;
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int count=0;
		try {
		
		 //register JDBC driver s/w
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root","root");
		 //create Statement object
		 if(con!=null)
			 ps=con.prepareStatement(INSERT_QUERY);
		 //gather student details for group registration
		 if(ps!=null) {
			 pmd=ps.getParameterMetaData();
			 count=pmd.getParameterCount();
		 }
		 
		 //display info about parameters
		 for(int i=1;i<=count;++i) {
			 System.out.println("parameter mode::"+pmd.getParameterMode(i));
			 System.out.println("parameter type::"+pmd.getParameterTypeName(i));
			 System.out.println("parameter isSigned?::"+pmd.isSigned(i));
			 System.out.println("parameter isNullable::"+pmd.isNullable(i));
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
