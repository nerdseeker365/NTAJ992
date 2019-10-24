package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*create or replace function  fx_view_delete_student(no in number,
        cnt out number)return sys_refcursor
as 
details  sys_refcursor;
begin
open  details  for
select sno,sname,sadd  from student where sno=no;
delete from student where sno=no;
cnt:=SQL%ROWCOUNT;
return details;
end;
/*/


public class CsFxTest1 {
   private static final String  CALL_FX="{?=call FX_VIEW_DELETE_STUDENT(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int count=0;
		String result=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter Student number::");
				no=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FX);
			//register return,OUt params with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1,OracleTypes.CURSOR);
				cs.registerOutParameter(3,Types.INTEGER);
			}
			//set values to IN params
			if(cs!=null)
				cs.setInt(2,no);
			//call PL/SQL function
			if(cs!=null)
				cs.execute();
			//gather result from CURSOR OUT params
			if(cs!=null) {
				rs=(ResultSet)cs.getObject(1);
				//display the record
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				}//while
			 count=cs.getInt(3);
			 System.out.println(count!=0?"Studen deleted":"student not found");
			}//if
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Emp not found");
			
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
