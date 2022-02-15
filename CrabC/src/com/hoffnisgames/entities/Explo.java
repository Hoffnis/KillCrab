package com.hoffnisgames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;

public class Explo {
	public int x,y;
	public static BufferedImage[] exp;
	public int max = 2;
	public int frame = 0;
	public int framemax = 10;
	public int cur = 0;

	

	
	public Explo(int x, int y) {
		this.x = x;
		this.y = y;
	
	
		exp = new BufferedImage[2];
		exp[0]= Game.spritesheet.getSprite(16, 32, 16, 16);
		exp[1]= Game.spritesheet.getSprite(0, 32, 16, 16);
	}

	
	
	
	public void tick() {
	
		
		frame++;
		if(frame == framemax) {
			cur++;
			frame = 0;
			if(cur == max) {
				cur = 0;
				Game.explo.remove(this);
			}
			
		}
	
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(exp[cur], (int)x,(int)y, 16,16,null);
	}
}
