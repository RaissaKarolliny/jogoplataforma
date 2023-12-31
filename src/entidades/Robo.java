package entidades;

import static utilz.Constantes.InimigoConstantes.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utilz.Constantes.Direcoes.*;

import main.Jogo;

public class Robo extends Inimigo {	
 /*Nesta parte, a classe Robo é declarada como uma subclasse de Inimigo.
 * e as constantes e classes importadas contêm valores como tamanhos, direções e outros parâmetros do jogo.
  */	
  private Rectangle2D.Float attackBox;
  private int attackBoxOffsetX;

  public Robo(float x, float y) {
    super(x, y, Robo_LARGURA, Robo_ALTURA, Robo);
    initHitbox(x, y, (int) (22 * Jogo.ESCALA), (int) (19 * Jogo.ESCALA));
    initAttackBox();
  }
 /* Aqui são declaradas duas variáveis privadas: attackBox, que é um retângulo usado para delimitar a área de 
  * ataque do robô, e attackBoxOffsetX, que é um deslocamento horizontal para ajustar a posição da attackBox. O construtor Robo é
  *  definido para criar um objeto Robo com a posição inicial (x, y), largura e altura específicas (Robo_LARGURA e Robo_ALTURA), 
  *  bem como um tipo de robô. Ele também inicializa a "hitbox" e a attackBox.
  */
  private void initAttackBox() {
    attackBox = new Rectangle2D.Float(x, y, (int) (82 * Jogo.ESCALA), (int) (19 * Jogo.ESCALA));
    attackBoxOffsetX = (int) (Jogo.ESCALA * 30);
  }
  /*O método initAttackBox() inicializa a attackBox, definindo seu tamanho com base em constantes multiplicadas pela escala do jogo e 
   * configurando o deslocamento horizontal attackBoxOffsetX.
   */

  public void atualizar(int[][] lvlData, Jogador jogador) {
    atualizarComportamento(lvlData, jogador);
    atualizarAnimacaoTick();
    atualizarAttackBox();
  }
  /*O método atualizar é responsável por atualizar o estado do robô. Ele chama outros métodos para atualizar
   * o comportamento do robô, a animação e a posição da attackBox.
   */

  private void atualizarAttackBox() {
    attackBox.x = hitbox.x - attackBoxOffsetX;
    attackBox.y = hitbox.y;
  }
  /* O método atualizarAttackBox() atualiza a posição da attackBox 
   * com base na posição da "hitbox" do robô e no deslocamento horizontal.
   */

  private void atualizarComportamento(int[][] lvlData, Jogador jogador) {
    if (primeiroatualizar)
      primeiroatualizarCheck(lvlData);

    if (noAr)
      atualizarNoAr(lvlData);
    else {
      switch (InimigoEstado) {
      case PARADO:
        novoEstado(CORRENDO);
        break;
      case CORRENDO:
        if (podeVerJogador(lvlData, jogador)) {
          virarParaJogador(jogador);
          if (JogadorPertoAtaca(jogador))
            novoEstado(ATAQUE);
        }

        mover(lvlData);
        break;
      case ATAQUE:
        if (aniIndex == 0)
          ataqueChecado = false;
        if (aniIndex == 5 && !ataqueChecado)
          checarDanoJogador(attackBox, jogador);
        break;
      case BATE:
        break;
      }
    }
  }
  /* O método atualizarComportamento é responsável por definir o comportamento do robô com base no estado atual. Ele lida com diferentes estados
   * , como "PARADO," "CORRENDO," "ATAQUE" e "BATE," e toma decisões com base na posição do jogador e em outras condições de jogo.
   */

  public void desenharCaixaAtaque(Graphics g, int xLvlOffset) {
    g.setColor(Color.red);
    g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
  }
  /*O método desenharCaixaAtaque é usado para desenhar a attackBox na tela com uma cor vermelha. 
   * Isso é útil para depuração e pode ajudar a visualizar a área de ataque do robô.
   */
  
  
  public int flipX() {
    if (andaDir == DIREITA)
      return LARGURA;
    else
      return 0;
  }

  public int flipW() {
    if (andaDir == DIREITA)
      return -1;
    else
      return 1;
  }
  /*Os métodos flipX e flipW retornam valores usados para controlar a orientação horizontal do robô
   *  com base na direção em que ele está se movendo.
   */
  
  
  
}