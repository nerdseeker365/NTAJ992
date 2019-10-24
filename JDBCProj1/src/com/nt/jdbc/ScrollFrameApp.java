package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrameApp extends JFrame implements ActionListener {
	private static final String GET_ALL_STUDENTS="SELECT SNO,SNAME,SADD FROM STUDENT";
	private JLabel lno,lname,laddrs;
	private JTextField tno,tname,taddrs;
	private JButton bFirst,bNext,bLast,bPrevious;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ScrollFrameApp() {
		System.out.println("constructor");
		setTitle("ScrollFrameApp-Data");
		setSize(200, 200);
		setLayout(new FlowLayout());
		lno=new JLabel("student Id");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		lname=new JLabel("student name");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		laddrs=new JLabel("student address");
		add(laddrs);
		taddrs=new JTextField(10);
		add(taddrs);
		bFirst=new JButton("first");
		bFirst.addActionListener(this);
		add(bFirst);
		bNext=new JButton("next");
		bNext.addActionListener(this);
		add(bNext);
		
		bPrevious=new JButton("previous");
		bPrevious.addActionListener(this);
		add(bPrevious);
		bLast=new JButton("Last");
		bLast.addActionListener(this);
		add(bLast);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//invoke method
		initialize();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("ScrollFrameApp.ScrollFrameApp().new WindowAdapter() {...}.windowClosing()");
				//close jdbc objs
				try {
				  if(rs!=null)
					  rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					  if(ps!=null)
						  ps.close();
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
			}//method
		});
		
	}//constructor
	
	private void initialize() {
		System.out.println("ScrollFrameApp.initialize()");
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement obj
			ps=con.prepareStatement(GET_ALL_STUDENTS,
					                ResultSet.TYPE_SCROLL_SENSITIVE,
					                ResultSet.CONCUR_UPDATABLE);
			//create Scrollable ResultSet obj
			rs=ps.executeQuery();
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("ScrollFrameApp.main(-)");
		new ScrollFrameApp();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("ScrollFrameApp::actionPerformed(-,-)");
		boolean flag=false;
		try {
		if(ae.getSource()==bFirst) {
			rs.first();
			flag=true;
			
		}
		else if(ae.getSource()==bNext) {
			if(!rs.isLast()) {
				rs.next();
				flag=true;
			}
			
		}
		else if(ae.getSource()==bPrevious) {
			if(!rs.isFirst()) {
				rs.previous();
				flag=true;
			}
			
		}
		else {
			rs.last();
			flag=true;
		}
		
		//set ResultSet obj record values to text boxes
		if(flag==true) {
			tno.setText(rs.getString(1));
			tname.setText(rs.getString(2));
			taddrs.setText(rs.getString(3));
		}
		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
			
	}//actionPerformed(-)

}//class
