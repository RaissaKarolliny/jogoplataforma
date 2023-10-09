package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.EntradasDeTeclado;
import inputs.EntradasDoMouse;
import static main.Jogo.GAME_HEIGHT;
import static main.Jogo.GAME_WIDTH;

public class PainelDoJogo extends JPanel {
    
	private EntradasDoMouse entradasDoMouse;
    private Jogo jogo;
   
    public PainelDoJogo(Jogo jogo) {
        entradasDoMouse = new EntradasDoMouse(this);
        this.jogo = jogo;
 

        definirTamanhoDoPainel();
        addKeyListener(new EntradasDeTeclado(this));
        addMouseListener(entradasDoMouse);
        addMouseMotionListener(entradasDoMouse);
    }

    private void definirTamanhoDoPainel() {
        Dimension tamanho = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(tamanho);
    }

    public void atualizarJogo() {
        
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        jogo.render(g);
 }
    public Jogo getJogo() {
    	return jogo;
    }
}
