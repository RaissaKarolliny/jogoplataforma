package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.PainelDoJogo;
import static utilz.Constantes.Direcoes.*;

public class EntradasDeTeclado implements KeyListener {

    private PainelDoJogo painelDoJogo;

    public EntradasDeTeclado(PainelDoJogo painelDoJogo) {
        this.painelDoJogo = painelDoJogo;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                painelDoJogo.definirMovimentacao(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                painelDoJogo.definirDirecao(CIMA);
                break;
            case KeyEvent.VK_A:
                painelDoJogo.definirDirecao(ESQUERDA);
                break;
            case KeyEvent.VK_S:
                painelDoJogo.definirDirecao(BAIXO);
                break;
            case KeyEvent.VK_D:
                painelDoJogo.definirDirecao(DIREITA);
                break;
        }
    }
}
