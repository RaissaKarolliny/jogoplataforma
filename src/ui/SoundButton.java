package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.CarregarSave;
import static utilz.Constantes.UI.PauseButtons.*;

public class SoundButton extends PauseButton {

  private BufferedImage[][] soundImgs;
  private boolean mouseOver, mousePressed;
  private boolean muted;
  private int rowIndex, colIndex;

  public SoundButton(int x, int y, int LARGURA, int ALTURA) {
    super(x, y, LARGURA, ALTURA);

    loadSoundImgs();
  }
//O construtor é chamado quando um objeto SoundButton é criado. Ele recebe coordenadas x e y, além das dimensões LARGURA e ALTURA do botão. 
  //Durante a inicialização, ele chama o construtor da classe pai PauseButton e carrega as imagens do botão de som.
  private void loadSoundImgs() {
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.SOUND_BUTTONS);
    soundImgs = new BufferedImage[2][3];
    for (int j = 0; j < soundImgs.length; j++)
      for (int i = 0; i < soundImgs[j].length; i++)
        soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
  }
// Este método é responsável por carregar as imagens do botão de som. Ele carrega uma imagem que contém duas imagens diferentes (uma para som ativo e outra para som desativado).
  //As imagens são então divididas em duas linhas na imagem e armazenadas na matriz soundImgs
  public void atualizar() {
    if (muted)
      rowIndex = 1;
    else
      rowIndex = 0;

    colIndex = 0;
    if (mouseOver)
      colIndex = 1;
    if (mousePressed)
      colIndex = 2;

  }
//Este método é chamado para atualizar o estado do botão de som. Ele determina a imagem a ser exibida com base nas seguintes condições:
//Se o som estiver desativado (muted), rowIndex é definido como 1 (linha que representa som desativado).
//Caso contrário, rowIndex é definido como 0 (linha que representa som ativado).
//colIndex é definido com base nas interações do mouse. Se o mouse estiver sobre o botão,
//colIndex é definido como 1 (para realçar o botão) e, se o botão estiver sendo pressionado, colIndex é definido como 2 (para indicar o clique).
  public void resetBools() {
    mouseOver = false;
    mousePressed = false;
  }
// Este método redefine as variáveis de controle mouseOver e mousePressed. Ele é usado para redefinir o estado do botão após interações do mouse.
  public void draw(Graphics g) {
    g.drawImage(soundImgs[rowIndex][colIndex], x, y, LARGURA, ALTURA, null);
  }
//Este método desenha o botão de som na tela com base nas imagens carregadas.
  //Ele usa rowIndex e colIndex para selecionar a imagem correta da matriz soundImgs e a renderiza na posição especificada.
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

  public boolean isMuted() {
    return muted;
  }

  public void setMuted(boolean muted) {
    this.muted = muted;
  }
// A classe também inclui métodos getters e setters para obter e definir o estado das variáveis mouseOver, mousePressed e muted.
}