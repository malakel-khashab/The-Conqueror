package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class ArmyInfo extends JPanel implements ActionListener {
	private JTextArea info;
	private JLabel idlearmies;
	private JLabel basic;
	private JButton returnb;
	private StartView view;
	private MapView map;
	private JLabel pic;
	private Game g;

	public ArmyInfo(StartView view,Game g) {
		super();
		
		this.view=view;
		this.map=view.getMapwindow();
		this.g=g;

		this.setBackground(Color.getColor("1FBED6"));

		basic=new JLabel("YOUR ARMIES INFO:");
		basic.setForeground(Color.WHITE);
		basic.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		basic.setBounds(500,5, 400, 100);
		this.add(basic);

//		idlearmies=new JLabel("Idle Armies:");
//		idlearmies.setForeground(Color.WHITE);
//		idlearmies.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
//		idlearmies.setBounds(380,60,200,100);
//		this.add(idlearmies);

		String data=this.getdefenddata();
		info=new JTextArea(data);
	    info.setBounds(10,100,1200,500);
		info.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		info.setForeground(Color.BLACK);
		info.setEditable(false);
		this.add(info);
		

		returnb=new JButton("Return to City View");
		returnb.setBackground(Color.getColor("1FBED6"));
		returnb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		returnb.setBounds(1060,610,300,25);
		returnb.addActionListener(this);
		this.add(returnb);

		this.setLayout(new BorderLayout());
		pic=new JLabel();
		pic.setIcon(new ImageIcon("visuals/armyinfo2.jpg"));
		pic.setLayout(new FlowLayout());
		this.add(pic);


	}
	public String getdefenddata() {
		String data="";
		for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
			if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
				Army defend=g.getPlayer().getControlledCities().get(i).getDefendingArmy();
				data+="My Defending Army Units:"+System.lineSeparator();
				data+="Current Status:"+defend.getCurrentStatus()+System.lineSeparator();
				data+="Current Location:"+defend.getCurrentLocation()+System.lineSeparator();
				//data+="Target Location:"+defend.getTarget()+System.lineSeparator();
				data+="My Defending Army Units:"+System.lineSeparator();
			}
			for(int j=0;j<g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().size();j++) {
				if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Archer) {
					data+="Type:Archer, "+"Level: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getLevel();
					data+=",Current Soldier Count: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount();
					data+=" ,Max Soldier Count: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getMaxSoldierCount();
					data+=System.lineSeparator();
				}
				else if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Infantry) {
					data+="Type:Infantry, "+"Level: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getLevel();
					data+=",Current Soldier Count: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount();
					data+=" ,Max Soldier Count: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getMaxSoldierCount();
					data+=System.lineSeparator();
				}
				else if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Cavalry) {
					data+="Type:Cavalry, "+"Level: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getLevel();
					data+=",Current Soldier Count: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount();
					data+=" ,Max Soldier Count: "+g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j).getMaxSoldierCount();
					data+=System.lineSeparator();
				}
			}
		} 
		data+="Your Controlled Armies:"+System.lineSeparator();
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
			for(int j=0;j<g.getPlayer().getControlledArmies().get(i).getUnits().size();j++) {
				if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
				data=data+"Unit Type: Archer,";
				data=data+"Status:"+g.getPlayer().getControlledArmies().get(i).getCurrentStatus();
				data=data+" ,Unit Level: "+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel();
				data=data+" ,Current Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount();
				data=data+" ,Max Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount();
				data=data+System.lineSeparator();
			}
				else if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Cavalry) {
					data=data+"Unit Type: Cavalry,";
					data=data+"Status:"+g.getPlayer().getControlledArmies().get(i).getCurrentStatus();
					data=data+" Unit Level: "+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel();
					data=data+" ,Current Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount();
					data=data+" ,Max Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount();
					data=data+System.lineSeparator();
				}
				else if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Infantry) {
					data=data+"Unit Type: Infantry,";
					data=data+"Status:"+g.getPlayer().getControlledArmies().get(i).getCurrentStatus();
					data=data+" Unit Level: "+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel();
					data=data+" ,Current Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount();
					data=data+" ,Max Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount();
					data=data+System.lineSeparator();
				}
			}
		}
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==returnb) {
			view.getArmyinfo().setVisible(false);
			view.getCitywindow().setVisible(true);
		}

	}



	public void setGame(Game game) {
		g = game;
	}


}
