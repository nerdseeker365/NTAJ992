package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table empAll(empno number(5) primary key,ename varchar2(20),esalay number(10,2),ephoto blob);

Table created.

SQL> create sequence  empno_seq start with 1 increment by 1; */

public class BLOBInsertMysqLTest {
  private static final String INSERT_EMPALL_QUERY="INSERT INTO EMPALL(ENAME,ESALARY,EPHOTO) VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		double salary=0.0;
		String photoPath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		InputStream is=null;
		int count=0;
		try {
			//read inputs
		   sc=new Scanner(System.in);	
		   if(sc!=null) {
			   System.out.println("Enter emp name::");
			   name=sc.next();
			   System.out.println("Enter Photo Path::");
			   photoPath=sc.next();
			   System.out.println("Enter salary::");
			   salary=sc.nextDouble();
			   
		   }
		   //get the lengtht of photo file
		   file=new File(photoPath);
		   length=file.length();
		   //create InputStream pointing to the file
		   is=new FileInputStream(file);
		   
		   
		   //register JDBC driver
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:mysql:///NTAJ992DB1", "root", "root");
		   //create PreparedStaement object
		   if(con!=null)
			   ps=con.prepareStatement(INSERT_EMPALL_QUERY);
		   //set values to Query params
		   if(ps!=null) {
			   ps.setString(1,name);
			   ps.setDouble(2,salary);
			   //ps.setBinaryStream(3,is,length);
			   ps.setBlob(3,is,length);
		   }
		   //execute the SQL Query
			count=ps.executeUpdate();
			//process the result
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println("Record inserted");
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
			try {
				  if(is!=null)
					  is.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
		}//finally
	}//main
}//class
