package com.wnxy.play;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.wnxy.Map.Map;
import com.wnxy.ui.GamePanel;

public class Player extends Character implements Runnable{
	public int status;
PlayerBullet pb=new PlayerBullet();
	public int isRun=0;
	JPanel jp=new JPanel();
	public boolean dd=false;
	public Player() {
		super();

	}
	public Player(int x, int y, int dirction, int index) {
		super(x, y, dirction, index);
		new Thread(this).start();
	}
	/**控制玩家脚步变换*/
	public void step(){
		index++;
		if(index==4){
			index=0;
		}
	}
	/**监听事件，控制玩家移动*/
	public void move(KeyEvent e){

		if(e.getKeyCode()==KeyEvent.VK_W){
			int element=Map.Maps[y-1][x];
			if(this.dirction==this.upward){
				if(element==d.grass||element==d.ice||element==d.floor){
					this.y--;
					dd=true;
				}
			}else{
				this.dirction=this.upward;
				dd=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_A){
			int element=Map.Maps[y][x-1];
			if(this.dirction==this.left){
				if(element==d.grass||element==d.ice||element==d.floor){
					this.x--;
					dd=true;
				}
			}else{
				this.dirction=this.left;
				dd=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			int element=Map.Maps[y+1][x];
			if(this.dirction==this.down){
				if(element==d.grass||element==d.ice||element==d.floor){
					this.y++;
					dd=true;
				}
			}else{
				this.dirction=this.down;
				dd=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			int element=Map.Maps[y][x+1];
			if(this.dirction==this.right){
				if(element==d.grass||element==d.ice||element==d.floor){
					this.x++;
					dd=true;
				}
			}else{
				this.dirction=this.right;
				dd=true;
			}
		}
	}
	/**判断是绘制玩家还是星星*/
	public void isDraw(Graphics g,JPanel panel,BufferedImage img,Image star){
		if(this.isDrawStar){
			drawStar(g, panel, star);
		}else{
			draw(g, panel, img);
		}

	}
	public void run() {
		int i=0;
		while(isRun==0){
			if(this.status!=d.death){
				if(isDrawStar){
					i++;
					if(i>=15){
						this.isDrawStar=false;
					}
				}
				else if(PlayerBullet.num>9){
					System.out.println("YOU ARE WINNER!!!");
					isRun=1;
				}
			}else if(GamePanel.j<2){
				GamePanel.j++;
				System.out.println("您被打死"+GamePanel.j+"次");
				this.status=d.normal;
				GamePanel.players=new Player(16, 25, 3, 0);
			}else{
				System.out.println("您被打死三次");
				System.out.println("YOU ARE LOSER!!!");
				isRun=2;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
