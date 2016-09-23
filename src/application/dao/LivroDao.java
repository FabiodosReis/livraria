package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Autor;
import application.model.Livro;



/**
 *
 * @author fabio
 */
public class LivroDao {

    private Connection conn;   

    public LivroDao(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Livro livro) {
        
        String sql = "INSERT INTO livro(nome,descricao,valor,isbn,autorFk) "
                + "Values(?,?,?,?,?)";   
        
        try {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, livro.getNome());
            ps.setString(2, livro.getDescricao());
            ps.setDouble(3, livro.getValor());
            ps.setString(4, livro.getIsbn());
            ps.setLong(5, livro.getAutor().getCodigo());
            
            ps.execute();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao conectar na tabela livro:" + ex.getMessage());
            
        }

    }

    public List<Livro> listar() {

        String sql = "SELECT "
                    + "li.codigo as codLivroPk,"
                    + "au.codigo as codAutorPk,"
                    + "au.nome as autor,"
                    + "li.nome as livro,"
                    + "li.descricao,"
                    + "li.valor,"
                    + "li.isbn "                    
                    + "FROM autor AS au "
                    + "INNER JOIN livro as li "
                    + "WHERE au.codigo = li.autorFk;";

        List<Livro> livros = new ArrayList<Livro>();
        
        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Livro livro = new Livro();
                Autor autor = new Autor();
                
                livro.setCodigo(rs.getLong("codLivroPk"));
                livro.setNome(rs.getString("livro"));
                livro.setDescricao(rs.getString("descricao"));
                livro.setValor(rs.getDouble("valor"));
                livro.setIsbn(rs.getString("isbn"));
                
                autor.setCodigo(rs.getLong("codAutorPk"));
                autor.setNome(rs.getString("autor"));
                livro.setAutor(autor);                              

                livros.add(livro);

            }

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao conectar na tabela livro:" + ex.getMessage());
        }

        return livros;
    }

    public void excluir(Long codigo){
        
         String sql = "DELETE FROM livro WHERE codigo = ?";   
        
        try {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);     
            
            ps.execute();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Error ao excluir o livro");
            
        }

    }

    public void editar(Livro livro) {    
        
         String sql = "UPDATE livro SET nome = ?, descricao = ?,"
                 + "valor = ?, isbn = ?, autorFk = ? WHERE codigo = ?";   
        
        try {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, livro.getNome());
            ps.setString(2, livro.getDescricao());
            ps.setDouble(3, livro.getValor());
            ps.setString(4, livro.getIsbn());
            ps.setLong(5, livro.getAutor().getCodigo());
            ps.setLong(6, livro.getCodigo());
            
            
            ps.execute();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao conectar na tabela livro:" + ex.getMessage());
            
        }

    }     
   
	public Livro get(Long codigo) {
		
		String sql = "SELECT * FROM livro WHERE codigo = ?";
		
		Livro livro = new Livro();
		Autor autor = new Autor();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, codigo);
			
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setValor(rs.getDouble("valor"));
				livro.setIsbn(rs.getString("isbn"));
				autor.setCodigo(rs.getLong("autorFk"));
				livro.setAutor(autor);	
				
			}		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return livro;
		
	}
    

}
