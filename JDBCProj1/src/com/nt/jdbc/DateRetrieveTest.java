package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateRetrieveTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int pid=0;
		String pname=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		String dob=null,doj=null,dom=null;
		SimpleDateFormat sdf1=null;
		try {
		/*	//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager"); */
			
		/*	//register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj992DB1","root", "root");
			
			 */
			//register JDBC driver
			Class.forName("org.postgresql.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres", "tiger");
			
			
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM PERSON_TAB");
			//process the ResultSet obj
			if(rs!=null) {
				while(rs.next()) {
				  pid=rs.getInt(1);
				  pname=rs.getString(2);
				  sqdob=rs.getDate(3);
				  sqdoj=rs.getDate(4);
				  sqdom=rs.getDate(5);
				  //convert java.sql.Date objs to java.util.DAte class objs
				  udob=sqdob;
				  udom=sqdom;
				  udoj=sqdoj;
				  //Convert  java.util.Date class obj to String date values
				  sdf1=new SimpleDateFormat("MMM-yyyy-dd");
				  dob=sdf1.format(udob);
				  dom=sdf1.format(udom);
				  doj=sdf1.format(udoj);
				  System.out.println(pid+" "+pname+"  "+dob+" "+doj+"  "+dom);
				}//while
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
	}//main
}//class
