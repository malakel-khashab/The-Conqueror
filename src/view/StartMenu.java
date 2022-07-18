package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartMenu extends JPanel implements ActionListener{
	private JButton start;
	private JLabel icon;
	private JTextArea ID;
	private StartView view;
	public StartMenu(StartView view) {
	this.view=view;
	
	this.setLayout(new BorderLayout());
	icon=new JLabel();
	icon.setIcon(new ImageIcon("visuals/startmenu2.jpg"));
	icon.setLayout(new FlowLayout());
	this.add(icon);
	
	start=new JButton("Start Game");
	start.setPreferredSize(new Dimension(1400,100));
	start.setBackground(Color.getColor("1FBED6"));
	start.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
	this.add(start,BorderLayout.SOUTH);
	
	ID=new JTextArea("Malak El-Khashab(49-0763),Nada El-Raggal(49-0832),Ahmed Abo El-Ella(49-0758)");
	ID.setBackground(Color.gray);
	ID.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
	ID.setEditable(false);
	
	start.addActionListener(this);
    }

	public JTextArea getID() {
		return ID;
	}

	public JButton getStart() {
		return start;
	}

	public JLabel getIcon() {
		return icon;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start) {
			view.getStartmenu().setVisible(false);
			view.add(view.getNextwindow());
			
		}
	}
}

