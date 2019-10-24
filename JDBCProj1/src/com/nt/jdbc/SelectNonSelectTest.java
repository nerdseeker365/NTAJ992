package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		String query=null;
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		int count=0;
		try {
		  //read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null) {
			 System.out.println("Enter SQL Query");
			 query=sc.nextLine();
		 }
		 //register JDBC driver s/w
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		 //create Statement object
		 if(con!=null)
			 st=con.createStatement();
		 //send and execute SQL Query in DB s/w
		 if(st!=null)
			 flag=st.execute(query);
		 //process the Result
		 if(flag) {
			 System.out.println("Select Query executed");
			 rs=st.getResultSet();
			 while(rs.next()) {
				 System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			 }
		 }
		 else {
			 System.out.println("Non-Select Query executed");
			 count=st.getUpdateCount();
			 System.out.println("no.of records that are effected::"+count);
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
