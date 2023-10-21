package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Jogo.Jogo_ALTURA;
import static main.Jogo.Jogo_LARGURA;

public class JogoPanel extends JPanel {

  private MouseInputs mouseInputs;
  private Jogo Jogo;
  //mouseInputs: Uma instância da classe MouseInputs, usada para gerenciar eventos de mouse.
 //Jogo: Uma referência à instância da classe Jogo a que o painel pertence.

  public JogoPanel(Jogo Jogo) {
    mouseInputs = new MouseInputs(this);
    this.Jogo = Jogo;
    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }/*Inicializa o mouseInputs passando uma referência a si mesmo (this) para que possa gerenciar eventos de mouse no painel.
	Armazena a referência ao objeto Jogo para interagir com ele posteriormente.
	Chama o método setPanelSize para definir as dimensões preferenciais do painel.
	Adiciona ouvintes de teclado e mouse ao painel para capturar eventos de entrada.*/

  private void setPanelSize() {
    Dimension size = new Dimension(Jogo_LARGURA, Jogo_ALTURA);
    setPreferredSize(size);
  }
//Define as dimensões preferenciais do painel com base nas constantes Jogo_LARGURA e Jogo_ALTURA da classe Jogo.
  public void atualizarJogo() {

  }
//Método vazio que poderia ser usado para atualizar a lógica do jogo, se necessário. Neste código, não é utilizado.
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Jogo.render(g);
  }
//Sobrescreve o método paintComponent para personalizar o desenho do painel.
// Chama o método render do objeto Jogo e repassa o contexto gráfico (Graphics g) para desenhar o jogo.
  public Jogo getJogo() {
    return Jogo;
  }
 // Permite acessar a instância da classe Jogo a partir de outros objetos.
}