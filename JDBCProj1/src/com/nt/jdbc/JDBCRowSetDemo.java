package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JDBCRowSetDemo {

	public static void main(String[] args) {
		OracleJDBCRowSet  jrowset=null;
		try {
			//create RowSet obj
			jrowset=new OracleJDBCRowSet();
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrowset.setUsername("system");
			jrowset.setPassword("manager");
			//set the SQL query
			jrowset.setCommand("SELECT * FROM STUDENT");
			//execute the Query
			jrowset.execute();
			//process the RowSet  obj
			while(jrowset.next()) {
				System.out.println(jrowset.getInt(1)+" "+jrowset.getString(2)+" "+jrowset.getString(3));
			}
			jrowset.setReadOnly(false);
			Thread.sleep(40000); //stop DB s/w
			jrowset.absolute(3);
			jrowset.updateString(3,"vizag");
			jrowset.updateRow();
			System.out.println("ResultSet obj data is modified in offline env..");
			Thread.sleep(40000); //start DB s/w
			//process the RowSet  obj
			while(jrowset.next()) {
				System.out.println(jrowset.getInt(1)+" "+jrowset.getString(2)+" "+jrowset.getString(3));
			}
			
			
			
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(jrowset!=null)
					jrowset.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}

	}//main
}//class
