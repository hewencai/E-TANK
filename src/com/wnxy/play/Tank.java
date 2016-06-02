package com.wnxy.play;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import com.wnxy.Map.Dic;
import com.wnxy.Map.Map;
import com.wnxy.ui.GamePanel;

public class Tank extends Character implements Runnable{
	Dic d=new Dic();
	/** 当前敌方坦克的生命状态*/
	public int status;
	/** 当前坦克的dirction方向随机*/
	private Random ran=new Random();
	public Tank() {
		super();
	}
	public Tank(int x, int y, int dirction, int index) {
		super(x, y, dirction, index);
		new Thread(this).start();
	}
	/**判断是绘制敌方坦克还是绘制闪烁*/
	public void isDraw(Graphics g,JPanel panel,BufferedImage img,Image star){
		if(this.isTankStar){
			drawStar(g, panel, star);
		}else if(isDrawTank){
			draw(g, panel, img);
		}
	}
	/**设置敌方坦克的脚步*/
	public void step(){
		index++;
		if(index==4){
			index=0;
		}
	}
	/**初始化敌方坦克的子弹添加到子弹集合*/
	public void addEnemyBullet(int x,int y){
		if(GamePanel.enemyBullet.size()<GamePanel.number){
		GamePanel.enemyBullets=new EnemyBullet(x, y, dirction, index);
		GamePanel.enemyBullet.add(GamePanel.enemyBullets);
		GamePanel.enemyBullets.changeDirect();
		}
	}
	/**控制敌方坦克的移动*/
	public void move(){
		if(dirction==0&&(Map.Maps[y+1][x]==d.grass||Map.Maps[y+1][x]==d.floor||Map.Maps[y+1][x]==d.ice)){
			this.addEnemyBullet(this.x, this.y+1);
			y++;
		}
		else if(dirction==1&&(Map.Maps[y][x-1]==d.grass||Map.Maps[y][x-1]==d.floor||Map.Maps[y][x-1]==d.ice)){
			this.addEnemyBullet(this.x-1, this.y);
			x--;
		}
		else if(dirction==2&&(Map.Maps[y][x+1]==d.grass||Map.Maps[y][x+1]==d.floor||Map.Maps[y][x+1]==d.ice)){
			this.addEnemyBullet(this.x+1, this.y);
			x++;
		}
		else if(dirction==3&&(Map.Maps[y-1][x]==d.grass||Map.Maps[y-1][x]==d.floor||Map.Maps[y-1][x]==d.ice)){
			this.addEnemyBullet(this.x, this.y-1);
			y--;
		}else{
			dirction=ran.nextInt(4);
			this.addEnemyBullet(this.x, this.y);
		}
	}	
	public void run() {
		int i=0,j=0;
		while(true){
			if(isTankStar){
				i++;
				if(i>=10){
					this.isTankStar=false;	
				}
			}else if(isDrawTank){
				i++;
				j++;
				if(i>3){
					this.step();
					this.move();
					i=0;
					if(j>12){
						dirction=ran.nextInt(4);
						j=0;
					}
				}
			}
			if(this.status==d.death){
				return;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}



}
