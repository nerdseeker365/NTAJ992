package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {

	public static void main(String[] args) {
		OracleCachedRowSet crowset=null;
		try {
			//create CachedRowSet 
			crowset=new OracleCachedRowSet();
			crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crowset.setUsername("system");
			crowset.setPassword("manager");
			//crowset.setReadOnly(false);
			crowset.setCommand("SELECT * FROM STUDENT");
			//execute the RowSet
			crowset.execute();
			while(crowset.next()) {
				System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
			}
			
			Thread.sleep(30000); //stop Db s/w
			//update the record
			crowset.absolute(4);
			crowset.updateString(3,"delhi567");
			crowset.updateRow();
			System.out.println("record updated in offline mode");
			Thread.sleep(60000); //start the DB s/w
			crowset.acceptChanges();
		while(crowset.next()) {
				System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			//close RowSet
			if(crowset!=null)
				crowset.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		

	}

}
