package entidades;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estadosjogos.Jogando;
import levels.Niveis;
import utilz.CarregarSave;
import static utilz.Constantes.InimigoConstantes.*;

public class GerenciadoInimigo {
//A classe GerenciadoInimigo é declarada.
  private Jogando jogando;
  private BufferedImage[][] RoboArr;
  private ArrayList<Robo> robos = new ArrayList<>();
//Essas são variáveis de instância da classe. jogando é uma referência ao estado do jogo,
//RoboArr é uma matriz de imagens que representa os sprites dos robôs, e robos é uma lista de objetos da classe Robo que representam os inimigos.
  public GerenciadoInimigo(Jogando jogando) {
    this.jogando = jogando;
    carregarInimigoImgs();
  }
//O construtor da classe recebe uma referência para o estado do jogo e carrega as imagens dos robôs.
  public void carregarInimigos(Niveis niveis) {
    robos = niveis.getRobos();
  }
//O método carregarInimigos é usado para carregar os inimigos de um nível específico e armazená-los na lista robos.
  public void atualizar(int[][] lvlData, Jogador jogador) {
    boolean AlgumAtivo = false;
    for (Robo c : robos)
      if (c.isActive()) {
        c.atualizar(lvlData, jogador);
        AlgumAtivo = true;
      }
    if(!AlgumAtivo)
      jogando.setLevelCompleto(true);
  }
//O método atualizar é responsável por atualizar os inimigos. Ele percorre a lista de robôs, atualizando cada um deles,
//e verifica se pelo menos um deles está ativo. Se nenhum estiver ativo, sinaliza que o nível foi concluído.
  public void draw(Graphics g, int xLvlOffset) {
    drawRobos(g, xLvlOffset);
  }
//O método draw é usado para desenhar os inimigos na tela. Ele chama o método drawRobos para desenhar os robôs.
  private void drawRobos(Graphics g, int xLvlOffset) {
    for (Robo c : robos)
      if (c.isActive()) {
        g.drawImage(RoboArr[c.getInimigoEstado()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - Robo_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - Robo_DRAWOFFSET_Y,
            Robo_LARGURA * c.flipW(), Robo_ALTURA, null);
//				c.drawHitbox(g, xLvlOffset);
//				c.desenharCaixaAtaque(g, xLvlOffset);

      }

  }
//O método drawRobos desenha os robôs ativos na tela com base em suas posições, animações e estados.
  public void checkInimigoHit(Rectangle2D.Float attackBox) {
    for (Robo c : robos)
      if (c.isActive())
        if (attackBox.intersects(c.getHitbox())) {
          c.ferir(10);
          return;
        }
  }
//O método checkInimigoHit é usado para verificar se a área de ataque do jogador colide com algum robô ativo. Se houver uma colisão, o robô sofre dano.
  private void carregarInimigoImgs() {
    RoboArr = new BufferedImage[5][9];
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.Robo_SPRITE);
    for (int j = 0; j < RoboArr.length; j++)
      for (int i = 0; i < RoboArr[j].length; i++)
        RoboArr[j][i] = temp.getSubimage(i * Robo_LARGURA_DEFAULT, j * Robo_ALTURA_DEFAULT, Robo_LARGURA_DEFAULT, Robo_ALTURA_DEFAULT);
  }
//O método carregarInimigoImgs carrega os sprites dos robôs a partir de uma folha de sprites e os armazena na matriz RoboArr.
  public void resetAllEnemies() {
    for (Robo c : robos)
      c.resetInimigo();
  }
//O método resetAllEnemies é usado para redefinir todos os robôs, restaurando suas configurações padrão.
}