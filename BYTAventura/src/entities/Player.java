package entities;
import static utilz.MetodosAjuda.*;
import static utilz.Constantes.ConstantesDoJogador.ObterQuantidadeDeSprites;
import static utilz.Constantes.ConstantesDoJogador.*;
import static utilz.Constantes.Direcoes.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Jogo;
import utilz.LoadSave;
public class Player extends Entity{
	
	

	private BufferedImage[][] animacoes;
    private int aniTick, aniIndex, aniVelocidade = 25;
    private int acaoDoJogador = PARADO;
    private boolean esquerda,cima,direita,baixo,pular;
    private boolean movimentando = false, atacando = false;
    private float playerSpeed= 1.0f;
    private int[][] lvlData;
    private float xDrawOffset = 50* Jogo.SCALE;
	private float yDrawOffset = 15* Jogo.SCALE;
	
	//gravidade//
	private float airSpeed = 0f;
	private float gravity = 0.04f * Jogo.SCALE;
	private float jumpSpeed = -2.25f * Jogo.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Jogo.SCALE;
	private boolean inAir = false;


    
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		carregarAnimacoes();
		//largura, altura
		initHitbox(x,y, 30* Jogo.SCALE, 45* Jogo.SCALE);
		

	}
	
	public void update() {
		 atualizarPosicao();
		 atualizarContagemDeAnimacao();
	     definirAnimacao();
	     	
	}
	public void render(Graphics g) {
		g.drawImage(animacoes[acaoDoJogador][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset ), width , height, null);
		 drawHitbox(g);
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
	        if(inAir) {
	        	if(airSpeed < 0)
	        	acaoDoJogador = PULAR;
	        	else
	        		acaoDoJogador = CAIR ;
	        }
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
	    	if(pular)
	    		pular();
	    	if(!esquerda && !direita && !inAir)
	    		return;
	    	float xSpeed = 0;
	    	
	    	if(esquerda) 
	    		xSpeed -= playerSpeed;
	        if (direita)
	    		xSpeed += playerSpeed;
	        if(!inAir) 
	        	if(!IsEntityOnFloor(hitbox, lvlData)) {
	        		inAir = true;
	        	
	        }
	    		
	    	if(inAir) {
	    		if(CanMoveHere(hitbox.x ,hitbox.y + airSpeed ,hitbox.width,hitbox.height, lvlData)) {
	    	    	hitbox.y += airSpeed;
	    	    	airSpeed += gravity;
	    	    	updateXPos(xSpeed);
	    	     }else {
	    	    	 hitbox.y = GetEntityYPosUnderRoofOrAboverFloor(hitbox,airSpeed);
	    	    	 if(airSpeed > 0 )
	    	    		 resetInAir();
	    	    	 else
	    	    		 airSpeed = fallSpeedAfterCollision;
	    	    	  updateXPos(xSpeed);
	    	     }
	    		
	    	} else
	    	   updateXPos(xSpeed);
	          movimentando = true;
	    } 	
	    private void pular() {
			if(inAir)
			 return;
			inAir = true;
			airSpeed = jumpSpeed;
		}

		private void resetInAir() {
			inAir= false;
			airSpeed = 0;
			
   }
	 private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed,hitbox.y,hitbox.width,hitbox.height, lvlData)) {
	    	hitbox.x += xSpeed;
		}else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
			
		}

	private void carregarAnimacoes() {
		 		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
	      
	            animacoes = new BufferedImage[5][4];
		        for (int j = 0; j < animacoes.length; j++)
		            for (int i = 0; i < animacoes[j].length; i++)
		                animacoes[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
		        
            }
    public void resetDirBooleans() {
    	esquerda = false;
    	direita = false;
    	cima = false;
    	baixo = false;
    }
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	    if(!IsEntityOnFloor(hitbox, lvlData))
	    	inAir = true;
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
	public void setPular(boolean pular) {
		this.pular = pular;
	}
	
}