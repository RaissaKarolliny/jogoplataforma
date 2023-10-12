package entities;
import static utilz.Constantes.ConstantesDosInimigos.*;

import main.Jogo;

public class Inimigo1 extends Inimigo{

	public Inimigo1(float x, float y) {
		super(x, y, INIMIGO_UM_WIDTH, INIMIGO_UM_HEIGHT, INIMIGO_UM);
		initHitbox(x, y, (int) (22 * Jogo.SCALE), (int) (19 * Jogo.SCALE));
	}

}
