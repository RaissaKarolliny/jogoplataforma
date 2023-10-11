package estadosDeJogo;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Jogo;
import utilz.Constantes;

public class CenaJogo extends Estados implements MetodosDeEstado {
	private Player player;
	private LevelManager levelManager;
    public CenaJogo(Jogo jogo) {
		super(jogo);
		initClasses();
	}

	
	
	private void initClasses() {
    	levelManager = new LevelManager(jogo);
    	player = new Player(200, 200, (int) (128 * Jogo.SCALE), (int) (80 * Jogo.SCALE));
    	player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		
	}
	 public void windowFocusLost() {
	    	player.resetDirBooleans();
	    }
	    
	    public Player getPlayer() {
	    	return player;
	    }



		@Override
		public void update() {
			levelManager.update();
			player.update();
			
		}



		@Override
		public void draw(Graphics g) {
			levelManager.draw(g);
			player.render(g);
		}



		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1)
	    		player.setAtaque(true); 
			
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
	             //SE PRESSIONAR (MENOS) O ESTADO DE JOGO VAI PARA O MENU
	         case KeyEvent.VK_BACK_SPACE:
	        	 EstadosDeJogo.estado = EstadosDeJogo.MENU;
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
}
