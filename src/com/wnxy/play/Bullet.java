package com.wnxy.play;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.wnxy.Map.Map;
import com.wnxy.ui.GamePanel;

public class Bullet extends Character{
	public int dir;
	public int status=d.normal;
	Boom bm=new Boom();
	public Bullet() {
		super();
	}

	public Bullet(int x, int y, int dirction, int index) {
		super(x, y, dirction, index);
	}
	/**将子弹的方向与玩家的方向对应*/
	public void changeDirect(){
		if(dirction==this.upward){
			dir=this.down;
		}else if(dirction==this.down){
			dir=this.right;
		}else if(dirction==this.right){
			dir=this.upward;
		}else{
			dir=dirction;
		}
	}
	/**子弹的移动（父类）及子弹死亡爆炸效果的添加*/
	public void move(){
		if(dir==0){
			int element=Map.Maps[y-1][x];
			if(element==d.grass||element==d.ice||element==d.floor||element==d.water){
				this.y--;
			}else if((element==d.brick)&&this.status!=d.death){
				this.y--;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}else if(element==d.king&&this.status!=d.death){
				this.y--;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}
			else{
				this.y--;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
		}else if(dir==1){
			int element=Map.Maps[y][x-1];
			if(element==d.grass||element==d.ice||element==d.floor||element==d.water){
				this.x--;
			}else if(element==d.brick&&this.status!=d.death){
				this.x--;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}else if(element==d.king&&this.status!=d.death){
				this.x--;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}
			else{
				this.x--;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
		}else if(dir==2){
			int element=Map.Maps[y+1][x];
			if(element==d.grass||element==d.ice||element==d.floor||element==d.water){
				this.y++;
			}else if(element==d.king&&this.status!=d.death){
				this.y++;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}else if(element==d.brick&&this.status!=d.death){
				this.y++;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
			else{
				this.y++;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
		}else if(dir==3){
			int element=Map.Maps[y][x+1];
			if(element==d.grass||element==d.ice||element==d.floor||element==d.water){
				this.x++;
			}else if(element==d.king&&this.status!=d.death){
				this.x++;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}else if((element==d.brick)&&this.status!=d.death){
				this.x++;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
			else{
				this.x++;
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
		}
		if(this.status==d.death){
			GamePanel.bulletss.remove(this);
			GamePanel.enemyBullet.remove(this);
		}
	}
	/**绘制子弹*/
	public void drawBullet(Graphics g,JPanel panel,BufferedImage img){
		g.drawImage(img, this.x*gf.getSizes(), this.y*gf.getSizes(), (this.x+1)*gf.getSizes(),
				(this.y+1)*gf.getSizes(),0, this.dir*img.getHeight()/4, 
				img.getWidth(), (this.dir+1)*img.getHeight()/4, panel);
	}
	

}
