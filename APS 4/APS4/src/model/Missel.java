package model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Missel {

    private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisivel;

    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 2;

    public Missel(int x, int y) {
        // recebe as coordenadas da nave 
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("src\\imagem\\missel.png");
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisivel = true;

    }

    public void mexer() {
        this.x += VELOCIDADE;
        
        // Se o missel passar da tela ele some
        if (this.x > LARGURA_TELA) {
            isVisivel = false;
        }
    }

    public Image getImagem() {
        return imagem;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }
    
    // Nome padrão para tratamento em jogos (retorna um retangulo(Rectangle) com seu formtado e limites (getBounds))
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura, altura);
    }

}
