package com.hoffnisgames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.hoffnisgames.entities.Enemy1;
import com.hoffnisgames.entities.Entity;
import com.hoffnisgames.entities.Explo;
import com.hoffnisgames.entities.Player;

import com.hoffnisgames.graficos.Spritesheet;
import com.hoffnisgames.graficos.UI;

import com.hoffnisgames.world.World;


public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 250;
	public static final int HEIGHT = 250;
	public static final int SCALE = 4;
	private int x = 0;
	private boolean showGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	
	public static Spritesheet spritesheet;
	//public static Player player;
	public static Random rand;
	public UI ui;
	public static World world;
	public static double score = 0;
	public BufferedImage GAME_B;
	public static List<EnemySpaw> enemyspaw;
	public Enemy1 enemy;
	public static Rectangle buraco;
	public static int mx, my;
	public static boolean press = false;
	public static List<Explo> explo;


	
	

	

	
public Game() {
	

	addKeyListener(this);
	addMouseListener(this);
	this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	initFrame();
	
	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	spritesheet = new Spritesheet("/spritesheet.png");
	entities = new ArrayList<Entity>();
	//player = new Player(Game.WIDTH/2, Game.HEIGHT - 17, 16, 16, 1, spritesheet.getSprite(32, 0, 16, 16));
	//entities.add(player);
	//world = new World();


	ui = new UI();
	enemyspaw = new ArrayList<>();
	explo = new ArrayList<>();
	enemy = new Enemy1();
	buraco = new Rectangle(WIDTH/2 -20, HEIGHT/2 - 20, 20, 20);
	
	try {
		GAME_B = ImageIO.read(getClass().getResource("/backgr.png"));
	} catch (IOException e) {

		e.printStackTrace();
	}


	



	

}

public void initFrame() {
	
	frame = new JFrame("Hoffnis");
	frame.add(this);
	frame.setResizable(false);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

public synchronized void start() {
	thread = new Thread(this);
	isRunning = true;
	thread.start();
}

public synchronized void stop() {
	isRunning = false;
	try {
		thread.join();
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	
	
}
	
public static void main(String args[]) {
	Game game = new Game();
	game.start();
	
	
}

public void tick() {
	
	enemy.tick();
	

	for(int i = 0; i < enemyspaw.size(); i++) {
		enemyspaw.get(i).tick();
	}
	for (int i = 0; i < explo.size(); i++) {
		explo.get(i).tick();
	}
	



	
	
	ui.tick();
	

	
	

}



public void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if(bs == null) {
		this.createBufferStrategy(3);
		return;
	}
	
	Graphics g = image.getGraphics();
	g.setColor(new Color(3,170,250));
	g.fillRect(0, 0, WIDTH, HEIGHT);
	g.setColor(Color.BLACK);
	g.drawImage(GAME_B,0,0,null);
	g.fillOval(WIDTH/2 -20, HEIGHT/2 - 20, 40, 40);
	for(int i = 0; i < enemyspaw.size(); i++) {
		enemyspaw.get(i).render(g);
	}
	for (int i = 0; i < explo.size(); i++) {
		explo.get(i).render(g);
	}

	
	//Graphics2D g2= (Graphics2D) g;

	
	

	Collections.sort(entities, Entity.nodeSorter);
	for(int i = 0; i < entities.size(); i++) {
		Entity e = entities.get(i);
		e.render(g);
	}
	
	
	
	g.dispose();
	g = bs.getDrawGraphics();
	g.drawImage(image,0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
	ui.render(g);
	bs.show();
}
	public void run() {
		long LastTime = System.nanoTime();
		double amountOfTicks= 60.0;
		double ns = 1000000000 / amountOfTicks;
		int frames = 0;
		double timer = System.currentTimeMillis();
		double delta= 0;
		requestFocus();
	
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - LastTime) / ns;
			LastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
		}
				
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			

			
		
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			

		
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {

		}
		
		
	}

	
	public void keyReleased(KeyEvent e) {
		
if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			

	
		
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			

		
		}
		
	}


	public void keyTyped(KeyEvent e) {
	
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton() == MouseEvent.BUTTON3) {
		
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
			press = true;
			mx = e.getX()/4;
			my = e.getY()/4;
	
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
