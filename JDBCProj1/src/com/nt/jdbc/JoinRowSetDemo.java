package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class JoinRowSetDemo {

	public static void main(String[] args) {
		CachedRowSet crs1=null,crs2=null;
		JoinRowSet  jrs=null;
		try {
			//create CachedRowSet1
			crs1=new OracleCachedRowSet();
			crs1.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crs1.setUsername("system");
			crs1.setPassword("manager");
			crs1.setCommand("SELECT EMPNO,ENAME,JOB,DEPTNO  FROM EMP");
			crs1.setMatchColumn(4);
			crs1.execute();
			//create CachedRowSet2
			crs2=new OracleCachedRowSet();
			crs2.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crs2.setUsername("system");
			crs2.setPassword("manager");
			crs2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			crs2.setMatchColumn(1);
			crs2.execute();
			//create JoinRowSet 
			jrs=new OracleJoinRowSet();
			jrs.addRowSet(crs2);
			jrs.addRowSet(crs1);
			//process the RowSEt
			while(jrs.next()) {
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4)+" "+jrs.getString(5)+" "+jrs.getString(6));
			}
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				 if(crs1!=null)
					 crs1.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				 if(crs2!=null)
					 crs2.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
			 if(jrs!=null)
				 jrs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main
}//class
