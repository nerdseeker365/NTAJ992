package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTestWithMySQL {

	public static void main(String[] args) {
		Scanner sc=null;
		String addrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
	
		try {
 		 //read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student address::");
				addrs=sc.next();  //gives hyd
			}
			//convert input values as required for the SQL Query
			addrs="'"+addrs+"'"; //gives 'hyd'
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj992db1","root","root");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			        //delete from student where sadd='hyd'
			  query="DELETE FROM STUDENT WHERE SADD="+addrs;
			  System.out.println(query);
			//send and execute SQLQuery in DB s/w
			  if(st!=null)
			    count=st.executeUpdate(query);
			  //process the Result
			  if(count==0)
				  System.out.println("records not found for deletion");
			  else
				  System.out.println(count+" no.of records are deleted");
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
				catch(Exception se) {
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
