package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estadosdejogo.CenaJogo;
import utilz.LoadSave;
import static utilz.Constantes.ConstantesDosInimigos.*;




public class GerenciadorDeInimigos {

	private BufferedImage [][] inimigo_Um_Arr;
	public CenaJogo cenajogo;
	private ArrayList<Inimigo1> inimigos1 = new ArrayList <>();
	
	
	
	
	public GerenciadorDeInimigos(CenaJogo cenajogo) {
		this.cenajogo = cenajogo;
		carregarImagensInimigos();
		addInimigos();
	}
	
	private void addInimigos() {
		inimigos1 = LoadSave.GetInimigos1();
		System.out.println("qtd dos inimigos1 ->"+ inimigos1.size());
	}

	public void update(int [][]lvlData) {
		for (Inimigo1 i : inimigos1)
			i.update(lvlData);
		
	}
	public void draw(Graphics g,int xLvloffset) {
		drawInimigos1(g, xLvloffset);
		
	}
	private void drawInimigos1(Graphics g,int xLvloffset) {
		for(Inimigo1 i: inimigos1)
			g.drawImage(inimigo_Um_Arr[i.getestadoDoInimigo()][i.getaniIndex()], (int)i.getHitbox().x - xLvloffset,(int)i.getHitbox().y,INIMIGO_UM_WIDTH ,INIMIGO_UM_HEIGHT, null);
	}
	private void carregarImagensInimigos() {
		inimigo_Um_Arr = new BufferedImage[5][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CABECA_PC);
		for(int j = 0; j < inimigo_Um_Arr.length; j++)
			for (int i = 0; i < inimigo_Um_Arr[j].length; i++ )
				inimigo_Um_Arr[j][i] = temp.getSubimage(i*INIMIGO_UM_WIDTH_DEFAULT,j*INIMIGO_UM_HEIGHT_DEFAULT,INIMIGO_UM_WIDTH_DEFAULT,INIMIGO_UM_HEIGHT_DEFAULT);
	}
}
