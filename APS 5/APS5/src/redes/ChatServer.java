package redes;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ChatServer {

    List<PrintWriter> escritores = new ArrayList<>();

    public ChatServer(int porta) {
        ServerSocket server;
        try {
            server = new ServerSocket(porta);

            JOptionPane.showMessageDialog(null, "Servidor Rodando");

            while (true) {
                Socket socket = server.accept();
                new Thread(new EscutaCliente(socket)).start();
                PrintWriter p = new PrintWriter(socket.getOutputStream());
                escritores.add(p);

            }
        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }

    }

    private void EncaminharParaTodos(String texto) {
        for (PrintWriter w : escritores) {
            try {
                w.println(texto);
                w.flush();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    private class EscutaCliente implements Runnable {

        Scanner leitor;

        public EscutaCliente(Socket socket) {
            try {
                leitor = new Scanner(socket.getInputStream());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        }

        @Override
        public void run() {
            try {
                String texto;
                while ((texto = leitor.nextLine()) != null) {
                    EncaminharParaTodos(texto);
                }
            } catch (Exception e){
            }
        }
    }
}
