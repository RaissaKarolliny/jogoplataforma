package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estadosjogos.Jogostate;
import main.Jogo;
import utilz.CarregarSave;

public class NiveisManager {

  private Jogo Jogo;//Uma referência à instância do jogo, permitindo o acesso a outros componentes do jogo.
  private BufferedImage[] levelSprite;// Um array de imagens representando os sprites dos tiles do nível.
  private ArrayList<Niveis> niveis;//Uma lista que armazena instâncias da classe Niveis, que contêm informações sobre os diferentes níveis.
  private int lvlIndex = 0;// Um índice que rastreia o nível atual em que o jogador está.

  public NiveisManager(Jogo Jogo) {//O construtor recebe uma instância do jogo (Jogo) como parâmetro.
    this.Jogo = Jogo;//Inicializa niveis como uma lista vazia.
    importOutsideSprites();//chama importOutsideSprites para carregar os sprites dos tiles do nível.
    niveis = new ArrayList<>();
    buildAllLevels();//Chama o método buildAllLevels para criar todas as instâncias de Niveis com base nas imagens de níveis disponíveis.
  }

  public void loadNextLevel() {//Carrega o próximo nível.
    lvlIndex++;
    if (lvlIndex >= niveis.size()) {//Verifica se lvlIndex ultrapassou o número de níveis disponíveis. Se sim, redefine lvlIndex para 0 e retorna ao menu.
      lvlIndex = 0;
      System.out.println("sem mais mapas! Jogo Completo!");
      Jogostate.state = Jogostate.MENU;
    }

    Niveis newLevel = niveis.get(lvlIndex);
    Jogo.getJogando().getGerenciadoInimigo().carregarInimigos(newLevel);
    Jogo.getJogando().getJogador().loadLvlData(newLevel.getLevelData());
    Jogo.getJogando().setMaxLvlOffset(newLevel.getLvlOffset());
  }

  private void buildAllLevels() {//Carrega todas as imagens de níveis disponíveis usando CarregarSave.GetAllLevels().
	  							//Cria uma instância da classe Niveis para cada imagem do nível e adiciona à lista niveis.
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
      }//Carrega os sprites dos tiles do nível a partir de uma imagem de atlas usando CarregarSave.GetSpriteAtlas(CarregarSave.LEVEL_ATLAS).
      //Divide o atlas em sprites individuais e os armazena em levelSprite.
  }

  public void draw(Graphics g, int lvlOffset) {
    for (int j = 0; j < Jogo.TILES_IN_ALTURA; j++)
      for (int i = 0; i < niveis.get(lvlIndex).getLevelData()[0].length; i++) {
        int index = niveis.get(lvlIndex).getSpriteIndex(i, j);
        g.drawImage(levelSprite[index], Jogo.TILES_SIZE * i - lvlOffset, Jogo.TILES_SIZE * j, Jogo.TILES_SIZE, Jogo.TILES_SIZE, null);
      }
  }//Desenha os tiles do nível na tela.
  // Usa um loop aninhado para percorrer as posições (i, j) dos tiles do nível.
  // Obtém o índice do sprite correspondente a essa posição usando o método getSpriteIndex do nível atual.
  // Desenha o sprite na posição correta ajustando o deslocamento do nível.

  public void atualizar() {

  }

  public Niveis getCurrentLevel() {
    return niveis.get(lvlIndex);
    //Retorna a instância do nível atual com base no lvlIndex.
  }

  public int getAmountOfLevels() {
    return niveis.size();
  }//Retorna a quantidade total de níveis disponíveis.

}
