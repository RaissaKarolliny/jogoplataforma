package ui;
import static utilz.Constantes.UI.URMButtons.URM_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import estadosjogos.Jogostate;
import estadosjogos.Jogando;
import main.Jogo;
import utilz.CarregarSave;

public class JogoOverOverlay {

  private Jogando jogando;
  private BufferedImage backgroundImgFinal;
  private int menuXFINAL, menuYFINAL, menuLARGURAFINAL, menuALTURAFINAL;
  private UrmButton menu, play;
  
  public JogoOverOverlay(Jogando jogando) {
    this.jogando = jogando;
    loadBackgroundfinal();
    crieButtons();
   
  }
 private void crieButtons() {
	 	int menuX = (int) (335 * Jogo.ESCALA);
		int playX = (int) (440 * Jogo.ESCALA);
		int y = (int) (129 * Jogo.ESCALA);
		play = new UrmButton(playX, y, URM_SIZE, URM_SIZE, 1);
		menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
}
//Este é o construtor da classe, que recebe um objeto Jogando como argumento.
 //O Jogando é provavelmente a parte do jogo em execução (o estado de jogo) que é responsável por manter a lógica do jogo enquanto ele está em execução.
 
  private void loadBackgroundfinal() {
	    backgroundImgFinal = CarregarSave.GetSpriteAtlas(CarregarSave.MENU_FINAL);
	    menuLARGURAFINAL = (int) (backgroundImgFinal.getWidth() * Jogo.ESCALA);
	    menuALTURAFINAL = (int) (backgroundImgFinal.getHeight() * Jogo.ESCALA);
	    menuXFINAL = Jogo.Jogo_LARGURA / 2 - menuLARGURAFINAL/ 2;
	    menuYFINAL = (int) (50 * Jogo.ESCALA);

	  }
  
  public void draw(Graphics g) {
	    g.setColor(new Color(0, 0, 0, 200));
	    g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);
	    
	    g.drawImage(backgroundImgFinal, menuXFINAL, menuYFINAL, menuLARGURAFINAL, menuALTURAFINAL, null);

	    //menu.draw(g);
	   // play.draw(g);
	  }
  
  public void atualizar() {
	  menu.atualizar();
	  play.atualizar();
  }
 
//Este método é usado para renderizar a tela de "Jogo Over".
//Ele mostra a imagem final
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      jogando.resetAll();
      Jogostate.state = Jogostate.MENU;
    }
  }
  //Este método é chamado quando uma tecla é pressionada. Ele verifica se a tecla pressionada é a tecla "ESC" (tecla de escape). Se for, ele executa duas ações:
//Chama o método resetAll() do objeto jogando. Isso provavelmente redefine o estado do jogo para um estado inicial ou reinicia o jogo.
//Define o estado do jogo (representado por Jogostate.state) como MENU, o que significa que o jogador voltará para o menu principal.
 
  private boolean isIn(UrmButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		play.setMouseOver(false);
		menu.setMouseOver(false);

		if (isIn(menu, e))
			menu.setMouseOver(true);
		else if (isIn(play, e))
			play.setMouseOver(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(menu, e)) {
			if (menu.isMousePressed()) {
				jogando.resetAll();
				Jogostate.state = Jogostate.MENU;
			}
		} else if (isIn(play, e))
			if (play.isMousePressed())
				jogando.resetAll();

		menu.resetBools();
		play.resetBools();
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(menu, e))
			menu.setMousePressed(true);
		else if (isIn(play, e))
			play.setMousePressed(true);
	}
}