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
  public static final String Robo_SPRITE = "dorgivalOHomi.png";
  public static final String STATUS_BAR = "health_power_bar.png";
  public static final String COMPLETED_IMG = "completed_sprite.png";
  public static final String MENU_FINAL = "death_screen.png";
//A classe define várias constantes de nomes de arquivos de imagem, como PLAYER_ATLAS, LEVEL_ATLAS, MENU_BUTTONS, etc. 
  //Esses nomes são usados para carregar as imagens correspondentes.
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
  }//Este método recebe o nome de um arquivo de imagem como parâmetro e carrega essa imagem.
 // Ele tenta abrir o arquivo de imagem usando ImageIO.read() a partir de um InputStream obtido da classe atual (CarregarSave.class).
 // Qualquer exceção durante o processo de carregamento da imagem é tratada no bloco catch, onde uma mensagem de erro é impressa.
 // A imagem carregada é retornada.

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
//Este método é usado para carregar todos os níveis do jogo. Ele obtém uma URL para a pasta de níveis e cria um array de arquivos representando os níveis.
 //Em seguida, ele organiza esses arquivos em ordem crescente com base nos números dos níveis (nomes de arquivos como "1.png", "2.png", etc.).
 //Para cada arquivo de nível, ele lê a imagem usando ImageIO.read() e armazena no array imgs.
 // Finalmente, ele retorna o array de imagens de nível carregadas.

}