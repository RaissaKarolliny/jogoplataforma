package estadosdejogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entities.GerenciadorDeInimigos;
import entities.Player;
import levels.LevelManager;
import main.Jogo;
import ui.PauseOverlay;
import utilz.LoadSave;

public class CenaJogo extends Estados implements MetodosDeEstado {
	private Player player;
	private LevelManager levelManager;
	private GerenciadorDeInimigos gerenciadorInimigos;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	private int xLvloffset;
	private int  bordaEsquerda = (int) (0.2 * Jogo.GAME_WIDTH);
	private int  bordaDireita = (int) (0.8 * Jogo.GAME_WIDTH);
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	private int maxTilesoffset = lvlTilesWide - Jogo.TILES_IN_WIDTH; 
	private int maxLvloffsetX = maxTilesoffset *Jogo.TILES_SIZE;
	
	private BufferedImage backgroundImg;
	
	
	
	public CenaJogo(Jogo jogo) {
		super(jogo);
		initClasses();
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
	}

	private void initClasses() {
		levelManager = new LevelManager(jogo);
		gerenciadorInimigos = new GerenciadorDeInimigos (this);
		player = new Player(200, 200, (int) (128  * Jogo.SCALE), (int) (80  * Jogo.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			gerenciadorInimigos.update(levelManager.getCurrentLevel().getLevelData());
			chegarPassouBorda();
		} else {
			pauseOverlay.update();
		}
	}

	private void chegarPassouBorda() {
		int playerX = (int) player.getHitbox().x;
		int dif = playerX - xLvloffset;
		
		if (dif > bordaDireita) 
			xLvloffset += dif - bordaDireita;
		else if(dif < bordaEsquerda) 
			xLvloffset += dif - bordaEsquerda;
		if (xLvloffset> maxLvloffsetX)
			xLvloffset = maxLvloffsetX;
		else if (xLvloffset<0)
			xLvloffset = 0;
		
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg,0,0,jogo.GAME_WIDTH,Jogo.GAME_HEIGHT,null);
		
		levelManager.draw(g, xLvloffset);
		player.render(g, xLvloffset);
		gerenciadorInimigos.draw(g, xLvloffset);

		if (paused) {
			g.setColor(new Color(0,0,0,125));
			g.fillRect(0, 0, Jogo.GAME_WIDTH, Jogo.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAtaque(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setEsquerda(true);
			break;
		case KeyEvent.VK_D:
			player.setDireita(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setPular(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setEsquerda(false);
			break;
		case KeyEvent.VK_D:
			player.setDireita(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setPular(false);
			break;
		}

	}

	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);

	}

	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

}