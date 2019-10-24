package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

/*SQL> create table JobSeekerInfo(jsId number(5) primary key,jsName varchar2(20),jsAddrs varchar2(20),jsResume CLOB);

Table created.

SQL> create sequence jsId_seq  start with 1 increment by 1 ;
Sequence created.
 */



public class CLOBRetrieveTestWithIOUtils {
	private static final String RETRIEVE_CLOB_QUERY="SELECT JSID,JSNAME,JSADDRS,JSRESUME FROM JOBSEEKERINFO  WHERE JSID=?";
	public static void main(String[] args) {
		int id=0;
		String name=null,addrs=null;
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter JobSeeker Id::");
				id=sc.nextInt();
			}
		}
	  //estalish the connection
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager")){
		  if(con!=null) {
			 try(PreparedStatement ps=con.prepareStatement(RETRIEVE_CLOB_QUERY)){
				 if(ps!=null) {
					 ps.setInt(1,id);
				 }
				//execute the SQL Query
				 try(ResultSet rs=ps.executeQuery()){
					 //process the ResultSEt
					 if(rs!=null) {
						 if(rs.next()) {
							id=rs.getInt(1);
							name=rs.getString(2);
							addrs=rs.getString(3);
							System.out.println(id+"  "+name+" "+addrs);
							try(Reader reader=rs.getCharacterStream(4)){
								try(Writer writer=new FileWriter("E:/d_resume.txt")){
								        IOUtils.copy(reader, writer);
								    System.out.println("Resume downloaded...");
								}//try
							}//try
						 }//if
					 }//if
				 }//try
				 
			 }//if
		  }//try
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
