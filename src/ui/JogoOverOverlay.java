package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import estadosjogos.Jogostate;
import estadosjogos.Jogando;
import main.Jogo;

public class JogoOverOverlay {

  private Jogando jogando;

  public JogoOverOverlay(Jogando jogando) {
    this.jogando = jogando;
  }

  public void draw(Graphics g) {
    g.setColor(new Color(0, 0, 0, 200));
    g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);

    g.setColor(Color.white);
    g.drawString("Jogo Over", Jogo.Jogo_LARGURA / 2, 150);
    g.drawString("Press esc to enter Main Menu!", Jogo.Jogo_LARGURA / 2, 300);

  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      jogando.resetAll();
      Jogostate.state = Jogostate.MENU;
    }
  }
}