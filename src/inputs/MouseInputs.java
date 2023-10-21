package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import estadosjogos.Jogostate;
import main.JogoPanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

  private JogoPanel JogoPanel;

  public MouseInputs(JogoPanel JogoPanel) {
    this.JogoPanel = JogoPanel;
  }
//A classe começa declarando uma variável JogoPanel para armazenar a referência ao painel do jogo em que os eventos do mouse estão sendo tratados.
 // O construtor da classe recebe uma instância do JogoPanel e atribui-a à variável JogoPanel.
  @Override
  public void mouseDragged(MouseEvent e) {
    switch (Jogostate.state) {
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseDragged(e);
      break;
    default:
      break;

    }/*Este método é chamado quando o mouse é arrastado (movido com um botão pressionado).
		Dependendo do estado do jogo (representado pela variável Jogostate.state), ele redireciona 
		o evento de mouse arrastado para o estado relevante do jogo, permitindo que o estado lide com o movimento do mouse.
     	*/
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().mouseMoved(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseMoved(e);
      break;
    default:
      break;

    }
    //Este método é chamado quando o mouse é movido (sem botões pressionados).
   // Assim como o método mouseDragged, ele verifica o estado atual do jogo e redireciona o evento de movimento do mouse para o estado apropriado.
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    switch (Jogostate.state) {
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseClicked(e);
      break;
    default:
      break;

    }
//Este método é chamado quando um clique do mouse é detectado (um clique é um pressionar e liberar do botão do mouse).
//Novamente, dependendo do estado do jogo, ele redireciona o evento de clique do mouse para o estado relevante do jogo.
  }

  @Override
  public void mousePressed(MouseEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().mousePressed(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().mousePressed(e);
      break;
    default:
      break;

    }
//Este método é chamado quando um botão do mouse é pressionado (antes de ser liberado).
//Ele verifica o estado do jogo e redireciona o evento de pressionar o botão do mouse para o estado apropriado.
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().mouseReleased(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().mouseReleased(e);
      break;
    default:
      break;

    }
  //Este método é chamado quando um botão do mouse é liberado (após ter sido pressionado).
  //Dependendo do estado do jogo, ele redireciona o evento de liberar o botão do mouse para o estado relevante do jogo.

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

}