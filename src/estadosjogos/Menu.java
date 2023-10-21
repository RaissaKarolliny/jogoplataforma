package estadosjogos;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Jogo;
import ui.MenuButton;
import utilz.CarregarSave;

public class Menu extends Estado implements Estadomethods {

  private MenuButton[] buttons = new MenuButton[3];
  private BufferedImage backgroundImg, backgroundfundoinicial;
  private int menuX, menuY, menuLARGURA, menuALTURA;

  public Menu(Jogo Jogo) {
    super(Jogo);
    loadButtons();
    loadBackground();
    backgroundfundoinicial = CarregarSave.GetSpriteAtlas(CarregarSave.MENU_BACKGROUND_IMG);

  }/*A classe começa declarando várias variáveis e objetos necessários para o menu, como botões de menu, imagens de fundo e coordenadas
  São inicializadas as imagens de fundo e os botões do menu. Os botões do menu estão em um array chamado buttons.
*/
  
  //O construtor da classe é chamado quando um objeto da classe é instanciado. No construtor, as imagens de fundo e os botões do menu são carregados.
  private void loadBackground() {
    backgroundImg = CarregarSave.GetSpriteAtlas(CarregarSave.MENU_BACKGROUND);
    menuLARGURA = (int) (backgroundImg.getWidth() * Jogo.ESCALA);
    menuALTURA = (int) (backgroundImg.getHeight() * Jogo.ESCALA);
    menuX = Jogo.Jogo_LARGURA / 2 - menuLARGURA / 2;
    menuY = (int) (45 * Jogo.ESCALA);

  }
//Este método é responsável por carregar as imagens de fundo do menu.
 // As dimensões da imagem de fundo são dimensionadas com base no valor da escala do jogo, e as coordenadas de onde a imagem deve ser desenhada também são definidas.
  private void loadButtons() {
    buttons[0] = new MenuButton(Jogo.Jogo_LARGURA / 2, (int) (150 * Jogo.ESCALA), 0, Jogostate.PLAYING);
    buttons[1] = new MenuButton(Jogo.Jogo_LARGURA / 2, (int) (220 * Jogo.ESCALA), 1, Jogostate.OPTIONS);
    buttons[2] = new MenuButton(Jogo.Jogo_LARGURA / 2, (int) (290 * Jogo.ESCALA), 2, Jogostate.QUIT);
  }
//Este método é responsável por criar os botões do menu.
 // São criados três botões: "PLAYING", "OPTIONS" e "QUIT" e são posicionados verticalmente no centro da tela.
  @Override
  public void atualizar() {
    for (MenuButton mb : buttons)
      mb.atualizar();
  }
//Este método é chamado para atualizar o estado do menu.
 // Ele percorre os botões do menu e os atualiza.
  @Override
  public void draw(Graphics g) {

    g.drawImage(backgroundfundoinicial, 0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA, null);
    g.drawImage(backgroundImg, menuX, menuY, menuLARGURA, menuALTURA, null);

    for (MenuButton mb : buttons)
      mb.draw(g);
  }
//Este método é chamado para renderizar o estado do menu.
 // Ele desenha as imagens de fundo e os botões na tela.
  @Override
  public void mouseClicked(MouseEvent e) { //Esses métodos são chamados em resposta a eventos do mouse
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e) {    //Esses métodos são chamados em resposta a eventos do mouse
    for (MenuButton mb : buttons) {
      if (isIn(e, mb)) {
        mb.setMousePressed(true);
      }
    }

  }

  @Override
  public void mouseReleased(MouseEvent e) {  //Esses métodos são chamados em resposta a eventos do mouse
    for (MenuButton mb : buttons) {
      if (isIn(e, mb)) {
        if (mb.isMousePressed())
          mb.applyJogostate();
        break;
      }
    }

    resetButtons();

  }

  private void resetButtons() {
    for (MenuButton mb : buttons)
      mb.resetBools();

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    for (MenuButton mb : buttons)
      mb.setMouseOver(false);

    for (MenuButton mb : buttons)
      if (isIn(e, mb)) {
        mb.setMouseOver(true);
        break;
      }

  }//Este método é usado para redefinir os botões do menu. 
  //Ele é chamado quando um botão do mouse é liberado para garantir que nenhum botão continue sendo pressionado.

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER)
      Jogostate.state = Jogostate.PLAYING;

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}