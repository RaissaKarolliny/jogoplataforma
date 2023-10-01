package main;

import java.awt.Graphics;

import entities.Player;

public class Jogo implements Runnable {

    private JanelaDoJogo janelaDoJogo;
    private PainelDoJogo painelDoJogo;
    private Thread threadDoJogo;
    private final int FPS_DEFINIDO = 120;
    private final int UPS_DEFINIDO = 200;
    private Player player;

    public Jogo() {
        painelDoJogo = new PainelDoJogo(this);
        janelaDoJogo = new JanelaDoJogo(painelDoJogo);
        painelDoJogo.requestFocus();
        initClasses();
        iniciarLoopDoJogo();
        
    }

    private void initClasses() {
		player = new Player(1,1);
		
	}

	private void iniciarLoopDoJogo() {
        threadDoJogo = new Thread(this);
        threadDoJogo.start();
    }

    public void atualizar() {
        player.update();
    }
    
    public void render(Graphics g) {
    	player.render(g);
    	
    }
    
    @Override
    public void run() {
        double tempoPorQuadro = 1000000000.0 / FPS_DEFINIDO;
        double tempoPorAtualizacao = 1000000000.0 / UPS_DEFINIDO;

        long tempoAnterior = System.nanoTime();

        int quadros = 0;
        int atualizacoes = 0;
        long ultimaVerificacao = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long tempoAtual = System.nanoTime();

            deltaU += (tempoAtual - tempoAnterior) / tempoPorAtualizacao;
            deltaF += (tempoAtual - tempoAnterior) / tempoPorQuadro;
            tempoAnterior = tempoAtual;

            if (deltaU >= 1) {
                atualizar();
                atualizacoes++;
                deltaU--;
            }

            if (deltaF >= 1) {
                painelDoJogo.repaint();
                quadros++;
                deltaF--;
            }

            if (System.currentTimeMillis() - ultimaVerificacao >= 1000) {
                ultimaVerificacao = System.currentTimeMillis();
                System.out.println("FPS: " + quadros + " | UPS: " + atualizacoes);
                quadros = 0;
                atualizacoes = 0;
            }
        }
    }
    public void windowFocusLost() {
    	player.resetDirBooleans();
    }
    
    public Player getPlayer() {
    	return player;
    }
}
