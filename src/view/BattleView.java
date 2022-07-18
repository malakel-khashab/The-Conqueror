package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Cavalry;
import units.Infantry;

public class BattleView extends JPanel implements ActionListener{
	private StartView view;
	private String targetcity;
	private ArrayList<JButton> mine;
	private ArrayList<JButton> his;
	private Game g;
	private JTextArea info;
	private JLabel mines;
	private JLabel hiss;
	private JButton attack;
	private JButton next;
	private boolean mychoice;
	private boolean oppchoice;
	private int indexme;
	private int indexopp;
	
	public BattleView(StartView view,Game g) {
		this.view=view;
		mine=new ArrayList<JButton>();
		his=new ArrayList<JButton>();
		this.mychoice=false;
		this.oppchoice=false;
		indexme=0;
		indexopp=0;
		this.g=g;
		
		info=new JTextArea("");
		info.setBounds(20,550,1000,20);
		info.setBackground(Color.LIGHT_GRAY);
		this.add(info);
		
		mines=new JLabel(g.getPlayer().getName()+" ARMY:");
		mines.setBounds(10,20,1000,25);
		mines.setForeground(Color.black);
		mines.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(mines);
		
		this.display1();
		
		this.display2();
		
		hiss=new JLabel("OPPONENTS ARMY:");
		hiss.setBounds(700,20,1000,25);
		hiss.setForeground(Color.black);
		hiss.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(hiss);
		
		attack=new JButton("ATTACK");
		attack.setBounds(350,350,200,100);
		attack.setBackground(Color.getColor("1FBED6"));
		attack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		attack.addActionListener(this);
		this.add(attack);
		
		next=new JButton("NEXT ATTACK");
		next.setBounds(550,350,200,100);
		next.setBackground(Color.getColor("1FBED6"));
		next.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		next.addActionListener(this);
		this.add(next);
		
		this.setLayout(new BorderLayout());
	}
	public void display1() {
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
			if(i==view.getTarget().getSelected()) {
				this.targetcity=g.getPlayer().getControlledArmies().get(i).getCurrentLocation();
				this.indexme=i;
				for(int j=0;j<g.getPlayer().getControlledArmies().get(i).getUnits().size();j++) {
					
					if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
						mine.add(new JButton("Archer "+(j+1)));
					}
					else if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Cavalry) {
						mine.add(new JButton("Cavalry "+(j+1)));
					}
					if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Infantry) {
						mine.add(new JButton("Infantry "+(j+1)));
					}
				}
			}
		}
		int h=10;
		int y=50;
		for(int i=0;i<mine.size();i++) {
			JButton x=mine.get(i);
			x.setBackground(Color.getColor("1FBED6"));
			x.setBounds(h,y,100,25);
			x.addActionListener(this);
			this.add(x);
			if(h>=500) {
				h=10;
				y=y+50;
			}
			else {
				h=h+110;
			}
		}
	}
	public void display2() {
		for(int i=0;i<g.getAvailableCities().size();i++) {
			if(g.getAvailableCities().get(i).getName().equals(targetcity)) {
				this.indexopp=i;
				for(int j=0;j<g.getAvailableCities().get(i).getDefendingArmy().getUnits().size();j++) {
					if(g.getAvailableCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Archer) {
						his.add(new JButton("Archer "+(j+1)));
					}
					else if(g.getAvailableCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Cavalry) {
						his.add(new JButton("Cavalry "+(j+1)));
					}
					if(g.getAvailableCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Infantry) {
						his.add(new JButton("Infantry "+(j+1)));
					}
				}
			}
		}
		int h=700;
		int y=50;
		for(int i=0;i<his.size();i++) {
			JButton x=his.get(i);
			x.setBackground(Color.getColor("1FBED6"));
			x.setBounds(h,y,100,25);
			x.addActionListener(this);
			this.add(x);
			if(h>=1200) {
				h=700;
				y=y+50;
			}
			else {
				h=h+110;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int i=0;
		for(i=0;i<mine.size();i++) {
			if(e.getSource()==mine.get(i)) {
				this.mychoice=true;
				break;
			}
		}
		
		int j=0;
		for(j=0;j<his.size();j++) {
			if(e.getSource()==his.get(i)) {
				this.oppchoice=true;
				break;
			}
		}
		
		if(e.getSource()==attack) {
			if(mychoice==false) {
				JOptionPane.showMessageDialog(this, "Please choose your unit","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else if(oppchoice==false) {
				JOptionPane.showMessageDialog(this, "Please Choose unit you wanna fight","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else{
				try {
					g.getPlayer().getControlledArmies().get(indexme).getUnits().get(i-1).attack(g.getAvailableCities().get(indexopp).getDefendingArmy().getUnits().get(j-1));
					info.setText(g.getPlayer().getControlledArmies().get(indexme).getUnits().get(i-1)+" ATTACKED "+g.getAvailableCities().get(indexopp).getDefendingArmy().getUnits().get(j-1));
				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(this, "Cant fire friendly unit","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

}
