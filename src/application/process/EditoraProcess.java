package application.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import application.dao.EditoraDao;
import application.model.Editora;
import application.util.ConnectionFactory;
import application.util.NegocioException;

public class EditoraProcess {
	
	private Connection conn = ConnectionFactory.getConnection();

	public List<Editora> listar(){
		
		return new EditoraDao(conn).listar();
	}
	
	
	public void persistir(Editora editora) throws NegocioException{
		
		try{
		
			if(editora.getNome().equals("") || editora.getNome() == null){
				
				throw new NegocioException("O Nome da editora é Obrigátorio");
			}
			
			if(editora.getCidade().equals("") || editora.getCidade() == null){
				
				throw new NegocioException("O Campo cidade é Obrigátorio");
			}		
			
			if(editora.getCodigo() != null && editora.getCodigo() != 0){
				
				new EditoraDao(conn).editar(editora);
				
			}else{
				
				new EditoraDao(conn).salvar(editora);			
				
			}		
			
			conn.close();
			
		}catch(SQLException e){
			
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void excluir(Long codigo){
		
		try {
			
			new EditoraDao(conn).excluir(codigo);
		
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		
	}


	public Editora get(Long codigo) {
		
		return new EditoraDao(conn).get(codigo);
	}	

}
