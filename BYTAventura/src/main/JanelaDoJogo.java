package main;

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
    }
}