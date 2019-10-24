package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

public class SelectTest_Connect {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		OracleDriver driver=null;
		Properties props=null;
		try {
			//driver calss obj
			driver=new OracleDriver();
			props=new Properties();
			props.put("user","system");
			props.put("password","manager");
			//estalihs the connect
			con=driver.connect("jdbc:oracle:thin:@localhost:1521:xe",
					            props);
			//create Statemetn object
			if(con!=null)
				st=con.createStatement();
			System.out.println("st obj class"+st.getClass());
			//prepare SQL Query
			query="SELECT COUNT(*) FROM STUDENT";
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			System.out.println("rs obj class::"+rs.getClass());
			//process the ResultSet obj
			if(rs!=null) {
				rs.next();
				System.out.println("Count of recors"+rs.getInt(1));
			}
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
