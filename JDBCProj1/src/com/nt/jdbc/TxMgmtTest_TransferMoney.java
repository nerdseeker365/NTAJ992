package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*SQL> desc jdbc_account;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
ACNO                                      NOT NULL NUMBER(5)
HOLDER                                             VARCHAR2(15)
BALANCE                                            NUMBER(10,2) */

public class TxMgmtTest_TransferMoney {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcAcno=0;
		int destAcno=0;
		float amt=0.0f;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try {
		 //read inputs
		 sc=new Scanner(System.in);	
		 if(sc!=null) {
			 System.out.println("Enter Source Account number::");
			 srcAcno=sc.nextInt();
			 System.out.println("Enter Dest Account Number::");
			 destAcno=sc.nextInt();
			 System.out.println("Enter Amount to transfer::");
			 amt=sc.nextFloat();
		 }
		 //register JDBC driver s/w
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		 //begin Tx
		  if(con!=null)
			  con.setAutoCommit(false);
		  //create STatement obj
		  if(con!=null)
			  st=con.createStatement();
		  //add queries to the batch
		  if(st!=null) {
			  st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE-"+amt+" WHERE ACNO="+srcAcno);
			  st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE+"+amt+" WHERE ACNO="+destAcno);
		  }
		  //execute the batch
		  if(st!=null) {
			result=st.executeBatch();  
		  }
		  
		  //perform Tx mgmt
		  for(int i=0;i<result.length;++i) {
			  if(result[i]==0){
				  flag=true;
			  }
		  }
		  
			
		}//try
		catch(SQLException se) {
			flag=true;
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			flag=true;
			cnf.printStackTrace();
		}
		catch(Exception e) {
			flag=true;
			e.printStackTrace();
		}
		finally {
			try {
			if(flag) {
				con.rollback();
				System.out.println("Tx RolledBack-Money not transfered");
			}
			else {
				con.commit();
				System.out.println("Tx committed-Money  transfered");
			}
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			//close jdbc objs
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
