package gui;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class foodPanel extends JPanel {
			//buttons
			private JButton addBtn;
			private JButton foodBtn;
			private JButton historyBtn;
			
			public foodPanel(){
				LayoutControls();
				//buttons
				addBtn = new JButton("Add into DB");
				foodBtn = new JButton("Food List");
				historyBtn = new JButton("History");
				//actionListener
				addBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						new AddFood();
					}
				});
				foodBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						new Food();
					}
				});
				historyBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						new History();					
					}
				});
				//add
				add(addBtn);
				add(foodBtn);
				add(historyBtn);
			}
			public void LayoutControls(){
				setBorder(BorderFactory.createTitledBorder("Food"));
			}
}
