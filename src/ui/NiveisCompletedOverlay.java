package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import estadosjogos.Jogostate;
import estadosjogos.Jogando;
import main.Jogo;
import utilz.CarregarSave;
import static utilz.Constantes.UI.URMButtons.*;

public class NiveisCompletedOverlay {

  private Jogando jogando;
  private UrmButton menu, next;
  private BufferedImage img;
  private int bgX, bgY, bgW, bgH;

  public NiveisCompletedOverlay(Jogando jogando) {
    this.jogando = jogando;
    initImg();
    initButtons();
  }
// O construtor é chamado quando um objeto NiveisCompletedOverlay é criado. 
  //Ele recebe uma instância de Jogando (a classe que representa o estado de jogo atual) como argumento.
//O construtor inicializa a imagem do nível concluído, os botões do menu e do próximo nível, além de suas posições.

  private void initButtons() {
    int menuX = (int) (330 * Jogo.ESCALA);
    int nextX = (int) (445 * Jogo.ESCALA);
    int y = (int) (195 * Jogo.ESCALA);
    next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
    menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
  }
//Este método inicializa os botões do menu e do próximo nível. 
  //Ele define as coordenadas x e y para cada botão com base nas constantes de posição definidas no arquivo de constantes. 
  //Os botões são criados como instâncias da classe UrmButton.

  private void initImg() {
    img = CarregarSave.GetSpriteAtlas(CarregarSave.COMPLETED_IMG);
    bgW = (int) (img.getWidth() * Jogo.ESCALA);
    bgH = (int) (img.getHeight() * Jogo.ESCALA);
    bgX = Jogo.Jogo_LARGURA / 2 - bgW / 2;
    bgY = (int) (75 * Jogo.ESCALA);
  }
//Este método inicializa a imagem que será usada como plano de fundo para a sobreposição de nível concluído.
//A imagem é carregada usando o método CarregarSave.GetSpriteAtlas, e as dimensões da imagem são ajustadas com base na escala do jogo.

  public void draw(Graphics g) {
    g.setColor(new Color(0, 0, 0, 200));
    g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);

    g.drawImage(img, bgX, bgY, bgW, bgH, null);
    next.draw(g);
    menu.draw(g);
  }
  //Este método é responsável por desenhar a sobreposição na tela. 
  //Ele desenha um fundo preto semi-transparente, a imagem do nível concluído, e os botões do menu e próximo nível.

  public void atualizar() {
    next.atualizar();
    menu.atualizar();
  }
//O método atualizar é responsável por atualizar os botões. 
  //Ele chama os métodos atualizar dos botões do menu e próximo nível para atualizar o estado visual deles com base nas ações do jogador.

  private boolean isIn(UrmButton b, MouseEvent e) {
    return b.getBounds().contains(e.getX(), e.getY());
  }

  public void mouseMoved(MouseEvent e) {
    next.setMouseOver(false);
    menu.setMouseOver(false);

    if (isIn(menu, e))
      menu.setMouseOver(true);
    else if (isIn(next, e))
      next.setMouseOver(true);
  }
//Este método é chamado quando o mouse é movido. 
  //Ele verifica se o mouse está sobre algum dos botões (menu ou próximo nível) e define a variável mouseOver dos botões apropriadamente.

  public void mouseReleased(MouseEvent e) {
    if (isIn(menu, e)) {
      if (menu.isMousePressed()) {
        jogando.resetAll();
        Jogostate.state = Jogostate.MENU;
      }
    } else if (isIn(next, e))
      if (next.isMousePressed())
        jogando.loadNextLevel();

    menu.resetBools();
    next.resetBools();
  }
  //mouseReleased: Este método é chamado quando o botão do mouse é liberado (clique é solto).
  // Ele verifica se o clique do mouse ocorreu em algum dos botões. Se o clique foi no botão do menu, 
  //ele reseta o estado do jogo e retorna ao menu principal. Se o clique foi no botão do próximo nível, ele carrega o próximo nível. 
  //Em ambos os casos, as variáveis mousePressed dos botões são redefinidas.

  public void mousePressed(MouseEvent e) {
    if (isIn(menu, e))
      menu.setMousePressed(true);
    else if (isIn(next, e))
      next.setMousePressed(true);
  }
//Este método é chamado quando o botão do mouse é pressionado (clique é iniciado).
// Ele verifica se o clique do mouse ocorreu em algum dos botões e define a variável mousePressed dos botões apropriadamente.
}







