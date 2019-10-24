package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBMEtaDataTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		DatabaseMetaData dbmd=null;
		try {
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create DatabaseMetaData obj
			if(con!=null)
				dbmd=con.getMetaData();
			// GEt DB s/w details...
			if(dbmd!=null) {
				System.out.println("DB  name:"+dbmd.getDatabaseProductName());
				System.out.println("DB version::"+dbmd.getDatabaseProductVersion());
				System.out.println("DB Major version::"+dbmd.getDatabaseMajorVersion());
				System.out.println("DB Minor version::"+dbmd.getDatabaseMinorVersion());
				System.out.println("SQL keywords::"+dbmd.getSQLKeywords());
				System.out.println("Numeric functions::"+dbmd.getNumericFunctions());
				System.out.println("System functions::"+dbmd.getSystemFunctions());
				System.out.println("Max Chars in table name::"+dbmd.getMaxTableNameLength());
				System.out.println("Max Row size::"+dbmd.getMaxRowSize());
				System.out.println("Max db tables in Select Query::"+dbmd.getMaxTablesInSelect());
				System.out.println("Supports PL/SQL Procedures?"+dbmd.supportsStoredProcedures());
			 }//if
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				//close jdbc objs
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
