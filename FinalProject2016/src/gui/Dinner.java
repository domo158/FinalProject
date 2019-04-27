package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Dinner extends JFrame {
		
	private JTextField searchField;
	private JTextField calorieField;
	private JTextField proteinField;
	private JTextField omega3Field;
	private JTextField omega6Field;
	private JTextField massField;
	private JButton searchBtn;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton saveBtn;
	private JButton cancelBtn;
	private JPanel searchPnl;
	private JPanel addPnl;
	private JPanel deletePnl;
	private JPanel sumPnl;
	private JPanel buttonPnl;
	private JPanel caloriePnl;
	private JPanel proteinPnl;
	private JPanel om3Pnl;
	private JPanel om6Pnl;
	private Connection connection;
	private float sumCal;
	private float sumProt;
	private float sumO3;
	private float sumO6;
	private String meal;
	private DefaultTableModel model;
	private DefaultTableModel dtm;
	private String code;
    private String name;
    private float calorie;
    private float protein;
    private float omega3;
    private float omega6;
    private String code_2;
    private String name_2;
    private float calorie_2;
    private float protein_2;
    private float omega3_2;
    private float omega6_2;
	public Dinner(){
			LayoutControls();
			FlowLayout layout = new FlowLayout();
			
			//declaring
			searchField = new JTextField(20);
			calorieField = new JTextField(10);
			proteinField = new JTextField(10);
			omega3Field = new JTextField(10);
			omega6Field = new JTextField(10);
			massField = new JTextField(12);
			searchBtn = new JButton("Search");
			addBtn = new JButton("Add");
			deleteBtn = new JButton("Delete");
			saveBtn = new JButton("Save");
			cancelBtn = new JButton("Cancel");
			searchPnl = new JPanel();
			addPnl = new JPanel();
			deletePnl = new JPanel();
			sumPnl = new JPanel();
			buttonPnl = new JPanel();
			caloriePnl = new JPanel();
			proteinPnl = new JPanel();
			om3Pnl = new JPanel();
			om6Pnl = new JPanel();
			
		//add everything
			searchPnl.setBorder(BorderFactory.createTitledBorder("Search"));
			searchPnl.setLayout(layout);
			searchPnl.add(searchField);
			searchPnl.add(searchBtn);
			
			JPanel tablePanel1 = new JPanel();
			
			addPnl.setLayout(layout);
			addPnl.setBorder(BorderFactory.createTitledBorder("Mass:"));
			addPnl.add(massField);
			addPnl.add(addBtn);
			
			JPanel tablePanel2 = new JPanel();
			tablePanel2.setPreferredSize(new Dimension(500,100));
			
			deletePnl.setLayout(layout);
			deletePnl.setBorder(BorderFactory.createEmptyBorder());
			deletePnl.setPreferredSize(new Dimension(300,50));
			deletePnl.add(deleteBtn);
			
			sumPnl.setLayout(layout);
			sumPnl.setBorder(BorderFactory.createTitledBorder("Sum:"));
			caloriePnl.setLayout(new BorderLayout());
			caloriePnl.add(new JLabel("Calories:"), BorderLayout.NORTH);
			caloriePnl.add(calorieField, BorderLayout.SOUTH);
			proteinPnl.setLayout(new BorderLayout());
			proteinPnl.add(new JLabel("Proteins:"), BorderLayout.NORTH );
			proteinPnl.add(proteinField, BorderLayout.SOUTH);
			om3Pnl.setLayout(new BorderLayout());
			om3Pnl.add(new JLabel("Omega3:"), BorderLayout.NORTH);
			om3Pnl.add(omega3Field, BorderLayout.SOUTH);
			om6Pnl.setLayout(new BorderLayout());
			om6Pnl.add(new JLabel("Omega6:"), BorderLayout.NORTH);
			om6Pnl.add(omega6Field, BorderLayout.SOUTH);
			sumPnl.add(caloriePnl);
			sumPnl.add(proteinPnl);
			sumPnl.add(om3Pnl);
			sumPnl.add(om6Pnl);
			
			calorieField.setEditable(false);
			proteinField.setEditable(false);
			omega3Field.setEditable(false);
			omega6Field.setEditable(false);
			buttonPnl.setLayout(layout);
			Dimension btnSize3 = cancelBtn.getPreferredSize();
			saveBtn.setPreferredSize(btnSize3);
			buttonPnl.add(saveBtn);
			buttonPnl.add(cancelBtn);
			
			Vector<Object> columnsFood = new Vector<Object>();
			Vector<Object> rowsFood = new Vector<Object>();
			
			// table create
			 model = new DefaultTableModel(rowsFood, columnsFood) {
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

			    table.addMouseListener(new MouseAdapter() {
			        public void mousePressed(MouseEvent e) {
			            int row = table.rowAtPoint(e.getPoint());
			            table.getSelectionModel().setSelectionInterval(row, row);
			            if(e.getButton() == MouseEvent.BUTTON1) {
			               model = (DefaultTableModel) table.getModel();
			               code = model.getValueAt(row, 0).toString();
			               name = model.getValueAt(row, 1).toString();
			               calorie = (float) model.getValueAt(row, 2);
			               protein = (float) model.getValueAt(row, 3);
			               omega3 = (float) model.getValueAt(row, 4);
			               omega6 = (float) model.getValueAt(row, 5);
			              System.out.println(code);
			              System.out.println(name);
			              System.out.println(calorie);
			              System.out.println(protein);
			              System.out.println(omega3);
			              System.out.println(omega6);
			            }
			        }
			    });

			JScrollPane scrollPane = new JScrollPane(table,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF );	
			
			JScrollBar bar = scrollPane.getVerticalScrollBar();
			bar.setPreferredSize(new Dimension(15, 0));
			scrollPane.setPreferredSize(new Dimension(478,100));
			tablePanel1.add(scrollPane);
			tablePanel1.setPreferredSize(new Dimension(500,105));
	
			 // create object of table and table model
			 JTable tbl = new JTable();
			  dtm = new DefaultTableModel(0, 0);

			// add header of the table
			String header[] = new String[] { "Id", "Name", "Calories",
			            "Proteins", "Omega3", "Omega6" };

			// add header in table model     
			 dtm.setColumnIdentifiers(header);
			 //set model into the table object
			       tbl.setModel(dtm);

			JScrollPane scrollPane2 = new JScrollPane(tbl,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
			tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbl.getColumnModel().getColumn(0).setPreferredWidth(35);
			tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
			tbl.getColumnModel().getColumn(2).setPreferredWidth(50);
			tbl.getColumnModel().getColumn(3).setPreferredWidth(90);
			tbl.getColumnModel().getColumn(4).setPreferredWidth(90);
			JScrollBar bar2 = scrollPane2.getVerticalScrollBar();
			bar2.setPreferredSize(new Dimension(15, 0));
			scrollPane2.setPreferredSize(new Dimension(478,100));
			tablePanel2.add(scrollPane2);
			tablePanel2.setPreferredSize(new Dimension(500,105));
			
			
			 tbl.addMouseListener(new MouseAdapter() {
			        public void mousePressed(MouseEvent e) {
			            int row = tbl.rowAtPoint(e.getPoint());
			            tbl.getSelectionModel().setSelectionInterval(row, row);
			          if(e.getButton() == MouseEvent.BUTTON1) {
			               dtm = (DefaultTableModel) tbl.getModel();
			               code_2 = dtm.getValueAt(row, 0).toString();
			               name_2 = dtm.getValueAt(row, 1).toString();
			               calorie_2 = (float) dtm.getValueAt(row, 2);
			               protein_2 = (float) dtm.getValueAt(row, 3);
			               omega3_2 = (float) dtm.getValueAt(row, 4);
			               omega6_2 = (float) dtm.getValueAt(row, 5);
			              System.out.println(code_2);
			              System.out.println(name_2);
			              System.out.println(calorie_2);
			              System.out.println(protein_2);
			              System.out.println(omega3_2);
			              System.out.println(omega6_2);   
			            }
			        }
			    });

			
			//add
			add(searchPnl);
			add(tablePanel1);
			add(addPnl);
			add(tablePanel2);
			add(deletePnl);
			add(sumPnl);
			add(buttonPnl);
	
			meal=  "Dinner";
			calorieField.setText(String.valueOf(sumCal));
			proteinField.setText(String.valueOf(sumProt));
			omega3Field.setText(String.valueOf(sumO3));
			omega6Field.setText(String.valueOf(sumO6));
		
			searchBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				try{
					String like = searchField.getText();
					model.getDataVector().removeAllElements();
					String driver = "com.mysql.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/finalproject";
					String username = "root";
					String password = "";
					Class.forName(driver);
					
					connection = DriverManager.getConnection(url, username, password);
					String sql = "Select * from Food WHERE Name LIKE '"+like+ "_%_%'";
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData md = rs.getMetaData();
					int columns = md.getColumnCount();
					// Column Name
					for (int i = 1; i <= columns; i++) {
						columnsFood.addElement(md.getColumnName(i));
					}
					// Data from rows
					while (rs.next()) {
						Vector<Object> row = new Vector<Object>(columns);
						for (int i = 1; i <= columns; i++) {
							row.addElement(rs.getObject(i));
						}
						rowsFood.addElement(row);
					}
					model.fireTableChanged(null);
					}catch(Exception e1){
						System.out.println(e1);
					}
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(35);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(50);
				table.getColumnModel().getColumn(3).setPreferredWidth(90);
				table.getColumnModel().getColumn(4).setPreferredWidth(90);
				}
			});
			
			addBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==addBtn){ 
						float mass = Float.parseFloat(massField.getText());
						System.out.println(mass);
						String id = code;
						String name_C = name;
						float calorie_C = calorie*mass;
		                float protein_C = protein*mass;
		                float omega3_C = omega3*mass;
		                float omega6_C = omega6*mass;
						dtm.addRow(new Object[] { id, name_C, calorie_C,
						                protein_C, omega3_C, omega6_C });
						dtm.fireTableChanged(null);
						sumCal=calorie_C + sumCal ;
						sumProt +=protein_C ;
						sumO3 += omega3_C; 
						sumO6 += omega6_C;
						
						calorieField.setText(String.valueOf(sumCal));
						proteinField.setText(String.valueOf(sumProt));
						omega3Field.setText(String.valueOf(sumO3));
						omega6Field.setText(String.valueOf(sumO6));
						} 
					
					tbl.getColumnModel().getColumn(0).setPreferredWidth(35);
					tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
					tbl.getColumnModel().getColumn(2).setPreferredWidth(50);
					tbl.getColumnModel().getColumn(3).setPreferredWidth(90);
					tbl.getColumnModel().getColumn(4).setPreferredWidth(90);
					
				}
			});
			deleteBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
					dtm = (DefaultTableModel) tbl.getModel();
					int index = tbl.getSelectedRow();
					String codeCalc = code_2;
					String nameCalc = name_2;
					float calorieCalc = calorie_2;
	                float proteinCalc = protein_2;
	                float omega3Calc = omega3_2;
	                float omega6Calc = omega6_2;
				    sumCal=sumCal - calorieCalc ;
					sumProt -= proteinCalc ;
					sumO3 -= omega3Calc; 
					sumO6 -= omega6Calc;
					calorieField.setText(String.valueOf(sumCal));
					proteinField.setText(String.valueOf(sumProt));
					omega3Field.setText(String.valueOf(sumO3));
					omega6Field.setText(String.valueOf(sumO6));
				  	dtm.removeRow(index);
				  	}catch(Exception ev){
				  		System.out.println("Row was not selected!");
				  	}
				}
			});
			
			saveBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						add();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			cancelBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
		public void LayoutControls(){
			setSize(550,700);
			setVisible(true);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setLayout(new FlowLayout());
			setMinimumSize(new Dimension(400,550));
			setResizable(false);
			setTitle("Dinner");
		}
		
		private void add()  throws Exception{
			String mealAdd = meal;
			float calorieAdd = sumCal;
			float proteinAdd = sumProt;
			float omega3Add = sumO3;
			float omega6Add = sumO6;
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/zavrsnirad";
	        String userid = "root";
	        String password = "";
			try{	
			Class.forName( driver );
	        Connection connection = DriverManager.getConnection( url, userid, password );
	        PreparedStatement st = connection.prepareStatement("INSERT INTO dish(meal, Calories, Proteins, Omega3, Omega6) values(?,?,?,?,?)");
			st.setString(1, mealAdd);
			st.setFloat(2, calorieAdd);
			st.setFloat(3, proteinAdd);
			st.setFloat(4, omega3Add);
			st.setFloat(5, omega6Add);
			st.executeUpdate();
			sumCal = 0;
			sumProt = 0;
			sumO3 = 0;
			sumO6 = 0;
			JOptionPane.showMessageDialog(null, "Success.");
			connection.close();
			}
			catch(Exception e1){
				System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Input error. Fix all fields!");
			}
		}
}
