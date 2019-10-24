
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorForMySQL {
	private static final String AGE_CALCULATOR="SELECT YEAR(CURDATE())-YEAR(DOB) FROM PERSON_TAB WHERE PID=? ";

	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person Id::");
				pid=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root","root");
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(AGE_CALCULATOR);
			//set values to query params
			if(ps!=null)
				ps.setInt(1,pid);
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs!=null) {
				if(rs.next())
				  System.out.println("Person age::"+rs.getFloat(1));
				else
					System.out.println("Person not found");
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
