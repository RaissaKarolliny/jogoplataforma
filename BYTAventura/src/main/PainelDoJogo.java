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
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animacoes;
    private int aniTick, aniIndex, aniVelocidade = 25;
    private int acaoDoJogador = PARADO;
    private int direcaoDoJogador = -1;
    private boolean movimentando = false;

    public PainelDoJogo() {
        entradasDoMouse = new EntradasDoMouse(this);
        importarImagem();
        carregarAnimacoes();

        definirTamanhoDoPainel();
        addKeyListener(new EntradasDeTeclado(this));
        addMouseListener(entradasDoMouse);
        addMouseMotionListener(entradasDoMouse);
    }

    private void carregarAnimacoes() {
        animacoes = new BufferedImage[5][4];
        for (int j = 0; j < animacoes.length; j++)
            for (int i = 0; i < animacoes[j].length; i++)
                animacoes[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
    }

    private void importarImagem() {
        InputStream is = getClass().getResourceAsStream("Sprite_Pedro.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void definirTamanhoDoPainel() {
        Dimension tamanho = new Dimension(920, 600);
        setPreferredSize(tamanho);
    }

    public void definirDirecao(int direcao) {
        this.direcaoDoJogador = direcao;
        movimentando = true;
    }

    public void definirMovimentacao(boolean movimentando) {
        this.movimentando = movimentando;
    }

    private void atualizarContagemDeAnimacao() {
        aniTick++;
        if (aniTick >= aniVelocidade) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= ObterQuantidadeDeSprites(acaoDoJogador))
                aniIndex = 0;
        }
    }

    private void definirAnimacao() {
        if (movimentando)
            acaoDoJogador = CORRENDO;
        else
            acaoDoJogador = PARADO;
    }

    private void atualizarPosicao() {
        if (movimentando) {
            switch (direcaoDoJogador) {
                case ESQUERDA:
                    xDelta -= 1;
                    break;
                case CIMA:
                    yDelta -= 1;
                    break;
                case DIREITA:
                    xDelta += 1;
                    break;
                case BAIXO:
                    yDelta += 1;
                    break;
            }
        }
    }

    public void atualizarJogo() {
        atualizarContagemDeAnimacao();
        definirAnimacao();
        atualizarPosicao();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(animacoes[acaoDoJogador][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
    }
}
