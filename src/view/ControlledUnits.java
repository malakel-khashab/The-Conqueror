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

public class ControlledUnits extends JPanel implements ActionListener{
	private StartView view;
	private RelocateUnit relocate;
	private MapView map;
	private ArrayList<JButton> units;
	private JLabel name;
	private JLabel image;
	private JButton back;
	private Game g;
	
	public ControlledUnits(StartView view,Game g) {
		this.view=view;
		this.g=g;
		map=view.getMapwindow();
		relocate=view.getRelocate();
		units=new ArrayList<JButton>();
		
		name=new JLabel("Please Choose a Unit:");
		name.setBounds(30,30,1000,100);
		name.setForeground(Color.BLACK);
		name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(name);
		
		//this.controlrefresh();
		System.out.println(relocate.getChosenindex());
		
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
	public void controlrefresh() {
		for(int i=0;i<units.size();i++) {
			this.remove(units.get(i));
		}
		this.setting();
	}
	
	public void setting() {
		int chosen=relocate.getChosenindex();
		for(int i=0;i<g.getPlayer().getControlledArmies().get(chosen).getUnits().size();i++) {
			if(g.getPlayer().getControlledArmies().get(relocate.getChosenindex()).getUnits().get(i) instanceof Archer) {
				units.add(new JButton("Archer " +i+1));
				
			}
			else if(g.getPlayer().getControlledArmies().get(relocate.getChosenindex()).getUnits().get(i) instanceof Cavalry) {
				units.add(new JButton("Cavalry " +i+1));
				
			}
			else if(g.getPlayer().getControlledArmies().get(relocate.getChosenindex()).getUnits().get(i) instanceof Infantry) {
				units.add(new JButton("Infantry " +i+1));
				
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
			view.getUnits2().setVisible(false);
			view.getCitywindow().setVisible(true);
		}
		
	}

}
