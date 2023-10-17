package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import estadosjogos.Jogostate;
import estadosjogos.Jogando;
import main.Jogo;
import utilz.CarregarSave;
import static utilz.Constantes.UI.URMButtons.*;

public class NiveisCompletedOverlay {

  private Jogando jogando;
  private UrmButton menu, next;
  private BufferedImage img;
  private int bgX, bgY, bgW, bgH;

  public NiveisCompletedOverlay(Jogando jogando) {
    this.jogando = jogando;
    initImg();
    initButtons();
  }

  private void initButtons() {
    int menuX = (int) (330 * Jogo.ESCALA);
    int nextX = (int) (445 * Jogo.ESCALA);
    int y = (int) (195 * Jogo.ESCALA);
    next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
    menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
  }

  private void initImg() {
    img = CarregarSave.GetSpriteAtlas(CarregarSave.COMPLETED_IMG);
    bgW = (int) (img.getWidth() * Jogo.ESCALA);
    bgH = (int) (img.getHeight() * Jogo.ESCALA);
    bgX = Jogo.Jogo_LARGURA / 2 - bgW / 2;
    bgY = (int) (75 * Jogo.ESCALA);
  }

  public void draw(Graphics g) {
    // Added after youtube cimaload
    g.setColor(new Color(0, 0, 0, 200));
    g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);

    g.drawImage(img, bgX, bgY, bgW, bgH, null);
    next.draw(g);
    menu.draw(g);
  }

  public void atualizar() {
    next.atualizar();
    menu.atualizar();
  }

  private boolean isIn(UrmButton b, MouseEvent e) {
    return b.getBounds().contains(e.getX(), e.getY());
  }

  public void mouseMoved(MouseEvent e) {
    next.setMouseOver(false);
    menu.setMouseOver(false);

    if (isIn(menu, e))
      menu.setMouseOver(true);
    else if (isIn(next, e))
      next.setMouseOver(true);
  }

  public void mouseReleased(MouseEvent e) {
    if (isIn(menu, e)) {
      if (menu.isMousePressed()) {
        jogando.resetAll();
        Jogostate.state = Jogostate.MENU;
      }
    } else if (isIn(next, e))
      if (next.isMousePressed())
        jogando.loadNextLevel();

    menu.resetBools();
    next.resetBools();
  }

  public void mousePressed(MouseEvent e) {
    if (isIn(menu, e))
      menu.setMousePressed(true);
    else if (isIn(next, e))
      next.setMousePressed(true);
  }

}
