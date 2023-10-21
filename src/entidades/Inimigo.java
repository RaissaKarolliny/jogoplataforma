package entidades;

import static utilz.Constantes.InimigoConstantes.*;
import static utilz.MetodosAjuda.*;

import java.awt.geom.Rectangle2D;

import static utilz.Constantes.Direcoes.*;

import main.Jogo;

public abstract class Inimigo extends Entidades {
	//A classe Inimigo é declarada como uma classe abstrata que estende a classe Entidades.
  protected int aniIndex, InimigoEstado, InimigoTipo;
  protected int aniTick, aniVelocidade = 25;
  protected boolean primeiroatualizar = true;
  protected boolean noAr;
  protected float velocidadeQueda;
  protected float gravidade = 0.04f * Jogo.ESCALA;
  protected float velocidadeAndar = 0.35f * Jogo.ESCALA;
  protected int andaDir = ESQUERDA;
  protected int tileY;
  protected float distanciaAtaque = Jogo.TILES_SIZE;
  protected int vidaMax;
  protected int vidaAtual;
  protected boolean ativado = true;
  protected boolean ataqueChecado;
//Essas variáveis de instância representam características comuns dos inimigos,
//como sua animação, estado, tipo, física (como gravidade e velocidade de queda), direção, vida, entre outros.

  public Inimigo(float x, float y, int LARGURA, int ALTURA, int InimigoTipo) {
    super(x, y, LARGURA, ALTURA);
    this.InimigoTipo = InimigoTipo;
    initHitbox(x, y, LARGURA, ALTURA);
    vidaMax = GetVidaMax(InimigoTipo);
    vidaAtual = vidaMax;
  }
//Este é o construtor da classe Inimigo. Ele inicializa a posição, tamanho, tipo e a "hitbox" do inimigo, bem como sua vida.
  protected void primeiroatualizarCheck(int[][] lvlData) {
    if (!EntidadesNoChao(hitbox, lvlData))
      noAr = true;
    primeiroatualizar = false;
  }
//O método primeiroatualizarCheck é usado para verificar se o inimigo está inicialmente no ar ou no chão. Ele atualiza a variável noAr de acordo.

  protected void atualizarNoAr(int[][] lvlData) {
    if (PodeMover(hitbox.x, hitbox.y + velocidadeQueda, hitbox.width, hitbox.height, lvlData)) {
      hitbox.y += velocidadeQueda;
      velocidadeQueda += gravidade;
    } else {
      noAr = false;
      hitbox.y = RetorneEntidadesYEntreTelhadoChao(hitbox, velocidadeQueda);
      tileY = (int) (hitbox.y / Jogo.TILES_SIZE);
    }
  }
 //O método atualizarNoAr lida com o movimento do inimigo no ar, aplicando a gravidade e verificando se o inimigo colide com obstáculos no nível.

  protected void mover(int[][] lvlData) {
    float velocidadeX = 0;

    if (andaDir == ESQUERDA)
      velocidadeX = -velocidadeAndar;
    else
      velocidadeX = velocidadeAndar;

    if (PodeMover(hitbox.x + velocidadeX, hitbox.y, hitbox.width, hitbox.height, lvlData))
      if (SeChao(hitbox, velocidadeX, lvlData)) {
        hitbox.x += velocidadeX;
        return;
      }

    mudarDirecao();
  }
//O método mover é responsável pelo movimento do inimigo no chão.
//Ele verifica se o inimigo pode se mover na direção especificada e muda a direção se necessário.
  protected void virarParaJogador(Jogador jogador) {
    if (jogador.hitbox.x > hitbox.x)
      andaDir = DIREITA;
    else
      andaDir = ESQUERDA;
  }
//O método virarParaJogador faz com que o inimigo vire na direção do jogador, com base na posição do jogador em relação ao inimigo.
  protected boolean podeVerJogador(int[][] lvlData, Jogador jogador) {
    int jogadorTileY = (int) (jogador.getHitbox().y / Jogo.TILES_SIZE);
    if (jogadorTileY == tileY)
      if (jogadorNoAlcance(jogador)) {
        if (visaoLivre(lvlData, hitbox, jogador.hitbox, tileY))
          return true;
      }

    return false;
  }
//O método podeVerJogador verifica se o inimigo tem linha de visão direta para o jogador.
  protected boolean jogadorNoAlcance(Jogador jogador) {
    int valorAbs = (int) Math.abs(jogador.hitbox.x - hitbox.x);
    return valorAbs <= distanciaAtaque * 5;
  }
//O método jogadorNoAlcance verifica se o jogador está dentro do alcance de ataque do inimigo.
  protected boolean JogadorPertoAtaca(Jogador jogador) {
    int valorAbs = (int) Math.abs(jogador.hitbox.x - hitbox.x);
    return valorAbs <= distanciaAtaque;
  }
//O método JogadorPertoAtaca verifica se o jogador está próximo o suficiente para atacar.
  protected void novoEstado(int InimigoEstado) {
    this.InimigoEstado = InimigoEstado;
    aniTick = 0;
    aniIndex = 0;
  }
  //O método novoEstado muda o estado do inimigo, reiniciando sua animação.

  public void ferir(int amount) {
    vidaAtual -= amount;
    if (vidaAtual <= 0)
      novoEstado(MORTO);
    else
      novoEstado(BATE);
  }
//O método ferir é usado para causar dano ao inimigo,
  //reduzindo sua vida. Se a vida chegar a zero, o estado do inimigo é alterado para "MORTO".
  protected void checarDanoJogador(Rectangle2D.Float attackBox, Jogador jogador) {
    if (attackBox.intersects(jogador.hitbox))
      jogador.mudarVida(-GetDanoInimigo(InimigoTipo));
    ataqueChecado = true;

  }
//O método checarDanoJogador verifica se a área de ataque do inimigo colide com o jogador e causa dano a ele.
  protected void atualizarAnimacaoTick() {
    aniTick++;
    if (aniTick >= aniVelocidade) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= GetQuantidadeSprite(InimigoTipo, InimigoEstado)) {
        aniIndex = 0;

        switch (InimigoEstado) {
        case ATAQUE, BATE -> InimigoEstado = PARADO;
        case MORTO -> ativado = false;
        }
      }
    }
  }
//O método atualizarAnimacaoTick lida com a animação do inimigo, atualizando seu índice de animação e verificando se a animação terminou
  protected void mudarDirecao() {
    if (andaDir == ESQUERDA)
      andaDir = DIREITA;
    else
      andaDir = ESQUERDA;
  }
//O método mudarDirecao inverte a direção do inimigo (esquerda para direita e vice-versa).
  public void resetInimigo() {
    hitbox.x = x;
    hitbox.y = y;
    primeiroatualizar = true;
    vidaAtual = vidaMax;
    novoEstado(PARADO);
    ativado = true;
    velocidadeQueda = 0;
  }
//O método resetInimigo é usado para redefinir as características do inimigo, como posição, vida e estado.
  public int getAniIndex() {
    return aniIndex;
  }

  public int getInimigoEstado() {
    return InimigoEstado;
  }

  public boolean isActive() {
    return ativado;
  }
//Esses 3 ultimos métodos fornecem informações sobre o estado do inimigo, seu índice de animação e se ele está ativo.
}