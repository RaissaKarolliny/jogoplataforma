package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constantes.ConstantesDosInimigos.INIMIGO_UM;
import javax.imageio.ImageIO;

import entities.Inimigo1;
import main.Jogo;

public class LoadSave {

	public static final String PLAYER_ATLAS = "Sprite_Pedro.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	//public static final String LEVEL_ONE_DATA = "level_one_data.png";
	public static final String MENU_BUTTONS = "botoes.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_INICIAL = "background_menu.png";
	public static final String PLAYING_BG_IMG = "backGame.png";
	public static final String CABECA_PC = "spritemonkey.png";


	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	
	public static ArrayList<Inimigo1> GetInimigos1(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Inimigo1> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == INIMIGO_UM)
					list.add(new Inimigo1(i* Jogo.TILES_SIZE,j*Jogo.TILES_SIZE));
			} 
		return list;
	}
	

	public static int[][] GetLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}