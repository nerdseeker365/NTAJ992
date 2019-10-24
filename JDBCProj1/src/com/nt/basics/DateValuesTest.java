package com.nt.basics;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateValuesTest {

	public static void main(String[] args)throws Exception {
		//converting String date value to java.util.Date class object
		String d1="56-23-1990"; //dd-MM-yyyy
		SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf1.parse(d1);
		System.out.println("util date::"+ud1);
		//Converting java.util.Date class obj to java.sql.Date class object
		long ms=ud1.getTime();
		java.sql.Date sd1=new java.sql.Date(ms);
		System.out.println("sql Date::"+sd1);
		// if given string date value pattern is yyyy-MM-dd 
		// then it can be converted directly to java.sql.Date class obj
		// with out converting to  java.util.Date class obj
		String d2="1990-12-10"; //yyyy-MM-dd
		java.sql.Date sd2=java.sql.Date.valueOf(d2);
		System.out.println("sql date::"+sd2);
		
		//converting java.sql.Date class obj to java.util.Date class obj
		java.util.Date ud2=sd2;
		System.out.println("util date::"+ud2);
		
		//converting  java.util.Date class obj to Strng value..
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MMM-dd");
		String d3=sdf2.format(ud2);
		System.out.println(d3);
		
		
		
		

	}

}
