package estadosDeJogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Jogo;

public class Menu extends Estados implements MetodosDeEstado {

	public Menu(Jogo jogo) {
		super(jogo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Menu", Jogo.GAME_WIDTH/2, 200);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			EstadosDeJogo.estado = EstadosDeJogo.CENAJOGO;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
