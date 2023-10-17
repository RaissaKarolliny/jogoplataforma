package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import estadosjogos.Jogostate;
import main.JogoPanel;

public class KeyboardInputs implements KeyListener {

  private JogoPanel JogoPanel;

  public KeyboardInputs(JogoPanel JogoPanel) {
    this.JogoPanel = JogoPanel;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().keyReleased(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().keyReleased(e);
      break;
    default:
      break;

    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().keyPressed(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().keyPressed(e);
      break;
    default:
      break;
    }
  }
}