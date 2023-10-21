package estadosjogos;

import java.awt.event.MouseEvent;

import main.Jogo;
import ui.MenuButton;

public class Estado {
//A classe Estado é declarada e parece ser uma classe base para diferentes estados do jogo.
  protected Jogo Jogo;

  public Estado(Jogo Jogo) {
    this.Jogo = Jogo;
  }
//A classe tem uma variável de instância chamada Jogo, que é uma referência à instância da classe principal do jogo. 
//O construtor Estado recebe uma instância do jogo e a atribui à variável Jogo. 
//Isso permite que os estados acessem a funcionalidade do jogo principal.
  public boolean isIn(MouseEvent e, MenuButton mb) {
    return mb.getBounds().contains(e.getX(), e.getY());
  }
//O método isIn parece ser usado para verificar se um evento de mouse (representado pelo objeto MouseEvent e) 
//ocorreu dentro da área delimitada por um botão de menu (MenuButton), que é passado como parâmetro. 
//Ele usa o método contains do retângulo delimitado pelo botão para verificar se as coordenadas do evento de mouse (e.getX() e e.getY())
//estão dentro desse retângulo. Retorna true se o evento ocorreu dentro do botão e false caso contrário.



  public Jogo getJogo() {
    return Jogo;
  } 
//O método getJogo permite que os estados obtenham uma referência à instância do jogo principal.
//Isso pode ser útil para acessar variáveis ou métodos do jogo a partir dos estados.
}

