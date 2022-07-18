package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import units.Army;
import units.Status;

public class RelocateUnit extends JPanel implements ActionListener {
	private StartView view;
	private DefendingArmyUnits defend;
	private ControlledUnits control;
	private Game g;
	private JLabel image;
	private JLabel name;
	private JButton back;
	private JButton chosen;
	private int chosenindex;
	private ArrayList<JButton> allarmies;
	private ArrayList<Army> myarmies;
	
	public RelocateUnit(StartView view,Game g) {
		this.view=view;
		this.g=g;
		this.setChosenindex(0);
		this.myarmies=new ArrayList<Army>();
		this.allarmies=new ArrayList<JButton>();
		
		name=new JLabel("Please Choose an Army:");
		name.setBounds(30,30,1000,100);
		name.setForeground(Color.BLACK);
		name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(name);
		
		this.relocaterefresh();
		//this.add(allarmies.get(0));
		
		back=new JButton("Return To City View");
		back.setBounds(1060,610,300,25);
		back.setBackground(Color.getColor("1FBED6"));
		back.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		back.addActionListener(this);
		this.add(back);
		
		this.setLayout(new BorderLayout());
		image=new JLabel();
		image.setBackground(Color.getColor("#C4A484"));
		image.setLayout(new FlowLayout());
		this.add(image);
	}
	public void relocaterefresh() {
		for(int i=0;i<allarmies.size();i++) {
			this.remove(allarmies.get(i));
		}
		this.setting();
	}
	public void setting() {
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
			if(g.getPlayer().getControlledArmies().get(i).getCurrentStatus()==Status.IDLE) {
				myarmies.add(g.getPlayer().getControlledArmies().get(i));
				allarmies.add(new JButton("Idle Army "+(i+1)));
			}
		}
		for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
			if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().size()!=0) {
				myarmies.add(g.getPlayer().getControlledCities().get(i).getDefendingArmy());
				allarmies.add(new JButton("Defending Army "+(i+1)));
			}
		}
		int h=30;
		int y=120;
		for(int i=0;i<allarmies.size();i++) {
			JButton x=allarmies.get(i);
			x.setBackground(Color.getColor("1FBED6"));
			x.setBounds(h,y,200,25);
			x.addActionListener(this);
			this.add(x);
			if(h>=900) {
				h=30;
				y=y+50;
			}
			else {
				h=h+210;
			}
		}
	}

	public ArrayList<JButton> getAllarmies() {
		return allarmies;
	}

	public void setAllarmies(ArrayList<JButton> allarmies) {
		this.allarmies = allarmies;
	}

	public ArrayList<Army> getMyarmies() {
		return myarmies;
	}

	public void setMyarmies(ArrayList<Army> myarmies) {
		this.myarmies = myarmies;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			view.getRelocate().setVisible(false);
			view.getCitywindow().setVisible(true);
		}
		for(int i=0;i<allarmies.size();i++) {
			if(e.getSource()==allarmies.get(i)) {
				if(allarmies.get(i).getText().equals("Defending Army 1")) {
					this.setChosen(allarmies.get(i));
					allarmies.remove(i);
					view.getRelocate().setVisible(false);
					defend=new DefendingArmyUnits(view,g);
					view.add(defend);
					view.setUnits1(defend);
					view.getUnits1().setVisible(true);
					
			}
			else {
				this.setChosen(allarmies.get(i));
				this.setChosenindex(i);
				allarmies.remove(i);
				view.getRelocate().setVisible(false);
				control=new ControlledUnits(view,g);
				view.add(control);
				view.setUnits2(control);
				view.getUnits2().setVisible(true);
				}
			}
			
		}
		
	}
	public JButton getChosen() {
		return chosen;
	}
	public void setChosen(JButton chosen) {
		this.chosen = chosen;
	}
	public int getChosenindex() {
		return chosenindex;
	}
	public void setChosenindex(int chosenindex) {
		this.chosenindex = chosenindex;
	}

}
