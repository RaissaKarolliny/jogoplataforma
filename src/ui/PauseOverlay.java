package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import estadosjogos.Jogostate;
import estadosjogos.Jogando;
import main.Jogo;
import utilz.CarregarSave;
import static utilz.Constantes.UI.PauseButtons.*;
import static utilz.Constantes.UI.URMButtons.*;
import static utilz.Constantes.UI.VolumeButtons.*;

public class PauseOverlay {

  private Jogando jogando;
  private BufferedImage backgroundImg;
  private int bgX, bgY, bgW, bgH;
  private SoundButton musicButton, sfxButton;
  private UrmButton menuB, replayB, unpauseB;
  private VolumeButton volumeButton;

  public PauseOverlay(Jogando jogando) {
    this.jogando = jogando;
    loadBackground();

    createSoundButtons();
    createUrmButtons();
    createVolumeButton();
  }

  private void createVolumeButton() {
    int vX = (int) (309 * Jogo.ESCALA);
    int vY = (int) (278 * Jogo.ESCALA);
    volumeButton = new VolumeButton(vX, vY, SLIDER_LARGURA, VOLUME_ALTURA);
  }

  private void createUrmButtons() {
    int menuX = (int) (313 * Jogo.ESCALA);
    int replayX = (int) (387 * Jogo.ESCALA);
    int unpauseX = (int) (462 * Jogo.ESCALA);
    int bY = (int) (325 * Jogo.ESCALA);

    menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
    replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
    unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
  }

  private void createSoundButtons() {
    int soundX = (int) (450 * Jogo.ESCALA);
    int musicY = (int) (140 * Jogo.ESCALA);
    int sfxY = (int) (186 * Jogo.ESCALA);
    musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
    sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
  }

  private void loadBackground() {
    backgroundImg = CarregarSave.GetSpriteAtlas(CarregarSave.PAUSE_BACKGROUND);
    bgW = (int) (backgroundImg.getWidth() * Jogo.ESCALA);
    bgH = (int) (backgroundImg.getHeight() * Jogo.ESCALA);
    bgX = Jogo.Jogo_LARGURA / 2 - bgW / 2;
    bgY = (int) (25 * Jogo.ESCALA);
  }

  public void atualizar() {
    musicButton.atualizar();
    sfxButton.atualizar();

    menuB.atualizar();
    replayB.atualizar();
    unpauseB.atualizar();

    volumeButton.atualizar();
  }

  public void draw(Graphics g) {
    // Background
    g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);


    musicButton.draw(g);
    sfxButton.draw(g);


    menuB.draw(g);
    replayB.draw(g);
    unpauseB.draw(g);

    volumeButton.draw(g);
  }

  public void mouseDragged(MouseEvent e) {
    if (volumeButton.isMousePressed()) {
      volumeButton.changeX(e.getX());
    }
  }

  public void mousePressed(MouseEvent e) {
    if (isIn(e, musicButton))
      musicButton.setMousePressed(true);
    else if (isIn(e, sfxButton))
      sfxButton.setMousePressed(true);
    else if (isIn(e, menuB))
      menuB.setMousePressed(true);
    else if (isIn(e, replayB))
      replayB.setMousePressed(true);
    else if (isIn(e, unpauseB))
      unpauseB.setMousePressed(true);
    else if (isIn(e, volumeButton))
      volumeButton.setMousePressed(true);
  }

  public void mouseReleased(MouseEvent e) {
    if (isIn(e, musicButton)) {
      if (musicButton.isMousePressed())
        musicButton.setMuted(!musicButton.isMuted());

    } else if (isIn(e, sfxButton)) {
      if (sfxButton.isMousePressed())
        sfxButton.setMuted(!sfxButton.isMuted());
    } else if (isIn(e, menuB)) {
      if (menuB.isMousePressed()) {
        Jogostate.state = Jogostate.MENU;
        jogando.unpauseJogo();
      }
    } else if (isIn(e, replayB)) {
      if (replayB.isMousePressed()) {
        jogando.resetAll();
        jogando.unpauseJogo();
      }
    } else if (isIn(e, unpauseB)) {
      if (unpauseB.isMousePressed())
        jogando.unpauseJogo();
    }

    musicButton.resetBools();
    sfxButton.resetBools();
    menuB.resetBools();
    replayB.resetBools();
    unpauseB.resetBools();
    volumeButton.resetBools();
  }

  public void mouseMoved(MouseEvent e) {
    musicButton.setMouseOver(false);
    sfxButton.setMouseOver(false);
    menuB.setMouseOver(false);
    replayB.setMouseOver(false);
    unpauseB.setMouseOver(false);
    volumeButton.setMouseOver(false);

    if (isIn(e, musicButton))
      musicButton.setMouseOver(true);
    else if (isIn(e, sfxButton))
      sfxButton.setMouseOver(true);
    else if (isIn(e, menuB))
      menuB.setMouseOver(true);
    else if (isIn(e, replayB))
      replayB.setMouseOver(true);
    else if (isIn(e, unpauseB))
      unpauseB.setMouseOver(true);
    else if (isIn(e, volumeButton))
      volumeButton.setMouseOver(true);
  }

  private boolean isIn(MouseEvent e, PauseButton b) {
    return b.getBounds().contains(e.getX(), e.getY());
  }

}
