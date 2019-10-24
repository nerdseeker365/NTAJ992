package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontEndStudentRegistrationApp extends JFrame implements ActionListener,WindowListener{
	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(SNO_SEQ.NEXTVAL,?,?)"; 
	private JLabel lname,ladd,lresult;
	private JTextField tname,tadd;
	private  JButton  btn;
	private Connection con;
	private PreparedStatement ps; 
	
	public GUIFrontEndStudentRegistrationApp() {
		System.out.println("GUIFrontEndStudentRegistrationApp:0-param constructor");
		setTitle("Student Registration App");
		setSize(300, 300);
		setLayout(new FlowLayout());
		//add comps
		lname=new JLabel("student name");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		ladd=new JLabel("student Address");
		add(ladd);
		tadd=new JTextField(10);
		add(tadd);
		btn=new JButton("register");
		add(btn);
		btn.addActionListener(this);
		lresult=new JLabel("Result is");
		add(lresult);
		//enable visibitly
		setVisible(true);
		//close the App when frame window is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//add WindowListener to FRame Window
				addWindowListener(this);
				
		//invoke helper method
		initialize();
		
		
	}//constructor
	
	//helper methods
	private void initialize() {
		System.out.println("GUIFrontEndStudentRegistrationApp::initialize()");
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			ps=con.prepareStatement(INSERT_STUDENT_QUERY);
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}//initialize
	
	
	public static void main(String[] args) {
		System.out.println("GUIFrontEndStudentRegistrationApp::main()");
		//create object
		GUIFrontEndStudentRegistrationApp  app=
				new GUIFrontEndStudentRegistrationApp();
	}//main

	@Override
	public void actionPerformed(ActionEvent ae) {
		String name=null,addrs=null;
		int count=0;
		System.out.println("GUIFrontEndStudentRegistrationApp:actionPerformed(-)");
		try {
			//read text box values
			name=tname.getText();
			addrs=tadd.getText();
			//set to query param values
			ps.setString(1,name);
			ps.setString(2,addrs);
			//execute the Query
			count=ps.executeUpdate();
			///process the Result
			if(count==0) {
				lresult.setForeground(Color.RED);
				lresult.setText("Registrtion failed");
			}
			else {
				lresult.setForeground(Color.GREEN);
				lresult.setText("Registration succeded");
			}
			
		}
		catch(SQLException se) {
			lresult.setForeground(Color.RED);
			lresult.setText("Registrtion failed");
			se.printStackTrace();
		}
		catch(Exception e) {
			lresult.setForeground(Color.RED);
			lresult.setText("Registrtion failed--Unknown Problem");
			e.printStackTrace();
		}
	}//actionPerformed(-)

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUIFrontEndStudentRegistrationApp.windowClosing()");
		try{
		  //close jdbc objs
			if(ps!=null) 
				ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		try{
			if(con!=null) 
				con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
	}//windowClosing(-)

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}//class

/*SQL> select * from student;

SNO SNAME                SADD
---------- -------------------- --------------------
123 ramesh               hyd
899 ramsh                hyd
1001 raamesh              hyd
1002 ramesh               hyd

SQL> create sequence sno_seq start with 1 increment by 1; */
