package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTable {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int count=0;
		try {
		  //register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		   //create STatement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query in Db s/w
			if(st!=null) 
				count=st.executeUpdate("CREATE TABLE TEMP(COL NUMBER(5))");
			//process the Result
			 if(count==0)
				 System.out.println("Table created");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Table not created");
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
