package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.PainelDoJogo;

public class EntradasDoMouse implements MouseListener, MouseMotionListener {

    private PainelDoJogo painelDoJogo;

    public EntradasDoMouse(PainelDoJogo painelDoJogo) {
        this.painelDoJogo = painelDoJogo;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	}
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1)
    		painelDoJogo.getJogo().getPlayer().setAtaque(true);    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}