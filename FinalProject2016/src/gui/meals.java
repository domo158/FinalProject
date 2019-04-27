package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class meals extends JPanel {
	//buttons
		private JButton breakfastBtn;
		private JButton lunchBtn;
		private JButton dinnerBtn;
		//panels
		private Breakfast breakfast;
		private Lunch lunch;
		private Dinner dinner;
		
		public meals(){
			LayoutControls();
			//buttons
			breakfastBtn = new JButton("BreakFast");
			lunchBtn = new JButton("Lunch");
			dinnerBtn = new JButton("Dinner");
			//actionListeners
			breakfastBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					breakfast = new Breakfast();
				}
			});
			lunchBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					lunch = new Lunch();
				}
			});
			dinnerBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					dinner = new Dinner();
				}
			});
			//addButtons
			add(breakfastBtn);
			add(lunchBtn);
			add(dinnerBtn);
		}
		
public void LayoutControls(){
	setBorder(BorderFactory.createTitledBorder("Meals"));
		
		}
}
