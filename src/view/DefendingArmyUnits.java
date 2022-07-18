package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class DefendingArmyUnits extends JPanel implements ActionListener {
	private StartView view;
	private Unit toberelocated;
	private ChooseArmy choose;
	private MapView map;
	private ArrayList<JButton> units;
	private JLabel name;
	private JLabel image;
	private JButton back;
	private Game g;

	public DefendingArmyUnits(StartView view,Game g) {
		this.view=view;
		this.g=g;
		map=view.getMapwindow();
		units=new ArrayList<JButton>();
		
		name=new JLabel("Please Choose a Unit:");
		name.setBounds(30,30,1000,100);
		name.setForeground(Color.BLACK);
		name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(name);
		
		this.defendrefresh();
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
	
	public void defendrefresh() {
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			view.getUnits1().setVisible(false);
			view.getCitywindow().setVisible(true);
		}
		int i=0;
		for(i=0;i<units.size();i++) {
			if(e.getSource()==units.get(i)) {
				for(int j=0;j<g.getPlayer().getControlledCities().size();j++) {
					if(g.getPlayer().getControlledCities().get(j).getName().equals(view.getMapwindow().getMycity())) {
						this.toberelocated=g.getPlayer().getControlledCities().get(j).getDefendingArmy().getUnits().get(i);
						
					}
				}
				view.getUnits1().setVisible(false);
				choose=new ChooseArmy(view,g);
				view.add(choose);
				view.getChooseArmy().setVisible(true);
			}
		}
		
	}

	public Unit getToberelocated() {
		return toberelocated;
	}

	public void setToberelocated(Unit toberelocated) {
		this.toberelocated = toberelocated;
	}

}
