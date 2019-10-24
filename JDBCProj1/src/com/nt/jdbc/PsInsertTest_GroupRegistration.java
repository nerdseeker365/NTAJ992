package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest_GroupRegistration {
  private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	
  public static void main(String[] args) {
		int  count=0;
		int result=0;
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		int sno=0;
		String name=null,addrs=null;
		try {
		 //read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null) {
			 System.out.println("enter students count");
			 count=sc.nextInt();
		 }
		 //register JDBC driver s/w
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root","root");
		 //create Statement object
		 if(con!=null)
			 ps=con.prepareStatement(INSERT_QUERY);
		 //gather student details for group registration
		 if(ps!=null && sc!=null) {
			 for(int i=1;i<=count;i++) {
				 //read each student details
				System.out.println("enter "+i+" student details");
				System.out.println("Enter Sno::");
				sno=sc.nextInt();
				System.out.println("Enter sname::");
				name=sc.next();
				System.out.println("Enter sadd ::");
				addrs=sc.next();
				//set each student details to query params
				ps.setInt(1, sno);
				ps.setString(2,name);
				ps.setString(3,addrs);
				//execute the SQL query
				result=ps.executeUpdate();
				//process the result
				if(result==0)
					System.out.println(i+" student details not inserted");
				else
				System.out.println(i+" student details are inserted ");
			 }//for
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
