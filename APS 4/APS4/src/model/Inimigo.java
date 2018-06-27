package model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Inimigo {

    private Image imagem;
    private int x, y;    
    private int altura, largura;
    private boolean isVisivel;

    private static final int LARGURA_TELA = 500;
    private int velocidade = 1;
    
    private static int contador = 0;

    public Inimigo(int x, int y, int velocidade) {
        this.velocidade = velocidade;
        
        // recebe as coordenadas do inimigo        
        this.x = x;
        this.y = y;
        
        ImageIcon referencia;
        // Condição ara aparecer mais inimigo_2 do que inimigo_1
        if(contador++ % 3 == 0){
            referencia = new ImageIcon("src\\imagem\\inimigo_2.gif");
        }else{
            referencia = new ImageIcon("src\\imagem\\inimigo_1.gif");
        }
        
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisivel = true;

    }

    public void mexer() {
        // Se o inimigo sair da esquerda da tela ele volta para o fim, senão vai andando da esquerda para a direita
        if(this.x < 0){
            this.x = LARGURA_TELA;
        }else{
            this.x -= velocidade;
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
