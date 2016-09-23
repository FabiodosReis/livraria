package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private final Connection conn;

    public LoginDao(Connection conn) {
        this.conn = conn;
    }

    public boolean isRegistrado(String user, String senha) {

        String sql = "SELECT * FROM login";

        boolean registrado = false;

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("senha");

                if (login.equals(user)
                        && password.equals(senha)) {

                    registrado = true;
                    break;
                }
            }

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao verficar tabela login:" + ex.getMessage());

        }

        return registrado;
    }
}
