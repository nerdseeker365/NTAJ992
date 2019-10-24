package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/*SQL> create table person_tab (pid number(5) primary key,pname varchar2(20),DOB date,DOJ Date,DOM Date); */
//MysqlSQL> create table person_tab (pid int(5) primary key,pname varchar(20),DOB date,DOJ Date,DOM Date);
//postgreSQL>create table person_tab (pid integer primary key,pname charater varying(20),DOB date,DOJ Date,DOM Date);

public class DateInsertTest {
  private static final String DATE_INSERT_QUERY="INSERT INTO PERSON_TAB VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		String pname=null;
		String dob=null,doj=null,dom=null;
		Connection con=null;
		PreparedStatement ps=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		java.util.Date udob=null,udoj=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		int count=0;
		//read inputs
		try {
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter Person Id::");
			  pid=sc.nextInt();
			  System.out.println("Enter PErson name::");
			  pname=sc.next();
			  System.out.println("Enter DOB::(dd-MM-yyyy)");
			  dob=sc.next();
			  System.out.println("Enter DOJ::(MM-dd-yyy)");
			  doj=sc.next();
			  System.out.println("Enter DOM::(yyyy-MM-dd)");
			  dom=sc.next();
		  }//if
			/*
			 * //register JDBC driver s/w 
			 * Class.forName("oracle.jdbc.driver.OracleDriver");
			 * //establish the connection
			 * con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
			 * "system", "manager");
			 */
		  
			/*  //register JDBC driver s/w 
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:mysql:///NTAJ992DB1","root", "root"); */
		  
		  //register JDBC driver s/w 
		  Class.forName("org.postgresql.Driver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres", "tiger");
		  

		  
		  //create PreparedStatement object
		  if(con!=null)
		    ps=con.prepareStatement(DATE_INSERT_QUERY);
		  //convert DOB,DOJ date values to java.util.Date obj
		   sdf1=new SimpleDateFormat("dd-MM-yyyy");
		   udob=sdf1.parse(dob);
		   sdf2=new SimpleDateFormat("MM-dd-yyyy");
		   udoj=sdf2.parse(doj);
		   //Convert java.util.Date class objs to java.sql.Date class objs
		   //and convert String DOM directly java.sql.Date class object.
		   sqdob=new java.sql.Date(udob.getTime());
		   sqdoj=new java.sql.Date(udoj.getTime());
		   sqdom=java.sql.Date.valueOf(dom);
		  //set values to query params
		   if(ps!=null) {
			   ps.setInt(1,pid);
			   ps.setString(2,pname);
			   ps.setDate(3,sqdob);
			   ps.setDate(4,sqdoj);
			   ps.setDate(5,sqdom);
		   }
		   //execute the SQL Query
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
