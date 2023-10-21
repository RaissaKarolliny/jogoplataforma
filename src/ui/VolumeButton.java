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
  }//O construtor é chamado ao criar um objeto VolumeButton.
 // Ele recebe as coordenadas x e y, largura LARGURA e altura ALTURA do botão.
 // Durante a inicialização, ele chama o construtor da classe pai PauseButton, ajusta as coordenadas x e bounds, 
 // além de definir os limites mínimo (minX) e máximo (maxX) para a posição do botão deslizante. 
  //Ele também chama o método loadImgs para carregar as imagens necessárias.

  private void loadImgs() {
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.VOLUME_BUTTONS);
    imgs = new BufferedImage[3];
    for (int i = 0; i < imgs.length; i++)
      imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_LARGURA, 0, VOLUME_DEFAULT_LARGURA, VOLUME_DEFAULT_ALTURA);

    slider = temp.getSubimage(3 * VOLUME_DEFAULT_LARGURA, 0, SLIDER_DEFAULT_LARGURA, VOLUME_DEFAULT_ALTURA);

  }
//Este método é usado para carregar as imagens dos botões de volume e do controle deslizante
 // a partir de uma imagem de sprite. A imagem de sprite é dividida em várias partes,
  //incluindo diferentes estados do botão de volume (normal, destacado e clicado) e o controle deslizante.
  public void atualizar() {
    index = 0;
    if (mouseOver)
      index = 1;
    if (mousePressed)
      index = 2;

  }
/*Este método atualiza o estado do botão de volume. 
 * O índice index é usado para determinar qual imagem do botão de volume deve ser desenhada com base nas seguintes condições:
Se o mouse estiver sobre o botão (mouseOver é verdadeiro), o índice index é definido como 1 para realçar o botão.
Se o botão estiver sendo pressionado (mousePressed é verdadeiro), 
o índice index é definido como 2 para indicar que o botão de volume está sendo clicado.
Caso contrário, o índice index é definido como 0 (estado normal).*/
  public void draw(Graphics g) {

    g.drawImage(slider, x, y, LARGURA, ALTURA, null);
    g.drawImage(imgs[index], buttonX - VOLUME_LARGURA / 2, y, VOLUME_LARGURA, ALTURA, null);

  }
// Este método desenha o controle de volume na tela. Ele usa a imagem do botão de volume e do controle deslizante apropriados com base no índice index.
  //O controle deslizante é posicionado na coordenada x do botão.
  public void changeX(int x) {
    if (x < minX)
      buttonX = minX;
    else if (x > maxX)
      buttonX = maxX;
    else
      buttonX = x;

    bounds.x = buttonX - VOLUME_LARGURA / 2;

  }
//Este método é chamado para atualizar a posição do controle deslizante quando o jogador arrasta o botão de volume.
  //Ele recebe a coordenada x da posição atual do mouse. O método verifica se a nova posição x está dentro dos limites minX e maxX
  //(para evitar que o botão deslizante saia dos limites)
  //e, em seguida, atualiza a posição do botão deslizante e os limites da área sensível (bounds) em conformidade.
  public void resetBools() {
    mouseOver = false;
    mousePressed = false;
  }
// Este método redefini as variáveis mouseOver e mousePressed, normalmente após uma interação do mouse.
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
}// A classe inclui métodos getters e setters para obter e definir o estado das variáveis mouseOver e mousePressed.
