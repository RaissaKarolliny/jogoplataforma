package estadosjogos;
public enum Jogostate {

  PLAYING, MENU, OPTIONS, QUIT;

  public static Jogostate state = MENU;

}
/*
A classe Jogostate é uma enumeração (enum) que define os possíveis estados do jogo. Cada estado é 
representado por um valor na enumeração, e esses valores são PLAYING, MENU, OPTIONS e QUIT. Aqui está uma breve explicação do que essa enumeração faz:
PLAYING: Representa o estado em que o jogador está jogando o jogo.
MENU: Representa o estado do menu principal do jogo.
OPTIONS: Representa o estado em que o jogador está nas opções do jogo.
QUIT: Representa o estado em que o jogador deseja sair do jogo.*/