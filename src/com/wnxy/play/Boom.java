package com.wnxy.play;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.wnxy.Map.Dic;
import com.wnxy.ui.GameFarms;
import com.wnxy.ui.GamePanel;

public class Boom{
	GameFarms gf=new GameFarms();
	Character ca=new Character();
	/**是否绘制爆炸效果*/
	public boolean isDrawBoom=true;
	public int x,y;
	Dic d=new Dic();
	/**定义当前爆炸生命状态*/
	public int status=d.normal;
	public int dirction=0,index=0;
	public Boom(){

	}
	public Boom(int x,int y){
		this.x=x;
		this.y=y;
	}
	/**绘制爆炸效果*/
	public void drawBoom(Graphics g,JPanel panel,BufferedImage img){
		if(this.status!=d.death){
			g.drawImage(img, this.x*gf.getSizes(), this.y*gf.getSizes(), (this.x+1)*gf.getSizes(),
					(this.y+1)*gf.getSizes(), this.index*img.getWidth()/5, this.dirction*img.getHeight()/3, 
					(this.index+1)*img.getWidth()/5, (this.dirction+1)*img.getHeight()/3, panel);
			stepBoom();
		}
	}
	/**爆炸动态脚步*/
	public void stepBoom(){
		index++;
		if(index==4){
			index=0;
			dirction++;
			if(dirction==2){
				dirction=0;
			}
		}
		if(dirction==0&&index==0){
			this.status=d.death;
			GamePanel.booms.remove(this);
		}
	}
}
