package main;

import java.awt.Graphics;
import estadosjogos.Jogostate;
import estadosjogos.Menu;
import estadosjogos.Jogando;
import utilz.CarregarSave;

public class Jogo implements Runnable {

  private JogoWindow JogoWindow;
  private JogoPanel JogoPanel;
  private Thread JogoThread;
  private final int FPS_SET = 120;
  private final int UPS_SET = 200;

  private Jogando jogando;
  private Menu menu;

  public final static int TILES_DEFAULT_SIZE = 32;
  public final static float ESCALA = 2f;
  public final static int TILES_IN_LARGURA = 26;
  public final static int TILES_IN_ALTURA = 14;
  public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * ESCALA);
  public final static int Jogo_LARGURA = TILES_SIZE * TILES_IN_LARGURA;
  public final static int Jogo_ALTURA = TILES_SIZE * TILES_IN_ALTURA;

  public Jogo() {
    initClasses();

    JogoPanel = new JogoPanel(this);
    JogoWindow = new JogoWindow(JogoPanel);
    JogoPanel.setFocusable(true);
    JogoPanel.requestFocus();

    startJogoLoop();
  }

  private void initClasses() {
    menu = new Menu(this);
    jogando = new Jogando(this);
  }

  private void startJogoLoop() {
    JogoThread = new Thread(this);
    JogoThread.start();
  }

  public void atualizar() {
    switch (Jogostate.state) {
    case MENU:
      menu.atualizar();
      break;
    case PLAYING:
      jogando.atualizar();
      break;
    case OPTIONS:
    case QUIT:
    default:
      System.exit(0);
      break;

    }
  }

  public void render(Graphics g) {
    switch (Jogostate.state) {
    case MENU:
      menu.draw(g);
      break;
    case PLAYING:
      jogando.draw(g);
      break;
    default:
      break;
    }
  }

  @Override
  public void run() {

    double timePerFrame = 1000000000.0 / FPS_SET;
    double timePeratualizar = 1000000000.0 / UPS_SET;

    long previousTime = System.nanoTime();

    int frames = 0;
    int atualizars = 0;
    long lastCheck = System.currentTimeMillis();

    double deltaU = 0;
    double deltaF = 0;

    while (true) {
      long currentTime = System.nanoTime();

      deltaU += (currentTime - previousTime) / timePeratualizar;
      deltaF += (currentTime - previousTime) / timePerFrame;
      previousTime = currentTime;

      if (deltaU >= 1) {
        atualizar();
        atualizars++;
        deltaU--;
      }

      if (deltaF >= 1) {
        JogoPanel.repaint();
        frames++;
        deltaF--;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        System.out.println("FPS: " + frames + " | UPS: " + atualizars);
        frames = 0;
        atualizars = 0;

      }
    }

  }

  public void windowFocusLost() {
    if (Jogostate.state == Jogostate.PLAYING)
      jogando.getJogador().resetDirBooleans();
  }

  public Menu getMenu() {
    return menu;
  }

  public Jogando getJogando() {
    return jogando;
  }
}