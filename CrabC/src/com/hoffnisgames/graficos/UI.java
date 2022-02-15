package com.hoffnisgames.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hoffnisgames.entities.Player;
import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.World;


public class UI {
	
	public static BufferedImage VIDA = Game.spritesheet.getSprite(0, 0, 8, 8);
	public static int seconds = 0;
	public static int minutes =0;
	public static int frames = 0;
	
	public void tick() {
		frames++;
		if(frames == 60) {
			frames =0;
			seconds++;
			if(seconds == 60) {
				seconds = 0;
				minutes++;
				if(UI.minutes % 1 == 0) {
					World.CICLO ++;
					if(World.CICLO > World.noite) {
						World.CICLO = 0;
					}
				}
			}
		}
		
		
	}

	
	public void render(Graphics g) {
	
		
		String format = "";
		
		if (minutes < 10) {
			format += "0"+minutes+":";
		} else {
		format+=minutes+":";	
		}
		if (seconds < 10) {
			format += "0"+seconds+"";
		} else {
		format+=seconds+"";	
		}
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD,25));
		g.drawString(format, 522, 30);
		
		g.setColor(Color.black);
		g.setFont(new Font("verdana",Font.BOLD,35));
		g.drawString("Score: "+ (int)Game.score, 2, 30);
		
	
		
	
		
	
}
}