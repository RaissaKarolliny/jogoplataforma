package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entidades {
//A classe Entidades é declarada como abstrata,
//o que significa que não pode ser instanciada diretamente, mas pode ser usada como uma classe base para outras entidades
  protected float x, y;
  protected int LARGURA, ALTURA;
  protected Rectangle2D.Float hitbox;
//Essas são as variáveis de instância da classe. x e y representam as coordenadas da entidade, 
//LARGURA e ALTURA representam suas dimensões, e hitbox é um retângulo de colisão.
  public Entidades(float x, float y, int LARGURA, int ALTURA) {
    this.x = x;
    this.y = y;
    this.LARGURA = LARGURA;
    this.ALTURA = ALTURA;
  }
//O construtor da classe recebe as coordenadas x e y,
//bem como as dimensões LARGURA e ALTURA da entidade e inicializa as variáveis de instância correspondentes.
  protected void drawHitbox(Graphics g, int xLvlOffset) {
    g.setColor(Color.PINK);
    g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
  }
//O método drawHitbox é usado para desenhar a caixa de colisão (hitbox) da entidade. Ela define a cor da caixa como rosa
//e a desenha na posição x - xLvlOffset (levando em consideração o deslocamento horizontal do nível), y, largura e altura da hitbox.
  protected void initHitbox(float x, float y, int LARGURA, int ALTURA) {
    hitbox = new Rectangle2D.Float(x, y, LARGURA, ALTURA);
  }
//O método initHitbox é usado para inicializar a hitbox da entidade com as coordenadas x e y, bem como as dimensões LARGURA e `ALTURA.
  public Rectangle2D.Float getHitbox() {
    return hitbox;
  }
//O método getHitbox permite acessar a hitbox da entidade a partir de outras classes.
}