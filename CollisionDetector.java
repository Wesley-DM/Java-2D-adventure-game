package main;
import entity.Entity;

public class CollisionDetector {

	GamePanel gp;
	
	public CollisionDetector(GamePanel gp) {
		this.gp = gp;
	}
	
	
		public void detectTile (Entity entity) {
			
			int entityLeftWorldX = (int) (entity.WorldX + entity. solidArea.x);
		    int entityRightWorldX = (int) (entity.WorldX + entity.solidArea.x + entity.solidArea.width);
			int entityTopWorldY = (int) (entity.WorldY + entity.solidArea.y);
			int entityBottomWorldY = (int) (entity.WorldY + entity.solidArea.y + entity.solidArea.height);
			
			int entityLeftCol = entityLeftWorldX/ gp.tileSize;
			int entityRightCol = entityRightWorldX/ gp.tileSize;
		    int entityTopRow = entityTopWorldY/ gp.tileSize;
			int entityBottomRow = entityBottomWorldY/ gp.tileSize;
			
			
			int tileNum1, tileNum2;
			 
			
			switch(entity.direction) {
			
			case "up":
				
				entityTopRow = (int) ((entityTopWorldY - entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				if(gp.tileM.tile[tileNum1].collision == true ||  gp.tileM.tile[tileNum2].collision == true) {
				entity.detectorOn = true;
				}
				break;
				
			case "down":
				entityBottomRow = (int) ((entityBottomWorldY + entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true ||  gp.tileM.tile[tileNum2].collision == true) {
				entity.detectorOn = true;
				}
				break;
				
			case "left":
				entityLeftCol = (int) ((entityLeftWorldX - entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true ||  gp.tileM.tile[tileNum2].collision == true) {
				entity.detectorOn = true;
				}
				break;
				
			case "right":
				entityRightCol = (int) ((entityRightWorldX + entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true ||  gp.tileM.tile[tileNum2].collision == true) {
				entity.detectorOn = true;
				}
			break;
				
				
			}
				
		}
	
}
