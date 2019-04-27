package gui;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {
	private meals meal;
	private foodPanel foodPnl;
	public void dimensions(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(400, 300);	
		setResizable(false);
		setTitle("Food fat calculator");
	}
	public MainFrame(){
		dimensions();
		setLayout(new FlowLayout());
		meal = new meals();
		foodPnl = new foodPanel();
		add(meal);
		add(foodPnl);
		
	}
}
