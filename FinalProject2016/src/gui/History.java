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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class History extends JFrame {

	private JButton showAllBtn;
	private JButton breakfastBtn;
	private JButton lunchBtn;
	private JButton dinnerBtn;
	private JButton deleteBtn;
	private Connection connection;
	
	public History(){
		LayoutCotrols();
		
		showAllBtn = new JButton("Show All");
		breakfastBtn = new JButton("Breakfast");
		lunchBtn = new JButton("Lunch");
		dinnerBtn = new JButton("Dinner");
		deleteBtn = new JButton("Delete");
		
		JPanel tablePnl = new JPanel();
		
		Vector<Object> columnNames = new Vector<Object>();
		Vector<Object> data = new Vector<Object>();
		// table create
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
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
		tablePnl.add(scrollPane);
		tablePnl.setPreferredSize(new Dimension(600,450));
		add(tablePnl);
		
		JPanel searchPnl = new JPanel();
		searchPnl.setLayout(new FlowLayout());
		searchPnl.setBorder(BorderFactory.createTitledBorder("Search"));
		Dimension size = showAllBtn.getPreferredSize();
		breakfastBtn.setPreferredSize(size);
		lunchBtn.setPreferredSize(size);
		dinnerBtn.setPreferredSize(size);
		searchPnl.add(showAllBtn);
		searchPnl.add(breakfastBtn);
		searchPnl.add(lunchBtn);
		searchPnl.add(dinnerBtn);
		add(searchPnl, BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.setBorder(BorderFactory.createTitledBorder("Brisanje obroka"));
		buttons.setPreferredSize(new Dimension(200,70));
		buttons.add(deleteBtn);
		add(buttons);
		
		showAllBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
				model.getDataVector().removeAllElements();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/zavrsnirad";
				String username = "root";
				String password = "";
				Class.forName(driver);
				
				connection = DriverManager.getConnection(url, username, password);
				String sql = "Select * from dish";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				// column name
				for (int i = 1; i <= columns; i++) {
					columnNames.addElement(md.getColumnName(i));
				}
				// row data
				while (rs.next()) {
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++) {
						row.addElement(rs.getObject(i));
					}
					data.addElement(row);
				}
				model.fireTableChanged(null);
				}catch(Exception e1){
					System.out.println(e1);
				}
			}
		});
		breakfastBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
				model.getDataVector().removeAllElements();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/zavrsnirad";
				String username = "root";
				String password = "";
				Class.forName(driver);
				
				connection = DriverManager.getConnection(url, username, password);
				String sql = "Select * from dish WHERE meal='breakfast'";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				// column name
				for (int i = 1; i <= columns; i++) {
					columnNames.addElement(md.getColumnName(i));
				}
				// row data
				while (rs.next()) {
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++) {
						row.addElement(rs.getObject(i));
					}
					data.addElement(row);
				}
				model.fireTableChanged(null);
				}catch(Exception e1){
					System.out.println(e1);
				}
			}
		});
		lunchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
				model.getDataVector().removeAllElements();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/zavrsnirad";
				String username = "root";
				String password = "";
				Class.forName(driver);
				
				connection = DriverManager.getConnection(url, username, password);
				String sql = "Select * from dish WHERE meal='lunch'";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				// column name
				for (int i = 1; i <= columns; i++) {
					columnNames.addElement(md.getColumnName(i));
				}
				// row data
				while (rs.next()) {
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++) {
						row.addElement(rs.getObject(i));
					}
					data.addElement(row);
				}
				model.fireTableChanged(null);
				}catch(Exception e1){
					System.out.println(e1);
					
				}
			}
		});
		dinnerBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
				model.getDataVector().removeAllElements();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/zavrsnirad";
				String username = "root";
				String password = "";
				Class.forName(driver);
				
				connection = DriverManager.getConnection(url, username, password);
				String sql = "Select * from dish WHERE meal='dinner'";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				// column name
				for (int i = 1; i <= columns; i++) {
					columnNames.addElement(md.getColumnName(i));
				}
				//data row
				while (rs.next()) {
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++) {
						row.addElement(rs.getObject(i));
					}
					data.addElement(row);
				}
				model.fireTableChanged(null);
				}catch(Exception e1){
					System.out.println(e1);
				}
			}
		});
		
		deleteBtn.addActionListener(new ActionListener(){
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
              int id = (int) table.getValueAt(table.getSelectedRow(),0) ;
              PreparedStatement stmt = connection.prepareStatement("DELETE FROM dish WHERE id ='"+id+"'");
              stmt.executeUpdate();  
          } catch(Exception e1) {
          	System.out.println(e1);
          }      
              model.removeRow( index );
			}
			});
	}
	public void LayoutCotrols(){
		setTitle("History");
		setSize(500,700);
		setVisible(true);
		setLayout(new FlowLayout());
		setResizable(false);
	}
}
