package entidades;

import static utilz.Constantes.InimigoConstantes.*;
import static utilz.MetodosAjuda.*;

import java.awt.geom.Rectangle2D;

import static utilz.Constantes.Direcoes.*;

import main.Jogo;

public abstract class Inimigo extends Entidades {
  protected int aniIndex, InimigoEstado, InimigoTipo;
  protected int aniTick, aniVelocidade = 25;
  protected boolean primeiroatualizar = true;
  protected boolean noAr;
  protected float velocidadeQueda;
  protected float gravidade = 0.04f * Jogo.ESCALA;
  protected float velocidadeAndar = 0.35f * Jogo.ESCALA;
  protected int andaDir = ESQUERDA;
  protected int tileY;
  protected float distanciaAtaque = Jogo.TILES_SIZE;
  protected int vidaMax;
  protected int vidaAtual;
  protected boolean ativado = true;
  protected boolean ataqueChecado;

  public Inimigo(float x, float y, int LARGURA, int ALTURA, int InimigoTipo) {
    super(x, y, LARGURA, ALTURA);
    this.InimigoTipo = InimigoTipo;
    initHitbox(x, y, LARGURA, ALTURA);
    vidaMax = GetVidaMax(InimigoTipo);
    vidaAtual = vidaMax;
  }

  protected void primeiroatualizarCheck(int[][] lvlData) {
    if (!EntidadesNoChao(hitbox, lvlData))
      noAr = true;
    primeiroatualizar = false;
  }

  protected void atualizarNoAr(int[][] lvlData) {
    if (PodeMover(hitbox.x, hitbox.y + velocidadeQueda, hitbox.width, hitbox.height, lvlData)) {
      hitbox.y += velocidadeQueda;
      velocidadeQueda += gravidade;
    } else {
      noAr = false;
      hitbox.y = RetorneEntidadesYEntreTelhadoChao(hitbox, velocidadeQueda);
      tileY = (int) (hitbox.y / Jogo.TILES_SIZE);
    }
  }

  protected void mover(int[][] lvlData) {
    float velocidadeX = 0;

    if (andaDir == ESQUERDA)
      velocidadeX = -velocidadeAndar;
    else
      velocidadeX = velocidadeAndar;

    if (PodeMover(hitbox.x + velocidadeX, hitbox.y, hitbox.width, hitbox.height, lvlData))
      if (SeChao(hitbox, velocidadeX, lvlData)) {
        hitbox.x += velocidadeX;
        return;
      }

    mudarDirecao();
  }

  protected void virarParaJogador(Jogador jogador) {
    if (jogador.hitbox.x > hitbox.x)
      andaDir = DIREITA;
    else
      andaDir = ESQUERDA;
  }

  protected boolean podeVerJogador(int[][] lvlData, Jogador jogador) {
    int jogadorTileY = (int) (jogador.getHitbox().y / Jogo.TILES_SIZE);
    if (jogadorTileY == tileY)
      if (jogadorNoAlcance(jogador)) {
        if (visaoLivre(lvlData, hitbox, jogador.hitbox, tileY))
          return true;
      }

    return false;
  }

  protected boolean jogadorNoAlcance(Jogador jogador) {
    int valorAbs = (int) Math.abs(jogador.hitbox.x - hitbox.x);
    return valorAbs <= distanciaAtaque * 5;
  }

  protected boolean JogadorPertoAtaca(Jogador jogador) {
    int valorAbs = (int) Math.abs(jogador.hitbox.x - hitbox.x);
    return valorAbs <= distanciaAtaque;
  }

  protected void novoEstado(int InimigoEstado) {
    this.InimigoEstado = InimigoEstado;
    aniTick = 0;
    aniIndex = 0;
  }

  public void ferir(int amount) {
    vidaAtual -= amount;
    if (vidaAtual <= 0)
      novoEstado(MORTO);
    else
      novoEstado(BATE);
  }

  protected void checarDanoJogador(Rectangle2D.Float attackBox, Jogador jogador) {
    if (attackBox.intersects(jogador.hitbox))
      jogador.mudarVida(-GetDanoInimigo(InimigoTipo));
    ataqueChecado = true;

  }

  protected void atualizarAnimacaoTick() {
    aniTick++;
    if (aniTick >= aniVelocidade) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= GetQuantidadeSprite(InimigoTipo, InimigoEstado)) {
        aniIndex = 0;

        switch (InimigoEstado) {
        case ATAQUE, BATE -> InimigoEstado = PARADO;
        case MORTO -> ativado = false;
        }
      }
    }
  }

  protected void mudarDirecao() {
    if (andaDir == ESQUERDA)
      andaDir = DIREITA;
    else
      andaDir = ESQUERDA;
  }

  public void resetInimigo() {
    hitbox.x = x;
    hitbox.y = y;
    primeiroatualizar = true;
    vidaAtual = vidaMax;
    novoEstado(PARADO);
    ativado = true;
    velocidadeQueda = 0;
  }

  public int getAniIndex() {
    return aniIndex;
  }

  public int getInimigoEstado() {
    return InimigoEstado;
  }

  public boolean isActive() {
    return ativado;
  }

}