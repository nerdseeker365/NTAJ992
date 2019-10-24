package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class ExcelInsertTest {
  private static final String EXCEL_INSERT_QUERY="INSERT INTO COLLEGE.SHEET1 VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String	sname=null,sadd=null;
		PreparedStatement ps=null;
		Connection con=null;
		int count=0;
		//read inputs
		try {
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter Student Id::");
			  sno=sc.nextInt();
			  System.out.println("Enter Student name::");
			  sname=sc.next();
			  System.out.println("Enter Student Address::");
			  sadd=sc.next();
		  }//if
			
		  
		  //register JDBC driver s/w 
		  Class.forName("com.hxtt.sql.excel.ExcelDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:excel:///F:\\Apps\\ExcelDB");
		  
		  //create PreparedStatement object
		  if(con!=null)
		    ps=con.prepareStatement(EXCEL_INSERT_QUERY);
		  //set values to query params
		   if(ps!=null) {
			   ps.setInt(1,sno);
			   ps.setString(2,sname);
			   ps.setString(3,sadd);
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
