package aps3;

public class Ordenacao {

    long tempoinicial, tempofinal, tempototal = 0;

    public int[] insertionSort(int[] vetor) {
        int i, j, eleito;
        long tempoinicial = System.currentTimeMillis();

        for (i = 1; i < vetor.length; i++) {

            eleito = vetor[i];

            j = i;

            while ((j > 0) && (vetor[j - 1] > eleito)) {
                vetor[j] = vetor[j - 1];
                j = j - 1;
            }
            vetor[j] = eleito;
        }
        long tempofinal = System.currentTimeMillis();
        long tempototal = tempofinal - tempoinicial;
        System.out.println("Tempo de Processamento de InsertionSort: " + tempototal + "ms");

        return vetor;
    }

    public String[] insertionSort(String[] vetor) {
        int i, j;
        String eleito;
        long tempoinicial = System.currentTimeMillis();

        for (i = 1; i < vetor.length; i++) {

            eleito = vetor[i];

            j = i;

            while ((j > 0) && (vetor[j - 1].compareToIgnoreCase(eleito) > 0)) {//vetor[j - 1] > eleito
                vetor[j] = vetor[j - 1];
                j = j - 1;
            }
            vetor[j] = eleito;
        }
        long tempofinal = System.currentTimeMillis();
        long tempototal = tempofinal - tempoinicial;
        System.out.println("Tempo de Processamento de InsertionSort: " + tempototal + "ms");
        return vetor;
    }

    public int[] bubbleSort(int[] vetor) {
        long tempoinicial = System.currentTimeMillis();

        for (int i = vetor.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }

        long tempofinal = System.currentTimeMillis();
        long tempototal = tempofinal - tempoinicial;
        System.out.println("Tempo de Processamento de Bubble Sort: " + tempototal + "ms");
        return vetor;
    }

    public String[] bubbleSort(String[] vetor) {
        long tempoinicial = System.currentTimeMillis();

        for (int i = vetor.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if ((vetor[j].compareToIgnoreCase(vetor[j + 1])) >= 1) {
                    String aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }

        long tempofinal = System.currentTimeMillis();
        long tempototal = tempofinal - tempoinicial;
        System.out.println("Tempo de Processamento de Bubble Sort: " + tempototal + "ms");
        return vetor;
    }

    public int[] quickSort(int[] vetor) {
        long tempoinicial = System.currentTimeMillis();

        ordenar(vetor, 0, vetor.length - 1);

        long tempofinal = System.currentTimeMillis();
        long tempototal = tempofinal - tempoinicial;
        System.out.println("Tempo de Processamento de Quick Sort: " + tempototal + "ms");
        return vetor;
    }

    public String[] quickSort(String[] vetor) {
        long tempoinicial = System.currentTimeMillis();
        
        ordenar(vetor, 0, vetor.length - 1);

        long tempofinal = System.currentTimeMillis();
        long tempototal = tempofinal - tempoinicial;
        System.out.println("Tempo de Processamento de Quick Sort: " + tempototal + "ms");
        return vetor;
    }

    // Métodos utilizados pelo Quick Sort Int
    private static void ordenar(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            ordenar(vetor, inicio, posicaoPivo - 1);
            ordenar(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo) {
                i++;
            } else if (pivo < vetor[f]) {
                f--;
            } else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

    // Métodos utilizados pelo Quick Sort String
    private static void ordenar(String[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            ordenar(vetor, inicio, posicaoPivo - 1);
            ordenar(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(String[] vetor, int inicio, int fim) {
        String pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i].compareToIgnoreCase(pivo) <= 0) { //vetor[i] <= pivo
                i++;
            } else if (pivo.compareToIgnoreCase(vetor[f]) < 0) { //pivo < vetor[f]
                f--;
            } else {
                String troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }
}
