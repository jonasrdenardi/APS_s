package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import model.Fase;
import model.Fase2;
import model.Jogador;
import model.Registro;

public class ContainerJanelas extends JFrame {
    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    boolean is2Jogadores = false;
    int velocidadeInimigos;
    Registro registro1 = new Registro();
    Registro registro2 = new Registro();
    
    public ContainerJanelas(Jogador jogador1, Jogador jogador2, int velocidadeInimigos, boolean is2Jogadores) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.is2Jogadores = is2Jogadores;
        this.velocidadeInimigos = velocidadeInimigos;
        // Adiciona a barra de menus, seus itens e ações
        JMenuBar barraMenu = new JMenuBar();        
        JMenu menu = new JMenu("Menu");       
        
        JMenuItem sobre  = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APS4 - JONAS R. DENARDI","Informações", JOptionPane.INFORMATION_MESSAGE);
            }
        });
                
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(sair);
        
        barraMenu.add(menu);        
        setJMenuBar(barraMenu);
        
        add(new Fase(jogador1, jogador2, velocidadeInimigos, this.is2Jogadores));
        setTitle("APS");
        setSize(500,420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

public ContainerJanelas(Registro registro1, Registro registro2, int velocidadeInimigos) {
        this.registro1 = registro1;
        this.registro2 = registro2;
        this.velocidadeInimigos = velocidadeInimigos;
        // Adiciona a barra de menus, seus itens e ações
        JMenuBar barraMenu = new JMenuBar();        
        JMenu menu = new JMenu("Menu");       
        
        JMenuItem sobre  = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APS4 - JONAS R. DENARDI","Informações", JOptionPane.INFORMATION_MESSAGE);
            }
        });
                
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(sair);
        
        barraMenu.add(menu);        
        setJMenuBar(barraMenu);
        add(new Fase2(registro1, registro2, velocidadeInimigos));
        setTitle("APS");
        setSize(500,420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
    }    
}