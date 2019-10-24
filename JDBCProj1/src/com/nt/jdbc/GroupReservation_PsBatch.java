package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table Railway_Ticket(tktId number(5) primary key,psngrNAme varchar2(10),source varchar2(10),dest varchar2(10),price number(10,2));
SQL> create sequence tktId_seq start with 1 increment by 1; */

public class GroupReservation_PsBatch {
  private static  final String  INSERT_TICKET_QUERY="INSERT INTO RAILWAY_TICKET VALUES(TKTID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int groupSize=0;
		Connection con=null;
		PreparedStatement ps=null;
		String source=null,dest=null;
		float price=0.0f;
		String name=null;
		int result[]=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter GroupSize::");
				groupSize=sc.nextInt();
				System.out.println("Enter source place::");
				source=sc.next();
				System.out.println("Enter Dest Place::");
				dest=sc.next();
				System.out.println("Enter Ticket Price::");
				price=sc.nextFloat();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PrpearedStaement object having SQL Query
			if(con!=null)
				ps=con.prepareStatement(INSERT_TICKET_QUERY);
			//read group passenger details and add them as multiple sets of query param values
			if(ps!=null && sc!=null) {
				for(int i=1;i<=groupSize;++i) {
					System.out.println("enter Person name::");
					name=sc.next();
					//add query param values to batch
					ps.setString(1,name);
					ps.setString(2,source);
					ps.setString(3,dest);
					ps.setFloat(4,price);
					//add query param values to batch
					ps.addBatch();
				}//for
				//execute batch
				result=ps.executeBatch();
				//process the result
				if(result!=null)
				   System.out.println("Group reservation is done");
				else
					System.out.println("Group reservation is failed");
				
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
