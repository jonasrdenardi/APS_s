package view;

import controller.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Jogador;

public class Login2 extends javax.swing.JFrame {

    private Connection con;
    private PreparedStatement cmd;
    Jogador jogador2 = new Jogador();
    Jogador jogador1 = new Jogador();
    int velocidadeInimigos;
    boolean is2Jogadores = false;

    public Login2(Jogador jogador1, int velocidadeInimigos, boolean is2Jogadores) {
        this.jogador1 = jogador1;
        this.velocidadeInimigos = velocidadeInimigos;
        this.is2Jogadores = is2Jogadores;
        initComponents();
        setTitle("APS4");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJogador = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtJogador = new javax.swing.JTextField();
        pfSenha = new javax.swing.JPasswordField();
        btnCadastrar = new javax.swing.JButton();
        btnJogar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblJogador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblJogador.setText("Usuário:");

        lblSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSenha.setText("Senha:");

        txtJogador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJogadorActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnJogar1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnJogar1.setText("Jogar");
        btnJogar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJogar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSenha)
                            .addComponent(lblJogador))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJogador)
                            .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(120, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnJogar1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJogador)
                    .addComponent(txtJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSenha)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnJogar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtJogadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJogadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJogadorActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        new Cadastro();
        dispose();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnJogar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJogar1ActionPerformed

        // Testa se o usuario deixou algum campo nullo
        if (txtJogador.getText().isEmpty() || String.valueOf(pfSenha.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos !");
        } else {
            // Pega os valores dos campos se todos forão preenchidos
            jogador2.setNome(txtJogador.getText());
            jogador2.setSenha(String.valueOf(pfSenha.getPassword()));

            try {
                String sql = "SELECT id_jogador,nm_jogador, senha_jogador FROM tb_jogador WHERE nm_jogador = ?";
                con = Conexao.conectar();
                cmd = con.prepareStatement(sql);
                cmd.setString(1, jogador2.getNome());
                ResultSet rs = cmd.executeQuery();

                if (rs.next()) {
                    if (rs.getString("nm_jogador").equals(jogador2.getNome()) && rs.getString("senha_jogador").equals(jogador2.getSenha())) {
                        jogador2.setId(rs.getInt("id_jogador"));
                        JOptionPane.showMessageDialog(null, "Logado com sucesso");
                        JOptionPane.showMessageDialog(null, "Agora é a vez de " + jogador1.getNome() + " jogar");
                        new ContainerJanelas(jogador1,jogador2, velocidadeInimigos, this.is2Jogadores);                        
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha Incorretos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha Incorretos");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERRO SQL: " + e.getMessage());
            } finally {
                Conexao.desconectar(con);
            }
        }
    }//GEN-LAST:event_btnJogar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Jogador jogador = null;
                int velocidade = 0;
                boolean is2Jogadores = false;
                new Login2(jogador, velocidade, is2Jogadores).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnJogar1;
    private javax.swing.JLabel lblJogador;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JPasswordField pfSenha;
    private javax.swing.JTextField txtJogador;
    // End of variables declaration//GEN-END:variables
}
