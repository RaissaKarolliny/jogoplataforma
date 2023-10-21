package estadosjogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entidades.GerenciadoInimigo;
import entidades.Jogador;
import levels.NiveisManager;
import main.Jogo;
import ui.JogoOverOverlay;
import ui.NiveisCompletedOverlay;
import ui.PauseOverlay;
import utilz.CarregarSave;
import static utilz.Constantes.Environment.*;

public class Jogando extends Estado implements Estadomethods {
  private Jogador jogador;
  private NiveisManager levelManager;
  private GerenciadoInimigo GerenciadoInimigo;
  private PauseOverlay pauseOverlay;
  private JogoOverOverlay JogoOverOverlay;
  private NiveisCompletedOverlay levelCompletedOverlay;
  private boolean paused = false;

  private int xLvlOffset;
  private int esquerdaBorder = (int) (0.2 * Jogo.Jogo_LARGURA);
  private int direitaBorder = (int) (0.8 * Jogo.Jogo_LARGURA);
  private int maxLvlOffsetX;

  private BufferedImage backgroundImg, bigCloud, smallCloud;

  private boolean JogoOver;
  private boolean lvlCompleted;

  public Jogando(Jogo Jogo) {
    super(Jogo);
    initClasses();

    backgroundImg = CarregarSave.GetSpriteAtlas(CarregarSave.PLAYING_BG_IMG);

    calcLvlOffset();
    loadStartLevel();
  }

  public void loadNextLevel() {
    resetAll();
    levelManager.loadNextLevel();
    jogador.setSpawn(levelManager.getCurrentLevel().getJogadorSpawn());
  }

  private void loadStartLevel() {
    GerenciadoInimigo.carregarInimigos(levelManager.getCurrentLevel());
  }

  private void calcLvlOffset() {
    maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
  }

  private void initClasses() {
    levelManager = new NiveisManager(Jogo);
    GerenciadoInimigo = new GerenciadoInimigo(this);

    jogador = new Jogador(200, 200, (int) (64 * Jogo.ESCALA), (int) (40 * Jogo.ESCALA), this);
    jogador.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    jogador.setSpawn(levelManager.getCurrentLevel().getJogadorSpawn());

    pauseOverlay = new PauseOverlay(this);
    JogoOverOverlay = new JogoOverOverlay(this);
    levelCompletedOverlay = new NiveisCompletedOverlay(this);
  }

  @Override
  public void atualizar() {
    if (paused) {
      pauseOverlay.atualizar();
    } else if (lvlCompleted) {
      levelCompletedOverlay.atualizar();
    } else if (!JogoOver) {
      levelManager.atualizar();
      jogador.atualizar();
      GerenciadoInimigo.atualizar(levelManager.getCurrentLevel().getLevelData(), jogador);
      checkCloseToBorder();
    }
  }

  private void checkCloseToBorder() {
    int jogadorX = (int) jogador.getHitbox().x;
    int diff = jogadorX - xLvlOffset;

    if (diff > direitaBorder)
      xLvlOffset += diff - direitaBorder;
    else if (diff < esquerdaBorder)
      xLvlOffset += diff - esquerdaBorder;

    if (xLvlOffset > maxLvlOffsetX)
      xLvlOffset = maxLvlOffsetX;
    else if (xLvlOffset < 0)
      xLvlOffset = 0;
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(backgroundImg, 0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA, null);


    levelManager.draw(g, xLvlOffset);
    jogador.render(g, xLvlOffset);
    GerenciadoInimigo.draw(g, xLvlOffset);

    if (paused) {
      g.setColor(new Color(0, 0, 0, 150));
      g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);
      pauseOverlay.draw(g);
    } else if (JogoOver)
      JogoOverOverlay.draw(g);
    else if (lvlCompleted)
      levelCompletedOverlay.draw(g);
  }


  public void resetAll() {
    JogoOver = false;
    paused = false;
    lvlCompleted = false;
    jogador.resetAll();
    GerenciadoInimigo.resetAllEnemies();
  }

  public void setJogoOver(boolean JogoOver) {
    this.JogoOver = JogoOver;
  }

  public void checkInimigoHit(Rectangle2D.Float attackBox) {
    GerenciadoInimigo.checkInimigoHit(attackBox);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (!JogoOver)
      if (e.getButton() == MouseEvent.BUTTON1)
        jogador.setAttacking(true);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (JogoOver)
      JogoOverOverlay.keyPressed(e);
    else
      switch (e.getKeyCode()) {
      case KeyEvent.VK_A:
        jogador.setLeft(true);
        break;
      case KeyEvent.VK_D:
        jogador.setRight(true);
        break;
      case KeyEvent.VK_SPACE:
        jogador.setJump(true);
        break;
      case KeyEvent.VK_ESCAPE:
        paused = !paused;
        break;
      }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (!JogoOver)
      switch (e.getKeyCode()) {
      case KeyEvent.VK_A:
        jogador.setLeft(false);
        break;
      case KeyEvent.VK_D:
        jogador.setRight(false);
        break;
      case KeyEvent.VK_SPACE:
        jogador.setJump(false);
        break;
      }

  }

  public void mouseDragged(MouseEvent e) {
    if (!JogoOver)
      if (paused)
        pauseOverlay.mouseDragged(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (!JogoOver) {
      if (paused)
        pauseOverlay.mousePressed(e);
      else if (lvlCompleted)
        levelCompletedOverlay.mousePressed(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (!JogoOver) {
      if (paused)
        pauseOverlay.mouseReleased(e);
      else if (lvlCompleted)
        levelCompletedOverlay.mouseReleased(e);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (!JogoOver) {
      if (paused)
        pauseOverlay.mouseMoved(e);
      else if (lvlCompleted)
        levelCompletedOverlay.mouseMoved(e);
    }
  }

  public void setLevelCompleto(boolean levelCompleted) {
    this.lvlCompleted = levelCompleted;
  }

  public void setMaxLvlOffset(int lvlOffset) {
    this.maxLvlOffsetX = lvlOffset;
  }

  public void unpauseJogo() {
    paused = false;
  }

  public void windowFocusLost() {
    jogador.resetDirBooleans();
  }

  public Jogador getJogador() {
    return jogador;
  }

  public GerenciadoInimigo getGerenciadoInimigo() {
    return GerenciadoInimigo;
  }

}