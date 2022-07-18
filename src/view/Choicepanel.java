package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;

public class Choicepanel extends JPanel implements ActionListener {
	private StartView view;
	private MapView map;
	private BattleView battle;
	private Choice2 plus;
	private Game g;
	private JButton laysiege;
	private JButton attack;
	
	public Choicepanel(StartView view,Game g) {
		this.view=view;
		this.g=g;
		
		map=new MapView(view,g);
		
		laysiege=new JButton("LAYSIEGE?");
		laysiege.setBounds(20,200,1330,100);
		laysiege.setBackground(Color.RED);
		laysiege.setForeground(Color.white);
		laysiege.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		laysiege.addActionListener(this);
		this.add(laysiege);
		
		attack=new JButton("ATTACK");
		attack.setBounds(20,400,1330,100);
		attack.setBackground(Color.RED);
		attack.setForeground(Color.white);
		attack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		attack.addActionListener(this);
		this.add(attack);
		
		this.setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==attack) {
			view.getChoose().setVisible(false);
			plus=new Choice2(view,g);
			view.add(plus);
			view.setChoice(plus);
			view.getChoice().setVisible(true);
		}
//		else if(e.getSource()==laysiege) {
//			for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
//				if(i==view.getTarget().getSelected()) {
//					if(g.getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0) {
//						g.getPlayer().laySiege(g.getPlayer().getControlledArmies().get(i),g.getPlayer().getControlledArmies().ge);
//						view.getChoose().setVisible(false);
//						view.getMapwindow().setVisible(true);
//					}
//			}
//		}
//		
}

}
