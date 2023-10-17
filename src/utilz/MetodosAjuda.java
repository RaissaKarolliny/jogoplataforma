package utilz;

import static utilz.Constantes.InimigoConstantes.Robo;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entidades.Robo;
import main.Jogo;

public class MetodosAjuda {

  public static boolean PodeMover(float x, float y, float LARGURA, float ALTURA, int[][] lvlData) {
    if (!IsSolid(x, y, lvlData))
      if (!IsSolid(x + LARGURA, y + ALTURA, lvlData))
        if (!IsSolid(x + LARGURA, y, lvlData))
          if (!IsSolid(x, y + ALTURA, lvlData))
            return true;
    return false;
  }

  private static boolean IsSolid(float x, float y, int[][] lvlData) {
    int maxLARGURA = lvlData[0].length * Jogo.TILES_SIZE;
    if (x < 0 || x >= maxLARGURA)
      return true;
    if (y < 0 || y >= Jogo.Jogo_ALTURA)
      return true;
    float xIndex = x / Jogo.TILES_SIZE;
    float yIndex = y / Jogo.TILES_SIZE;

    return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
  }

  public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
    int value = lvlData[yTile][xTile];

    if (value >= 48 || value < 0 || value != 11)
      return true;
    return false;
  }

  public static float GetEntidadesXPosNextToWall(Rectangle2D.Float hitbox, float velocidadeX) {
    int currentTile = (int) (hitbox.x / Jogo.TILES_SIZE);
    if (velocidadeX > 0) {
      int tileXPos = currentTile * Jogo.TILES_SIZE;
      int xOffset = (int) (Jogo.TILES_SIZE - hitbox.width);
      return tileXPos + xOffset - 1;
    } else
      return currentTile * Jogo.TILES_SIZE;
  }

  public static float RetorneEntidadesYEntreTelhadoChao(Rectangle2D.Float hitbox, float VelocidadeNoAr) {
    int currentTile = (int) (hitbox.y / Jogo.TILES_SIZE);
    if (VelocidadeNoAr > 0) {
      int tileYPos = currentTile * Jogo.TILES_SIZE;
      int yOffset = (int) (Jogo.TILES_SIZE - hitbox.height);
      return tileYPos + yOffset - 1;
    } else
      return currentTile * Jogo.TILES_SIZE;

  }

  public static boolean EntidadesNoChao(Rectangle2D.Float hitbox, int[][] lvlData) {
    if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
      if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
        return false;
    return true;
  }

  public static boolean SeChao(Rectangle2D.Float hitbox, float velocidadeX, int[][] lvlData) {
    if (velocidadeX > 0)
      return IsSolid(hitbox.x + hitbox.width + velocidadeX, hitbox.y + hitbox.height + 1, lvlData);
    else
      return IsSolid(hitbox.x + velocidadeX, hitbox.y + hitbox.height + 1, lvlData);
  }

  public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
    for (int i = 0; i < xEnd - xStart; i++) {
      if (IsTileSolid(xStart + i, y, lvlData))
        return false;
      if (!IsTileSolid(xStart + i, y + 1, lvlData))
        return false;
    }
    return true;
  }

  public static boolean visaoLivre(int[][] lvlData, Rectangle2D.Float primeiroHitbox, Rectangle2D.Float secondHitbox, int yTile) {
    int primeiroXTile = (int) (primeiroHitbox.x / Jogo.TILES_SIZE);
    int secondXTile = (int) (secondHitbox.x / Jogo.TILES_SIZE);

    if (primeiroXTile > secondXTile)
      return IsAllTilesWalkable(secondXTile, primeiroXTile, yTile, lvlData);
    else
      return IsAllTilesWalkable(primeiroXTile, secondXTile, yTile, lvlData);
  }

  public static int[][] GetLevelData(BufferedImage img) {
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

  public static ArrayList<Robo> GetRobs(BufferedImage img) {
    ArrayList<Robo> list = new ArrayList<>();
    for (int j = 0; j < img.getHeight(); j++)
      for (int i = 0; i < img.getWidth(); i++) {
        Color color = new Color(img.getRGB(i, j));
        int value = color.getGreen();
        if (value == Robo)
          list.add(new Robo(i * Jogo.TILES_SIZE, j * Jogo.TILES_SIZE));
      }
    return list;
  }

  public static Point GetJogadorSpawn(BufferedImage img) {
    for (int j = 0; j < img.getHeight(); j++)
      for (int i = 0; i < img.getWidth(); i++) {
        Color color = new Color(img.getRGB(i, j));
        int value = color.getGreen();
        if (value == 100)
          return new Point(i * Jogo.TILES_SIZE, j * Jogo.TILES_SIZE);
      }
    return new Point(1 * Jogo.TILES_SIZE, 1 * Jogo.TILES_SIZE);
  }

}


