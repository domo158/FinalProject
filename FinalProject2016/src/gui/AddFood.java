package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFood extends JFrame  {
	
	private JLabel nameLbl;
	private JLabel massLbl;
	private JLabel calorieLbl;
	private JLabel omega3Lbl;
	private JLabel omega6Lbl;
	private JLabel codeLbl;
	private JLabel proteinLbl;
	private JTextField nameFld;
	private JTextField massFld;
	private JTextField calorieFld;
	private JTextField omega3Fld;
	private JTextField omega6Fld;
	private JTextField codeFld;
	private JTextField proteinFld;
	private JPanel namePnl;
	private JPanel massPnl;
	private JPanel caloriePnl;
	private JPanel omega3Pnl;
	private JPanel omega6Pnl;
	private JPanel sifraPanel;
	private JPanel proteinPnl;
	private JPanel buttonPanel;
	private JButton saveBtn;
	private JButton cancelBtn;
	
//////constructor/////////////////
	
	public AddFood(){	
	LayoutControls();
//////declaring
	codeLbl = new JLabel("Food Code:");
	nameLbl = new JLabel("Name:");
	massLbl = new JLabel("Mass:");
	calorieLbl = new JLabel("Calories:");
	proteinLbl = new JLabel("Proteins:");
	omega3Lbl = new JLabel("Omega3:");
	omega3Lbl = new JLabel("Omega6:");
	codeFld = new JTextField(15);
	nameFld = new JTextField(15);
	massFld = new JTextField(15);
	calorieFld = new JTextField(15);
	proteinFld = new JTextField(15);
	omega3Fld = new JTextField(15);
	omega6Fld = new JTextField(15);
	saveBtn = new JButton("Save");
	cancelBtn = new JButton("Cancel");
	sifraPanel = new JPanel(new BorderLayout());
	namePnl = new JPanel(new BorderLayout());
	massPnl = new JPanel(new BorderLayout());
	caloriePnl = new JPanel(new BorderLayout());
	proteinPnl = new JPanel(new BorderLayout());
	omega3Pnl = new JPanel(new BorderLayout());
	omega6Pnl = new JPanel(new BorderLayout());
	buttonPanel = new JPanel();	
//////actionListener
	saveBtn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				unos();
			} catch (Exception e1) {
				e1.printStackTrace();}}
	});
	
	cancelBtn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	});
	
//////add
	sifraPanel.add(codeLbl, BorderLayout.NORTH);
	sifraPanel.add(codeFld, BorderLayout.SOUTH);
	add(sifraPanel);
	
	namePnl.add(nameLbl, BorderLayout.NORTH);
	namePnl.add(nameFld, BorderLayout.SOUTH);
	add(namePnl);
	
	massPnl.add(massLbl, BorderLayout.NORTH);
	massPnl.add(massFld, BorderLayout.SOUTH);
	add(massPnl);
	
	caloriePnl.add(calorieLbl, BorderLayout.NORTH);
	caloriePnl.add(calorieFld, BorderLayout.SOUTH);
	add(caloriePnl);
	
	proteinPnl.add(proteinLbl, BorderLayout.NORTH);
	proteinPnl.add(proteinFld, BorderLayout.SOUTH);
	add(proteinPnl);
	
	omega3Pnl.add(omega3Lbl, BorderLayout.NORTH);
	omega3Pnl.add(omega3Fld, BorderLayout.SOUTH);
	add(omega3Pnl);
	
	omega6Pnl.add(omega6Lbl, BorderLayout.NORTH);
	omega6Pnl.add(omega6Fld, BorderLayout.SOUTH);
	add(omega6Pnl);
	
	buttonPanel.add(saveBtn);
	buttonPanel.add(cancelBtn);
	add(buttonPanel);
	}
	
	private void LayoutControls(){
		this.setTitle("Add Food");
		setSize(300,500);
		setVisible(true);
		setLayout(new FlowLayout());
		setResizable(false);
	}
	
	
	private void unos()  throws Exception{
		String code = codeFld.getText();
		String name = nameFld.getText();
		float mass = Float.parseFloat(massFld.getText());
		float calorie = Float.parseFloat(calorieFld.getText()) / mass;
		float protein = Float.parseFloat(proteinFld.getText())/ mass;
		float omega3 = Float.parseFloat(omega3Fld.getText()) / mass;
		float omega6 = Float.parseFloat(omega6Fld.getText()) / mass;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/zavrsnirad";
        String userid = "root";
        String password = "";
        int id=0;
		try{
			
		Class.forName( driver );
        Connection connection = DriverManager.getConnection( url, userid, password );
        PreparedStatement st = connection.prepareStatement("INSERT INTO Food(id, name, calories, proteins, omega3, omega6) values(?,?,?,?,?,?)");
		st.setString(1, code);
		st.setString(2, name);
		st.setFloat(3, calorie);
		st.setFloat(4,protein);
		st.setFloat(5, omega3);
		st.setFloat(6, omega6);
		st.executeUpdate();
		codeFld.setText("");
		nameFld.setText("");
		massFld.setText("");
		calorieFld.setText("");
		proteinFld.setText("");
		omega3Fld.setText("");
		omega6Fld.setText("");
		JOptionPane.showMessageDialog(null, "Success.");
		connection.close();
		}
		catch(Exception e1){
			System.out.println(e1);
		JOptionPane.showMessageDialog(null, e1);
		}
	}
}
