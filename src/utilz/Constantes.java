package utilz;

import main.Jogo;

public class Constantes {
	
	public static class ConstantesDosInimigos{
		public static final int INIMIGO_UM = 0;
		public static final int INIMIGO_DOIS=99;
		public static final int  PARADO = 0;
		public static final int  CORRENDO = 1;
		public static final int  ATAQUE = 2;
		public static final int  HIT = 3;
		public static final int  MORTO = 4;
		
		
		public static final int  INIMIGO_UM_WIDTH_DEFAULT = 72;
		public static final int  INIMIGO_UM_HEIGHT_DEFAULT = 32;

		public static final int  INIMIGO_UM_WIDTH = (int)(INIMIGO_UM_WIDTH_DEFAULT * Jogo.SCALE);
		public static final int  INIMIGO_UM_HEIGHT = (int)(INIMIGO_UM_HEIGHT_DEFAULT * Jogo.SCALE);
		
		public static final int INIMIGO_UM_DRAWOFFSET_X = (int) (26 * Jogo.SCALE);
		public static final int INIMIGO_UM_DRAWOFFSET_Y = (int) (9 * Jogo.SCALE);
		
		
		public static int ObterQuantidadeDeSprites(int tipo_inimigo,int estado_inimigo) {
			
			switch (tipo_inimigo) {
			case INIMIGO_UM:
				switch(estado_inimigo) {
				
			    case PARADO:
			    	return 5;
	            case ATAQUE:
	            	return 6;
	            case CORRENDO:
	            case HIT:
	            case MORTO:
	            return 4;
				}
			}
			return 0;
		}
	}
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Jogo.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Jogo.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Jogo.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Jogo.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Jogo.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Jogo.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Jogo.SCALE);
		}
	}
    public static class Direcoes {
        public static final int ESQUERDA = 0;
        public static final int CIMA = 1;
        public static final int DIREITA = 2;
        public static final int BAIXO = 3;
    }

    public static class ConstantesDoJogador {
        public static final int PARADO = 0;
        public static final int CORRENDO = 1;
        public static final int PULAR = 2;
        public static final int CAIR = 3;
        public static final int SOLO = 9;
        public static final int ATACAR = 11;
        public static final int ATAQUE_1 = 4;
        public static final int ATAQUE_PULAR_1 = 7;
        public static final int ATAQUE_PULAR_2 = 8;

        public static int ObterQuantidadeDeSprites(int acaoDoJogador) {
            switch (acaoDoJogador) {
                case CORRENDO:
                case PARADO:
                case ATACAR:
                case PULAR:
                case ATAQUE_1:
                case ATAQUE_PULAR_1:
                case ATAQUE_PULAR_2:
                    return 4;
                case SOLO:
                    return 2;
                case CAIR:
                	return 1;
                default:
                    return 1;
            	}
        	}
    	}
	}