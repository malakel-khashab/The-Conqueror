package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class MapView extends JPanel implements ActionListener {
	private StartView view;
	private CityView city;
	private Choicepanel choose;
	private TargetCity targetcity;
	private JButton cairo;
	private JButton rome;
	private JButton sparta;
	private JButton target;
	private JLabel image;
	private JTextArea info;
	private Game g;
	private String data;
	private JButton endTurn;
    private LostGame lost;
    private String mycity;

	public MapView(StartView view,Game g) {
		
		this.view=view;
		this.g=g;
		info=new JTextArea("");
		mycity="";
		
		cairo=new JButton("Cairo");
		cairo.setBounds(110,270,200,100);
		cairo.setBackground(Color.getColor("1FBED6"));
		cairo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		cairo.addActionListener(this);
		this.add(cairo);
		
		rome=new JButton("Rome");
		rome.setBounds(110,50,200,100);
		rome.setBackground(Color.getColor("1FBED6"));
		rome.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		rome.addActionListener(this);
		this.add(rome);
		
		sparta=new JButton("Sparta");
		sparta.setBounds(110,500,200,100);
		sparta.setBackground(Color.getColor("1FBED6"));
		sparta.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		sparta.addActionListener(this);
		this.add(sparta);
		
		data=this.refreshinfo();
		info=new JTextArea(data);
	    info.setBounds(650,50,600,550);
		info.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		info.setForeground(Color.BLACK);
		info.setEditable(false);
		this.add(info);
		
		target=new JButton("Set Target City");
		target.setBounds(650,608,600,34);
		target.setBackground(Color.BLUE);
		target.setForeground(Color.white);
		target.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		target.addActionListener(this);
		this.add(target);
		
		endTurn=new JButton("End Turn");
		endTurn.setBounds(1190,20,150,25);
		endTurn.setBackground(Color.getColor("1FBED6"));
		endTurn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		endTurn.addActionListener(this);
		this.add(endTurn);
		
		this.setLayout(new BorderLayout());
		image=new JLabel();
		image.setIcon(new ImageIcon("visuals/mapview.jpg"));
		image.setLayout(new FlowLayout());
		this.add(image);
		
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Game getG() {
		return g;
	}
	public JButton getEndTurn() {
		return endTurn;
	}
	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==cairo && view.getNextwindow().getCities().getSelectedItem()!="Cairo" 
//				|| e.getSource()==rome && view.getNextwindow().getCities().getSelectedItem()!="Rome"
//				||e.getSource()==sparta && view.getNextwindow().getCities().getSelectedItem()!="Sparta") {
//			JOptionPane.showMessageDialog(this, "Please Choose The Same City You Chose Before","ERROR",JOptionPane.ERROR_MESSAGE);
//		}
	
		if(e.getSource()==cairo){ 
			boolean found=false;
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals("Cairo")) {
						found=true;
				}	
			}
			if(found) {
				view.getMapwindow().setVisible(false);
				view.add(view.getCitywindow());
				view.getCitywindow().setVisible(true);
				this.setMycity("Cairo");
			}
			else {
				JOptionPane.showMessageDialog(this, "Please Choose The Same City You Chose Before","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}

		else if(e.getSource()==rome){ 
			boolean found=false;
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals("Rome")) {
						found=true;
				}	
			}
			if(found) {
				view.getMapwindow().setVisible(false);
				view.add(view.getCitywindow());
				view.getCitywindow().setVisible(true);
				this.setMycity("Rome");
			}
			else {
				JOptionPane.showMessageDialog(this, "Please Choose The Same City You Chose Before","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource()==sparta){ 
			boolean found=false;
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals("Sparta")) {
						found=true;
				}	
			}
			if(found) {
				view.getMapwindow().setVisible(false);
				view.add(view.getCitywindow());
				view.getCitywindow().setVisible(true);
				this.setMycity("Sparta");
			}
			else {
				JOptionPane.showMessageDialog(this, "Please Choose The Same City You Chose Before","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==endTurn) {
			g.endTurn();
			for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
				if(i==view.getTarget().getSelected()) {
					if(g.getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0) {
						view.getMapwindow().setVisible(false);
						choose=new Choicepanel(view,g);
						view.add(choose);
						view.setChoose(choose);
						view.getChoose().setVisible(true);
					}
				}
			}
			if(g.getMaxTurnCount()>=g.getCurrentTurnCount()) {
				view.refreshText();
			}
			
			else {
				lost=new LostGame(view);
				view.getDataalways().setVisible(false);
				this.endTurn.setVisible(false);
				JOptionPane.showMessageDialog(this, "Your Turns have Finished","ERROR",JOptionPane.ERROR_MESSAGE);
				view.getMapwindow().setVisible(false);
				view.getCitywindow().setVisible(false);
				view.getArmyinfo().setVisible(false);
				view.add(lost);
				view.getLost().setVisible(true);
			}
		}
		if(e.getSource()==target) {
			view.getMapwindow().setVisible(false);
			this.targetcity=new TargetCity(view,g);
			view.add(targetcity);
			view.setTarget(targetcity);
			view.getTarget().setVisible(true);
		}
		
	}
	public String refreshinfo() {
		this.info.removeAll();
		return this.showarmies();
	}
	public String showarmies() {
		String data="";
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
				for(int j=0;j<g.getPlayer().getControlledArmies().get(i).getUnits().size();j++) {
					if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
					data=data+" Unit Type: Archer"+ System.lineSeparator();
					data=data+" Status:"+g.getPlayer().getControlledArmies().get(i).getCurrentStatus()+ System.lineSeparator();
					data=data+" Unit Level: "+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+System.lineSeparator();
					data=data+" Current Soldeier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+System.lineSeparator();
					data=data+" Max Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+System.lineSeparator();
				}
					else if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Cavalry) {
						data=data+" Unit Type: Cavalry"+ System.lineSeparator();
						data=data+" Unit Level: "+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+System.lineSeparator();
						data=data+" Current Soldeier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+System.lineSeparator();
						data=data+" Max Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+System.lineSeparator();
					}
					else if(g.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Infantry) {
						data=data+" Unit Type: Infantry"+ System.lineSeparator();
						data=data+" Unit Level: "+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+System.lineSeparator();
						data=data+" Current Soldeier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+System.lineSeparator();
						data=data+" Max Soldier Count:"+g.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+System.lineSeparator();
					}
					if(g.getPlayer().getControlledArmies().get(i).getCurrentStatus()==Status.MARCHING) {
						data=data+" Target:"+g.getPlayer().getControlledArmies().get(i).getTarget()+System.lineSeparator();
						data=data+" Turns Left:"+g.getPlayer().getControlledArmies().get(i).getDistancetoTarget()+System.lineSeparator();
					}
					else if(g.getPlayer().getControlledArmies().get(i).getCurrentStatus()==Status.BESIEGING) {
						data=data+" City being Beseiged:"+g.getPlayer().getControlledArmies().get(i).getCurrentLocation()+System.lineSeparator();
						for(int k=0;k<g.getPlayer().getControlledCities().size();k++) {
							if(g.getPlayer().getControlledCities().get(k).getName().equals(g.getPlayer().getControlledArmies().get(i).getCurrentLocation())){
								data=data+" Turns Under Siege:"+g.getPlayer().getControlledCities().get(k).getTurnsUnderSiege()+System.lineSeparator();
							}
						}
					
				}
		}
	}
		return data;

}

	public void setInfo(JTextArea info) {
		this.info = info;
	}
	public JButton getCairo() {
		return cairo;
	}
	public JButton getRome() {
		return rome;
	}
	public JButton getSparta() {
		return sparta;
	}
	public String getMycity() {
		return mycity;
	}
	public void setMycity(String mycity) {
		this.mycity = mycity;
	}
	public JTextArea getInfo() {
		return info;
	}
}
