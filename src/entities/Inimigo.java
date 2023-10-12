package entities;


import static utilz.Constantes.ConstantesDosInimigos.*;
import static utilz.MetodosAjuda.*;
import static utilz.Constantes.Direcoes.*;


import main.Jogo;
public abstract class Inimigo extends Entity{
	private int aniIndex, estadoDoInimigo, tipoDoInimigo;
	private int anitick,aniSpeed = 25;
	private boolean firstUpdate = true;
	private boolean inAir;
	private float fallSpeed;
	private float gravity = 0.04f * Jogo.SCALE;
	private float walkSpeed = 0.35f * Jogo.SCALE;
	private int walkDir = ESQUERDA;
	
	
	
	

	public Inimigo(float x, float y, int width, int height,int tipoDoInimigo) {
		super(x, y, width, height);
		this.tipoDoInimigo = tipoDoInimigo;
		initHitbox(x,y,width,height);
	}

	
	private void atualizarAnimationtick() {
		anitick++;
		if (anitick>= aniSpeed) {
			anitick = 0;
			aniIndex++;
			if (aniIndex>= ObterQuantidadeDeSprites(estadoDoInimigo,tipoDoInimigo)) {
				aniIndex= 0;
			}
		}
	}


	public void update(int[][]lvlData) {
		updateMove(lvlData);
		atualizarAnimationtick();
		
	}
	private void updateMove(int[][] lvlData) {
		if (firstUpdate) {
			if (!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;
			firstUpdate = false;
		}
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += fallSpeed;
				fallSpeed += gravity;
			} else {
				inAir = false;
				hitbox.y = GetEntityYPosUnderRoofOrAboverFloor(hitbox, fallSpeed);
			}
		} else {
			switch (estadoDoInimigo) {
			case PARADO:
				estadoDoInimigo = CORRENDO;
				break;
			case CORRENDO:
				float xSpeed = 0;

				if (walkDir == ESQUERDA)
					xSpeed = -walkSpeed;
				else
					xSpeed = walkSpeed;

				if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
					if (IsFloor(hitbox, xSpeed, lvlData)) {
						hitbox.x += xSpeed;
						return;
					}

				changeWalkDir();

				break;
			}
		}

	}
	private void changeWalkDir() {
		if (walkDir == ESQUERDA)
			walkDir = DIREITA;
		else
			walkDir = ESQUERDA;
	}
	public int getaniIndex() {
		return aniIndex;
	}
	
	public int getestadoDoInimigo(){
		return estadoDoInimigo;
		
	}
}
