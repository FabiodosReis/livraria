package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Editora;
import application.util.ConnectionFactory;


/**
 *
 * @author fabio
 */
public class EditoraDao{
	
	private Connection conn;
	
	public EditoraDao(Connection conn){
		this.conn = conn;
	}

    
    public void salvar(Editora editora) {
    	
    	String sql = "INSERT INTO editora(nome,cidade) "
    			+ "Values(?,?)";
    	
    	try {
    		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, editora.getNome());
			ps.setString(2, editora.getCidade());
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
       
    }

   
    public List<Editora> listar() {
    	
    	String sql = "SELECT * FROM editora";
    	
    	List<Editora> editoras = new ArrayList<>();
    	
    	try {
    		
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Editora editora = new Editora();
				editora.setCodigo(rs.getLong("codigo"));
				editora.setNome(rs.getString("nome"));
				editora.setCidade(rs.getString("cidade"));
				editoras.add(editora);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}   			
        
        return editoras;
        
    }

    
    public void excluir(Long id) {
    	
    	String sql = "DELETE FROM editora WHERE codigo = ?";
    	
    	try {
    		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
       
    }

   
    public void editar(Editora editora) {
    	
    	String sql = "UPDATE editora SET nome = ?, cidade = ? "
    			+ "WHERE codigo = ?";
    	
    	try {
    		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,editora.getNome());
			ps.setString(2, editora.getCidade());
			ps.setLong(3, editora.getCodigo());
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
        
    }   
    
}
