package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;

public class PlayerName extends JPanel implements ActionListener {
	private JLabel playername;
	private JLabel cityname;
	private JLabel instruction1;
	private JTextField name;
    private JButton begin;
	private JLabel icon;
	private JComboBox<String> cities;
	private StartView view;
	private Game gameatt;
	
	public Game getGameatt() {
		return gameatt;
	}

	public PlayerName(StartView view) {
		super();
		this.view=view;
		
		playername=new JLabel("Please Enter Your Name:");
		playername.setBounds(50,100,1000,100);
		playername.setForeground(Color.WHITE);
		playername.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(playername);
		
		name=new JTextField();
		name.setBounds(350,140,150,25);
		name.addActionListener(this);
		this.add(name);
		
		cityname=new JLabel("Please Choose A City:");
		cityname.setBounds(50,200,1000,100);
		cityname.setForeground(Color.WHITE);
		cityname.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(cityname);
		
		String [] cityname=new String [] {"City Options","Cairo","Rome","Sparta"};
		cities=new JComboBox<String>(cityname);
		cities.setBounds(320,240,150,25);
		cities.addActionListener(this);
		this.add(cities);
	
		instruction1=new JLabel("Please Click Begin Once You are Ready To Start");
		instruction1.setBounds(50,300,1000,100);
		instruction1.setForeground(Color.WHITE);
		instruction1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(instruction1);
		
		begin=new JButton("LETS BEGIN!!!!");
		begin.setBackground(Color.getColor("1FBED6"));
		begin.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		begin.addActionListener(this);
		begin.setBounds(50,400,300,50);
		this.add(begin);
	
		this.setLayout(new BorderLayout());
		icon=new JLabel();
		icon.setIcon(new ImageIcon("visuals/map1.jpg"));
		icon.setLayout(new FlowLayout());
		this.add(icon);
		
	}

	public JLabel getPlayername() {
		return playername;
	}

	public JButton getBegin() {
		return begin;
	}

	public void setName(JTextField name) {
		this.name = name;
	}
	public void nextwindow() {
		this.setVisible(true);
	}
	public JComboBox<String> getCities() {
		return cities;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==begin) {
			//didnot enter a name
			if(name.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "You must enter your name","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			//didnot choose a city
		   if(cities.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "You must choose a city","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else {
				view.getNextwindow().setVisible(false);
				try {
					view.saveData(name.getText(),(String)cities.getSelectedItem());
					gameatt=view.getGame1();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
	}
	}

}
