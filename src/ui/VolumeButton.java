package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.CarregarSave;
import static utilz.Constantes.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {

  private BufferedImage[] imgs;
  private BufferedImage slider;
  private int index = 0;
  private boolean mouseOver, mousePressed;
  private int buttonX, minX, maxX;

  public VolumeButton(int x, int y, int LARGURA, int ALTURA) {
    super(x + LARGURA / 2, y, VOLUME_LARGURA, ALTURA);
    bounds.x -= VOLUME_LARGURA / 2;
    buttonX = x + LARGURA / 2;
    this.x = x;
    this.LARGURA = LARGURA;
    minX = x + VOLUME_LARGURA / 2;
    maxX = x + LARGURA - VOLUME_LARGURA / 2;
    loadImgs();
  }

  private void loadImgs() {
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.VOLUME_BUTTONS);
    imgs = new BufferedImage[3];
    for (int i = 0; i < imgs.length; i++)
      imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_LARGURA, 0, VOLUME_DEFAULT_LARGURA, VOLUME_DEFAULT_ALTURA);

    slider = temp.getSubimage(3 * VOLUME_DEFAULT_LARGURA, 0, SLIDER_DEFAULT_LARGURA, VOLUME_DEFAULT_ALTURA);

  }

  public void atualizar() {
    index = 0;
    if (mouseOver)
      index = 1;
    if (mousePressed)
      index = 2;

  }

  public void draw(Graphics g) {

    g.drawImage(slider, x, y, LARGURA, ALTURA, null);
    g.drawImage(imgs[index], buttonX - VOLUME_LARGURA / 2, y, VOLUME_LARGURA, ALTURA, null);

  }

  public void changeX(int x) {
    if (x < minX)
      buttonX = minX;
    else if (x > maxX)
      buttonX = maxX;
    else
      buttonX = x;

    bounds.x = buttonX - VOLUME_LARGURA / 2;

  }

  public void resetBools() {
    mouseOver = false;
    mousePressed = false;
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
}
