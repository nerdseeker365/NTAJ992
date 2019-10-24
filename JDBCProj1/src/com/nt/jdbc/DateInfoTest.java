package com.nt.jdbc;

public class DateInfoTest {

	public static void main(String[] args) {
		java.util.Date d=new java.util.Date();
		java.sql.Date sd=new java.sql.Date(d.getTime());
		System.out.println(d+"  "+sd);

	}

}
