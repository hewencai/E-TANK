package com.wnxy.play;

import com.wnxy.ui.GamePanel;

public class EnemyBullet extends Bullet implements Runnable{
	public EnemyBullet() {
		super();
	}

	public EnemyBullet(int x, int y, int dirction, int index) {
		super(x, y, dirction, index);
		new Thread(this).start();
	}
	/**敌方子弹与玩家碰撞*/
	public void collision(){
		Player players=GamePanel.players;
			if((players.y==y&&players.x==x)&&this.status!=d.death&&players.status!=d.death){
				players.status=d.death;
				GamePanel.booms.add(new Boom(this.x, this.y));
				GamePanel.enemyBullet.remove(this);
			}
		}
	/**敌方子弹的移动和碰撞线程*/
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
