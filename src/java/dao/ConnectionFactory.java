package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/uniacademia?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection conecta() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // autocommit é true por padrão, então não precisa setar
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Driver não encontrado", ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Erro ao conectar ao banco", ex);
            return null;
        }
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        Connection conn = conecta();
        if (conn != null) {
            return conn.prepareStatement(sql);
        } else {
            throw new SQLException("Não foi possível obter conexão com o banco.");
        }
    }
}
