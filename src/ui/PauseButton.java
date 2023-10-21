package ui;

import java.awt.Rectangle;

public class PauseButton {

  protected int x, y, LARGURA, ALTURA;
  protected Rectangle bounds;

  public PauseButton(int x, int y, int LARGURA, int ALTURA) {
    this.x = x;
    this.y = y;
    this.LARGURA = LARGURA;
    this.ALTURA = ALTURA;
    createBounds();
  }

  private void createBounds() {
    bounds = new Rectangle(x, y, LARGURA, ALTURA);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getLARGURA() {
    return LARGURA;
  }

  public void setLARGURA(int LARGURA) {
    this.LARGURA = LARGURA;
  }

  public int getALTURA() {
    return ALTURA;
  }

  public void setALTURA(int ALTURA) {
    this.ALTURA = ALTURA;
  }

  public Rectangle getBounds() {
    return bounds;
  }

  public void setBounds(Rectangle bounds) {
    this.bounds = bounds;
  }

}
