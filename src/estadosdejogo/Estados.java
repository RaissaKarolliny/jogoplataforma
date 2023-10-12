package estadosdejogo;

import java.awt.event.MouseEvent;

import main.Jogo;
import ui.MenuBotao;

public class Estados {
	
	protected Jogo jogo;
	
	public Estados(Jogo jogo) {
		this.jogo = jogo;
	}
	public boolean isIn(MouseEvent e, MenuBotao mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	public Jogo getJogo() {
		return jogo;
	}
}
