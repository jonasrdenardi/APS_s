package model;

public class Perceptron {
    private int posicao;

    private char nome;
    // pesos sinápticos
    public double[][] peso = new double[30][30];

    // declara o BIAS
    private double pesoBias = 0;

    // variável responsável pelo somatório(rede).
    private double NET = 0;

    // variavél responsável pelo número máximo de épocas
    private final int epocasMax = 500;

    // variável responsável pela contagem das épocas durante o treinamento
    private int countEpocas = 0;

    // variável com o valor da taxa de aprendizado
    private double txApredizado = 0.8;

    public double getPesoBias() {
        return pesoBias;
    }

    public void setPesoBias(double pesoBias) {
        this.pesoBias = pesoBias;
    }

    // MÉTODO DE RETORNO DO CONTADOR
    public int getCountEpocas() {

        return this.countEpocas;

    }
    
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

    // Método responsável pelo somatório e a função de ativação.
    public int executar(int[][] matrizExecucao) {
        //NET = 0;
        // Somatório (NET)        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                NET += matrizExecucao[i][j] * peso[i][j];
            }
        }

        // Somatório (NET) + peso bias
        NET += pesoBias;

        // Função de Ativação
        if (NET > 0) {
            return 1;
        }
        return 0;
    }
    
    // Método para a correção de pesos
    public void corrigirPeso(int saida, int saidaEsperada, int[][] matrizAprendizado) {

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                peso[i][j] = peso[i][j] + txApredizado * matrizAprendizado[i][j] * (saidaEsperada - saida);
            }            
        }
        
        pesoBias = pesoBias + txApredizado * 1 * (saidaEsperada - saida);
    }
}
