package com.wnxy.music;

import com.wnxy.ui.GamePanel;

public class PlayerBGM implements Runnable{
	public PlayerBGM(){
		new Thread(this).start();
	}
	public void run() {
		while(true){
			
			if(GamePanel.players.isRun!=0){
				return;
			}else{
				Music.soundEffect(Music.m3);
			}
			try {
				Thread.sleep(272000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
