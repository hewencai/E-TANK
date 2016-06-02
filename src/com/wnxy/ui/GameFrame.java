package com.wnxy.ui;

import javax.swing.JFrame;

import com.wnxy.music.PlayerBGM;
public class GameFrame extends JFrame{
	
	GameFarms gaf=new GameFarms();
	/**设置窗口属性*/
	public GameFrame(){
		this.setSize((gaf.getColumn()+1) * gaf.getSizes(),(gaf.getRow()+2) * gaf.getSizes() );
		this.setContentPane(new GamePanel());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new GameFrame();
		new PlayerBGM();
}
}
