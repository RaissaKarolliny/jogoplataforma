package estadosDeJogo;

import main.Jogo;

public class Estados {
	
	protected Jogo jogo;
	
	public Estados(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public Jogo getJogo() {
		return jogo;
	}
}
