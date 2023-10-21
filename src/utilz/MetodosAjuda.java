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
 //Este método verifica se um objeto pode se mover para uma determinada posição no mapa.
//Ele verifica se os quatro cantos do objeto não colidem com blocos sólidos no mapa.
//A ideia é garantir que o objeto possa se mover sem colidir com obstáculos.

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
  // Este método verifica se uma determinada posição (representada por coordenadas x e y) no mapa é sólida.
  //Ele verifica se a posição está fora dos limites do mapa,
  //se não está colidindo com uma parede ou bloco sólido no mapa e retorna true se for uma posição sólida.

  public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
    int value = lvlData[yTile][xTile];

    if (value >= 48 || value < 0 || value != 11)
      return true;
    return false;
  }//IsTileSolid: Este método verifica se um bloco (tile) específico no mapa é sólido. 
  //Ele consulta o valor do bloco no mapa e verifica se ele é considerado sólido (valor maior ou igual a 48 ou diferente de 11).

  public static float GetEntidadesXPosNextToWall(Rectangle2D.Float hitbox, float velocidadeX) {
    int currentTile = (int) (hitbox.x / Jogo.TILES_SIZE);
    if (velocidadeX > 0) {
      int tileXPos = currentTile * Jogo.TILES_SIZE;
      int xOffset = (int) (Jogo.TILES_SIZE - hitbox.width);
      return tileXPos + xOffset - 1;
    } else
      return currentTile * Jogo.TILES_SIZE;
  }
//Este método retorna a próxima posição x de uma entidade ao lado de uma parede. 
 //É usado para garantir que a entidade não colida com a parede ao mover-se horizontalmente.
  public static float RetorneEntidadesYEntreTelhadoChao(Rectangle2D.Float hitbox, float VelocidadeNoAr) {
    int currentTile = (int) (hitbox.y / Jogo.TILES_SIZE);
    if (VelocidadeNoAr > 0) {
      int tileYPos = currentTile * Jogo.TILES_SIZE;
      int yOffset = (int) (Jogo.TILES_SIZE - hitbox.height);
      return tileYPos + yOffset - 1;
    } else
      return currentTile * Jogo.TILES_SIZE;

  }
// Este método retorna a próxima posição y de uma entidade entre o teto e o chão. 
 //Ele é usado para garantir que a entidade não colida com o teto ou o chão ao mover-se verticalmente.
  public static boolean EntidadesNoChao(Rectangle2D.Float hitbox, int[][] lvlData) {
    if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
      if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
        return false;
    return true;
  }
// Verifica se há algum bloco sólido logo abaixo da entidade, indicando que a entidade está no chão.
  public static boolean SeChao(Rectangle2D.Float hitbox, float velocidadeX, int[][] lvlData) {
    if (velocidadeX > 0)
      return IsSolid(hitbox.x + hitbox.width + velocidadeX, hitbox.y + hitbox.height + 1, lvlData);
    else
      return IsSolid(hitbox.x + velocidadeX, hitbox.y + hitbox.height + 1, lvlData);
  }
// Verifica se há um chão sólido na direção em que a entidade está se movendo horizontalmente.
  public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
    for (int i = 0; i < xEnd - xStart; i++) {
      if (IsTileSolid(xStart + i, y, lvlData))
        return false;
      if (!IsTileSolid(xStart + i, y + 1, lvlData))
        return false;
    }
    return true;
  }
//Este método verifica se todos os blocos entre duas posições x no mesmo nível de y são transitáveis (não sólidos).
  //É usado para verificar se uma entidade pode se mover horizontalmente entre duas posições no mapa.
  public static boolean visaoLivre(int[][] lvlData, Rectangle2D.Float primeiroHitbox, Rectangle2D.Float secondHitbox, int yTile) {
    int primeiroXTile = (int) (primeiroHitbox.x / Jogo.TILES_SIZE);
    int secondXTile = (int) (secondHitbox.x / Jogo.TILES_SIZE);

    if (primeiroXTile > secondXTile)
      return IsAllTilesWalkable(secondXTile, primeiroXTile, yTile, lvlData);
    else
      return IsAllTilesWalkable(primeiroXTile, secondXTile, yTile, lvlData);
  }
//Este método verifica se não há obstruções entre duas entidades (indicadas por seus retângulos de colisão) em um determinado nível y no mapa.
  //Ele é usado para verificar se há uma linha de visão livre entre duas entidades.
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
//Este método cria uma matriz bidimensional que representa os dados do nível (mapa) a partir de uma imagem.
  //Ele examina cada pixel na imagem e converte a cor em um valor numérico, que é armazenado na matriz como um bloco do mapa.
  //O valor é obtido a partir do componente vermelho da cor.
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
//Este método procura na imagem do mapa por pixels verdes que representam a localização dos inimigos Robo
  //e cria uma lista de objetos Robo com base nessas posições.
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
// Este método procura na imagem do mapa por um pixel verde (valor 100) que representa a posição inicial do jogador
  //e retorna um objeto Point com as coordenadas dessa posição.
}


