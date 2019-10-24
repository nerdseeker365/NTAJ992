package com.nt.jdbc;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBRetrieveTest {
	private static final String GET_EMP_BY_NO="SELECT EMPNO,ENAME,ESALARY,EPHOTO FROM EMPALL WHERE EMPNO=?";

	public static void main(String[] args) {
		Scanner sc=null;
		int eid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String ename=null;
		float salary=0.0f;
		InputStream is=null;
		OutputStream os=null;
		int bytesRead=0;
		byte[] buffer=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person Number::");
				eid=sc.nextInt();
			}
			
		/*	Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager"); */
			
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root","root"); 
			
			
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_EMP_BY_NO);
			//set value to query param
			if(ps!=null)
				ps.setInt(1,eid);
			//create ResultSet obj
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs!=null) {
				if(rs.next()) {
					eid=rs.getInt(1);
					ename=rs.getString(2);
					salary=rs.getFloat(3);
					is=rs.getBinaryStream(4);
					System.out.println(eid+"  "+ename+"  "+salary);
					//create Outputstream
					os=new FileOutputStream("E:/new_sara.jpg");
					//write buffer based logic
					buffer=new byte[4096];	
					while((bytesRead=is.read(buffer))!=-1) {
						os.write(buffer,0,bytesRead);
					}//while
					System.out.println("Photo retrieved and stored");
				}//if
				else {
					System.out.println("Record not found");
				}
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
				  if(is!=null)
					  is.close();
				}
				catch(Exception se) {
					se.printStackTrace();
				}
			try {
				  if(os!=null)
					  os.close();
				}
				catch(Exception se) {
					se.printStackTrace();
				}
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
				catch(Exception se) {
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
