package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.EntradasDeTeclado;
import inputs.EntradasDoMouse;

import static utilz.Constantes.ConstantesDoJogador.*;
import static utilz.Constantes.Direcoes.*;

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
        Dimension tamanho = new Dimension(920, 600);
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
