package application.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabio
 */
public class ConnectionFactory {
    

    public static Connection getConnection() {
        
        Connection conn = null;

        String rost = "jdbc:mysql://127.0.0.1:3306/livraria";
        String user = "root";
        String senha = "Fa14151415";

        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(rost, user, senha);           
           
        } catch (SQLException ex) {

            System.err.println("Erro ao conectar na base de dados" + ex.getMessage());

            throw new RuntimeException(ex.getMessage());
            
        }catch (ClassNotFoundException e) {
			
        	System.out.println(e.getMessage());
		}   
        
        return conn;        
    }
}
