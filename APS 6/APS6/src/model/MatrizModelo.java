package model;

public class MatrizModelo {

    private int posicao;
    private char nome;

    public int[][] matriz = new int[30][30];

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public char getNome() {
        return nome;
    }

    public void setNome(char nome) {
        this.nome = nome;
    }

}
