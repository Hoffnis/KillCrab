package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hoffnisgames.main.EnemySpaw;
import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.FloorTile;
import com.hoffnisgames.world.Tile;
import com.hoffnisgames.world.WallTile;
import com.hoffnisgames.world.World;

public class Enemy1{
	
	public int curtime = 0; 
	public int target = 60*3;
	public static Random random;
	
	public Enemy1() {
		random = new Random();
	}
	



	public void tick() {
		curtime++;
		if(curtime >= target) {
			curtime = random.nextInt(200);
			EnemySpaw.speed = random.nextInt(4-1) +1;
			
			if(random.nextInt(100) <25 ) {
			Game.enemyspaw.add(new EnemySpaw(random.nextInt(Game.WIDTH ),0));
		} else if (random.nextInt(100) <50 ) {
			Game.enemyspaw.add(new EnemySpaw(random.nextInt(Game.WIDTH ),Game.HEIGHT));
		}
		else if (random.nextInt(100) <75 ) {
				Game.enemyspaw.add(new EnemySpaw((Game.WIDTH ),random.nextInt(Game.HEIGHT)));
			} else {
				Game.enemyspaw.add(new EnemySpaw(0,random.nextInt(Game.HEIGHT)));
			}
	

	}
	}
	
	
}
