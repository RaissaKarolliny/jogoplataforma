package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage [] idleAni;
    private int aniTick, aniIndex, aniSpeed = 120; 


    public GamePanel() {
    	    mouseInputs = new MouseInputs(this);
    	    importImg();  
    	    loadAnimations();
    	    setPanelSize();
    	    addKeyListener(new KeyboardInputs(this));
    	    addMouseListener(mouseInputs);
    	    addMouseMotionListener(mouseInputs);

    }


    private void loadAnimations() {
		idleAni = new BufferedImage[3];
		for (int i = 0 ; i < idleAni.length; i++)
			idleAni[i] = img.getSubimage( i*31,2, 32, 32);
	}
	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= idleAni.length)
				aniIndex = 0 ;
		}
	
		
	}


	private void importImg() {
        InputStream is = getClass().getResourceAsStream("SpriteFi.png"); 

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	try {
        		is.close();        	
        	}catch (IOException e) {
        		e.printStackTrace();
        	}
        }
        
    }


    private void setPanelSize() {
        Dimension size = new Dimension(1080,640);
        setPreferredSize(size);
    }


    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }
    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        g.drawImage(idleAni[aniIndex],(int) xDelta, (int) yDelta, 120 , 120, null);
    }


    
}