package entidades;

import static utilz.Constantes.JogadorConstantes.*;
import static utilz.MetodosAjuda.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import estadosjogos.Jogando;
import main.Jogo;
import utilz.CarregarSave;

public class Jogador extends Entidades {
  private BufferedImage[][] Animacaos;
  private int aniTick, aniIndex, aniVelocidade = 25;
  private int jogadorAcao = PARADO;
  private boolean movendo = false, atacando = false;
  private boolean esquerda, cima, direita, baixo, pulo;
  private float velocidadeJogador = 1.0f * Jogo.ESCALA;
  private int[][] lvlData;
  private float xDrawOffset = 21 * Jogo.ESCALA;
  private float yDrawOffset = 4 * Jogo.ESCALA;

  
  private float VelocidadeNoAr = 0f;
  private float gravidade = 0.04f * Jogo.ESCALA;
  private float velocidadePulo = -2.25f * Jogo.ESCALA;
  private float velocidadeQuedaPosColisao = 0.5f * Jogo.ESCALA;
  private boolean noAr = false;

  private BufferedImage statusBarImg;

  private int statusBarLARGURA = (int) (192 * Jogo.ESCALA);
  private int statusBarALTURA = (int) (58 * Jogo.ESCALA);
  private int statusBarX = (int) (10 * Jogo.ESCALA);
  private int statusBarY = (int) (10 * Jogo.ESCALA);

  private int barraVidaLARGURA = (int) (150 * Jogo.ESCALA);
  private int barraVidaALTURA = (int) (4 * Jogo.ESCALA);
  private int barraVidaXStart = (int) (34 * Jogo.ESCALA);
  private int barraVidaYStart = (int) (14 * Jogo.ESCALA);

  private int vidaMax = 10;
  private int vidaAtual = vidaMax;
  private int healthLARGURA = barraVidaLARGURA;


  private Rectangle2D.Float attackBox;

  private int flipX = 0;
  private int flipW = 1;

  private boolean ataqueChecado;
  private Jogando jogando;

  public Jogador(float x, float y, int LARGURA, int ALTURA, Jogando jogando) {
    super(x, y, LARGURA, ALTURA);
    this.jogando = jogando;
    carregarAnimacoes();
    initHitbox(x, y, (int) (20 * Jogo.ESCALA), (int) (27 * Jogo.ESCALA));
    initAttackBox();
  }

  public void setSpawn(Point spawn) {
    this.x = spawn.x;
    this.y = spawn.y;
    hitbox.x = x;
    hitbox.y = y;
  }

  private void initAttackBox() {
    attackBox = new Rectangle2D.Float(x, y, (int) (20 * Jogo.ESCALA), (int) (20 * Jogo.ESCALA));
  }

  public void atualizar() {
    atualizarHealthBar();

    if (vidaAtual <= 0) {
      jogando.setJogoOver(true);
      return;
    }

    atualizarAttackBox();

    atualizarPos();
    if (atacando)
      checkAttack();
    atualizarAnimacaoTick();
    setAnimacao();
  }

  private void checkAttack() {
    if (ataqueChecado || aniIndex != 1)
      return;
    ataqueChecado = true;
    jogando.checkInimigoHit(attackBox);

  }

  private void atualizarAttackBox() {
    if (direita)
      attackBox.x = hitbox.x + hitbox.width + (int) (Jogo.ESCALA * 10);
    else if (esquerda)
      attackBox.x = hitbox.x - hitbox.width - (int) (Jogo.ESCALA * 10);

    attackBox.y = hitbox.y + (Jogo.ESCALA * 10);
  }

  private void atualizarHealthBar() {
    healthLARGURA = (int) ((vidaAtual / (float) vidaMax) * barraVidaLARGURA);
  }

  public void render(Graphics g, int lvlOffset) {
    g.drawImage(Animacaos[jogadorAcao][aniIndex], (int) (hitbox.x - xDrawOffset) - lvlOffset + flipX, (int) (hitbox.y - yDrawOffset), LARGURA * flipW, ALTURA, null);

    drawUI(g);
  }

  private void desenharCaixaAtaque(Graphics g, int lvlOffsetX) {
    g.setColor(Color.red);
    g.drawRect((int) attackBox.x - lvlOffsetX, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
  }

  private void drawUI(Graphics g) {
    g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarLARGURA, statusBarALTURA, null);
    g.setColor(Color.red);
    g.fillRect(barraVidaXStart + statusBarX, barraVidaYStart + statusBarY, healthLARGURA, barraVidaALTURA);
  }

