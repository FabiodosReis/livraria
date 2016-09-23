package application.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import application.dao.LivroDao;
import application.model.Livro;
import application.util.ConnectionFactory;
import application.util.NegocioException;

public class LivroProcess {
    
    private Connection conn = ConnectionFactory.getConnection();
    
    private LivroDao livroDao = new LivroDao(conn);
    
    
    public List<Livro> listar(){
    	
    	return livroDao.listar();             
       
    }
    
    public Livro get(Long codigo){
    	
    	return new LivroDao(conn).get(codigo);
    	
    	
    }
    
    public void persistir(Livro livro) throws NegocioException{
    	
    	try{   		
    	
    	
	    	if(livro.getNome().equals("") || livro.getNome() == null){
	    		
	    		throw new NegocioException("O campo Livro é obrigátorio");
	    		
	    	}
	    	
	    	if(livro.getDescricao().equals("") || livro.getDescricao() == null){
	    		
	    		throw new NegocioException("O campo Descrição é obrigátorio");
	    		
	    	}
	    	
	    	if(livro.getIsbn().equals("") || livro.getIsbn() == null){
	    		
	    		throw new NegocioException("O campo ISBN é obrigátorio");
	    		
	    	}
	    	
	    	if(livro.getValor() == null){
	    		
	    		throw new NegocioException("O campo Valor é obrigátorio");
	    		
	    	}
	    	
	    	if(livro.getAutor().getCodigo() == null){
	    		
	    		throw new NegocioException("O campo Autor é obrigátorio");    		
	    	}
	    	
	    	if(livro.getCodigo() == null || livro.getCodigo() == 0){
	    		
	    		livroDao.salvar(livro);   		
	    		
	    	}else{
	    		
	    		livroDao.editar(livro);
	    	}    	 	
	    
			conn.close();	
			
    	}catch(SQLException ex){
    		
    		System.out.println(ex.getMessage());
    	}
    	
    }
    
        

	public void excluir(Long livroCodigo){
		
		
		
		try {
			
			new LivroDao(conn).excluir(livroCodigo);
			
			conn.close();
			
		} catch (SQLException e) {
			
			throw new RuntimeException("Error: " + e.getMessage());
		}
		
		
	}
    
}
