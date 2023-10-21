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

  /*JogoWindow e JogoPanel: Instâncias da classe JogoWindow e JogoPanel que são usadas para exibir o jogo na janela.
	JogoThread: Uma instância da classe Thread usada para executar o loop principal do jogo em uma thread separada.
	FPS_SET e UPS_SET: Taxas de quadros por segundo (FPS) e atualizações por segundo (UPS) definidas para controlar a velocidade do jogo.
	jogando e menu: Instâncias das classes Jogando e Menu que representam os estados do jogo.
	Constantes relacionadas às configurações de tamanho e escala do jogo.  */
  
  
  
  public Jogo() {
    initClasses();

    JogoPanel = new JogoPanel(this);
    JogoWindow = new JogoWindow(JogoPanel);
    JogoPanel.setFocusable(true);
    JogoPanel.requestFocus();

    startJogoLoop();
  }
  //Inicializa as classes principais do jogo chamando o método initClasses.
 // Cria uma instância do JogoPanel e do JogoWindow para configurar a interface gráfica do jogo.
 // Define o foco no JogoPanel para capturar eventos de entrada.
 // Inicia o loop principal do jogo chamando o método startJogoLoop.

  private void initClasses() {
    menu = new Menu(this);
    jogando = new Jogando(this);
  }
//Inicializa as instâncias das classes menu e jogando.
  private void startJogoLoop() {
    JogoThread = new Thread(this);
    JogoThread.start();
  }
//Inicia a thread que executará o loop principal do jogo.
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
  }//Método para atualizar o jogo com base no estado atual definido por Jogostate.state.
  //Chama o método atualizar do estado do menu ou do estado do jogo, dependendo do valor de Jogostate.state

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
//Método para renderizar o jogo com base no estado atual definido por Jogostate.state.
 // Chama o método draw do estado do menu ou do estado do jogo, dependendo do valor de Jogostate.state.
  @Override
  public void run() {	//Implementa o loop principal do jogo.

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
    }//Calcula o tempo entre atualizações e renderizações, ajustando a taxa de atualização para atingir as taxas especificadas.
//Chama os métodos atualizar e repaint do JogoPanel para atualizar e renderizar o jogo.
    //Controla o FPS e UPS e exibe essas informações no console.
  }

  public void windowFocusLost() {
    if (Jogostate.state == Jogostate.PLAYING)
      jogando.getJogador().resetDirBooleans();
  }//Método chamado quando o foco da janela é perdido.
 // Usado para garantir que o estado do jogador seja atualizado corretamente quando o foco é retomado.

  public Menu getMenu() {		     //Getters para menu e jogando:
	  								//Permitem o acesso às instâncias das classes Menu e Jogando.
    return menu;
  }

  public Jogando getJogando() {
    return jogando;
  }
}