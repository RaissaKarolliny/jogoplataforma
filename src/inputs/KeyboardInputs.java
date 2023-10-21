package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import estadosjogos.Jogostate;
import main.JogoPanel;

public class KeyboardInputs implements KeyListener {

  private JogoPanel JogoPanel;

  public KeyboardInputs(JogoPanel JogoPanel) {
    this.JogoPanel = JogoPanel;
  }
//A classe começa declarando uma variável JogoPanel para armazenar a referência ao painel do jogo em que os eventos de teclado estão sendo tratados.
 // O construtor da classe recebe uma instância do JogoPanel e atribui-a à variável JogoPanel.
  @Override
  public void keyTyped(KeyEvent e) { //Este método é chamado quando uma tecla é digitada, mas não é usado no código.
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent e) {   
	  									 
	  									
    switch (Jogostate.state) {          			//Este método é chamado quando uma tecla é liberada.
    case MENU:										//Dependendo do estado do jogo (representado pela variável Jogostate.state),
      JogoPanel.getJogo().getMenu().keyReleased(e);	//ele redireciona o evento de tecla liberada para o estado relevante do jogo. Por exemplo, se o estado do jogo for "MENU,"		
      break;										//ele chama o método keyReleased do objeto de menu do jogo, e se o estado for "PLAYING," ele chama o método keyReleased do
    case PLAYING:									//objeto de jogo em execução.
      JogoPanel.getJogo().getJogando().keyReleased(e);
      break;
    default:
      break;

    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (Jogostate.state) {
    case MENU:
      JogoPanel.getJogo().getMenu().keyPressed(e);
      break;
    case PLAYING:
      JogoPanel.getJogo().getJogando().keyPressed(e);
      break;
    default:
      break;
    }
  }
//Este método é chamado quando uma tecla é pressionada.
// Similar ao método keyReleased, ele verifica o estado atual do jogo e redireciona o evento de tecla pressionada 
//para o estado apropriado, permitindo que o estado lide com a entrada do teclado.
}