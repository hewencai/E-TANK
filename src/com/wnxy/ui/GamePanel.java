package com.wnxy.ui;


import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;














import com.wnxy.Map.Dic;
import com.wnxy.Map.Map;
import com.wnxy.music.Music;
import com.wnxy.play.Boom;
import com.wnxy.play.Bullet;
import com.wnxy.play.EnemyBullet;
import com.wnxy.play.Player;
import com.wnxy.play.PlayerBullet;
import com.wnxy.play.Tank;

public class GamePanel extends JPanel implements Runnable{
	private BufferedImage grass2;
	private BufferedImage grass3;
	private BufferedImage water;
	private BufferedImage ice;
	private BufferedImage king;
	private BufferedImage biankuang;
	private BufferedImage stell;
	private BufferedImage brick;
	private BufferedImage floor;
	private BufferedImage GameOver;
	private BufferedImage player;
	private BufferedImage star;
	private BufferedImage tank;
	private BufferedImage bullet;
	private BufferedImage boom;
	private BufferedImage Winner;
	private int position=0;
	public static int j=0;
	public boolean r=false;
	public int sta=0;
	/**number控制敌方坦克的数量*/
	public static int number=5;
	public static PlayerBullet bullets;
	public EnemyBullet eb=new EnemyBullet();
	public static EnemyBullet enemyBullets;
	Dic d=new Dic();
	Bullet bl=new Bullet();
	GameFarms gf=new GameFarms();
	public Tank t=new Tank();
	public Boom boomss=new Boom();
	/**敌方坦克集合*/
	public static Vector<Tank> tanks=new Vector<Tank>();
	/**玩家子弹集合*/
	public static Vector<PlayerBullet> bulletss=new Vector<PlayerBullet>();
	/**敌方子弹集合*/
	public static Vector<EnemyBullet> enemyBullet=new Vector<EnemyBullet>();
	/**爆炸效果集合*/
	public static Vector<Boom> booms=new Vector<Boom>();
	/**玩家初始化*/
	public static Player players=new Player(16, 25, 3, 0);
	public Tank tankss;
	/**加载图片，添加监听事件，完成子弹发射*/
	public GamePanel(){
		try {
			// 加载图片文件
			grass2 = ImageIO.read(new File("img/grass2.jpg"));
			grass3 = ImageIO.read(new File("img/grasss3.jpg"));
			water = ImageIO.read(new File("img/water.png"));
			ice = ImageIO.read(new File("img/ice.png"));
			king = ImageIO.read(new File("img/king.bmp"));
			biankuang = ImageIO.read(new File("img/biankuang.png"));
			stell = ImageIO.read(new File("img/stell.png"));
			brick = ImageIO.read(new File("img/brick.png"));
			floor = ImageIO.read(new File("img/floor.png"));
			player = ImageIO.read(new File("img/renwu.png"));
			star = ImageIO.read(new File("img/shanshuo.png"));
			tank = ImageIO.read(new File("img/tanks.png"));
			bullet = ImageIO.read(new File("img/bullet.jpg"));
			boom = ImageIO.read(new File("img/boom.png"));
			GameOver = ImageIO.read(new File("img/gameover.jpg"));
			Winner = ImageIO.read(new File("img/winner.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**添加监听事件*/
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				players.move(e);
				/**玩家子弹死亡时按键才能添加子弹*/
				if(bulletss.size()<1){
					if(e.getKeyCode()==KeyEvent.VK_J&&players.status!=d.death){
						Music.soundEffect(Music.m2);
						bullets=new PlayerBullet(players.x, players.y,players.dirction,players.index);
						GamePanel.bulletss.add(bullets);
						bullets.changeDirect();
						r=true;
					}
				}
				if(players.dd){
					players.step();
				}
				players.dd=false;
			}
		});
		Thread th=new Thread(this);
		th.start();
	}
	/**创建敌方坦克*/
	public void creatTank(){
		if(tanks.size()<number){
			Tank tankss=null;
			if(position>2){
				position=0;
			}
			int len=1+17*position;
			tankss=new Tank(len, 1, 0, 0);
			tanks.add(tankss);
			position++;
		}
	}
	/**调用绘制敌方子弹及钻草的方法*/
	public void drawEnemyBullet(Graphics g){
		for(int i=0;i<enemyBullet.size();i++){
			enemyBullet.get(i).drawBullet(g, this, bullet);
			enemyBullet.get(i).undergrass(g, this, grass2);
		}
	}
	/**调用绘制敌方坦克钻草的方法*/
	public void drawTank(Graphics g){
		for(int i=0;i<tanks.size();i++){
			tanks.get(i).isDraw(g, this, tank, star);
			tanks.get(i).undergrass(g, this, grass2);
		}
	}
	/**调用绘制玩家子弹及钻草的方法*/
	public void drawBullet(Graphics g){
		if(r==true&&bullets.status!=d.death){
			for(int i=0;i<bulletss.size();i++){
				bulletss.get(i).drawBullet(g, this, bullet);
				bulletss.get(i).undergrass(g, this, grass2);
			}
		}
	}
	/**调用绘制爆炸效果的方法*/
	public void drawBooms(Graphics g){
		for(int i=0;i<booms.size();i++){

			booms.get(i).drawBoom(g, this, boom);
		}
	}
	/**调用绘制玩家及钻草的方法*/
	public void drawPlayer(Graphics g){
		if(players.status!=d.death){
			players.isDraw(g, this, player,star);
			players.undergrass(g, this, grass2);
		}
	}
	/**绘制gameover结束画面*/
	public void drawGameOver(Graphics g,JPanel panel,BufferedImage img){
		if(players.isRun==2){
			g.drawImage(img, 0, 0, 37*gf.getSizes(),27*gf.getSizes(), 0,0,img.getWidth(), img.getHeight(), panel);
		}
	}
	/**绘制winner胜利画面*/
	public void drawWinner(Graphics g,JPanel panel,BufferedImage img){
		if(players.isRun==1){
			g.drawImage(img, 0, 0, 37*gf.getSizes(),27*gf.getSizes(), 0,0,img.getWidth(), img.getHeight(), panel);
		}
	}
	/**画板*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.drawMap(g);
		this.drawPlayer(g);
		this.drawTank(g);
		this.drawBullet(g);
		this.drawBooms(g);
		this.drawEnemyBullet(g);
		this.drawGameOver(g, this, GameOver);
		this.drawWinner(g, this, Winner);
	}
	/**绘制地图*/
	private void drawMap(Graphics g) {
		int element;
		GameFarms gf=new GameFarms();
		for(int i=0;i<Map.Maps[0].length;i++){
			for(int j=0;j<Map.Maps.length;j++){
				element=Map.Maps[j][i];
				switch (element) {
				case 0:
					g.drawImage(biankuang,gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, biankuang.getWidth(), biankuang.getHeight(), this);
					break;
				case 1:
					g.drawImage(king, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, king.getWidth(), king.getHeight(), this);
					break;
				case 2:
					g.drawImage(brick, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, brick.getWidth(), brick.getHeight(), this);
					break;
				case 3:
					g.drawImage(stell, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, stell.getWidth(), stell.getHeight(), this);
					break;
				case 4:
					g.drawImage(ice, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, ice.getWidth(), ice.getHeight(), this);
					break;
				case 5:
					g.drawImage(water, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, water.getWidth(), water.getHeight(), this);
					break;
				case 6:
					g.drawImage(grass3, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, grass3.getWidth(), grass3.getHeight(), this);
					break;
				case 7:
					g.drawImage(floor, gf.getSizes()*i, gf.getSizes()*j, gf.getSizes()*i+gf.getSizes(), gf.getSizes()*j+gf.getSizes(), 
							0, 0, floor.getWidth(), floor.getHeight(), this);
					break;
				default:
					break;
				}
			}
		}
	}

	public void run() {
		while(true) {
			this.repaint();
			creatTank();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


