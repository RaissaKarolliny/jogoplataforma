package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entidades.Dorgival;
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
  private ArrayList<Dorgival> dorgivais;
  private int maxTilesOffset;
  private int maxLvlOffsetX;
  private Point jogadorSpawn;
  /*img: Representa uma imagem que descreve o nível.
	lvlData: Um array bidimensional de inteiros que contém dados do nível.
	robos: Uma lista de objetos da classe Robo, que representam inimigos no nível.
	lvlTilesWide: Armazena a largura do nível em termos de tiles.
	dorgivais: Uma lista de objetos da classe Dorgival.
	maxTilesOffset: O deslocamento máximo em termos de tiles permitido no nível.
	maxLvlOffsetX: O deslocamento máximo em pixels permitido no eixo X.
	jogadorSpawn: Representa a posição de spawn do jogador, definida como um objeto Point.
  */
  
  
  public Niveis(BufferedImage img) {//O construtor recebe uma imagem img como parâmetro. 
    this.img = img;					//Essa imagem é usada para criar os dados do nível e configurar inimigos e informações de spawn do jogador.
    createLevelData();
    createEnemies();
    calcLvlOffsets();
    calcJogadorSpawn();
  }

  private void calcJogadorSpawn() {
    jogadorSpawn = GetJogadorSpawn(img);
  }
//Um método privado que calcula a posição de spawn do jogador usando funções utilitárias como GetJogadorSpawn(img).
  private void calcLvlOffsets() {
    lvlTilesWide = img.getWidth();
    maxTilesOffset = lvlTilesWide - Jogo.TILES_IN_LARGURA;
    maxLvlOffsetX = Jogo.TILES_SIZE * maxTilesOffset;
  }
//Um método privado que calcula a largura do nível em termos de tiles (lvlTilesWide), o deslocamento máximo em termos de tiles (maxTilesOffset)
 //e o deslocamento máximo em pixels no eixo X (maxLvlOffsetX).
  private void createEnemies() {
	  robos = GetRobs(img);
  }
 //Um método privado que cria inimigos (robôs) no nível usando funções utilitárias como GetRobs(img).


  private void createLevelData() {
    lvlData = GetLevelData(img);
  }
//Um método privado que cria dados do nível usando funções utilitárias como GetLevelData(img).
  public int getSpriteIndex(int x, int y) {
    return lvlData[y][x];
  }
//Um método que retorna o índice de sprite para uma determinada posição (x, y) no nível. Isso é usado para obter informações sobre os tiles do nível.
  public int[][] getLevelData() {
    return lvlData;
  }
//Um método que retorna o array bidimensional de dados do nível.
  public int getLvlOffset() {
    return maxLvlOffsetX;
  }
//Um método que retorna o deslocamento máximo em pixels no eixo X.
  public ArrayList<Robo> getRobos() {
    return robos;
  }
  //Um método que retorna a lista de robôs no nível.
  public ArrayList<Dorgival> getDogivals() {
	    return dorgivais;
	  }//Um método que retorna a lista de objetos da classe Dorgival no nível. -->ainda na fase de teste

  public Point getJogadorSpawn() {
    return jogadorSpawn;
  }
//Um método que retorna a posição de spawn do jogador.
}
