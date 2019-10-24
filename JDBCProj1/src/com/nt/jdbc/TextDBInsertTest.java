package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class TextDBInsertTest {
  private static final String TEXT_INSERT_QUERY="INSERT INTO FILE1 VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String	name=null,add=null;
		PreparedStatement ps=null;
		Connection con=null;
		int count=0;
		//read inputs
		try {
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter Student Id::");
			  no=sc.nextInt();
			  System.out.println("Enter Student name::");
			  name=sc.next();
			  System.out.println("Enter Student Address::");
			  add=sc.next();
		  }//if
			
		  
		  //register JDBC driver s/w 
		  Class.forName("com.hxtt.sql.text.TextDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:text:///F:\\Apps\\TextDB");
		  
		  //create PreparedStatement object
		  if(con!=null)
		    ps=con.prepareStatement(TEXT_INSERT_QUERY);
		  //set values to query params
		   if(ps!=null) {
			   ps.setInt(1,no);
			   ps.setString(2,name);
			   ps.setString(3,add);
		   }
		   //execute the SQL Query
		   if(ps!=null)
		     count=ps.executeUpdate();
		   //process the result
		   if(count==0)
			   System.out.println("Record insertion failed");
		   else
			   System.out.println("record insertion succeded");
		}
		catch(SQLException se) {
			System.out.println("Record insertion failed");
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
