package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import engine.Game;

public class StartView extends JFrame{
	private StartMenu startmenu;
	private PlayerName nextwindow;
	private MapView mapwindow;
	private CityView citywindow;
	private ArmyInfo armyinfo;
	private BattleView Battlewindow;
	private Choicepanel choose;
	private TargetCity target;
	private ChooseArmy chooseArmy;
	private RelocateUnit relocate;
	private Choice2 choice;
	private DefendingArmyUnits units1;
	private ControlledUnits units2;
	private InitiateArmy initiate;;
	private LostGame lost;
	private JTextArea dataalways;
	public Game game1;

	public StartView() {
		super();
		this.setTitle("Empire Building");

		Dimension Max = Toolkit.getDefaultToolkit().getScreenSize();
		this.setMaximumSize(Max);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		StartMenu startmenu=new StartMenu(this);
		this.startmenu=startmenu;
		this.add(startmenu.getID(),BorderLayout.SOUTH);

		PlayerName nextwindow=new PlayerName(this);
		this.nextwindow=nextwindow;
		refresh();
		
	}
	public void refreshText() {
		double gold=this.getGame1().getPlayer().getTreasury();
		int maxturn=this.getGame1().getMaxTurnCount();
		double food=this.getGame1().getPlayer().getFood();
		int current=this.getGame1().getCurrentTurnCount();
		this.dataalways.setText("Player name: "+game1.getPlayer().getName()+",Gold:"+gold+",Food:"+food+",Max Turn Count: "+maxturn+",Current Turn Count: "+current);
		this.getMapwindow().getInfo().setText(this.getMapwindow().showarmies());
	}
	
	public void refresh() {	
		
		
		LostGame lost=new LostGame(this);
		this.lost=lost;
		
		this.add(startmenu);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.revalidate();
		this.repaint();	
	}
	public void saveData(String name,String cityname) throws IOException{
		
		Game game=new Game(name,cityname);
		this.setGame1(game);
		
		this.mapwindow=new MapView(this,game1);
		mapwindow.setVisible(true);
		
		CityView citywindow=new CityView(this,game1);
		this.citywindow=citywindow;
		
		ArmyInfo armyinfo=new ArmyInfo(this,game1);
		this.armyinfo=armyinfo;
		
		BattleView Battlewindow=new BattleView(this,game1);
		this.Battlewindow=Battlewindow;
		
		RelocateUnit relocate=new RelocateUnit(this,game1);
		this.relocate=relocate;
		
		DefendingArmyUnits units1=new DefendingArmyUnits(this,game1);
		this.units1=units1;
		
		ControlledUnits units2=new ControlledUnits(this,game1);
		this.units2=units2;
		
		TargetCity target=new TargetCity(this,game1);
		this.target=target;
		
		Choicepanel choose=new Choicepanel(this,game1);
		this.setChoose(choose);
		
		InitiateArmy initiate=new InitiateArmy(this,game1);
		this.setInitiate(initiate);
		
		ChooseArmy choosearmy=new ChooseArmy(this,game1);
		this.chooseArmy=choosearmy;
		
		Choice2 choice=new Choice2(this,game1);
		this.choice=choice;
		

		double gold=game1.getPlayer().getTreasury();
		int maxturn=game1.getMaxTurnCount();
		double food=game1.getPlayer().getFood();
		int current=game1.getCurrentTurnCount();
		dataalways=new JTextArea("Player name: "+name+",Gold:"+gold+",Food:"+food+",Max Turn Count: "+maxturn+",Current Turn Count: "+current);
		dataalways.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		dataalways.setEditable(false);
		dataalways.setBackground(Color.LIGHT_GRAY);
		dataalways.setBounds(0,650,1400,30);
		this.add(dataalways);
		
		this.add(mapwindow.getEndTurn());
		
		this.add(mapwindow);
		

	}
	public JTextArea getDataalways() {
		return dataalways;
	}
	public void setDataalways(JTextArea dataalways) {
		this.dataalways = dataalways;
	}
	public StartMenu getStartmenu() {
		return startmenu;
	}
	public void setStartmenu(StartMenu startmenu) {
		this.startmenu = startmenu;
	}
	public PlayerName getNextwindow() {
		return nextwindow;
	}
	public void setNextwindow(PlayerName nextwindow) {
		this.nextwindow = nextwindow;
	}

	public MapView getMapwindow() {
		return mapwindow;
	}
	public void setMapwindow(MapView mapwindow) {
		this.mapwindow = mapwindow;
	}

	public static void main(String[] args) {
		new StartView();
	}
	public ArmyInfo getArmyinfo() {
		return armyinfo;
	}
	public Game getGame1() {
		return game1;
	}
	public void setGame1(Game game1) {
		this.game1 = game1;
	}
	public void setArmyinfo(ArmyInfo armyinfo) {
		this.armyinfo = armyinfo;
	}
	public CityView getCitywindow() {
		return citywindow;
	}
	public void setCitywindow(CityView citywindow) {
		this.citywindow = citywindow;
	}
	
	public LostGame getLost() {
		return lost;
	}
	public void setLost(LostGame lost) {
		this.lost = lost;
	}

	public BattleView getBattlewindow() {
		return Battlewindow;
	}
	public void setBattlewindow(BattleView battlewindow) {
		Battlewindow = battlewindow;
	}
	public RelocateUnit getRelocate() {
		return relocate;
	}
	public void setRelocate(RelocateUnit relocate) {
		this.relocate = relocate;
	}
	public DefendingArmyUnits getUnits1() {
		return units1;
	}
	public void setUnits1(DefendingArmyUnits units1) {
		this.units1 = units1;
	}
	public TargetCity getTarget() {
		return target;
	}
	public void setTarget(TargetCity target) {
		this.target = target;
	}
	public ControlledUnits getUnits2() {
		return units2;
	}
	public void setUnits2(ControlledUnits units2) {
		this.units2 = units2;
	}
	public InitiateArmy getInitiate() {
		return initiate;
	}
	public void setInitiate(InitiateArmy initiate) {
		this.initiate = initiate;
	}
	public Choicepanel getChoose() {
		return choose;
	}
	public void setChoose(Choicepanel choose) {
		this.choose = choose;
	}
	public ChooseArmy getChooseArmy() {
		return chooseArmy;
	}
	public void setChooseArmy(ChooseArmy chooseArmy) {
		this.chooseArmy = chooseArmy;
	}
	public Choice2 getChoice() {
		return choice;
	}
	public void setChoice(Choice2 choice) {
		this.choice = choice;
	}

	

}




