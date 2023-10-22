package estadosjogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import entidades.GerenciadoInimigo;
import entidades.Jogador;
import levels.NiveisManager;
import main.Jogo;
import ui.JogoOverOverlay;
import ui.NiveisCompletedOverlay;
import ui.PauseOverlay;
import utilz.CarregarSave;

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

  private BufferedImage backgroundImg;

  private boolean JogoOver;
  private boolean lvlCompleted;
  private boolean jogadorMorrendo;

  public Jogando(Jogo Jogo) {
	  // Construtor da classe
    super(Jogo);
    initClasses();// Inicializa várias classes do jogo
    // Carrega a imagem de fundo do jogo
    backgroundImg = CarregarSave.GetSpriteAtlas(CarregarSave.PLAYING_BG_IMG);

    calcLvlOffset();// Calcula o deslocamento do nível
    loadStartLevel();// Carrega o nível inicial
  }
//Carrega o próximo nível do jogo
  public void loadNextLevel() {
    resetAll();// Reinicia o estado do jogo
    levelManager.loadNextLevel();// Carrega o próximo nível
    jogador.setSpawn(levelManager.getCurrentLevel().getJogadorSpawn());// Define a posição inicial do jogador
  }
  // Carrega o nível inicial
  private void loadStartLevel() {
    GerenciadoInimigo.carregarInimigos(levelManager.getCurrentLevel());
  }
  // Calcula o deslocamento do nível
  private void calcLvlOffset() {
    maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
  }
  // Inicializa várias classes do jogo
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
  // Método de atualização do jogo
  @Override
  public void atualizar() {
    if (paused) {
      pauseOverlay.atualizar();// Atualiza a tela de pausa
    } else if (lvlCompleted) {
      levelCompletedOverlay.atualizar(); // Atualiza a tela de nível concluído
    } else if (JogoOver) {
    	//jogoOverOverlay.atualizar();
    }else if (jogadorMorrendo) {
    	jogador.atualizar(); 	
    }else{
      levelManager.atualizar();// Atualiza o gerenciador de níveis
      jogador.atualizar();// Atualiza o jogador
      GerenciadoInimigo.atualizar(levelManager.getCurrentLevel().getLevelData(), jogador);
      checkCloseToBorder();// Verifica a posição do jogador em relação à tela
    }
  }
  // Verifica a proximidade do jogador em relação às bordas da tela
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
  // Método de desenho do jogo
  @Override
  public void draw(Graphics g) {
    g.drawImage(backgroundImg, 0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA, null);


    levelManager.draw(g, xLvlOffset);// Desenha o nível
    jogador.render(g, xLvlOffset);// Renderiza o jogador
    GerenciadoInimigo.draw(g, xLvlOffset);// Desenha os inimigos

    if (paused) {
      g.setColor(new Color(0, 0, 0, 150));
      g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);
      pauseOverlay.draw(g);// Desenha a tela de pausa
    } else if (JogoOver)
      JogoOverOverlay.draw(g);// Desenha a tela de jogo encerrado
    else if (lvlCompleted)
      levelCompletedOverlay.draw(g);// Desenha a tela de nível concluído
  }

  // Reseta o estado do jogo
  public void resetAll() {
    JogoOver = false;
    paused = false;
    lvlCompleted = false;
    jogador.resetAll();
    GerenciadoInimigo.resetAllEnemies();
  }
  // Define se o jogo foi encerrado
  public void setJogoOver(boolean JogoOver) {
    this.JogoOver = JogoOver;
  }
  // Verifica colisões com inimigos
  public void checkInimigoHit(Rectangle2D.Float attackBox) {
    GerenciadoInimigo.checkInimigoHit(attackBox);
  }
  // Manipuladores de eventos de mouse
  @Override
  public void mouseClicked(MouseEvent e) {
    if (!JogoOver)
      if (e.getButton() == MouseEvent.BUTTON1)
        jogador.setAttacking(true);
  }
//Manipuladores de eventos de teclado
  @Override
  public void keyPressed(KeyEvent e) {
    if (JogoOver)
      JogoOverOverlay.keyPressed(e);
    else
      switch (e.getKeyCode()) {
      case KeyEvent.VK_A:
        jogador.setEsquerda(true);
        break;
      case KeyEvent.VK_D:
        jogador.setDireita(true);
        break;
      case KeyEvent.VK_SPACE:
        jogador.setPulando(true);
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
        jogador.setEsquerda(false);
        break;
      case KeyEvent.VK_D:
        jogador.setDireita(false);
        break;
      case KeyEvent.VK_SPACE:
        jogador.setPulando(false);
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
//Define se o nível foi concluído
  public void setLevelCompleto(boolean levelCompleted) {
    this.lvlCompleted = levelCompleted;
  }
  // Define o deslocamento máximo do nível
  public void setMaxLvlOffset(int lvlOffset) {
    this.maxLvlOffsetX = lvlOffset;
  }
  // Despausa o jogo

  public void unpauseJogo() {
    paused = false;
  }
  // Quando o foco da janela é perdido, redefine as direções do jogador
  public void windowFocusLost() {
    jogador.resetDirBooleans();
  }
  // Retorna o jogador
  public Jogador getJogador() {
    return jogador;
  }
  // Retorna o gerenciador de inimigos
  public GerenciadoInimigo getGerenciadoInimigo() {
    return GerenciadoInimigo;
  }
public void setJogadorMorrendo(boolean jogadorMorrendo) {
	this.jogadorMorrendo = jogadorMorrendo;
	
}

}