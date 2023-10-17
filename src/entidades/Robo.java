package entidades;

import static utilz.Constantes.InimigoConstantes.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utilz.Constantes.Direcoes.*;

import main.Jogo;

public class Robo extends Inimigo {

  private Rectangle2D.Float attackBox;
  private int attackBoxOffsetX;

  public Robo(float x, float y) {
    super(x, y, Robo_LARGURA, Robo_ALTURA, Robo);
    initHitbox(x, y, (int) (22 * Jogo.ESCALA), (int) (19 * Jogo.ESCALA));
    initAttackBox();
  }

  private void initAttackBox() {
    attackBox = new Rectangle2D.Float(x, y, (int) (82 * Jogo.ESCALA), (int) (19 * Jogo.ESCALA));
    attackBoxOffsetX = (int) (Jogo.ESCALA * 30);
  }

  public void atualizar(int[][] lvlData, Jogador jogador) {
    atualizarComportamento(lvlData, jogador);
    atualizarAnimacaoTick();
    atualizarAttackBox();
  }

  private void atualizarAttackBox() {
    attackBox.x = hitbox.x - attackBoxOffsetX;
    attackBox.y = hitbox.y;
  }

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

  public void desenharCaixaAtaque(Graphics g, int xLvlOffset) {
    g.setColor(Color.red);
    g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
  }

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
}