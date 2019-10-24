package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6OCI {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","system", "manager");
			//create Statemetn object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			query="SELECT COUNT(*) FROM STUDENT";
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet obj
			if(rs!=null) {
				rs.next();
				System.out.println("Count of recors"+rs.getInt(1));
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
			
		}//finally
	}//main
}//class
