package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;
import units.Unit;

public class CityView extends JPanel implements ActionListener{
	private StartView view;
	private MapView map;
	private InitiateArmy initiate;
	private JButton BuildFarm;
	private JButton BuildMarket;
	private JButton BuildStable;
	private JButton BuildArcheryRange;
	private JButton BuildBarracks;
	private JButton UpgradeFarm;
	private JButton UpgradeMarket;
	private JButton UpgradeStable;
	private JButton UpgradeBarracks;
	private JButton UpgradeArcheryRange;
	private JButton RecruitArcher;
	private JButton RecruitInfantry;
	private JButton RecruitCavalry;
	private JButton InitiateArmy;
	private JButton yourarmy;
	private JButton relocate;
	private ArmyInfo armyinfo;
	private RelocateUnit unit;
	private String data;
	private JTextArea info;
	private Game g;
	private JButton backtomapview;
	public JButton getBacktomapview() {
		return backtomapview;
	}

	private JLabel image;

	
	public CityView(StartView view,Game g) {
		
		this.view=view;
		this.map=view.getMapwindow();
		this.data="";
		this.g=g;
		//this.armyinfo= new ArmyInfo(view,g);
		//this.battleview= new BattleView(view,g);
		
		BuildFarm=new JButton("Build Farm",new ImageIcon("visuals/farm.jpg"));
		BuildFarm.setHorizontalTextPosition(SwingConstants.CENTER);
		BuildFarm.setBounds(20,20,250,100);
		BuildFarm.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		BuildFarm.addActionListener(this);
		BuildFarm.setForeground(Color.WHITE);
		this.add(BuildFarm);
		
		BuildMarket=new JButton("Build Market",new  ImageIcon("visuals/market.jpg"));
		BuildMarket.setHorizontalTextPosition(SwingConstants.CENTER);
		BuildMarket.setForeground(Color.WHITE);
		BuildMarket.setBounds(20,130,250,100);
		BuildMarket.setBackground(Color.getColor("1FBED6"));
		BuildMarket.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		BuildMarket.addActionListener(this);
		this.add(BuildMarket);
		
		BuildArcheryRange=new JButton("Build Archery Range",new  ImageIcon("visuals/archeryrange.jpg"));
		BuildArcheryRange.setHorizontalTextPosition(SwingConstants.CENTER);
		BuildArcheryRange.setForeground(Color.WHITE);
		BuildArcheryRange.setBounds(20,240,250,100);
		BuildArcheryRange.setBackground(Color.getColor("1FBED6"));
		BuildArcheryRange.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
		BuildArcheryRange.addActionListener(this);
		this.add(BuildArcheryRange);
		
		BuildStable=new JButton("Build Stable",new  ImageIcon("visuals/stable.jpg"));
		BuildStable.setHorizontalTextPosition(SwingConstants.CENTER);
		BuildStable.setForeground(Color.WHITE);
		BuildStable.setBounds(20,350,250,100);
		BuildStable.setBackground(Color.getColor("1FBED6"));
		BuildStable.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		BuildStable.addActionListener(this);
		this.add(BuildStable);
		
		BuildBarracks=new JButton("Build Barracks",new  ImageIcon("visuals/barracks.png"));
		BuildBarracks.setHorizontalTextPosition(SwingConstants.CENTER);
		BuildBarracks.setForeground(Color.WHITE);
		BuildBarracks.setBounds(20,460,250,100);
		BuildBarracks.setBackground(Color.getColor("1FBED6"));
		BuildBarracks.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		BuildBarracks.addActionListener(this);
		this.add(BuildBarracks);
		
		UpgradeFarm=new JButton("Upgrade Farm",new  ImageIcon("visuals/farm.jpg"));
		UpgradeFarm.setHorizontalTextPosition(SwingConstants.CENTER);
		UpgradeFarm.setBounds(320,20,250,100);
		UpgradeFarm.setForeground(Color.WHITE);
		UpgradeFarm.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		UpgradeFarm.addActionListener(this);
		UpgradeFarm.setEnabled(false);
		this.add(UpgradeFarm);
		
		UpgradeMarket=new JButton("Upgrade Market",new  ImageIcon("visuals/market.jpg"));
		UpgradeMarket.setHorizontalTextPosition(SwingConstants.CENTER);
		UpgradeMarket.setBounds(320,130,250,100);
		UpgradeMarket.setForeground(Color.WHITE);
		UpgradeMarket.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		UpgradeMarket.addActionListener(this);
		UpgradeMarket.setEnabled(false);
		this.add(UpgradeMarket);
		
		UpgradeArcheryRange=new JButton("Upgrade Archery Range",new  ImageIcon("visuals/archeryrange.jpg"));
		UpgradeArcheryRange.setHorizontalTextPosition(SwingConstants.CENTER);
		UpgradeArcheryRange.setBounds(320,240,250,100);
		UpgradeArcheryRange.setForeground(Color.WHITE);
		UpgradeArcheryRange.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
		UpgradeArcheryRange.addActionListener(this);
		UpgradeArcheryRange.setEnabled(false);
		this.add(UpgradeArcheryRange);
		
		UpgradeStable=new JButton("Upgrade Stable",new  ImageIcon("visuals/stable.jpg"));
		UpgradeStable.setHorizontalTextPosition(SwingConstants.CENTER);
		UpgradeStable.setBounds(320,350,250,100);
		UpgradeStable.setForeground(Color.WHITE);
		UpgradeStable.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		UpgradeStable.addActionListener(this);
		UpgradeStable.setEnabled(false);
		this.add(UpgradeStable);
		
		UpgradeBarracks=new JButton("Upgrade Barracks",new  ImageIcon("visuals/barracks.png"));
		UpgradeBarracks.setHorizontalTextPosition(SwingConstants.CENTER);
		UpgradeBarracks.setBounds(320,460,250,100);
		UpgradeBarracks.setForeground(Color.WHITE);
		UpgradeBarracks.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		UpgradeBarracks.addActionListener(this);
		UpgradeBarracks.setEnabled(false);
		this.add(UpgradeBarracks);
		
		RecruitArcher=new JButton("Recruit Archer",new  ImageIcon("visuals/archeryrange.jpg"));
		RecruitArcher.setHorizontalTextPosition(SwingConstants.CENTER);
		RecruitArcher.setBounds(600,240,250,100);
		RecruitArcher.setForeground(Color.WHITE);
		RecruitArcher.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		RecruitArcher.addActionListener(this);
		RecruitArcher.setEnabled(false);
		this.add(RecruitArcher);
		
		RecruitCavalry=new JButton("Recruit Cavalry",new  ImageIcon("visuals/stable.jpg"));
		RecruitCavalry.setHorizontalTextPosition(SwingConstants.CENTER);
		RecruitCavalry.setBounds(600,350,250,100);
		RecruitCavalry.setForeground(Color.WHITE);
		RecruitCavalry.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		RecruitCavalry.addActionListener(this);
		RecruitCavalry.setEnabled(false);
		this.add(RecruitCavalry);
		
		RecruitInfantry=new JButton("Recruit Infantry",new  ImageIcon("visuals/barracks.png"));
		RecruitInfantry.setHorizontalTextPosition(SwingConstants.CENTER);
		RecruitInfantry.setBounds(600,460,250,100);
		RecruitInfantry.setForeground(Color.WHITE);
		RecruitInfantry.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		RecruitInfantry.addActionListener(this);
		RecruitInfantry.setEnabled(false);
		this.add(RecruitInfantry);
		
		InitiateArmy=new JButton("Initiate Army");
		InitiateArmy.setBounds(320,570,250,25);
		InitiateArmy.setBackground(Color.getColor("1FBED6"));
		InitiateArmy.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		InitiateArmy.addActionListener(this);
		InitiateArmy.setEnabled(false);
		this.add(InitiateArmy);
		
		yourarmy=new JButton("Army Info.");
		yourarmy.setBounds(20,570,250,25);
		yourarmy.setBackground(Color.getColor("1FBED6"));
		yourarmy.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		yourarmy.addActionListener(this);
		this.add(yourarmy);
		
		backtomapview=new JButton("Return To Map View");
		backtomapview.setBounds(870,20,300,25);
		backtomapview.setBackground(Color.getColor("1FBED6"));
		backtomapview.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		backtomapview.addActionListener(this);
		this.add(backtomapview);
		
		relocate=new JButton("Relocate");
		relocate.setBounds(600,570,250,25);
		relocate.setBackground(Color.getColor("1FBED6"));
		relocate.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		relocate.addActionListener(this);
		relocate.setEnabled(false);
		this.add(relocate);
		
		
		info=new JTextArea(data);
	    info.setBounds(870,48,480,595);
		info.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		info.setForeground(Color.BLACK);
		info.setEditable(false);
		this.add(info);
		
		this.setLayout(new BorderLayout());
//		image=new JLabel();
//		image.setIcon(new ImageIcon("visuals/city.jpg"));
//		image.setLayout(new FlowLayout());
//		this.add(image);
	}
	public String buildinginfo() {
		String data="";
		for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
			for(int j=0;j<g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().size();j++) {
				if(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Farm) {
					data+="Building Type: Farm,"+System.lineSeparator();
					data+="Level:"+g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j).getLevel()+System.lineSeparator();
					data+="Upgrade Cost:"+g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j).getUpgradeCost()+System.lineSeparator();
					data+=System.lineSeparator();
				}
				else if(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Market) {
					data+="Building Type: Market,"+System.lineSeparator();
					data+="Level:"+g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j).getLevel()+System.lineSeparator();
					data+="Upgrade Cost:"+g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j).getUpgradeCost()+System.lineSeparator();
					data+=System.lineSeparator();
				}
			}
			for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
				if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof ArcheryRange) {
					data+="Building Type: Archery Range,"+System.lineSeparator();
					data+="Level:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getLevel()+System.lineSeparator();
					data+="Upgrade Cost:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getUpgradeCost()+System.lineSeparator();
					data+="Recruitment Cost:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getRecruitmentCost()+System.lineSeparator();
					data+=System.lineSeparator();
						
				}
				else if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof Barracks) {
					data+="Building Type: Barracks,"+System.lineSeparator();
					data+="Level:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getLevel()+System.lineSeparator();
					data+="Upgrade Cost:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getUpgradeCost()+System.lineSeparator();
					data+="Recruitment Cost:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getRecruitmentCost()+System.lineSeparator();
					data+=System.lineSeparator();
				}
				else if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof Stable) {
					data+="Building Type: Stable,"+System.lineSeparator();
					data+="Level:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getLevel()+System.lineSeparator();
					data+="Upgrade Cost:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getUpgradeCost()+System.lineSeparator();
					data+="Recruitment Cost:"+g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).getRecruitmentCost()+System.lineSeparator();
					data+=System.lineSeparator();
				}
			}
		}
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==yourarmy) {
			view.getCitywindow().setVisible(false);
			this.armyinfo= new ArmyInfo(view,g);
			view.add(armyinfo);
			view.setArmyinfo(armyinfo);
			view.getArmyinfo().setVisible(true);
			
		}
		else if(e.getSource()==backtomapview) {
			view.getCitywindow().setVisible(false);
			view.getMapwindow().setVisible(true);
			view.getMapwindow().getInfo().setText(view.getMapwindow().showarmies());
		}
		else if(e.getSource()==BuildFarm) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					try {
						g.getPlayer().build("Farm",g.getPlayer().getControlledCities().get(i).getName());
						view.setGame1(g);
						view.refreshText();
						this.BuildFarm.setEnabled(false);
						this.data=this.buildinginfo();
						info.setText(data);
						this.UpgradeFarm.setEnabled(true);
					
					} catch (NotEnoughGoldException e1) {
						this.BuildFarm.setEnabled(true);
						JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
		else if(e.getSource()==BuildMarket) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					try {
						g.getPlayer().build("Market",g.getPlayer().getControlledCities().get(i).getName());
						view.setGame1(g);
						view.refreshText();
						this.data=this.buildinginfo();
						info.setText(data);
						this.BuildMarket.setEnabled(false);
						UpgradeMarket.setEnabled(true);
						
					} catch (NotEnoughGoldException e1) {
						this.BuildMarket.setEnabled(true);
						JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
		else if(e.getSource()==BuildArcheryRange) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					try {
						g.getPlayer().build("ArcheryRange",g.getPlayer().getControlledCities().get(i).getName());
						view.setGame1(g);
						view.refreshText();
						this.BuildArcheryRange.setEnabled(false);
						this.data=this.buildinginfo();
						info.setText(data);
						UpgradeArcheryRange.setEnabled(true);
						RecruitArcher.setEnabled(true);
						
					} catch (NotEnoughGoldException e1) {
						this.BuildArcheryRange.setEnabled(true);
						JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
		else if(e.getSource()==BuildBarracks) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					try {
						g.getPlayer().build("Barracks",g.getPlayer().getControlledCities().get(i).getName());
						view.setGame1(g);
						view.refreshText();
						this.BuildBarracks.setEnabled(false);
						this.data=this.buildinginfo();
						info.setText(data);
						UpgradeBarracks.setEnabled(true);
						RecruitInfantry.setEnabled(true);
						
					} catch (NotEnoughGoldException e1) {
						this.BuildBarracks.setEnabled(true);
						JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
		else if(e.getSource()==BuildStable) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					try {
						g.getPlayer().build("Stable",g.getPlayer().getControlledCities().get(i).getName());
						view.setGame1(g);
						view.refreshText();
						this.BuildStable.setEnabled(false);
						this.data=this.buildinginfo();
						info.setText(data);
						UpgradeStable.setEnabled(true);
						RecruitCavalry.setEnabled(true);
						
					} catch (NotEnoughGoldException e1) {
						this.BuildStable.setEnabled(true);
						JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
		else if(e.getSource()==UpgradeFarm) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Farm) {
							try {
								g.getPlayer().upgradeBuilding(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j));
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								view.refreshText();
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant upgrade this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxLevelException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of upgrading","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
			}
		}
		else if(e.getSource()==UpgradeMarket) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Market) {
							try {
								g.getPlayer().upgradeBuilding(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j));
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								view.refreshText();
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant upgrade this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxLevelException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of upgrading","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
			}
		}
		else if(e.getSource()==UpgradeStable) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof Stable) {
							try {
								g.getPlayer().upgradeBuilding(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j));
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								view.refreshText();
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant upgrade this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxLevelException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of upgrading","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
			}
		}
		else if(e.getSource()==UpgradeBarracks) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof Barracks) {
							try {
								g.getPlayer().upgradeBuilding(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j));
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								view.refreshText();
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant upgrade this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxLevelException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of upgrading","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
			}
		}
		else if(e.getSource()==UpgradeArcheryRange) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof ArcheryRange) {
							try {
								g.getPlayer().upgradeBuilding(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j));
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								view.refreshText();
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant upgrade this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxLevelException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of upgrading","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
			}
		}
		else if(e.getSource()==RecruitArcher) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof ArcheryRange) {
							try {
								g.getPlayer().recruitUnit("Archer", g.getPlayer().getControlledCities().get(i).getName());
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								InitiateArmy.setEnabled(true);
								relocate.setEnabled(true);
								view.refreshText();
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant recruit this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxRecruitedException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of recruiting units this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}
						
					}
				}
			}
		}
		else if(e.getSource()==RecruitCavalry) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof Stable) {
							try {
								g.getPlayer().recruitUnit("Cavalry", g.getPlayer().getControlledCities().get(i).getName());
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								InitiateArmy.setEnabled(true);
								relocate.setEnabled(true);
								view.refreshText();
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant recruit this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxRecruitedException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of recruiting units this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}
						
					}
				}
			}
		}
		else if(e.getSource()==RecruitInfantry) {
			for(int i=0;i<g.getPlayer().getControlledCities().size();i++) {
				if((g.getPlayer().getControlledCities().get(i).getName().equals(map.getMycity()))) {
					for(int j=0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++) {
						if(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j) instanceof Barracks) {
							try {
								g.getPlayer().recruitUnit("Infantry", g.getPlayer().getControlledCities().get(i).getName());
								view.setGame1(g);
								this.data=this.buildinginfo();
								info.setText(data);
								InitiateArmy.setEnabled(true);
								relocate.setEnabled(true);
								view.refreshText();
							} catch (BuildingInCoolDownException e1) {
								JOptionPane.showMessageDialog(this, "Cant recruit this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (MaxRecruitedException e1) {
								JOptionPane.showMessageDialog(this, "Reached Max Level of recruiting units this turn","ERROR",JOptionPane.ERROR_MESSAGE);
							} catch (NotEnoughGoldException e1) {
								JOptionPane.showMessageDialog(this, "No Enough Gold","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						}
						
					}
				}
			}
		}
		else if(e.getSource()==relocate) {
			view.getCitywindow().setVisible(false);
			this.unit= new RelocateUnit(view,g);
			view.add(unit);
			view.setRelocate(unit);
			view.getRelocate().setVisible(true);
		}
		else if(e.getSource()==InitiateArmy) {
			
			view.getCitywindow().setVisible(false);
			this.initiate= new InitiateArmy(view,g);
			view.add(initiate);
			view.setInitiate(initiate);
			view.getInitiate().setVisible(true);
			
		}
			
	}
		
}


