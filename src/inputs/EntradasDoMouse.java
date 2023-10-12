package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import estadosdejogo.EstadosDeJogo;
import main.PainelDoJogo;

public class EntradasDoMouse implements MouseListener, MouseMotionListener {

	private PainelDoJogo paineldoJogo;

	public EntradasDoMouse(PainelDoJogo paineldoJogo) {
		this.paineldoJogo = paineldoJogo;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (EstadosDeJogo.estado) {
		case CENAJOGO:
			paineldoJogo.getJogo().getCenaJogo().mouseDragged(e);
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (EstadosDeJogo.estado) {
		case MENU:
			paineldoJogo.getJogo().getMenu().mouseMoved(e);
			break;
		case CENAJOGO:
			paineldoJogo.getJogo().getCenaJogo().mouseMoved(e);
			break;
		default:
			break;

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (EstadosDeJogo.estado) {
		case CENAJOGO:
			paineldoJogo.getJogo().getCenaJogo().mouseClicked(e);
			break;
		default:
			break;

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (EstadosDeJogo.estado) {
		case MENU:
			paineldoJogo.getJogo().getMenu().mousePressed(e);
			break;
		case CENAJOGO:
			paineldoJogo.getJogo().getCenaJogo().mousePressed(e);
			break;
		default:
			break;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (EstadosDeJogo.estado) {
		case MENU:
			paineldoJogo.getJogo().getMenu().mouseReleased(e);
			break;
		case CENAJOGO:
			paineldoJogo.getJogo().getCenaJogo().mouseReleased(e);
			break;
		default:
			break;

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
