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
//O construtor é chamado quando um objeto PauseButton é criado.
  //Ele recebe as coordenadas x e y da posição do botão, bem como as dimensões LARGURA e ALTURA do botão.
  ////O construtor inicializa os campos da classe com base nos argumentos passados e chama o método createBounds() para criar o
  //retângulo que representa a área do botão.
  private void createBounds() {
    bounds = new Rectangle(x, y, LARGURA, ALTURA);
  }
//Este método cria o retângulo bounds que representa a área do botão com base nas coordenadas x e y e nas dimensões LARGURA e ALTURA.
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
///Métodos Getters e Setters: A classe fornece métodos getters e setters para as propriedades x, y, LARGURA, ALTURA, e bounds. 
//Esses métodos permitem acessar e modificar essas propriedades a partir de outras partes do código.
}
