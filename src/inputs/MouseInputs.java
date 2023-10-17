package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import estadosjogos.Jogostate;
import main.JogoPanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

  private JogoPanel JogoPanel;

  public MouseInputs(JogoPanel JogoPanel) {
    this.JogoPanel = JogoPanel;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    switch (Jogostate.state) {
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseDragged(e);
      break;
    default:
      break;

    }

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().mouseMoved(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseMoved(e);
      break;
    default:
      break;

    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    switch (Jogostate.state) {
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseClicked(e);
      break;
    default:
      break;

    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().mousePressed(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().mousePressed(e);
      break;
    default:
      break;

    }

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().mouseReleased(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseReleased(e);
      break;
    default:
      break;

    }

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

}