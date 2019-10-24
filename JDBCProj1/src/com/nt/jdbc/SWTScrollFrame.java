package com.nt.jdbc;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SWTScrollFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static final String GET_ALL_STUDS="SELECT SNO,SNAME,SADD FROM STUDENT";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SWTScrollFrame window = new SWTScrollFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SWTScrollFrame() {
		initialize();
		initializeJDBC() ;
	}
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	private  void initializeJDBC() {
		try {
			//register JDBc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			ps=con.prepareStatement(GET_ALL_STUDS,
					                ResultSet.TYPE_SCROLL_SENSITIVE,
					                ResultSet.CONCUR_UPDATABLE);
			//create ScrollableResultSet obj
			rs=ps.executeQuery();
			
		}//try
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("sno");
		lblNewLabel.setBounds(40, 13, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(180, 10, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setBounds(40, 66, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 63, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("address");
		lblNewLabel_2.setBounds(40, 115, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 112, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("first");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			  try {
				  rs.first();
				  textField.setText(rs.getString(1));
				  textField_1.setText(rs.getString(2));
				  textField_2.setText(rs.getString(3));
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			
			}
		});
		btnNewButton.setBounds(12, 160, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  if(!rs.isLast())
						  rs.next();
					  
					  textField.setText(rs.getString(1));
					  textField_1.setText(rs.getString(2));
					  textField_2.setText(rs.getString(3));
				  }
				  catch(SQLException se) {
					  se.printStackTrace();
				  }
			
			}
		});
		btnNewButton_1.setBounds(121, 160, 97, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  if(!rs.isFirst())
						  rs.previous();
					  textField.setText(rs.getString(1));
					  textField_1.setText(rs.getString(2));
					  textField_2.setText(rs.getString(3));
				  }
				  catch(SQLException se) {
					  se.printStackTrace();
				  }
			
			}
		});
		btnNewButton_2.setBounds(230, 160, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					   rs.last();
					  textField.setText(rs.getString(1));
					  textField_1.setText(rs.getString(2));
					  textField_2.setText(rs.getString(3));
				  }
				  catch(SQLException se) {
					  se.printStackTrace();
				  }
			}
		});
		btnNewButton_3.setBounds(339, 160, 97, 25);
		frame.getContentPane().add(btnNewButton_3);
	}
}
