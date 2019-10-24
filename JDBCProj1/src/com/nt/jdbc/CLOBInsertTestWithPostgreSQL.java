package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table JobSeekerInfo(jsId number(5) primary key,jsName varchar2(20),jsAddrs varchar2(20),jsResume CLOB);

Table created.

SQL> create sequence jsId_seq  start with 1 increment by 1 ;
Sequence created.
 */



public class CLOBInsertTestWithPostgreSQL {
   private static final String INSERT_CLOB_QUERY="INSERT INTO JOBSEEKERINFO(JSNAME,JSADDRS,JSRESUME) VALUES(?,?,?)";
	public static void main(String[] args) {
		String name=null,addrs=null,resumeLocation=null;
		File file=null;
		Reader reader=null;
		int count=0;
	   try(Scanner sc=new Scanner(System.in)){
		   System.out.println("Enter JobSeeker Name::");
		   name=sc.next();
		   System.out.println("Enter JobSeeker Address::");
		   addrs=sc.next();
		   System.out.println("Enter Resume Location::");
		   resumeLocation=sc.next();
		 //create Reader STeam pointig  to resume
		   file=new File(resumeLocation);
		   reader=new FileReader(file);
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	   //estblish the connection
	   try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ992DB","postgres","tiger")){
		   if(con!=null) {
			   try(PreparedStatement ps=con.prepareStatement(INSERT_CLOB_QUERY)){
				   if(ps!=null) {
					   //set values to query params
					 ps.setString(1,name);
					 ps.setString(2,addrs);
					 ps.setCharacterStream(3, reader,file.length());
					 //ps.setClob(3,reader, file.length());
					 //execute the Query
					 count=ps.executeUpdate();
					 if(count==0)
						 System.out.println("Record not inserted");
					 else
						 System.out.println("Record inserted");
				   }//if
				   
			   }//try
		   }//if
		   
	   }//try
	   catch(SQLException se) {
		   se.printStackTrace();
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
	
		

	}//main
}//class
