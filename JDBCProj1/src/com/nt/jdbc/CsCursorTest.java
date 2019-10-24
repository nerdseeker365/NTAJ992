package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*create or replace procedure p_get_StudDetails(initChars  in varchar,
        details out sys_refcursor) as
begin
open details for
select sno,sname,sadd from student where sname like initChars;
end;
/*/


public class CsCursorTest {
  private static final String CALL_PROCEDURE="{CALL P_GET_STUDDETAILS(?,?)}"; 
	public static void main(String[] args) {
		 Scanner sc=null;
		 String initialChars=null;
		 Connection con=null;
		 CallableStatement cs=null;
		 ResultSet rs=null;
		 boolean flag=false;
		 try {
			 //read inputs
			 sc=new Scanner(System.in);
			 if(sc!=null) {
				 System.out.println("Enter student names's initial chars ");
				 initialChars=sc.next();
			 }
			 //register JDBC driver s/w
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create CallableStatement obj 
			 if(con!=null)
				 cs=con.prepareCall(CALL_PROCEDURE);
			 //register OUT param with JDBC data type
			 if(cs!=null)
				 cs.registerOutParameter(2, OracleTypes.CURSOR);
			 //set values to IN params
			 if(cs!=null)
				 cs.setString(1,initialChars+"%");
			  //call PL/SQL procedure
			 if(cs!=null)
				 cs.execute();
			 //gather result from Out Params
			 if(cs!=null) {
				rs=(ResultSet)cs.getObject(2);
				//process the ResultSet 
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
				if(flag==false)
					System.out.println("no records found");
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
				 if(cs!=null)
					 cs.close();
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
