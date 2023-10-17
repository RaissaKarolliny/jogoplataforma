package entidades;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estadosjogos.Jogando;
import levels.Niveis;
import utilz.CarregarSave;
import static utilz.Constantes.InimigoConstantes.*;

public class GerenciadoInimigo {

  private Jogando jogando;
  private BufferedImage[][] RoboArr;
  private ArrayList<Robo> robos = new ArrayList<>();

  public GerenciadoInimigo(Jogando jogando) {
    this.jogando = jogando;
    carregarInimigoImgs();
  }

  public void carregarInimigos(Niveis niveis) {
    robos = niveis.getRobos();
  }

  public void atualizar(int[][] lvlData, Jogador jogador) {
    boolean AlgumAtivo = false;
    for (Robo c : robos)
      if (c.isActive()) {
        c.atualizar(lvlData, jogador);
        AlgumAtivo = true;
      }
    if(!AlgumAtivo)
      jogando.setLevelCompleto(true);
  }

  public void draw(Graphics g, int xLvlOffset) {
    drawRobos(g, xLvlOffset);
  }

  private void drawRobos(Graphics g, int xLvlOffset) {
    for (Robo c : robos)
      if (c.isActive()) {
        g.drawImage(RoboArr[c.getInimigoEstado()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - Robo_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - Robo_DRAWOFFSET_Y,
            Robo_LARGURA * c.flipW(), Robo_ALTURA, null);
//				c.drawHitbox(g, xLvlOffset);
//				c.desenharCaixaAtaque(g, xLvlOffset);

      }

  }

  public void checkInimigoHit(Rectangle2D.Float attackBox) {
    for (Robo c : robos)
      if (c.isActive())
        if (attackBox.intersects(c.getHitbox())) {
          c.ferir(10);
          return;
        }
  }

  private void carregarInimigoImgs() {
    RoboArr = new BufferedImage[5][9];
    BufferedImage temp = CarregarSave.GetSpriteAtlas(CarregarSave.Robo_SPRITE);
    for (int j = 0; j < RoboArr.length; j++)
      for (int i = 0; i < RoboArr[j].length; i++)
        RoboArr[j][i] = temp.getSubimage(i * Robo_LARGURA_DEFAULT, j * Robo_ALTURA_DEFAULT, Robo_LARGURA_DEFAULT, Robo_ALTURA_DEFAULT);
  }

  public void resetAllEnemies() {
    for (Robo c : robos)
      c.resetInimigo();
  }

}