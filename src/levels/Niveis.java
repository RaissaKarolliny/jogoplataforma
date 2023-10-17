package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entidades.Robo;
import main.Jogo;
import static utilz.MetodosAjuda.GetLevelData;
import static utilz.MetodosAjuda.GetRobs;
import static utilz.MetodosAjuda.GetJogadorSpawn;

public class Niveis {

  private BufferedImage img;
  private int[][] lvlData;
  private ArrayList<Robo> robos;
  private int lvlTilesWide;
  private int maxTilesOffset;
  private int maxLvlOffsetX;
  private Point jogadorSpawn;

  public Niveis(BufferedImage img) {
    this.img = img;
    createLevelData();
    createEnemies();
    calcLvlOffsets();
    calcJogadorSpawn();
  }

  private void calcJogadorSpawn() {
    jogadorSpawn = GetJogadorSpawn(img);
  }

  private void calcLvlOffsets() {
    lvlTilesWide = img.getWidth();
    maxTilesOffset = lvlTilesWide - Jogo.TILES_IN_LARGURA;
    maxLvlOffsetX = Jogo.TILES_SIZE * maxTilesOffset;
  }

  private void createEnemies() {
	  robos = GetRobs(img);
  }

  private void createLevelData() {
    lvlData = GetLevelData(img);
  }

  public int getSpriteIndex(int x, int y) {
    return lvlData[y][x];
  }

  public int[][] getLevelData() {
    return lvlData;
  }

  public int getLvlOffset() {
    return maxLvlOffsetX;
  }

  public ArrayList<Robo> getRobos() {
    return robos;
  }

  public Point getJogadorSpawn() {
    return jogadorSpawn;
  }

}
