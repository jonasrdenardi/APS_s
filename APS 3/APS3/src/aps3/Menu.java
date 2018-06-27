package aps3;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    Arquivo arquivo = new Arquivo();
    Ordenacao ordenar = new Ordenacao();
    Scanner teclado = new Scanner(System.in);
    int opcao, qtdElem = 0;

    // Menu Principal
    public void menu1() throws IOException {

        do {
            System.out.println("-----------------------------------------------");
            System.out.println("-  APS 3° SEMESTRE - ALGORITMOS DE ORDENAÇÃO  -");
            System.out.println("-----------------------------------------------");
            System.out.println("-         Jonas R. Denardi - C45588-1         -");
            System.out.println("-----------------------------------------------\n");            
            System.out.println("Escolha o que deseja ordenar: ");
            System.out.println("[1] - Números");
            System.out.println("[2] - Palavras");
            System.out.println("[0] - Sair");
            System.out.print("Opcao: ");
            opcao = teclado.nextInt();
            System.out.println();

            switch (opcao) {
                case 1: {
                    menu1_1();
                    break;
                }
                case 2: {
                    menu1_2();
                    break;
                }
            }
        } while (opcao != 0);
    }

    // Menu que escolhe opcao de entrada de dados Numéricos pelo teclado ou arquivo
    public void menu1_1() throws IOException {
        System.out.println("Como deseja inserir os dados ?");
        System.out.println("[1] - Digitar");
        System.out.println("[2] - Importar de arquivo");
        System.out.println("[0] - Sair");
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println();
        
        switch (opcao) {
            case 0: {
                System.exit(0);
                break;
            }
            case 1: {
                menu1_1_1();
                break;
            }
            case 2: {
                int vetorImport[] = arquivo.lerArquivoInt("arquivo_desordenadoInt.txt");
                menuOrdenar(vetorImport);
                break;
            }

        }
    }

    // Menu que escolhe opcao de entrada de dados String pelo teclado ou arquivo
    public void menu1_2() throws IOException {
        System.out.println("Como deseja inserir os dados ?");
        System.out.println("[1] - Digitar");
        System.out.println("[2] - Importar de arquivo");
        System.out.println("[0] - Sair");
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println();
        
        switch (opcao) {
            case 0: {
                System.exit(0);
                break;
            }
            case 1: {
                menu1_1_2();
                break;
            }
            case 2: {
                String vetorImport[] = arquivo.lerArquivoString("arquivo_desordenadoString.txt");
                menuOrdenar(vetorImport);
                break;
            }

        }
    }

    // Menu de entrada de dados Numércos pelo teclado
    public void menu1_1_1() throws IOException {
        int qtdElem = 0;
        System.out.println("Quantos numeros irá digitar ?");
        System.out.print("Quantidade: ");
        qtdElem = teclado.nextInt();
        int[] vetorDesord = new int[qtdElem];

        for (int i = 0; i < qtdElem; i++) {
            System.out.print("Digite o " + (i + 1) + "° numero: ");
            vetorDesord[i] = teclado.nextInt();
        }
        menuOrdenar(vetorDesord);
    }

    // Menu de entrada de dados String pelo tecladso
    public void menu1_1_2() throws IOException {
        int qtdElem = 0;
        System.out.println("Quantas palavras irá digitar ?");
        System.out.print("Quantidade: ");
        qtdElem = teclado.nextInt();
        String[] vetorDesord = new String[qtdElem];

        teclado.nextLine(); //limpa buffer do teclado
        for (int i = 0; i < qtdElem; i++) {
            System.out.print("Escreva a " + (i + 1) + "° palavra: ");
            vetorDesord[i] = teclado.nextLine();
        }
        menuOrdenar(vetorDesord);
    }

    // Menu para Ordenar dados do tipo Numérico    
    public void menuOrdenar(int[] vetor) throws IOException {
        int[] vetorOrd = new int[qtdElem];
        System.out.println("Escolha o método de ordenacao: ");
        System.out.println("[1] - Quick Sort");
        System.out.println("[2] - Insertion Sort");
        System.out.println("[3] - Bubble Sort");
        System.out.println("[0] - Sair");
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println();

        switch (opcao) {
            case 0: {
                System.exit(0);
                break;
            }
            case 1: {
                vetorOrd = ordenar.quickSort(vetor);
                break;
            }
            case 2: {
                vetorOrd = ordenar.insertionSort(vetor);
                break;
            }
            case 3: {
                vetorOrd = ordenar.bubbleSort(vetor);
                break;
            }
        }
        menu1_1_1_1(vetorOrd);
    }

    // Menu para Ordenar dados do tipo String
    public void menuOrdenar(String[] vetor) throws IOException {
        String[] vetorOrd = new String[qtdElem];
        System.out.println("Escolha o método de ordenacao: ");
        System.out.println("[1] - Quick Sort");
        System.out.println("[2] - Insertion Sort");
        System.out.println("[3] - Bubble Sort");
        System.out.println("[0] - Sair");
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println();

        switch (opcao) {
            case 0: {
                System.exit(0);
                break;
            }
            case 1: {
                vetorOrd = ordenar.quickSort(vetor);
                break;
            }
            case 2: {
                vetorOrd = ordenar.insertionSort(vetor);
                break;
            }
            case 3: {
                vetorOrd = ordenar.bubbleSort(vetor);
                break;
            }
        }
        menu1_1_1_2(vetorOrd);
    }

    // Menu de saída de dados do tipo Numérico    
    public void menu1_1_1_1(int[] vetor) throws IOException {
        System.out.println("Como deseja mostrar a ordenacao ?");
        System.out.println("[1] - Mostrar na tela");
        System.out.println("[2] - Gravar em Arquivo");
        System.out.println("[0] - Sair");
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println();

        switch (opcao) {
            case 0: {
                System.exit(0);
                break;
            }
            case 1: {
                for (int i = 0; i < vetor.length; i++) {
                    System.out.println(vetor[i]);
                }
                break;
            }
            case 2: {
                arquivo.gravarArquivo("arquivo_ordenadoInt.txt", vetor);
                break;
            }
        }
    }

    // Menu de saída de dados do tipo String
    public void menu1_1_1_2(String[] vetor) throws IOException {
        System.out.println("Como deseja mostrar a ordenacao ?");
        System.out.println("[1] - Mostrar na tela");
        System.out.println("[2] - Gravar em Arquivo");
        System.out.println("[0] - Sair");
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println();

        switch (opcao) {
            case 0: {
                System.exit(0);
                break;
            }
            case 1: {
                for (int i = 0; i < vetor.length; i++) {
                    System.out.println(vetor[i]);
                }
                break;
            }
            case 2: {
                arquivo.gravarArquivo("arquivo_ordenadoString.txt", vetor);
                break;
            }
        }
    }
}
