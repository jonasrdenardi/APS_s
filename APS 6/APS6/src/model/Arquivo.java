package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

    public ArrayList<MatrizModelo> lerArquivo(String endereco) throws FileNotFoundException, IOException {
        File arquivo = new File(endereco);
        String dados = null;
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        // variável de retono que recebe as matrizes de modelo
        ArrayList<MatrizModelo> al = new ArrayList<>();
        // variavel que seta a posicao das matrizes modelo dentro do arraylist
        int posicao = 0;

        // le cada linha do arquivo
        while (br.ready()) {
            // variavel contadora do vetor
            int count = 0;

            MatrizModelo mm = new MatrizModelo();

            // limpa a variavel dados para nao concatenar com os dados antigos
            dados = null;
            dados = br.readLine();

            //divide os dados em vetor
            String[] vetor = dados.split(";");

            //caso o contador for zero seta a letra correspondente da matriz modelo de treinamento
            if (count == 0) {
                mm.setNome(vetor[count].charAt(0));
                count++;
            }

            // passa os valores do vetor na matriz
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    mm.matriz[i][j] = Integer.parseInt(vetor[count]);
                    count++;
                }
            }

            // seta a posicao da matriz modelo dentro do arraylist
            mm.setPosicao(posicao);
            al.add(mm);
            posicao++;
        }

        br.close();
        fr.close();

        return al;

    }

    public void gravarArquivo(String endereco, MatrizModelo mm) throws IOException {

        File arquivo = new File(endereco);

        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(endereco, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // escreve primeiro a letra correspondente
            bw.write(mm.getNome() + ";");
            // depois escreve o valor de cada posicao da matriz
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    bw.write(mm.matriz[i][j] + ";");
                }
            }
            // pula de linha
            bw.newLine();

            bw.close();
            fw.close();

        } catch (IOException ex) {
        }
    }
}
