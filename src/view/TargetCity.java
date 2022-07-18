package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import units.Status;

public class TargetCity extends JPanel implements ActionListener {
	private StartView view;
	private Choicepanel choose;
	private Game g;
	private JLabel name;
	private JLabel target;
	private JLabel instr;
	private JButton battlefield;
	private boolean choice;
	private int selected;
	private ArrayList<JButton> contarmies;
	private JComboBox choices;
	private String TargetCity;
	private BattleView battleview;
	private JButton back;
	private JButton set;
	private JLabel image;
	
	public TargetCity(StartView view,Game g) {
		this.view=view;
		this.g=g;
		TargetCity="";
		this.choice=false;
		this.setSelected(0);
		contarmies=new ArrayList<JButton>();
		
		back=new JButton("Return To Map View");
		back.setBounds(850,22,300,25);
		back.setBackground(Color.getColor("1FBED6"));
		back.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		back.addActionListener(this);
		this.add(back);
		
		instr=new JLabel("When you choose City and an Army click here:");
		instr.setBounds(30,220,600,100);
		instr.setForeground(Color.BLACK);
		instr.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(instr);
		
		set=new JButton("Set Target");
		set.setBounds(600,260,300,25);
		set.setBackground(Color.getColor("1FBED6"));
		set.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		set.addActionListener(this);
		this.add(set);
		
		battlefield=new JButton("GET READY FOR ATTACK!!");
		battlefield.setBounds(20,600,1330,40);
		battlefield.setBackground(Color.RED);
		battlefield.setForeground(Color.white);
		battlefield.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		battlefield.setEnabled(false);
		this.add(battlefield);
		
		name=new JLabel("Please Choose Target City:");
		name.setBounds(30,30,400,100);
		name.setForeground(Color.BLACK);
		name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(name);
		
		String [] cityname=new String[3];
		cityname[0]="City Choice";
		int i=0;
		int j=1;
		while(i<3) {
			if(!(g.getAvailableCities().get(i).getName().equals((String)view.getNextwindow().getCities().getSelectedItem()))) {
				cityname[j]=g.getAvailableCities().get(i).getName();
				j++;
				i++;
			}
			else {
				i++;
			}
		}
		choices=new JComboBox<String>(cityname);
		choices.setBounds(360,70,150,25);
		choices.addActionListener(this);
		this.add(choices);
		
		this.targetrefresh();
		target=new JLabel("Please Choose an Army:");
		target.setBounds(30,100,400,100);
		target.setForeground(Color.BLACK);
		target.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		this.add(target);
		
		this.setLayout(new BorderLayout());
		image=new JLabel();
		//image.setBackground(Color.white);
		image.setLayout(new FlowLayout());
		this.add(image);
	}
	public void targetrefresh() {
		for(int i=0;i<contarmies.size();i++) {
			this.remove(contarmies.get(i));
		}
		this.setting();
	}
	public void setting() {
		for(int i=0;i<g.getPlayer().getControlledArmies().size();i++) {
				contarmies.add(new JButton("Controlled Army "+(i+1)));
			}
		
		int h=30;
		int y=190;
		for(int i=0;i<contarmies.size();i++) {
			JButton x=contarmies.get(i);
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

//		if(e.getSource()==battlefield) {
//			view.getTarget().setVisible(false);
//			view.add(view.getBattlewindow());	
//			this.battleview= new BattleView(view,g);
//			view.getBattlewindow().setVisible(true);		
//			
//		}
		if(e.getSource()==back) {
			view.getTarget().setVisible(false);
			view.getMapwindow().setVisible(true);
		}
		int i=0;
		for(i=0;i<contarmies.size();i++) {
			if(e.getSource()==contarmies.get(i)) {
				this.choice=true;
				break;
			}
		}
		this.setSelected(i-1);
		if(e.getSource()==set) {
			if(choices.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "You must choose a Target city","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			if(this.choice==false){
				JOptionPane.showMessageDialog(this, "You must choose an Army","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else {
				this.setTargetCity((String)choices.getSelectedItem());
				g.targetCity(g.getPlayer().getControlledArmies().get(i-1),(String)choices.getSelectedItem());
				view.getMapwindow().getInfo().setText(view.getMapwindow().showarmies());
					}
				}
			}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public String getTargetCity() {
		return TargetCity;
	}
	public void setTargetCity(String targetCity) {
		TargetCity = targetCity;
	}
		}
	


