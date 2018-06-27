package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private static final String DATABASE = "APS4";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASE;
    private static final String USR = "postgres";
    private static final String SENHA = "admin";

    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR, SENHA);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            return null;
        }
    }

    public static void desconectar(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
    }

}
