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

  public JogoPanel(Jogo Jogo) {
    mouseInputs = new MouseInputs(this);
    this.Jogo = Jogo;
    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  private void setPanelSize() {
    Dimension size = new Dimension(Jogo_LARGURA, Jogo_ALTURA);
    setPreferredSize(size);
  }

  public void atualizarJogo() {

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Jogo.render(g);
  }

  public Jogo getJogo() {
    return Jogo;
  }

}