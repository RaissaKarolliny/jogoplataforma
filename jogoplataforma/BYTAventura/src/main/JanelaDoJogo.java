package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class JanelaDoJogo {
    private JFrame jframe;

    public JanelaDoJogo(PainelDoJogo painelDoJogo) {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(painelDoJogo);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);
        jframe.addWindowFocusListener(new WindowFocusListener() {
        	@Override
        	public void windowLostFocus(WindowEvent e) {
        		PainelDoJogo.getJogo().windowFocusLost();
        	}
        	@Override
            public void windowGainedFocus(WindowEvent e) {
        	
        	}
        });
    }
}