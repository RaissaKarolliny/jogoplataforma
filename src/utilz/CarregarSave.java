package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class CarregarSave {

  public static final String PLAYER_ATLAS = "Sprite_Pedro.png";
  public static final String LEVEL_ATLAS = "outside_sprites.png";
  public static final String MENU_BUTTONS = "botoes.png";
  public static final String MENU_BACKGROUND = "menu_background.png";
  public static final String PAUSE_BACKGROUND = "pause_menu.png";
  public static final String SOUND_BUTTONS = "sound_button.png";
  public static final String URM_BUTTONS = "urm_buttons.png";
  public static final String VOLUME_BUTTONS = "volume_buttons.png";
  public static final String MENU_BACKGROUND_IMG = "UFCA_alto.png";
  public static final String PLAYING_BG_IMG = "playing_bg_img.png";
  public static final String Robo_SPRITE = "pc.png";
  public static final String STATUS_BAR = "health_power_bar.png";
  public static final String COMPLETED_IMG = "completed_sprite.png";

  public static BufferedImage GetSpriteAtlas(String fileName) {
    BufferedImage img = null;
    InputStream is = CarregarSave.class.getResourceAsStream(""+fileName);
    try {
      img = ImageIO.read(is);

    } catch (IOException e) {
    	   System.out.println("PROVAVEL QUE TU ERROU O CAMINHO");
      e.printStackTrace();
      System.out.println("PROVAVEL QUE TU ERROU O CAMINHO");
    } finally {
      try {
        is.close();
      } catch (IOException e) {
    	   System.out.println("PROVAVEL QUE TU ERROU O CAMINHO");
        e.printStackTrace();
        System.out.println("PROVAVEL QUE TU ERROU O CAMINHO");

      }
    }
    return img;
  }

  public static BufferedImage[] GetAllLevels() {
    URL url = CarregarSave.class.getResource("/lvls");
    File file = null;

    try {
      file = new File(url.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    File[] files = file.listFiles();
    File[] filesSorted = new File[files.length];

    for (int i = 0; i < filesSorted.length; i++)
      for (int j = 0; j < files.length; j++) {
        if (files[j].getName().equals((i + 1) + ".png"))
          filesSorted[i] = files[j];

      }

    BufferedImage[] imgs = new BufferedImage[filesSorted.length];

    for (int i = 0; i < imgs.length; i++)
      try {
        imgs[i] = ImageIO.read(filesSorted[i]);
      } catch (IOException e) {
        e.printStackTrace();
      }

    return imgs;
  }

}