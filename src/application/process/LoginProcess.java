package application.process;

import java.sql.Connection;
import java.sql.SQLException;

import application.dao.LoginDao;
import application.util.ConnectionFactory;
import application.util.NegocioException;


/**
 *
 * @author fabio
 */
public class LoginProcess {

    private final Connection conn = ConnectionFactory.getConnection();

    public void verificarLogin(String user, String senha) throws NegocioException, SQLException {

        if (user.equals("")) {
            throw new NegocioException("O campo login é obrigátorio");
        }

        if (senha.equals("")) {
            throw new NegocioException("O campo senha é obrigátorio");
        }
        
        if(!new LoginDao(conn).isRegistrado(user, senha)){
        	
        	throw new NegocioException("Usuário Não Cadastrado");        	
        }
        
        conn.close();             

    }
}
