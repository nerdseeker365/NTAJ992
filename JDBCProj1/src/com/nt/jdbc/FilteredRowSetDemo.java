package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;

class Filter1 implements Predicate{
	private String condData;
	
	public  Filter1(String condData) {
		this.condData=condData;
	}

	@Override
	public boolean evaluate(RowSet rs) {
		System.out.println("Filter1.evaluate(-)");
		CachedRowSet crs=null;
		String empName=null;
		try {
		crs=(CachedRowSet)rs;
		empName=crs.getString(2);
		if(empName.startsWith(condData)) {
			System.out.println("added");
			return true;
		}
		else {
			System.out.println("not added");
			return false;
		}
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}


public class FilteredRowSetDemo {
	
	public static void main(String[] args) {
		FilteredRowSet  frowset=null;
		try {
		frowset=new OracleFilteredRowSet();
		frowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		frowset.setUsername("system");
		frowset.setPassword("manager");
		//set Query
		frowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
		//set Filter
		frowset.setFilter(new Filter1("S"));
		//execute Query
		frowset.execute();
		//pricess the RowSet
		while(frowset.next()) {
			System.out.println(frowset.getInt(1)+"  "+frowset.getString(2)+" "+frowset.getString(3)+" "+frowset.getInt(4));
		}
		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			//close obj
			try{
				if(frowset!=null)
					frowset.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		
	}//main
}//class
