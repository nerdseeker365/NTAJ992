package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFxTest {
   private static final String  CALL_FX="{? = call FX_GET_EMPDETAILS(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
		  //read inputs
			sc=new Scanner(System.in);
			
			if(sc!=null) {
				System.out.println("Enter Emp Number::");
				no=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallabeStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FX);
			//register return,OUT params with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.INTEGER);//return params
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
			}
			//set values to IN params
			if(cs!=null)
				cs.setInt(2,no);
			//call PL/SQL function
			if(cs!=null)
				cs.execute();
			//gather results from return,OUT params
			if(cs!=null) {
				System.out.println("Emp name::"+cs.getString(3));
				System.out.println("Emp Desg::"+cs.getString(4));
				System.out.println("Emp Salary::"+cs.getInt(1));
			}
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
