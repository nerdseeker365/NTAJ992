package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
 		 //read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			        //delete from student where sno=101
			  query="DELETE FROM STUDENT WHERE SNO="+no;
			//send and execute SQLQuery in DB s/w
			  if(st!=null)
			    count=st.executeUpdate(query);
			  //process the ResultSet
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
