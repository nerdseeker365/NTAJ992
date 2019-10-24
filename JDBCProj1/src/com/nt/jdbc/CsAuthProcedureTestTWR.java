package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsAuthProcedureTestTWR {
	private static final String CALL_PROCEDURE = "{CALL P_AUTHENTICATION(?,?,?)}";

	public static void main(String[] args) {
		String user = null;
		String pass = null;
		String result = null;
		// read inputs
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter username::");
			user = sc.next();
			System.out.println("Enter Password::");
			pass = sc.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// establish the connection
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager")) {
			// create CallableStatement obj
			if (con != null) {
				try (CallableStatement cs = con.prepareCall(CALL_PROCEDURE)) {
					// register OUT params with JDBC types
					if (cs != null)
						cs.registerOutParameter(3, Types.VARCHAR);
					// set values to IN params
					if (cs != null) {
						cs.setString(1, user);
						cs.setString(2, pass);
					}
					// execute PL/SQL function
					if (cs != null)
						cs.execute();
					// gather results from OUT params
					if (cs != null)
						System.out.println("Result::" + cs.getString(3));
				} // try2
			} // f
		} // try1
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main
}// class
