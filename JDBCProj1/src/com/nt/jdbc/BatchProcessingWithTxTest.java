package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingWithTxTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		boolean flag=false;
		try {
		 //register JDBC driver
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		 
		   //create Statement object
		   if(con!=null)
			   st=con.createStatement();
		   //begin Transaction
		   if(con!=null)
			   con.setAutoCommit(false);
		   
		   //add queries to the batch
		   st.addBatch("INSERT INTO STUDENT VALUES(1029,'mahesh','vizag')");
		   st.addBatch("UPDATE STUDENT SET SADD='hyd189' WHERE SNO>=1000");
		   st.addBatch("DELETE FROM STUDENT WHERE SNAME LIKE 'aa%'");
		  
		   //execute the SQL Query
		   if(st!=null) {
			 result=st.executeBatch();  
		   }
		   //perform Transaction(Tx) mgmt
		   for(int i=0;i<result.length;++i) {
			   if(result[i]==0) {
				   flag=true;
				   break;
			   }
		   }
		   if(flag) {
			   con.rollback();
			   System.out.println("Tx rolledback");
		   }
		   else {
			   con.commit();
			   System.out.println("Tx committed");
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
