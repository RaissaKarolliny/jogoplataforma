package utilz;

import java.awt.geom.Rectangle2D;

import main.Jogo;

public class MetodosAjuda {
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsSolid(x, y, lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		int maxWidth = lvlData[0].length * Jogo.TILES_SIZE;
		if (x < 0 || x >= maxWidth)
			return true;
		if (y < 0 || y >= Jogo.GAME_HEIGHT)
			return true;
		float xIndex = x / Jogo.TILES_SIZE;
		float yIndex = y / Jogo.TILES_SIZE;
		
		int value = lvlData[(int) yIndex][(int) xIndex];

		if(value >=48 || value < 0 || value !=11) 
			return true;
		return false;
	}
	//gravidade//
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currenTile = (int)(hitbox.x/Jogo.TILES_SIZE);
		if(xSpeed > 0) {
			//direita 
			int tileXPos = currenTile * Jogo.TILES_SIZE;
			int xOffset= (int)(Jogo.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset -1;
		}else 
			//esquerda
			return currenTile * Jogo.TILES_SIZE;
	}
	//metodo para ficarmos no chão ou conseguirmos pular
	public static float GetEntityYPosUnderRoofOrAboverFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currenTile = (int)(hitbox.y/Jogo.TILES_SIZE);
		if(airSpeed > 0) {
			//caindo - tocando o chão
			int tileYPos = currenTile * Jogo.TILES_SIZE;
			int yOffset= (int)(Jogo.TILES_SIZE - hitbox.height);
			return tileYPos - yOffset +9;
			}else 
				//pulando
				return currenTile * Jogo.TILES_SIZE;
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		//check the pixel below bottomleft and bottonright
		if(!IsSolid(hitbox.x, hitbox.y + hitbox.height+6, lvlData))
			if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height+6, lvlData))
				  return false;
		return true;
	}
	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
		return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height -4, lvlData);
		
		}
	}

  
		
	
	

