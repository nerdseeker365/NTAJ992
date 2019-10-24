package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		try {
		 //register JDBC driver
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		   //create Statement object
		   if(con!=null)
			   st=con.createStatement();
		   //add queries to the batch
		   st.addBatch("INSERT INTO STUDENT VALUES(1027,'raja','hyd')");
		   st.addBatch("UPDATE STUDENT SET SADD='hyd167' WHERE SNO>=1000");
		   st.addBatch("DELETE FROM STUDENT WHERE SNAME LIKE 's%'");
		   //execute the SQL Query
		   if(st!=null) {
			 result=st.executeBatch();  
		   }
		   //display count
		   for(int i=0;i<result.length;++i) {
			   sum=sum+result[i];
		   }
		   System.out.println("no.of records that are effected::"+sum);
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
