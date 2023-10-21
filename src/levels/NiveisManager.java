package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estadosjogos.Jogostate;
import main.Jogo;
import utilz.CarregarSave;

public class NiveisManager {

  private Jogo Jogo;
  private BufferedImage[] levelSprite;
  private ArrayList<Niveis> niveis;
  private int lvlIndex = 0;

  public NiveisManager(Jogo Jogo) {
    this.Jogo = Jogo;
    importOutsideSprites();
    niveis = new ArrayList<>();
    buildAllLevels();
  }

  public void loadNextLevel() {
    lvlIndex++;
    if (lvlIndex >= niveis.size()) {
      lvlIndex = 0;
      System.out.println("sem mais mapas! Jogo Completo!");
      Jogostate.state = Jogostate.MENU;
    }

    Niveis newLevel = niveis.get(lvlIndex);
    Jogo.getJogando().getGerenciadoInimigo().carregarInimigos(newLevel);
    Jogo.getJogando().getJogador().loadLvlData(newLevel.getLevelData());
    Jogo.getJogando().setMaxLvlOffset(newLevel.getLvlOffset());
  }

  private void buildAllLevels() {
    BufferedImage[] allLevels = CarregarSave.GetAllLevels();
    for (BufferedImage img : allLevels)
    	niveis.add(new Niveis(img));
  }

  private void importOutsideSprites() {
    BufferedImage img = CarregarSave.GetSpriteAtlas(CarregarSave.LEVEL_ATLAS);
    levelSprite = new BufferedImage[48];
    for (int j = 0; j < 4; j++)
      for (int i = 0; i < 12; i++) {
        int index = j * 12 + i;
        levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
      }
  }

  public void draw(Graphics g, int lvlOffset) {
    for (int j = 0; j < Jogo.TILES_IN_ALTURA; j++)
      for (int i = 0; i < niveis.get(lvlIndex).getLevelData()[0].length; i++) {
        int index = niveis.get(lvlIndex).getSpriteIndex(i, j);
        g.drawImage(levelSprite[index], Jogo.TILES_SIZE * i - lvlOffset, Jogo.TILES_SIZE * j, Jogo.TILES_SIZE, Jogo.TILES_SIZE, null);
      }
  }

  public void atualizar() {

  }

  public Niveis getCurrentLevel() {
    return niveis.get(lvlIndex);
  }

  public int getAmountOfLevels() {
    return niveis.size();
  }

}
