package com.wnxy.play;

import com.wnxy.Map.Map;
import com.wnxy.music.Music;
import com.wnxy.ui.GamePanel;

public class PlayerBullet extends Bullet implements Runnable{
	public static int num=0;
	public PlayerBullet() {
		super();
	}

	public PlayerBullet(int x, int y, int dirction, int index) {
		super(x, y, dirction, index);
		new Thread(this).start();
	}
	public void move(){
		if(dir==0){
			int element=Map.Maps[y-1][x];
			if(element==d.grass||element==d.ice||element==d.floor||element==d.water){
				this.y--;
			}else if((element==d.brick)&&this.status!=d.death){
				this.y--;
				Map.Maps[y][x]=d.floor;
				GamePanel.booms.add(new Boom(this.x, this.y));
				Music.soundEffect(Music.m1);
				this.status=d.death;
			}else if(element==d.king&&this.status!=d.death){
				this.y--;
				Map.Maps[y][x]=d.floor;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("您已亲自干死老王！！！");
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}
			else{
				this.y--;
				Music.soundEffect(Music.m1);
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
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}else if(element==d.king&&this.status!=d.death){
				this.x--;
				Map.Maps[y][x]=d.floor;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("您已亲自干死老王！！！");
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}
			else{
				this.x--;
				Music.soundEffect(Music.m1);
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
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("您已亲自干死老王！！！");
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}else if(element==d.brick&&this.status!=d.death){
				this.y++;
				Map.Maps[y][x]=d.floor;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
			else{
				this.y++;
				Music.soundEffect(Music.m1);
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
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
				System.out.println("您已亲自干死老王！！！");
				System.out.println("YOU ARE LOSER!!!");
				GamePanel.players.isRun=2;
			}else if((element==d.brick)&&this.status!=d.death){
				this.x++;
				Map.Maps[y][x]=d.floor;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
			else{
				this.x++;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				this.status=d.death;
			}
		}
		if(this.status==d.death){
			Music.soundEffect(Music.m1);
			GamePanel.bulletss.remove(this);
			GamePanel.enemyBullet.remove(this);
		}
	}
	/**玩家子弹与敌方坦克、敌方子弹的碰撞检测*/
	public void collision(){
		for(int i=0;i<GamePanel.tanks.size();i++){
			Tank tank=GamePanel.tanks.get(i);
			if((tank.y==y&&tank.x==x)&&this.status!=d.death){
				tank.isDrawTank=false;
				this.status=d.death;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				GamePanel.tanks.remove(i);
				num++;
				System.out.println("打死"+num+"个敌军坦克");
				GamePanel.bulletss.remove(this);
			}
		}for(int j=0;j<GamePanel.enemyBullet.size();j++){
			EnemyBullet enemyBullets=GamePanel.enemyBullet.get(j);
			if((enemyBullets.y==y&&enemyBullets.x==x)&&this.status!=d.death){
				enemyBullets.status=d.death;
				this.status=d.death;
				Music.soundEffect(Music.m1);
				GamePanel.booms.add(new Boom(this.x, this.y));
				GamePanel.enemyBullet.remove(j);
				GamePanel.bulletss.remove(this);
			}
		}
	}
	/**玩家子弹碰撞移动线程开启关闭*/
	public void run() {
		while(true){
			this.move();
			this.collision();
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
