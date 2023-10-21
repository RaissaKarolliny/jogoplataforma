package estadosjogos;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Estadomethods {
  public void atualizar();
// Este método é usado para atualizar o estado do jogo. Pode incluir a lógica do jogo, como a movimentação de personagens, a detecção de colisões e a atualização de variáveis de jogo.
  public void draw(Graphics g);
//Este método é usado para renderizar o estado do jogo na tela. Ele recebe um objeto Graphics como parâmetro, que é usado para desenhar elementos gráficos na tela, como personagens, cenários, UI, etc.
  public void mouseClicked(MouseEvent e);
//Este método é chamado quando ocorre um evento de clique do mouse. Ele recebe um objeto MouseEvent que contém informações sobre o evento, como a posição do clique.
  public void mousePressed(MouseEvent e);
//Este método é chamado quando um botão do mouse é pressionado. Ele recebe um objeto MouseEvent que contém informações sobre o evento.
  public void mouseReleased(MouseEvent e);
//Este método é chamado quando um botão do mouse é solto após ter sido pressionado. Ele recebe um objeto MouseEvent que contém informações sobre o evento.
  public void mouseMoved(MouseEvent e);
// Este método é chamado quando o mouse é movido. Ele recebe um objeto MouseEvent que contém informações sobre o movimento do mouse.
  public void keyPressed(KeyEvent e);
// Este método é chamado quando uma tecla do teclado é pressionada. Ele recebe um objeto KeyEvent que contém informações sobre a tecla pressionada.
  public void keyReleased(KeyEvent e);
//Este método é chamado quando uma tecla do teclado é solta após ter sido pressionada. Ele recebe um objeto KeyEvent que contém informações sobre a tecla liberada.
}
