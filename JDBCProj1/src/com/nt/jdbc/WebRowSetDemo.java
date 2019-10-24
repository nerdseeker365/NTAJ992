package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDemo {

	public static void main(String[] args) {
	   OracleWebRowSet wrowset=null;
	   Writer writer=null;
	   try {
		   //set jdbc properties
		   wrowset=new OracleWebRowSet();
		   wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		   wrowset.setUsername("system");
		   wrowset.setPassword("manager");
		   //set SQL Query
		   wrowset.setCommand("SELECT * FROM STUDENT");
		   //execute the SQL Query
		   wrowset.execute();
		   //create Writer Stream
		   writer=new FileWriter("E:/student.xml");
		   //write db table records to file
		   wrowset.writeXml(writer);
		   System.out.println("data written to DB table");
		   
	   }//try
	   catch(SQLException se) {
		   se.printStackTrace();
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
	   finally {
		   try {
		   //close jdbc objs
		   if(wrowset!=null)
			   wrowset.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }//finally

	}//main
}//class
