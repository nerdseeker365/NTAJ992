package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try {
		  //Locate Properties file	
			is=new FileInputStream("src/com/nt/commons/details.properties");
		  //create java.util.Properties class obj
			props=new Properties();
		  //Load the content of Properties file to java.util.Properties class obj
			props.load(is);
		  //display data
			System.out.println(props);
			System.out.println("name key value::"+props.getProperty("name"));
			System.out.println("age key value::"+props.getProperty("age"));
			
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
