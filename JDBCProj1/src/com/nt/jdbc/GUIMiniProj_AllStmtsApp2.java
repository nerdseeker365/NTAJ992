package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.hxtt.b.r;

/*
create or replace procedure P_FIND_PASS_FAIL(m1 in number,m2 in number,
                                                                                         m3 in number,result out varchar)
as
begin
   if(m1<35 or m2<35 or m3<35)then
          result:='FAIL';
   else 
      result:='PASS';
   end if;
end;
/
 
 */


public class GUIMiniProj_AllStmtsApp2 extends JFrame implements ActionListener{
	private static final String GET_ALL_SNOs="SELECT SNO FROM ALL_STUDENT";
	private  static final String GET_STUDENT_BY_SNO="SELECT SNAME,MARKS1,MARKS2,MARKS3 FROM ALL_STUDENT WHERE SNO=?";
	private  static final String CALL_PROCEDURE="{CALL P_FIND_PASS_FAIL(?,?,?,?)}";
	
	private JLabel lno,lname,lm1,lm2,lm3,lresult;
	private JComboBox<Integer> tno;
	private JTextField tname,tm1,tm2,tm3,tresult;
	private JButton bDetails,bResult;
	private Connection con;
	private PreparedStatement ps;
	private Statement st;
	private CallableStatement cs;
	private ResultSet rs1,rs2;
	
	public GUIMiniProj_AllStmtsApp2() {
		System.out.println("GUIMiniProj_AllStmtsApp:0-param constructor");
		setTitle("MiniProject-All Statements");
		setLayout(new FlowLayout());
		setSize(200,300);
		//add comps
		lno=new JLabel("student Id");
		add(lno);
		tno=new  JComboBox<Integer>();
		add(tno);
		bDetails=new JButton("Details");
		bDetails.addActionListener(this);
		add(bDetails);
		lname=new JLabel("student name");
		add(lname);
		tname=new  JTextField(10);
		add(tname);
		lm1=new JLabel("marks1");
		add(lm1);
		tm1=new  JTextField(10);
		add(tm1);
		lm2=new JLabel("marks2");
		add(lm2);
		tm2=new  JTextField(10);
		add(tm2);
		lm3=new JLabel("marks3");
		add(lm3);
		tm3=new  JTextField(10);
		add(tm3);
		bResult=new JButton("result");
		bResult.addActionListener(this);
		add(bResult);
		lresult=new JLabel("Result::");
		add(lresult);
		tresult=new  JTextField(10);
		add(tresult);
		//disable editing of text boxes
		tname.setEditable(false);
		tm1.setEditable(false);
		tm2.setEditable(false);
		tm3.setEditable(false);
		tresult.setEditable(false);
		
		//add WindowListener
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println(
						"GUIMiniProj_AllStmtsApp2.::windowClosing()");
				//close jdbc objs
				try {
					if(rs1!=null)
						rs1.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(rs2!=null)
						rs2.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(st!=null)
						st.close();
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
					if(cs!=null)
						cs.close();
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
			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	private void initialize() {
		System.out.println("GUIMiniProj_AllStmtsApp::initialize()");
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Simple STatement obj
			st=con.createStatement();
			//Load all snos to  ComboBox
			rs1=st.executeQuery(GET_ALL_SNOs);
			//copy ResultSet obj records to Combo Box
			while(rs1.next()) {
				tno.addItem(rs1.getInt(1));
			}
			//create PreparedStatement obj
			ps=con.prepareStatement(GET_STUDENT_BY_SNO);
		    //create CallableStatement obj
			cs=con.prepareCall(CALL_PROCEDURE);
			//register OUt params with JDBC types
			cs.registerOutParameter(4,Types.VARCHAR);
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

	public static void main(String[] args) {
		System.out.println("GUIMiniProj_AllStmtsApp.main(-)(start)");
		new GUIMiniProj_AllStmtsApp2();
		
		System.out.println("GUIMiniProj_AllStmtsApp.main(-)(end)");

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		int m1=0,m2=0,m3=0;
		String result=null;
		System.out.println("GUIMiniProj_AllStmtsApp::actionPerformed(-)");
		if(ae.getSource()==bDetails) {
			System.out.println("Details button is clicked");
			try {
			  //get selected sno from combo Box	
				no=(int)tno.getSelectedItem();
			  //set value to PreparedStatement SQL Query
				ps.setInt(1,no);
			   //execute the Query
				rs2=ps.executeQuery();
				//read record from ResultSet obj and set to text boxes
				if(rs2.next()) {
					tname.setText(rs2.getString(1));
					tm1.setText(rs2.getString(2));
					tm2.setText(rs2.getString(3));
					tm3.setText(rs2.getString(4));
				}
				
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(ae.getSource()==bResult) {
			try {
			System.out.println("Result button is clicked");
			//read m1,m2,m3 values from text boxes
			m1=Integer.parseInt(tm1.getText());
			m2=Integer.parseInt(tm2.getText());
			m3=Integer.parseInt(tm3.getText());
			//set values to  IN params
			cs.setInt(1,m1);
			cs.setInt(2,m2);
			cs.setInt(3,m3);
			//call PL/SQL function
			cs.execute();
			//gather results from OUT params
			result=cs.getString(4);
			//set result to text box
			tresult.setText(result);
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//else if
	}//actionPerfomed(-)

  }//class
