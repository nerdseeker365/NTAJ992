package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt(); //gives 101
				System.out.println("Enter new name  for student ::");
				newName=sc.next(); //gives raja
				System.out.println("Enter new address  for student ::");
				newAddrs=sc.next(); //gives newhyd
			}
			//convert input values as required for SQL Query
			newName="'"+newName+"'"; //gives 'raja'
			newAddrs="'"+newAddrs+"'"; //gives 'newHyd'
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			//update student set sname='newraja',sadd='newhyd' where sno=1001;
			query="UPDATE STUDENT SET SNAME="+newName+",SADD="+newAddrs+"  WHERE SNO="+no;
			System.out.println(query);
			//send and execute SQL Query in DB s/w
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("Record not found to updated");
			else
				System.out.println(count+" no.of records are updaed");
				
		}
		catch(SQLException se) {
			if(se.getErrorCode()==12899)
				System.out.println("Too Large values for Column");
			else if(se.getErrorCode()==917)
				System.out.println("Invalid SQL Query");
			else
				System.out.println("Unknown PRoblem");
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
				catch(Exception e) {
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
