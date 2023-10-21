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
  }//O construtor é chamado quando um objeto MenuButton é criado. 
  //Ele recebe vários argumentos, incluindo as coordenadas xPos e yPos onde o botão deve ser desenhado na tela,
  //o rowIndex, que provavelmente indica a linha da folha de sprite da qual obter a imagem do botão, e o state, que é o estado do jogo
  //que deve ser ativado quando o botão é pressionado.

  private void initBounds() {
    bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_LARGURA, B_ALTURA);

  }
  // Este método inicializa o retângulo de colisão do botão. 
  //O retângulo é definido para cobrir a área do botão na tela. Isso é usado para verificar se o cursor do mouse está sobre o botão.

  private void loadImgs() {
    imgs = new BufferedImage[3];
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.MENU_BUTTONS);
    for (int i = 0; i < imgs.length; i++)
      imgs[i] = temp.getSubimage(i * B_LARGURA_DEFAULT, rowIndex * B_ALTURA_DEFAULT, B_LARGURA_DEFAULT, B_ALTURA_DEFAULT);
  }//Este método é usado para carregar as imagens dos botões a partir de uma folha de sprite.
 // Ele usa o método CarregarSave.GetSpriteAtlas para obter a imagem da folha de sprite contendo os botões do menu
  //e, em seguida, divide essa imagem em várias sub-imagens com base nas coordenadas na folha de sprite. 
  //As imagens são armazenadas em um array chamado imgs.

  public void draw(Graphics g) {
    g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_LARGURA, B_ALTURA, null);
  }
//Este método é responsável por desenhar o botão na tela. 
//Ele usa o Graphics passado como argumento para desenhar a imagem do botão no local especificado pelas coordenadas xPos e yPos. 
//A imagem a ser desenhada é escolhida com base no valor de index.
  public void atualizar() {
    index = 0;
    if (mouseOver)
      index = 1;
    if (mousePressed)
      index = 2;
  }
//O método atualizar é responsável por atualizar o botão. Ele define o valor de index com base nas condições a seguir:
//Se o mouse estiver sobre o botão, index é definido como 1 para destacar o botão.
//Se o botão estiver sendo pressionado (mousePressed), index é definido como 2, o que provavelmente representa o estado pressionado do botão.
//Caso contrário, index permanece como 0, que é o estado normal do botão.
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
  
//Métodos isMouseOver, setMouseOver, isMousePressed, setMousePressed: 
//Esses métodos são getters e setters para as variáveis mouseOver e mousePressed.
//Eles permitem definir e verificar se o mouse está sobre o botão ou se o botão está sendo pressionado.
  
  
  public Rectangle getBounds() {
    return bounds;
  }
//Este método retorna o retângulo de colisão do botão. É usado para verificar se o cursor do mouse está sobre o botão
  public void applyJogostate() {
    Jogostate.state = state;
  }
//Quando o botão é pressionado, este método é chamado para aplicar um novo estado do jogo.
  //O estado do jogo é alterado para o valor especificado no construtor (state).
  public void resetBools() {
    mouseOver = false;
    mousePressed = false;
  }
//Este método é chamado para redefinir as variáveis mouseOver e mousePressed para false.
//É útil para garantir que o botão não permaneça destacado após ser pressionado.
}
