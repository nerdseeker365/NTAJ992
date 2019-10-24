package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class FlexibleSelectTest1 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		InputStream is=null;
		Properties props=null;		
	try {
		//Locate the Properties file file
		is=new FileInputStream("src/com/nt/commons/DBDetails.properties");
		//Load jdbc.properties file content to  java.util.Properties class obj
		props=new Properties();
		props.load(is);
		//register JDBC driver s/w
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				                             props);
		//create STatement object
		if(con!=null)
		  st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
		  rs=st.executeQuery("SELECT * FROM STUDENT");
		//process the ResultSet obj
		if(rs!=null) {
			while(rs.next()) {
				flag=true;
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			}
		  if(flag==false)
			  System.out.println("records not found");
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
		
   }//method
}//class
