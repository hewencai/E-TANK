package com.wnxy.play;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.wnxy.Map.Dic;
import com.wnxy.Map.Map;
import com.wnxy.ui.GameFarms;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Character {
	GameFarms gf=new GameFarms();
	Dic d=new Dic();
	public int x,y,dirction,index;
	public int upward=3;
	public int down=0;
	public int left=1;
	public int right=2;
	public int starId=0;
	public boolean isDrawStar=true;
	public boolean isTankStar=true;
	public boolean isDrawTank=true;
	public int isTank=0;

	public boolean isDrawPlayer=true;
	public Character(){

	}
	public Character(int x,int y,int dirction,int index){
		this.x=x;
		this.y=y;
		this.dirction=dirction;
		this.index=index;
	}
	/** 绘制玩家坦克和敌方坦克*/
	public void draw(Graphics g,JPanel panel,BufferedImage img){
		g.drawImage(img, this.x*gf.getSizes(), this.y*gf.getSizes(), (this.x+1)*gf.getSizes(),
				(this.y+1)*gf.getSizes(), this.index*img.getWidth()/4, this.dirction*img.getHeight()/4, 
				(this.index+1)*img.getWidth()/4, (this.dirction+1)*img.getHeight()/4, panel);
	}
	/**玩家和敌方坦克闪烁状态的绘制 */
	public void drawStar(Graphics g,JPanel panel,Image img){
		if(starId==2){
			starId=0;
		}starId++;
		g.drawImage(img, this.x*gf.getSizes(), this.y*gf.getSizes(), (this.x+1)*gf.getSizes(),
				(this.y+1)*gf.getSizes(), starId*192, 0, 
				(starId+1)*192, 192, panel);
	}
	/** 玩家、子弹、敌方坦克钻草*/
	public void undergrass(Graphics g,JPanel panel,Image grass){
		if(Map.Maps[this.y][this.x]==d.grass){
			g.drawImage(grass, ((this.x)*gf.getSizes()), ((this.y)*gf.getSizes()), ((this.x+1)*gf.getSizes()),
					((this.y+1)*gf.getSizes()), 0, 0, 57, 70, panel);
		}

	}
}
