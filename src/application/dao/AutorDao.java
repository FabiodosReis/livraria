package application.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Autor;

public class AutorDao {

    private Connection conn;

    public AutorDao(Connection conn) {
    	
        this.conn = conn;
        
    }

    public void salvar(Autor autor) {

        String sql = "INSERT INTO autor(nome,email,dataNascimento) "
                + "VALUES(?,?,?)";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getEmail());
            ps.setDate(3, (Date)autor.getDataNascimento());

            ps.execute();

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao acessar a tabela autor: "
                    + ex.getLocalizedMessage());
        }

    }

    public List<Autor> listar() {

        String sql = "SELECT * FROM autor WHERE ativo = true";

        List<Autor> autores = new ArrayList<Autor>();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Autor autor = new Autor();
                autor.setCodigo(rs.getLong("codigo"));
                autor.setNome(rs.getString("nome"));
                autor.setEmail(rs.getString("email"));
                autor.setDataNascimento(rs.getDate("dataNascimento"));

                autores.add(autor);

            }

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao conectar na tabela autor:" + ex.getMessage());
        }

        return autores;

    }
    
    public void editar(Autor autor) {
        
        String sql = "UPDATE autor SET nome = ?, email = ?,"
                 + "dataNascimento = ? WHERE codigo = ?";   
        
        try {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getEmail());
            ps.setDate(3, (Date)autor.getDataNascimento());
            ps.setLong(4, autor.getCodigo());
                        
            ps.execute();       
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao conectar na tabela autor:" + ex.getMessage());
            
        }

    }
    
    public Autor buscarPeloCodigo(Long codigo){
    	
    	String sql = "SELECT * FROM "
    			+ "autor "
    			+ "WHERE codigo = ? AND ativo = true";
    	
    	Autor autor = new Autor();
    	
    	try {    		
    		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, codigo);
			
			ps.execute();
			
		} catch (SQLException e) {
			
			throw new RuntimeException("Erro ao conectar na tabela autor:" + e.getMessage());
			
		}
    	
    	return autor;    	
    	
    }
    
    public void excluir(Autor autor){
    	
    	String Sql = "UPDATE autor SET ativo = false WHERE codigo = ?";
    	
    	try {
    		
			PreparedStatement ps = conn.prepareStatement(Sql);
			ps.setLong(1, autor.getCodigo());
			
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

}
