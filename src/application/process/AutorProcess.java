package application.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import application.dao.AutorDao;
import application.model.Autor;
import application.util.ConnectionFactory;
import application.util.NegocioException;
import application.util.Validation;

public class AutorProcess {
	
	private Connection conn = ConnectionFactory.getConnection();
	
	private AutorDao autorDao = new AutorDao(conn);
	
	public List<Autor> listar(){
		
		return this.autorDao.listar();
	}

	public void persistir(Autor autor) throws NegocioException{
		
		try{
		
			if(autor.getNome().equals("") || autor.getNome() == null){
				
				throw new NegocioException("O Campo nome é Obrigátorio");
			}
			
			if(autor.getEmail().equals("") || autor.getEmail() == null){
				
				throw new NegocioException("O Campo Email é Obrigátorio");
			}
			
			if(!Validation.email(autor.getEmail())){
				
				throw new NegocioException("Email Inválido");
			}
			
			if(autor.getDataNascimento() == null){
				
				throw new NegocioException("O campo Data de Nascimento é Obrigátorio");
			}
			
			if(autor.getCodigo() == null || autor.getCodigo() == 0){
				
				new AutorDao(conn).salvar(autor);		
				
			}else{
				
				new AutorDao(conn).editar(autor);
			}	
			
			
			conn.close();		
		
		}catch(SQLException e){
			
			throw new RuntimeException(e.getMessage());			
			
		}		
	}
	
	public void excluir(Long codigo){
		
		try {
		
			new AutorDao(conn).excluir(codigo);		
		
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public Autor get(Long id){
		
		return new AutorDao(conn).buscarPeloCodigo(id);
	}
	
	
	

}
