package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Jogo;
import utilz.LoadSave;

public class LevelManager {

	private Jogo jogo;
	private BufferedImage[] levelSprite;
	private Level levelOne;

	public LevelManager(Jogo jogo) {
		this.jogo = jogo;
		importOutsideSprites();
		levelOne = new Level(LoadSave.GetLevelData());
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g, int xLvloffset) {
		for (int j = 0; j < Jogo.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Jogo.TILES_SIZE * i - xLvloffset, Jogo.TILES_SIZE * j, Jogo.TILES_SIZE, Jogo.TILES_SIZE, null);
			}
	}

	public void update() {

	}
	public Level getCurrentLevel() {
		return levelOne;
	}

}