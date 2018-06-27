package aps3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class Arquivo {

    public String[] lerArquivoString(String endereco) throws FileNotFoundException, IOException {
        File arquivo = new File(endereco);
        String dados = null;
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            dados = br.readLine();
        }
        
        String caracteres = " #,@_\\/.*;"; // seleciona quais delemitadores serão usados no split
        String[] vetor = dados.split("[" + Pattern.quote(caracteres) + "]+");

        br.close();
        fr.close();

        return vetor;

    }
    
        public int[] lerArquivoInt(String endereco) throws FileNotFoundException, IOException {
        File arquivo = new File(endereco);
        String dados = null;
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            dados = br.readLine();
        }

        String[] vetor = dados.split(";");
        int vetorInt[] = new int[vetor.length];
        
        for(int i = 0; i < vetor.length; i++){
            vetorInt[i] = Integer.parseInt(vetor[i]);
        }
        

        br.close();
        fr.close();

        return vetorInt;

    }
    
    public void gravarArquivo(String endereco, int[] vetor) throws IOException {

        File arquivo = new File(endereco);

        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(endereco);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i <= vetor.length - 1; i++) {
                bw.write(vetor[i] + ";");
                //bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException ex) {
        }
    }
    
        public void gravarArquivo(String endereco, String[] vetor) throws IOException {

        File arquivo = new File(endereco);

        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(endereco);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i <= vetor.length - 1; i++) {
                bw.write(vetor[i] + ";");
                //bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException ex) {
        }
    }
    
}
