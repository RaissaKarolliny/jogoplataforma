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
            	painelDoJogo.getJogo().getPlayer().setCima(false);
            	break;
            case KeyEvent.VK_A:
            	painelDoJogo.getJogo().getPlayer().setEsquerda(false);
            	break;
            case KeyEvent.VK_S:
            	painelDoJogo.getJogo().getPlayer().setBaixo(false);
            	break;
            case KeyEvent.VK_D:
                painelDoJogo.getJogo().getPlayer().setDireita(false);
                break;
              
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	 switch (e.getKeyCode()) {
         case KeyEvent.VK_W:
         	painelDoJogo.getJogo().getPlayer().setCima(true);
         	break;
         case KeyEvent.VK_A:
         	painelDoJogo.getJogo().getPlayer().setEsquerda(true);
         	break;
         case KeyEvent.VK_S:
         	painelDoJogo.getJogo().getPlayer().setBaixo(true);
         	break;
         case KeyEvent.VK_D:
             painelDoJogo.getJogo().getPlayer().setDireita(true);
             break;
     }
    }
}
