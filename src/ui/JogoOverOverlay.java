package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import estadosjogos.Jogostate;
import estadosjogos.Jogando;
import main.Jogo;

public class JogoOverOverlay {

  private Jogando jogando;

  public JogoOverOverlay(Jogando jogando) {
    this.jogando = jogando;
  }
 //Este é o construtor da classe, que recebe um objeto Jogando como argumento.
 //O Jogando é provavelmente a parte do jogo em execução (o estado de jogo) que é responsável por manter a lógica do jogo enquanto ele está em execução.

  public void draw(Graphics g) {
    g.setColor(new Color(0, 0, 0, 200));
    g.fillRect(0, 0, Jogo.Jogo_LARGURA, Jogo.Jogo_ALTURA);

    g.setColor(Color.white);
    g.drawString("Jogo Over", Jogo.Jogo_LARGURA / 2, 150);
    g.drawString("Press esc to enter Main Menu!", Jogo.Jogo_LARGURA / 2, 300);

  }
//Este método é usado para renderizar a tela de "Jogo Over".
//Ele desenha um retângulo preto semi-transparente no tamanho da tela do jogo para criar um fundo escuro. 
//Em seguida, ele desenha duas mensagens de texto no centro da tela: "Jogo Over" e "Press esc to enter Main Menu!".
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      jogando.resetAll();
      Jogostate.state = Jogostate.MENU;
    }
  }
  //Este método é chamado quando uma tecla é pressionada. Ele verifica se a tecla pressionada é a tecla "ESC" (tecla de escape). Se for, ele executa duas ações:
//Chama o método resetAll() do objeto jogando. Isso provavelmente redefine o estado do jogo para um estado inicial ou reinicia o jogo.
//Define o estado do jogo (representado por Jogostate.state) como MENU, o que significa que o jogador voltará para o menu principal.
}