  private void atualizarAnimacaoTick() {
    aniTick++;
    if (aniTick >= aniVelocidade) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= GetQuantidadeSprite(jogadorAcao)) {
        aniIndex = 0;
        atacando = false;
        ataqueChecado = false;
      }

    }

  }

  private void setAnimacao() {
    int startAni = jogadorAcao;

    if (movendo)
      jogadorAcao = CORRENDO;
    else
      jogadorAcao = PARADO;

    if (noAr) {
      if (VelocidadeNoAr < 0)
        jogadorAcao = PULAR;
      else
        jogadorAcao = CAINDO;
    }

    if (atacando) {
      jogadorAcao = ATAQUE;
      if (startAni != ATAQUE) {
        aniIndex = 1;
        aniTick = 0;
        return;
      }
    }
    if (startAni != jogadorAcao)
      resetAniTick();
  }

  private void resetAniTick() {
    aniTick = 0;
    aniIndex = 0;
  }

  private void atualizarPos() {
    movendo = false;

    if (pulo)
      pulo();

    if (!noAr)
      if ((!esquerda && !direita) || (direita && esquerda))
        return;

    float velocidadeX = 0;

    if (esquerda) {
      velocidadeX -= velocidadeJogador;
      flipX = LARGURA;
      flipW = -1;
    }
    if (direita) {
      velocidadeX += velocidadeJogador;
      flipX = 0;
      flipW = 1;
    }

    if (!noAr)
      if (!EntidadesNoChao(hitbox, lvlData))
        noAr = true;

    if (noAr) {
      if (PodeMover(hitbox.x, hitbox.y + VelocidadeNoAr, hitbox.width, hitbox.height, lvlData)) {
        hitbox.y += VelocidadeNoAr;
        VelocidadeNoAr += gravidade;
        atualizarXPos(velocidadeX);
      } else {
        hitbox.y = RetorneEntidadesYEntreTelhadoChao(hitbox, VelocidadeNoAr);
        if (VelocidadeNoAr > 0)
          resetNoAr();
        else
          VelocidadeNoAr = velocidadeQuedaPosColisao;
        atualizarXPos(velocidadeX);
      }

    } else
      atualizarXPos(velocidadeX);
    movendo = true;
  }

  private void pulo() {
    if (noAr)
      return;
    noAr = true;
    VelocidadeNoAr = velocidadePulo;
  }

  private void resetNoAr() {
    noAr = false;
    VelocidadeNoAr = 0;
  }

  private void atualizarXPos(float velocidadeX) {
    if (PodeMover(hitbox.x + velocidadeX, hitbox.y, hitbox.width, hitbox.height, lvlData))
      hitbox.x += velocidadeX;
    else
      hitbox.x = GetEntidadesXPosNextToWall(hitbox, velocidadeX);
  }

  public void mudarVida(int value) {
    vidaAtual += value;

    if (vidaAtual <= 0)
      vidaAtual = 0;
    else if (vidaAtual >= vidaMax)
      vidaAtual = vidaMax;
  }

  private void carregarAnimacoes() {
    BufferedImage img = CarregarSave.GetSpriteAtlas(CarregarSave.PLAYER_ATLAS);
    Animacaos = new BufferedImage[7][8];
    for (int j = 0; j < Animacaos.length; j++)
      for (int i = 0; i < Animacaos[j].length; i++)
        Animacaos[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

    statusBarImg = CarregarSave.GetSpriteAtlas(CarregarSave.STATUS_BAR);
  }

  public void loadLvlData(int[][] lvlData) {
    this.lvlData = lvlData;
    if (!EntidadesNoChao(hitbox, lvlData))
      noAr = true;
  }

  public void resetDirBooleans() {
    esquerda = false;
    direita = false;
    cima = false;
    baixo = false;
  }

  public void setAttacking(boolean atacando) {
    this.atacando = atacando;
  }

  public boolean eEsquerda() {
    return esquerda;
  }

  public void setLeft(boolean esquerda) {
    this.esquerda = esquerda;
  }

  public boolean isUp() {
    return cima;
  }

  public void setUp(boolean cima) {
    this.cima = cima;
  }

  public boolean isRight() {
    return direita;
  }

  public void setRight(boolean direita) {
    this.direita = direita;
  }

  public boolean isDown() {
    return baixo;
  }

  public void setDown(boolean baixo) {
    this.baixo = baixo;
  }

  public void setJump(boolean pulo) {
    this.pulo = pulo;
  }

  public void resetAll() {
    resetDirBooleans();
    noAr = false;
    atacando = false;
    movendo = false;
    jogadorAcao = PARADO;
    vidaAtual = vidaMax;

    hitbox.x = x;
    hitbox.y = y;

    if (!EntidadesNoChao(hitbox, lvlData))
      noAr = true;
  }

}