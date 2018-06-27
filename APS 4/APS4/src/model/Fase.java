package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Nave;
import view.ContainerJanelas;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Nave nave;
    private Timer timer;
    private boolean emJogo;
    private List<Inimigo> inimigos;
    private int velocidadeInimigos;
    private Registro registro1 = new Registro();
    private Registro registro2 = new Registro();
    private int contador = 0;
    private boolean is2Jogadores = false;
    private boolean isPausado = false;
    private boolean isSom = true;
    private Som som = new Som();

    private int[][] coordenadas = {{2380, 29}, {2600, 59}, {1380, 89},
    {780, 109}, {580, 139}, {880, 239}, {790, 259},
    {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
    {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
    {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
    {920, 300}, {856, 328}, {456, 320}};

    public Fase(Jogador jogador1, Jogador jogador2, int velocidadeInimigos, boolean is2Jogadores) {
        registro1.setJogador(jogador1);
        registro2.setJogador(jogador2);
        this.is2Jogadores = is2Jogadores;
        this.velocidadeInimigos = velocidadeInimigos;
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new Fase.TecladoAdpter());

        ImageIcon referencia = new ImageIcon("src\\imagem\\fundo.png");
        fundo = referencia.getImage();

        nave = new Nave();
        
        emJogo = true;

        inicializaInimigos();

        timer = new Timer(5, this);
        timer.start();   
        
    }

    // Inicializa todos os inimigos
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < coordenadas.length; i++) {
            inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1], this.velocidadeInimigos));
        }

    }

    public void paint(Graphics g) {
        if (isPausado == false) {
            Graphics2D graficos = (Graphics2D) g;
            graficos.drawImage(fundo, 0, 0, null); // Desenha o fundo

            // Testa pra ver se o jogo está em funcionamento
            if (emJogo) {

                graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this); // Desenha a nave, this é o observer, ela que ira chamar a classe mecher()

                List<Missel> misseis = nave.getMisseis();

                //Desenha todos os misseis da lista
                for (int i = 0; i < misseis.size(); i++) {
                    Missel m = (Missel) misseis.get(i);
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }

                //Desenha todos os inimigos da lista
                for (int i = 0; i < inimigos.size(); i++) {
                    Inimigo in = inimigos.get(i);
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }

                // Desenha uma mensagem de quantos inimigos ainda restam 
                graficos.setColor(Color.WHITE);
                graficos.drawString("JOGADOR: " + registro1.getJogador().getNome(), 5, 15);
                graficos.drawString("INIMIGOS RESTANTES: " + inimigos.size(), 310, 15);

            } else if (is2Jogadores) {
                ImageIcon fimJogo = new ImageIcon("src\\imagem\\sua_vez_terminou.jpg");
                graficos.drawImage(fimJogo.getImage(), 0, 0, null);

                // Testa se o jogador ganhou ou perdeu.Se matou 30 inimigos sem eles tocarem na nave então mostra a imagem de Venceu
                // Senão mostra a imagem de Perdeu
            } else if (registro1.getInimigosAbatidos() == 30) {
                isSom = false;
                ImageIcon fimJogo = new ImageIcon("src\\imagem\\voce_ganhou.jpg");
                graficos.drawImage(fimJogo.getImage(), 0, 0, null);

            } else {
                ImageIcon fimJogo = new ImageIcon("src\\imagem\\voce_perdeu.jpg");
                graficos.drawImage(fimJogo.getImage(), 0, 0, null);

            }

            g.dispose();// apaga o último desenho

        }
    }

    // Método das ações que sempre estarão sendo chamadas na aplicação
    @Override
    public void actionPerformed(ActionEvent arg0) {

        // Testa se Ganhou o jogo
        if (inimigos.size() == 0) {
            emJogo = false;
            isSom = false;
        }

        // Se o jogo não estiver pausado move os objetos
        if (isPausado == false) {

            List<Missel> misseis = nave.getMisseis();
            // Mover os mísseis
            for (int i = 0; i < misseis.size(); i++) {

                Missel m = (Missel) misseis.get(i);

                if (m.isIsVisivel()) {
                    m.mexer();
                } else {
                    misseis.remove(i);
                }
            }

            // Mover os inimigos
            for (int i = 0; i < inimigos.size(); i++) {

                Inimigo in = inimigos.get(i);

                if (in.isIsVisivel()) {
                    in.mexer();
                } else {
                    inimigos.remove(i);
                }
            }
            nave.mexer();
            checarColisoes();
            repaint();
        }
    }

    public void checarColisoes() {
        // um retangulo para cada elemento
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;

        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();

            // Testa se a nave intercepta um inimigo 
            if (formaNave.intersects(formaInimigo)) {
                
                som.somExplodirNave(isSom);                
                nave.setIsVisivel(false);
                tempInimigo.setIsVisivel(false);
                
                emJogo = false;
                isSom = false;
            }
        }

        // Testa 1 míssel para cada um dos inimigos
        List<Missel> misseis = nave.getMisseis();
        for (int i = 0; i < misseis.size(); i++) {
            // Pega um Missel
            Missel tempMissel = misseis.get(i);
            // Pega a forma desse Missel
            formaMissel = tempMissel.getBounds();

            for (int j = 0; j < inimigos.size(); j++) {
                // Pega um Inimigo
                Inimigo tempInimigo = inimigos.get(j);
                // Pega a forma desse Inimigo
                formaInimigo = tempInimigo.getBounds();

                // Testa se o Missel interceptou o inimigo
                if (formaMissel.intersects(formaInimigo)) {
                    contador++;
                    registro1.setInimigosAbatidos(contador);
                    som.somExplodirInimigo(isSom);
                    
                    tempInimigo.setIsVisivel(false);
                    tempMissel.setIsVisivel(false);
                }

            }

        }
    }

    private class TecladoAdpter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // Atira
            if (e.getKeyCode() == KeyEvent.VK_SPACE && emJogo == true && isPausado == false) {
            nave.atira(isSom);
            }
            
            // Pausa e despausa o Jogo
            if (e.getKeyCode() == KeyEvent.VK_P && isPausado == false) {
                isPausado = true;
                isSom = false;
            } else if (e.getKeyCode() == KeyEvent.VK_P && isPausado == true) {
                isPausado = false;
                isSom = true;
            }
            
            // Pausa e despausa o som
            if (e.getKeyCode() == KeyEvent.VK_S && isSom == true) {
                isSom = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S && isSom == false) {
                isSom = true;
            }

            // Reinicia jogo quando precionar enter(se o jgoo tiver terminado e não for de 2 jogadores)
            if (e.getKeyCode() == KeyEvent.VK_ENTER && emJogo == false && is2Jogadores == false) {
                isPausado = false;
                isSom = true;                
                emJogo = true;
                nave = new Nave();
                inicializaInimigos();
                registro1.setInimigosAbatidos(0);
                contador = 0;
            }
            
            // Chama a tela do Jogador 2
            if ((e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2)) {
                new ContainerJanelas(registro1, registro2, velocidadeInimigos);
                setVisible(false);
            }

            // Caso o usuário aperte R e não estiver em jogo mostrará os resultados da partida
            if (e.getKeyCode() == KeyEvent.VK_R && emJogo == false) {
                JOptionPane.showMessageDialog(null, "Jogador: " + registro1.getJogador().getNome() + "\n"
                        + "Inimigos Abatidos: " + registro1.getInimigosAbatidos());
            }

            nave.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.keyReleased(e);
        }
    }

}
