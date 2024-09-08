package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	

	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler KeyH) {
		

		solidArea = new Rectangle();
     	solidArea.x = 16;
		solidArea.y = 32;
		solidArea.width = 64;
		solidArea.height = 64;
		
		this.gp = gp;
		this.keyH = KeyH;
		
		screenX = gp.screenWidth/2 -(gp.tileSize/2);
		screenY =gp.screenHeight/2 -(gp.tileSize/2);
		
		setDefaultValues();
		getplayerImage();
	}
	
	public void getplayerImage() {
		
		try {
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full2.png"));	
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full4.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full5.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full6.png"));
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_2.png"));	
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_4.png"));
			up5 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_5.png"));
			up6 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_6.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_right_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_right_3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_right_4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_right_5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_right_6.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_left_2l.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_left_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_left_4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_left_5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/Unarmed_Walk_full_left_6.png"));
			
		}catch(IOException e) {
			
			
			
			e.printStackTrace();
		}
	}
	
	public void setDefaultValues() {
		WorldX = gp.tileSize * 24;
		WorldY = gp.tileSize * 27;
		speed = 2;
		speed = gp.worldWidth/1200;
		direction = "right";
	}
	
	public void update() {   //allows the character sprite to only change when keys are pressed
		  if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
	|| keyH.rightPressed == true) {
			  
			  if(keyH.upPressed == true) {
					 direction = "up";
					
					 
				 }
				 else if(keyH.downPressed == true) {
					 direction = "down";
					
					 	 
				 }
				 else if(keyH.leftPressed == true) {
					 direction = "left";
				
					 
				 }
				 else if(keyH.rightPressed == true) {
					 direction = "right";
					
				 }
			  //check tile collision
			 detectorOn = false;
			 gp.cDetector.detectTile(this);
		  //movement keys
			  
			 //if collision is false player can move
			 if(detectorOn == false) {
				 
				 switch(direction) {
				 case "up":
					 WorldY -= speed;
					 break;
					 
				 case "down":
					 WorldY += speed;
					 break;
					 
				 case "right":
					 WorldX += speed;
				 break;
				 
				 case "left":
					 WorldX -= speed;
				 break;
				 }
			 }
			 
			 
			  spriteCounter++; //funition for player movement animation 
				 if (spriteCounter > 16) {
					    spriteNum++;
					    if (spriteNum > 6) {
					        spriteNum = 1;
					    }
					    spriteCounter = 0;
					    }
					    
					 
		  }	 
	 }
		
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			} 
			if (spriteNum == 2) {
				image = up2;
			} if (spriteNum == 3) {
				image = up3;
			} if (spriteNum == 4) {
				image = up4;
			} if (spriteNum == 5) {
				image = up5;
			} if (spriteNum == 6) {
				image = up6;
			} 
			
			break;
		case "down":
			if (spriteNum == 1) {
			image = down1;}
			if (spriteNum == 2) {
				image = down2;
			} if (spriteNum == 3) {
				image = down3;
			} if (spriteNum == 4) {
				image = down4;
			} if (spriteNum == 5) {
				image = down5;
			} if (spriteNum == 6) {
				image = down6;
			} 
			break;
			
		case "left": if(spriteNum == 1){
			image = left1;}
		if (spriteNum == 2) {
			image = left2;
		} if (spriteNum == 3) {
			image = left3;
		} if (spriteNum == 4) {
			image = left4;
		} if (spriteNum == 5) {
			image = left5;
		} if (spriteNum == 6) {
			image = left6;
		} 
			break;
		case "right": if(spriteNum == 1) {
			image = right1;}
		if (spriteNum == 2) {
			image = right2;
		} if (spriteNum == 3) {
			image = right3;
		} if (spriteNum == 4) {
			image = right4;
		} if (spriteNum == 5) {
			image = right5;
		} if (spriteNum == 6) {
			image = right6;
		} 
			break;
		     
		}
				
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}

}
