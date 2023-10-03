package entities;

import static utilz.Constantes.ConstantesDoJogador.CORRENDO;
import static utilz.Constantes.ConstantesDoJogador.ATAQUE_1 ;
import static utilz.Constantes.ConstantesDoJogador.ObterQuantidadeDeSprites;
import static utilz.Constantes.ConstantesDoJogador.PARADO;
import static utilz.Constantes.Direcoes.BAIXO;
import static utilz.Constantes.Direcoes.CIMA;
import static utilz.Constantes.Direcoes.DIREITA;
import static utilz.Constantes.Direcoes.ESQUERDA;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{
	
	

	private BufferedImage[][] animacoes;
    private int aniTick, aniIndex, aniVelocidade = 25;
    private int acaoDoJogador = PARADO;
    private boolean esquerda,cima,direita,baixo;
    private boolean movimentando = false, atacando = false;
    private float playerSpeed= 1.0f;
    
	public Player(float x, float y) {
		super(x, y);
		carregarAnimacoes();

	}
	
	public void update() {
		 atualizarPosicao();	
		 atualizarContagemDeAnimacao();
	     definirAnimacao();
	     	
	}
	public void render(Graphics g) {
		 g.drawImage(animacoes[acaoDoJogador][aniIndex], (int) x, (int) y, 256, 160, null);
	}

	private void atualizarContagemDeAnimacao() {
		
	        aniTick++;
	        if (aniTick >= aniVelocidade) {
	            aniTick = 0;
	            aniIndex++;
	            if (aniIndex >= ObterQuantidadeDeSprites(acaoDoJogador)) {
	            	aniIndex = 0;
	            	atacando = false;
	            }
	                
	        }
	    }

	    private void definirAnimacao() {
	    	
	    	int startAni = acaoDoJogador;
	    	
	        if (movimentando)
	            acaoDoJogador = CORRENDO;
	        else
	            acaoDoJogador = PARADO;
	        
	        if(atacando)
	        	acaoDoJogador = ATAQUE_1;
	        if(startAni != acaoDoJogador)
	        	resertAniTick();
	    }

	    private void resertAniTick() {
			aniTick=0;
			aniIndex=0;
			
		}

		private void atualizarPosicao() {
	    	
	    	movimentando= false;
	    	
	    	if(esquerda && !direita) {
	    		x-=playerSpeed;
	    		movimentando= true;
	    	}else if (direita && !esquerda){
	    		x+=playerSpeed;
	    		movimentando= true;
	    	}
	    	
	    	if(cima && !baixo) {
	    		y-=playerSpeed;
	    		movimentando= true;
	    	}else if (baixo && !cima){
	    		y+=playerSpeed;
	    		movimentando= true;
	    	}
	    }
	
	 private void carregarAnimacoes() {
		 InputStream is = getClass().getResourceAsStream("img_jogador.png");
	        try {
	        	BufferedImage img = ImageIO.read(is);
	        	
	            animacoes = new BufferedImage[5][4];
		        for (int j = 0; j < animacoes.length; j++)
		            for (int i = 0; i < animacoes[j].length; i++)
		                animacoes[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
		        
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	           }
              }	       
            }
    public void resetDirBooleans() {
    	esquerda = false;
    	direita = false;
    	cima = false;
    	baixo = false;
    }
    
    public void setAtaque(boolean atacando) {
    	this.atacando = atacando;
    }
	public boolean isEsquerda() {
		return esquerda;
	}

	public void setEsquerda(boolean esquerda) {
		this.esquerda = esquerda;
	}

	public boolean isCima() {
		return cima;
	}

	public void setCima(boolean cima) {
		this.cima = cima;
	}

	public boolean isDireita() {
		return direita;
	}

	public void setDireita(boolean direita) {
		this.direita = direita;
	}

	public boolean isBaixo() {
		return baixo;
	}

	public void setBaixo(boolean baixo) {
		this.baixo = baixo;
	}
	
}
