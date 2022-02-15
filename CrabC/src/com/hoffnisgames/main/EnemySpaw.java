package com.hoffnisgames.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.hoffnisgames.entities.Enemy1;
import com.hoffnisgames.entities.Entity;
import com.hoffnisgames.entities.Explo;
import com.hoffnisgames.graficos.UI;

public class EnemySpaw {
	
	public double x,y,dx,dy;
	public static double speed =1;
	public static BufferedImage[] crabs;
	public static BufferedImage[] exp;
	public int max = 2;
	public int frame = 0;
	public int framemax = 10;
	public int cur = 0;

	
	public int targ = 60*1;
	public int curtime = 0;
	public int enem;
	
	public EnemySpaw(int x, int y) {
		this.x = x;
		this.y = y;
		double radius = Math.atan2((Game.HEIGHT/2 -13) - y, (Game.WIDTH/2 -15) -x);
		this.dx = Math.cos(radius);
		this.dy = Math.sin(radius);
		crabs = new BufferedImage[2];
		crabs[0]= Game.spritesheet.getSprite(0, 16, 15, 13);
		crabs[1]= Game.spritesheet.getSprite(16, 16, 15, 13);
		crabs = new BufferedImage[2];
		crabs[0]= Game.spritesheet.getSprite(0, 16, 15, 13);
		crabs[1]= Game.spritesheet.getSprite(16, 16, 15, 13);
	}

	
	
	
	public void tick() {
		x+=dx*speed;
		y+=dy*speed;
		if(new Rectangle((int)x,(int)y,7,7).intersects(Game.buraco)) {
			Game.enemyspaw.remove(this);
			Game.score--;
		}
		
		frame++;
		if(frame == framemax) {
			cur++;
			frame = 0;
			if(cur == max) {
				cur = 0;
			}
			
		}
		colisao();
	}
	
	public void colisao() {
		if(Game.press) {
			Game.press = false;
		if(Game.mx >= x && Game.mx <= x +15) {
			if(Game.my >= y && Game.my <= y + 13) {
				Game.enemyspaw.remove(this);
				Game.score++;
				Game.explo.add(new Explo((int)x,(int)y));
				return;
			}
		}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(crabs[cur], (int)x,(int)y, 15,13,null);
	}
	
}
