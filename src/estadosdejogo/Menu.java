package estadosdejogo;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Jogo;
import ui.MenuBotao;
import utilz.LoadSave;

public class Menu extends Estados implements MetodosDeEstado {

	private MenuBotao[] buttons = new MenuBotao[3];
	private BufferedImage backgroundImg,backgrondImgUfca;
	private int menuX, menuY, menuWidth, menuHeight;

	public Menu(Jogo jogo) {
		super(jogo);
		loadButtons();
		loadBackground();
		backgrondImgUfca = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_INICIAL);

	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int) (backgroundImg.getWidth() * Jogo.SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * Jogo.SCALE);
		menuX = Jogo.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (45 * Jogo.SCALE);

	}

	private void loadButtons() {
		buttons[0] = new MenuBotao(Jogo.GAME_WIDTH / 2, (int) (150 * Jogo.SCALE), 0, EstadosDeJogo.CENAJOGO);
		buttons[1] = new MenuBotao(Jogo.GAME_WIDTH / 2, (int) (220 * Jogo.SCALE), 1, EstadosDeJogo.OPTIONS);
		buttons[2] = new MenuBotao(Jogo.GAME_WIDTH / 2, (int) (290 * Jogo.SCALE), 2, EstadosDeJogo.QUIT);
	}

	@Override
	public void update() {
		for (MenuBotao mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgrondImgUfca, 0, 0, Jogo.GAME_WIDTH, Jogo.GAME_HEIGHT, null);
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
	
		

		for (MenuBotao mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuBotao mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuBotao mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}

		resetButtons();

	}

	private void resetButtons() {
		for (MenuBotao mb : buttons)
			mb.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuBotao mb : buttons)
			mb.setMouseOver(false);

		for (MenuBotao mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			EstadosDeJogo.estado = EstadosDeJogo.CENAJOGO;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}