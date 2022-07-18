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
import javax.swing.JPanel;

public class LostGame extends JPanel implements ActionListener{
	private JLabel icon;
	private StartView view;
	private JButton Exit;
	public LostGame(StartView view) {
		this.view=view;
		
		Exit=new JButton("Exit");
		Exit.setBounds(670,500,100,50);;
		Exit.setBackground(Color.BLACK);
		Exit.setForeground(Color.WHITE);
		Exit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		Exit.addActionListener(this);
		this.add(Exit,BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		icon=new JLabel();
		icon.setIcon(new ImageIcon("visuals/lose.jpg"));
		icon.setLayout(new FlowLayout());
		this.add(icon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Exit) {
			view.dispose();
		}
		
	}

}
