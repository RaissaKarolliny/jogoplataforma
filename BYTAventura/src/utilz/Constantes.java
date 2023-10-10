package utilz;

public class Constantes {
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
        public static final int CAIR = 9;
        public static final int SOLO = 4;
        public static final int ATACAR = 5;
        public static final int ATAQUE_1 = 3;
        public static final int ATAQUE_PULAR_1 = 7;
        public static final int ATAQUE_PULAR_2 = 8;

        public static int ObterQuantidadeDeSprites(int acaoDoJogador) {
            switch (acaoDoJogador) {
                case CORRENDO:
                    return 4;
                case PARADO:
                    return 4;
                case ATACAR:
                    return 4;
                case PULAR:
                	return 4;
                case ATAQUE_1:
                case ATAQUE_PULAR_1:
                case ATAQUE_PULAR_2:
                    return 4;
                case SOLO:
                    return 2;
                case CAIR:
                	return 4;
                default:
                    return 1;
            }
        }
    }
}