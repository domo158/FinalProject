package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Food extends JFrame {
	
	private JTextField pretragaField;
	private JButton pretraziBtn;
	private JButton pokaziSveBtn;
	private JButton dodajBtn;
	private JButton obrisiBtn;
	private Connection connection;
	
	public Food(){
		LayoutCotrols();
		
		pretragaField = new JTextField(20);
		pretraziBtn = new JButton("Pretrazi");
		pokaziSveBtn = new JButton("Pokazi Sve");
		dodajBtn = new JButton("Dodaj");
		obrisiBtn = new JButton("Obrisi");
		
		JPanel pretragaPanel = new JPanel();
		pretragaPanel.setLayout(new FlowLayout());
		pretragaPanel.setBorder(BorderFactory.createTitledBorder("Pretraga"));
		Dimension BtnSize = pokaziSveBtn.getPreferredSize();
		pretraziBtn.setPreferredSize(BtnSize);
		pretragaPanel.add(pretragaField);
		pretragaPanel.add(pretraziBtn);
		pretragaPanel.add(pokaziSveBtn);
		add(pretragaPanel, BorderLayout.NORTH);
		
		JPanel tablicaPanel = new JPanel();
		
		Vector<Object> stupciNamirnica = new Vector<Object>();
		Vector<Object> redNamirnica = new Vector<Object>();
		
		// stvaranje tablice
		DefaultTableModel model = new DefaultTableModel(redNamirnica, stupciNamirnica) {
			@Override
            public boolean isCellEditable(int row, int col) {
                return false;
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int column) {
				for (int row = 0; row < getRowCount(); row++) {
					Object o = getValueAt(row, column);
					if (o != null) {
						return o.getClass();
					}
				}
				return Object.class;
		        };
			};
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
		JScrollBar bar = scrollPane.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(15, 0));
		scrollPane.setPreferredSize(new Dimension(478, 400));
		tablicaPanel.add(scrollPane);
		tablicaPanel.setPreferredSize(new Dimension(700,450));
		add(tablicaPanel);
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(dodajBtn);
		buttons.add(obrisiBtn);
		add(buttons);
		
		pretraziBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					String like = pretragaField.getText();
					model.getDataVector().removeAllElements();
					String driver = "com.mysql.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/zavrsnirad";
					String username = "root";
					String password = "";
					Class.forName(driver);
					
					connection = DriverManager.getConnection(url, username, password);
					String sql = "Select * from Namirnice WHERE Naziv LIKE '"+like+ "_%_%'";
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData md = rs.getMetaData();
					int columns = md.getColumnCount();
					// Imena stupaca
					for (int i = 1; i <= columns; i++) {
						stupciNamirnica.addElement(md.getColumnName(i));
					}
					// Podatci iz redaka
					while (rs.next()) {
						Vector<Object> row = new Vector<Object>(columns);
						for (int i = 1; i <= columns; i++) {
							row.addElement(rs.getObject(i));
						}
						redNamirnica.addElement(row);
					}
					model.fireTableChanged(null);
					}catch(Exception e1){
						System.out.println(e1);
					}
				table.getColumnModel().getColumn(0).setPreferredWidth(35);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(50);
				table.getColumnModel().getColumn(3).setPreferredWidth(90);
				table.getColumnModel().getColumn(4).setPreferredWidth(90);
			}
		});
		
		pokaziSveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
				model.getDataVector().removeAllElements();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/zavrsnirad";
				String username = "root";
				String password = "";
				Class.forName(driver);
				
				connection = DriverManager.getConnection(url, username, password);
				String sql = "Select * from Namirnice";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				// Imena stupaca
				for (int i = 1; i <= columns; i++) {
					stupciNamirnica.addElement(md.getColumnName(i));
				}
				// Podatci iz redaka
				while (rs.next()) {
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++) {
						row.addElement(rs.getObject(i));
					}
					redNamirnica.addElement(row);
				}
				model.fireTableChanged(null);
				}catch(Exception e1){
					System.out.println(e1);
				}
				table.getColumnModel().getColumn(0).setPreferredWidth(35);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(50);
				table.getColumnModel().getColumn(3).setPreferredWidth(90);
				table.getColumnModel().getColumn(4).setPreferredWidth(90);
			}
		});
		dodajBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new AddFood();
			}
		});
		obrisiBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/zavrsnirad";
				String username = "root";
				String password = "";
          try {
              Connection connection = DriverManager.getConnection(url,username,password);
              System.out.println("database connected");
              int id = (int) table.getValueAt(table.getSelectedRow(),6) ;
              PreparedStatement stmt = connection.prepareStatement("DELETE FROM Namirnice WHERE id ='"+id+"'");
              stmt.executeUpdate();  
          } catch(Exception e1) {
          	System.out.println(e1);
          }      
              model.removeRow( index );
			}
			});
	}
	public void LayoutCotrols(){
		setTitle("Namirnice");
		setSize(550,700);
		setVisible(true);
		setLayout(new FlowLayout());
		setResizable(false);
		setTitle("Namirnice");
	}
}
