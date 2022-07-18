package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import exceptions.FriendlyFireException;
import units.Army;

public class Choice2 extends JPanel implements ActionListener {
	private StartView view;
	private MapView map;
	private BattleView battle;
	private Game g;
	private JButton manual;
	private JButton autos;
	
	public Choice2(StartView view,Game g) {
		this.view=view;
		this.g=g;
		
		map=new MapView(view,g);
		
		manual=new JButton("MANUAL ATTACK");
		manual.setBounds(20,200,1330,100);
		manual.setBackground(Color.RED);
		manual.setForeground(Color.white);
		manual.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		manual.addActionListener(this);
		this.add(manual);
		
		autos=new JButton("AUTORESOLVE");
		autos.setBounds(20,400,1330,100);
		autos.setBackground(Color.RED);
		autos.setForeground(Color.white);
		autos.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		autos.addActionListener(this);
		this.add(autos);
		
		this.setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==manual) {
			JOptionPane.showMessageDialog(this, "GET READY FOR THE BATTLE","ALERT",JOptionPane.INFORMATION_MESSAGE);
			view.getChoice().setVisible(false);
			battle=new BattleView(view,g);
			view.add(battle);
			view.setBattlewindow(battle);
			view.getBattlewindow().setVisible(true);
		}
		else if(e.getSource()==autos) {
			Army attacker=null;
			Army defender=null;
			String city="";
		    int i=0;
		    int j=0;
			for(i=0;i<g.getPlayer().getControlledArmies().size();i++) {
				if(i==view.getTarget().getSelected()) {
						attacker=g.getPlayer().getControlledArmies().get(i);
						city=g.getPlayer().getControlledArmies().get(i).getCurrentLocation();
						break;
					}
			}
			for(j=0;j<g.getAvailableCities().size();j++) {
				if(g.getAvailableCities().get(j).getName().equals(city)) {
					break;
				}
			}
	
			try {
				g.autoResolve(g.getPlayer().getControlledArmies().get(i), g.getAvailableCities().get(j).getDefendingArmy());
				if(attacker.getUnits().size()!=0) {
					JOptionPane.showMessageDialog(this, "YOU WON AUTORESOLVE","CONGRATS",JOptionPane.INFORMATION_MESSAGE);	
				}
				else {
					g.getPlayer().getControlledArmies().remove(i);
					JOptionPane.showMessageDialog(this, "YOU LOST AUTORESOLVE","SAD",JOptionPane.INFORMATION_MESSAGE);
				}
				view.getChoice().setVisible(false);
				view.setGame1(g);
				view.getMapwindow().getInfo().setText(view.getMapwindow().showarmies());
				view.getMapwindow().setVisible(true);
				
			} catch (FriendlyFireException e1) {
				JOptionPane.showMessageDialog(this, "Cant fire friendly unit","ERROR",JOptionPane.ERROR_MESSAGE);
			}
	}
	}
}

