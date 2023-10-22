package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estadosjogos.Jogostate;
import utilz.CarregarSave;
import static utilz.Constantes.UI.URMButtons.*;

public class UrmButton extends PauseButton {
  private BufferedImage[] imgs;
  private int rowIndex, index;
  private boolean mouseOver, mousePressed;
  private Jogostate state;
  
  public UrmButton(int x, int y, int LARGURA, int ALTURA, int rowIndex) {
    super(x, y, LARGURA, ALTURA);
    this.rowIndex = rowIndex;
    loadImgs();
  }
//O construtor é chamado ao criar um objeto UrmButton. Ele recebe coordenadas x e y, largura LARGURA, altura ALTURA, e 
  //o rowIndex que determina a linha da imagem de botão a ser usada (cada linha da imagem pode conter botões diferentes).
  //Durante a inicialização, ele chama o construtor da classe pai PauseButton e carrega as imagens dos botões.
  private void loadImgs() {
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.URM_BUTTONS);
    imgs = new BufferedImage[3];
    for (int i = 0; i < imgs.length; i++)
      imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);

  }
//Este método é usado para carregar as imagens dos botões a partir de uma imagem de sprite.
  //A imagem é dividida em várias partes, uma para cada botão. As imagens são armazenadas em um array imgs.
  public void atualizar() {
    index = 0;
    if (mouseOver)
      index = 1;
    if (mousePressed)
      index = 2;

  }
/* Este método atualiza o estado do botão. O índice index é usado para determinar qual imagem do botão deve ser desenhada com base nas seguintes condições:
	Se o mouse estiver sobre o botão (mouseOver é verdadeiro), o índice index é definido como 1 para realçar o botão.
	Se o botão estiver sendo pressionado (mousePressed é verdadeiro), o índice index é definido como 2 para indicar que o botão está sendo clicado.
	Caso contrário, o índice index é definido como 0 (estado normal)*/
  public void draw(Graphics g) {
    g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
  }
// Este método desenha o botão na tela. Ele utiliza o índice index para selecionar a imagem apropriada do array imgs e a renderiza na posição especificada.
  public void resetBools() {
    mouseOver = false;
    mousePressed = false;
  }
//Este método redefini as variáveis mouseOver e mousePressed, normalmente após uma interação do mouse.
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
//Métodos getters e setters: A classe inclui métodos getters e setters para obter e definir o estado das variáveis mouseOver e mousePressed.
}

