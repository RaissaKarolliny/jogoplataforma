package utilz;

import main.Jogo;

public class Constantes {

  public static class InimigoConstantes {
//Contém constantes relacionadas aos inimigos do jogo, como Robôs (Robo) e Dorgivais. Ela define valores como estados (parado, correndo, ataque, etc.),
//tamanhos padrão, deslocamentos de desenho e métodos para obter quantidades de sprites, vida máxima e dano dos inimigos.
    public static final int Robo = 0;
    public static final int Dorgival = 1;

    public static final int PARADO = 0;
    public static final int CORRENDO = 1;
    public static final int ATAQUE = 2;
    public static final int BATE = 3;
    public static final int MORTO = 4;

    public static final int Robo_LARGURA_DEFAULT = 72;
    public static final int Robo_ALTURA_DEFAULT = 32;
    public static final int Robo_LARGURA = (int) (Robo_LARGURA_DEFAULT * Jogo.ESCALA);
    public static final int Robo_ALTURA = (int) (Robo_ALTURA_DEFAULT * Jogo.ESCALA);
    public static final int Robo_DRAWOFFSET_X = (int) (26 * Jogo.ESCALA);
    public static final int Robo_DRAWOFFSET_Y = (int) (9 * Jogo.ESCALA);


    public static final int Dorgival_LARGURA_DEFAULT = 72;
    public static final int Dorgival_ALTURA_DEFAULT = 32;

    public static final int Dorgival_LARGURA = (int) (Robo_LARGURA_DEFAULT * Jogo.ESCALA);
    public static final int Dorgival_ALTURA = (int) (Robo_ALTURA_DEFAULT * Jogo.ESCALA);

    public static final int Dorgival_DRAWOFFSET_X = (int) (26 * Jogo.ESCALA);
    public static final int Dorgival_DRAWOFFSET_Y = (int) (9 * Jogo.ESCALA);

    
    public static int GetQuantidadeSprite(int Inimigo_type, int Inimigo_state) {

      switch (Inimigo_type) {
      case Robo:
      case Dorgival:
        switch (Inimigo_state) {
        case PARADO:
          return 5;
        case CORRENDO:
          return 4;
        case ATAQUE:
          return 6;
        case BATE:
          return 4;
        case MORTO:
          return 5;
        }
      }

      return 0;

    }

    public static int GetVidaMax(int Inimigo_type) {
      switch (Inimigo_type) {
      case Robo:
        return 10;
      case Dorgival:
    	return 50;
      default:
        return 1;
      }
    }

    public static int GetDanoInimigo(int Inimigo_type) {
      switch (Inimigo_type) {
      case Robo:
        return 15;
      case Dorgival:
      	return 50;
      default:
        return 0;
      }

    }

  }

 

  public static class UI {
//Contém classes internas relacionadas à interface do usuário (UI) do jogo.
//Isso inclui botões do menu, botões de pausa, botões de som, botões de controles do jogo e tamanhos padrão para esses elementos.
    public static class Buttons {
      public static final int B_LARGURA_DEFAULT = 140;
      public static final int B_ALTURA_DEFAULT = 56;
      public static final int B_LARGURA = (int) (B_LARGURA_DEFAULT * Jogo.ESCALA);
      public static final int B_ALTURA = (int) (B_ALTURA_DEFAULT * Jogo.ESCALA);
    }

    public static class PauseButtons {
      public static final int SOUND_SIZE_DEFAULT = 42;
      public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Jogo.ESCALA);
    }

    public static class URMButtons {
      public static final int URM_DEFAULT_SIZE = 56;
      public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Jogo.ESCALA);

    }

    public static class VolumeButtons {
      public static final int VOLUME_DEFAULT_LARGURA = 28;
      public static final int VOLUME_DEFAULT_ALTURA = 44;
      public static final int SLIDER_DEFAULT_LARGURA = 215;

      public static final int VOLUME_LARGURA = (int) (VOLUME_DEFAULT_LARGURA * Jogo.ESCALA);
      public static final int VOLUME_ALTURA = (int) (VOLUME_DEFAULT_ALTURA * Jogo.ESCALA);
      public static final int SLIDER_LARGURA = (int) (SLIDER_DEFAULT_LARGURA * Jogo.ESCALA);
    }
  }

  public static class Direcoes {
// Define constantes para direções como ESQUERDA, CIMA, DIREITA e BAIXO.
    public static final int ESQUERDA = 0;
    public static final int UP = 1;
    public static final int DIREITA = 2;
    public static final int DOWN = 3;
  }

  public static class JogadorConstantes {
	  // Contém constantes relacionadas ao personagem jogável (jogador) no jogo.
	  //Ela define estados (parado, correndo, etc.) e métodos para obter a quantidade de sprites para cada ação do jogador.
    public static final int PARADO = 0;
    public static final int CORRENDO = 1;
    public static final int PULAR = 2;
    public static final int CAINDO = 3;
    public static final int ATAQUE = 4;
    public static final int BATE = 6;
    public static final int MORTO = 5;

    public static int GetQuantidadeSprite(int jogador_action) {
      switch (jogador_action) {
      case MORTO:
        return 5;
      case CORRENDO:
        return 4;
      case PARADO:
        return 4;
      case BATE:
        return 4;
      case PULAR:
      case ATAQUE:
        return 4;
      case CAINDO:
      default:
        return 1;
      }
    }
  }

}


