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

import engine.Game;
import exceptions.MaxCapacityException;
import units.Army;
import units.Status;

public class ChooseArmy extends JPanel implements ActionListener {
	private StartView view;
	private boolean found;
	private JLabel name;
	private RelocateUnit rep;
	private JButton back;
	private ArrayList<JButton> extra;
	private Game g;
	
	public ChooseArmy(StartView view,Game g) {
		this.view=view;
		this.g=g;
		extra=new ArrayList<JButton>();
		//this.found=false;
		//rep=new RelocateUnit(view,g);
		
		name=new JLabel("Please Choose an Army:");
		name.setBounds(30,30,1000,100);
		name.setForeground(Color.BLACK);
		name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(name);
		
		this.display();
		
		back=new JButton("Return To City View");
		back.setBounds(1060,610,300,25);
		back.setBackground(Color.getColor("1FBED6"));
		back.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		back.addActionListener(this);
		this.add(back);
		
		this.setLayout(new BorderLayout());
	}
	public void display() {
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
			if(g.getPlayer().getControlledArmies().get(i).getCurrentStatus()==Status.IDLE) {
				extra.add(new JButton("Idle Army "+(i+1)));
			}
		}
		int h=30;
		int y=120;
		for(int i=0;i<extra.size();i++) {
			JButton x=extra.get(i);
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
			this.setVisible(false);
			view.getCitywindow().setVisible(true);
		}
		for(int i=0;i<extra.size();i++) {
			if(e.getSource()==extra.get(i)) {
				
				try {
					Army parent=(g.getPlayer().getControlledArmies().get(i));
					parent.relocateUnit(view.getUnits1().getToberelocated());
					this.setVisible(false);
					view.getCitywindow().setVisible(true);
				} catch (MaxCapacityException e1) {
					JOptionPane.showMessageDialog(this, "Max Level,choose another Army","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}

}