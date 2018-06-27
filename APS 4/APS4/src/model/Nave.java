package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Nave extends JFrame {

    private int x, y;
    private int dx, dy;
    private int altura, largura;
    private Image imagem;
    private List<Missel> misseis;
    private boolean isVisivel;
    Som som = new Som();


    public Nave() {

        ImageIcon referencia = new ImageIcon("src\\imagem\\nave.gif");
        imagem = referencia.getImage();
        
        // Pega os valores de altura e largura da nave
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
        

        misseis = new ArrayList<Missel>();

        // Posição em que a nave ficará quando for gerada
        this.x = 100;
        this.y = 100;
    }

    public void mexer() {
        x += dx; // 1 e 462
        y += dy; // 1 e 340

        // Condições para a nave não sair da tela
        if (this.x < 1) {
            x = 1;
        }

        if (this.x > 462) {
            x = 462;
        }

        if (this.y < 1) {
            y = 1;
        }

        if (this.y > 340) {
            y = 340;
        }

    }

    public List<Missel> getMisseis() {
        return misseis;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
    
    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    
    // toda vezz que aperta o Espaço add um objeto do tipo missel a Lista de Misseis
    public void atira(boolean isSom) {
        this.misseis.add(new Missel(x + largura, y + altura / 2));
        som.somAtirar(isSom);
       
    }
    
    // Nome padrão para tratamento em jogos (retorna um retangulo(Rectangle) com seu formtado e limites (getBounds))
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura, altura);
    }

    // Movimentação da nave
     public void keyPressed(KeyEvent tecla) {
        
        int codigo = tecla.getKeyCode();


        if (codigo == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 1;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    // Condições para quanda soltar a tecla de movimentação a nave nao continue a andar
    public void keyReleased(KeyEvent tecla) {
        
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
