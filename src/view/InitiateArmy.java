package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import units.Archer;
import units.Cavalry;
import units.Infantry;

public class InitiateArmy extends JPanel implements ActionListener{
	private StartView view;
	private MapView map;
	private JButton back;
	private JLabel instr;
	private boolean found;
	private ArrayList<JButton> units;
	private Game g;
	private JButton initiate;
	
	public InitiateArmy(StartView view,Game g) {
		this.view=view;
		this.g=g;
		this.found=false;
		units=new ArrayList<JButton>();
		map=view.getMapwindow();
		
		this.initrefresh();
		
		back=new JButton("Return To City View");
		back.setBounds(1060,610,300,25);
		back.setBackground(Color.getColor("1FBED6"));
		back.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		back.addActionListener(this);
		this.add(back);
		
		initiate=new JButton("Initiate");
		initiate.setBounds(600,570,250,25);
		initiate.setBackground(Color.getColor("1FBED6"));
		initiate.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		initiate.addActionListener(this);
		this.add(initiate);
		
		instr=new JLabel("Please choose a unit to initiate:");
		instr.setBounds(30,30,600,100);
		instr.setForeground(Color.BLACK);
		instr.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(instr);
		
		
		this.setLayout(new BorderLayout());
	}
	public void initrefresh() {
		for(int i=0;i<units.size();i++) {
			this.remove(units.get(i));
		}
		this.setting();
	}
	
	public void setting() {
		for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				for(int j=0;j<g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().size();j++) {
					if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Archer) {
						units.add(new JButton("Archer "+(j+1)));
					}
					else if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Cavalry) {
						units.add(new JButton("Cavalry "+(j+1)));
					}
					else if(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j) instanceof Infantry) {
						units.add(new JButton("Infantry "+(j+1)));
					}
				}
			}
		int h=30;
		int y=120;
		for(int i=0;i<units.size();i++) {
			JButton x=units.get(i);
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			view.getInitiate().setVisible(false);
			view.getCitywindow().setVisible(true);
		}
		int i=0;
		 for(i=0;i<units.size();i++) {
		    	if(e.getSource()==units.get(i)) {
		    		this.found=true;
		    		break;
		    	}
		    }
		 
		if(e.getSource()==initiate) {
			if(this.found==false) {
				JOptionPane.showMessageDialog(this, "Choose a unit first to initiate","ERROR",JOptionPane.ERROR_MESSAGE);	
			}
			else {
				for(int j=0;j<g.getPlayer().getControlledCities().size();j++) {
					if((g.getPlayer().getControlledCities().get(j).getName().equals(map.getMycity()))) {
						g.getPlayer().initiateArmy(g.getPlayer().getControlledCities().get(j), g.getPlayer().getControlledCities().get(j).getDefendingArmy().getUnits().get(i-1));
					}
				}
			view.getInitiate().setVisible(false);
			view.getCitywindow().setVisible(true);
			
		
			}
		}
	}

}
