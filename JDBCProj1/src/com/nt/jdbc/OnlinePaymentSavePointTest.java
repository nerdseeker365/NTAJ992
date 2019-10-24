package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

public class OnlinePaymentSavePointTest {
   private static final String  GET_PRICE_OF_PRODUCT="SELECT PRICE FROM PRODUCT WHERE PID=?";
   private static final String  BOOK_PRODUCT="UPDATE PRODUCT SET QTY=QTY-1 WHERE PID=? ";
   private static final String  PAYMENT_PRODUCT="UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE-? WHERE ACNO=?";
   
   
   public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		int acno=0;
		Connection con=null;
		PreparedStatement ps=null,ps1=null,ps2=null;
		ResultSet rs=null;
		float price=0;
		int result1=0,result2=0;
		Savepoint sp=null;
		int status=0;
		//read inputs 
		try {
		 sc=new Scanner(System.in);	
		 if(sc!=null) {
			 System.out.println("Enter Product number::");
			 pid=sc.nextInt();
			 System.out.println("Enter Bank Account number::");
			 acno=sc.nextInt();
		 }
		  //register JDBC driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create PreparedStatement obj
		 if(con!=null)
			 ps=con.prepareStatement(GET_PRICE_OF_PRODUCT);
		 //set query param value
		 if(ps!=null)
			 ps.setInt(1,pid);
		 //execute the query
		 if(ps!=null) {
			rs=ps.executeQuery(); 
		 }
		 if(rs!=null) {
			 if(rs.next())
			     price=rs.getFloat(1);
			 else {
				 System.out.println("Product not available");
				 return ;
			 }
		 }
		 //begin Tx
		 if(con!=null)
		    con.setAutoCommit(false);
		 //execute queries in transaction
		  if(con!=null) {
			  ps1=con.prepareStatement(BOOK_PRODUCT);
			  ps2=con.prepareStatement(PAYMENT_PRODUCT);
		  }
		  //set values to query params
		  if(ps1!=null && ps2!=null) {
			  ps1.setInt(1,pid);
			  ps2.setFloat(1,price);
			  ps2.setInt(2,acno);
		  }
		  //execute queries
		  if(ps1!=null && ps2!=null) {
			  result1=ps1.executeUpdate();
			  sp=con.setSavepoint("p1");
			  result2=ps2.executeUpdate();
		  }
		  //manage payment 
		  if(result1!=0 && result2!=0) {
			  status=1;
		  }
		  else if(result1!=0 && result2==0) {
			  status=2;
		  }
		  else if(result1==0 && result2==0 ) {
			  status=3;
		  }
		  else if(result1==0 && result2!=0) {
			  status=4;
		  }
		}//try
		catch(SQLException se) {
			status=3;
		}
		catch (Exception e) {
			status=3;
		}
		finally {
			try {
			//manage payment
			if(status==1) {
				con.commit();
				System.out.println("Item is booked ,payment succeded ,So Product will be delivered very soon");
			}
			else if(status==2) {
				con.rollback(sp);
				con.commit();
				System.out.println("Item is booked ,but payment failed ,So COD is enabled");
			}
			else if(status==3 || status==4) {
				con.rollback();
				System.out.println("Item is not booked and payment failed");
			}
		}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
		   //close jdbc objs
		   try {
			   if(rs!=null)
				   rs.close();
		   }
		   catch(SQLException se) {
			   se.printStackTrace();
		   }
		   try {
			   if(ps1!=null)
				   ps1.close();
		   }
		   catch(SQLException se) {
			   se.printStackTrace();
		   }
		   try {
			   if(ps2!=null)
				   ps2.close();
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
	}//method
}//class
