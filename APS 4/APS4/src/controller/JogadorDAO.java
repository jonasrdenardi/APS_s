package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Jogador;

public class JogadorDAO {

    private Connection con;
    private PreparedStatement cmd;

    public int inserir(Jogador jogador) {
        try {
            String sql = "INSERT INTO tb_jogador(nm_jogador,senha_jogador)"
                    + "VALUES"
                    + "(?,?);";
            con = Conexao.conectar();
            cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, jogador.getNome());
            cmd.setString(2, jogador.getSenha());

            if (cmd.executeUpdate() > 0) {
                ResultSet rs = cmd.getGeneratedKeys();
                return 1;
            }
            return -1;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            return -1;

        } finally {
            Conexao.desconectar(con);
        }

    }
}
