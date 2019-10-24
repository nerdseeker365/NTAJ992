package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null;
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
				System.out.println("Enter student name::");
				name=sc.next(); //gives raja
				System.out.println("Enter student address::");
				addrs=sc.next();//gives hyd
			}
			//convert  input values as required for SQL Query
			name="'"+name+"'"; //gives 'raja'
			addrs="'"+addrs+"'"; //gives 'hyd'
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			  //insert into student values(1001,'chari','hyd')
			query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+")";
			System.out.println(query);
			//send and execute SQL Query in DB s/w
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record  inserted");
		}
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("Student already registered");
			else if(se.getErrorCode()==12899)
				System.out.println("value too Large for column");
			else if(se.getErrorCode()==917)
				System.out.println("Invalid SQL Query");
			else
				System.out.println("Unknow Internal Problem");
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
