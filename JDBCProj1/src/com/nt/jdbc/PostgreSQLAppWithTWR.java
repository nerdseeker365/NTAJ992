package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLAppWithTWR {

	public static void main(String[] args) {
		
			//establish the connection
			try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres","tiger")){
				if(con!=null) {
					try(Statement st=con.createStatement()){
					if(st!=null) {
						try(ResultSet rs=st.executeQuery("SELECT PID,PNAME,PRICE FROM PRODUCT")){
							if(rs!=null) {
								boolean flag=false;
								while(rs.next()) {
									System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getDouble(3));
									flag=true;
								}//while
								if(flag==false) {
									System.out.println("records not found");
								}//if
							}//if1
						}//try1
						
						}//if2
				    }//try2
				}//if
			}//try
			catch(SQLException  se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
				
	}//main
}//class
