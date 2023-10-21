package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class JogoWindow {//jframe: Uma instância da classe JFrame, que representa a janela principal do jogo.
  private JFrame jframe;

  public JogoWindow(JogoPanel JogoPanel) {

    jframe = new JFrame();

    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(JogoPanel);

    jframe.setResizable(false);
    jframe.pack();
    jframe.setLocationRelativeTo(null);
    jframe.setVisible(true);
    jframe.addWindowFocusListener(new WindowFocusListener() {
    	//Inicializa o jframe.
    	 // Define o comportamento de fechar a janela como encerrar o jogo quando a janela é fechada (JFrame.EXIT_ON_CLOSE).
    	  //Adiciona o painel do jogo (JogoPanel) à janela.
    	  // a janela para não ser redimensionável.
    	  // pack() para dimensionar a janela de acordo com o tamanho preferencial do conteúdo.
    	 // a janela na tela.
    	 // Torna a janela visível.
    	 // Adiciona um ouvinte de foco à janela usando addWindowFocusListener.
      @Override
      public void windowLostFocus(WindowEvent e) {
        JogoPanel.getJogo().windowFocusLost();
      }

      @Override
      public void windowGainedFocus(WindowEvent e) {
        // TODO Auto-generated method stub

      }
    });

  }

}