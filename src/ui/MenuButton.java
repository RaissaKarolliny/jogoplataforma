package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import estadosjogos.Jogostate;
import utilz.CarregarSave;
import static utilz.Constantes.UI.Buttons.*;

public class MenuButton {
  private int xPos, yPos, rowIndex, index;
  private int xOffsetCenter = B_LARGURA / 2;
  private Jogostate state;
  private BufferedImage[] imgs;
  private boolean mouseOver, mousePressed;
  private Rectangle bounds;

  public MenuButton(int xPos, int yPos, int rowIndex, Jogostate state) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.rowIndex = rowIndex;
    this.state = state;
    loadImgs();
    initBounds();
  }

  private void initBounds() {
    bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_LARGURA, B_ALTURA);

  }

  private void loadImgs() {
    imgs = new BufferedImage[3];
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.MENU_BUTTONS);
    for (int i = 0; i < imgs.length; i++)
      imgs[i] = temp.getSubimage(i * B_LARGURA_DEFAULT, rowIndex * B_ALTURA_DEFAULT, B_LARGURA_DEFAULT, B_ALTURA_DEFAULT);
  }

  public void draw(Graphics g) {
    g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_LARGURA, B_ALTURA, null);
  }

  public void atualizar() {
    index = 0;
    if (mouseOver)
      index = 1;
    if (mousePressed)
      index = 2;
  }

  public boolean isMouseOver() {
    return mouseOver;
  }

  public void setMouseOver(boolean mouseOver) {
    this.mouseOver = mouseOver;
  }

  public boolean isMousePressed() {
    return mousePressed;
  }

  public void setMousePressed(boolean mousePressed) {
    this.mousePressed = mousePressed;
  }

  public Rectangle getBounds() {
    return bounds;
  }

  public void applyJogostate() {
    Jogostate.state = state;
  }

  public void resetBools() {
    mouseOver = false;
    mousePressed = false;
  }

}
