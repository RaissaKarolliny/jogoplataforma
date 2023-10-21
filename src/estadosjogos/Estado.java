package estadosjogos;

import java.awt.event.MouseEvent;

import main.Jogo;
import ui.MenuButton;

public class Estado {

  protected Jogo Jogo;

  public Estado(Jogo Jogo) {
    this.Jogo = Jogo;
  }

  public boolean isIn(MouseEvent e, MenuButton mb) {
    return mb.getBounds().contains(e.getX(), e.getY());
  }


  public Jogo getJogo() {
    return Jogo;
  }
}

