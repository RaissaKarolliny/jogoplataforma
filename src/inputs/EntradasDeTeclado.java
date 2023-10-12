package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import estadosdejogo.EstadosDeJogo;
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
    	switch(EstadosDeJogo.estado) {
    	case MENU:
    		PainelDoJogo.getJogo().getMenu().keyReleased(e);
    		break;
    	case CENAJOGO:
    		PainelDoJogo.getJogo().getCenaJogo().keyReleased(e);;
    		break;
		default:
			break;
    	
    	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	switch(EstadosDeJogo.estado) {
    	case MENU:
    		PainelDoJogo.getJogo().getMenu().keyPressed(e);
    		break;
    	case CENAJOGO:
    		PainelDoJogo.getJogo().getCenaJogo().keyPressed(e);;
    		break;
		default:
			break;
    	
    	}
   
    }
}
