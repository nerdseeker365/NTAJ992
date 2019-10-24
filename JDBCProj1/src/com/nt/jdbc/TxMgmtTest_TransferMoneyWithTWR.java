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

public class TxMgmtTest_TransferMoneyWithTWR {

	public static void main(String[] args) {
		int srcAcno=0;
		int destAcno=0;
		float amt=0.0f;
		int result[]=null;
		boolean flag=false;
		 //read inputs
		try(Scanner sc=new Scanner(System.in)){	
		 if(sc!=null) {
			 System.out.println("Enter Source Account number::");
			 srcAcno=sc.nextInt();
			 System.out.println("Enter Dest Account Number::");
			 destAcno=sc.nextInt();
			 System.out.println("Enter Amount to transfer::");
			 amt=sc.nextFloat();
		 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		 //establish the connection
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager")){
		 //begin Tx
		  if(con!=null)
			  con.setAutoCommit(false);
		  //create STatement obj
		  if(con!=null)
			try(Statement st=con.createStatement()){
		  //add queries to the batch
					if(st!=null) {
						st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE-"+amt+" WHERE ACNO="+srcAcno);
						st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE+"+amt+" WHERE ACNO="+destAcno);
					}
					//execute the batch
					if(st!=null) {
						result=st.executeBatch();  
					}
			}//try2
		  //perform Tx mgmt
		  for(int i=0;i<result.length;++i) {
			  if(result[i]==0){
				  flag=true;
			  }
		  }
		  	if(flag) {
				con.rollback();
				System.out.println("Tx RolledBack-Money not transfered");
				}
				else {
					con.commit();
					System.out.println("Tx committed-Money  transfered");
				}
		}//try1
			
		catch(SQLException se) {
			flag=true;
			se.printStackTrace();
		}
		catch(Exception e) {
			flag=true;
			e.printStackTrace();
		}
	}//main
}//class
