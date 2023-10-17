package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entidades {

  protected float x, y;
  protected int LARGURA, ALTURA;
  protected Rectangle2D.Float hitbox;

  public Entidades(float x, float y, int LARGURA, int ALTURA) {
    this.x = x;
    this.y = y;
    this.LARGURA = LARGURA;
    this.ALTURA = ALTURA;
  }

  protected void drawHitbox(Graphics g, int xLvlOffset) {
    g.setColor(Color.PINK);
    g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
  }

  protected void initHitbox(float x, float y, int LARGURA, int ALTURA) {
    hitbox = new Rectangle2D.Float(x, y, LARGURA, ALTURA);
  }

  public Rectangle2D.Float getHitbox() {
    return hitbox;
  }

}