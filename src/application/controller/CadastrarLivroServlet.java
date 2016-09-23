package application.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.model.Livro;
import application.process.LivroProcess;
import application.util.NegocioException;

@WebServlet(asyncSupported = true, urlPatterns = { "/cadastrarLivroServlet" })
public class CadastrarLivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CadastrarLivroServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			
			String json = request.getParameter("livro");		
		
			Livro livro = new Gson().fromJson(json, Livro.class);	
			
			new LivroProcess().persistir(livro);
				
		} catch (NegocioException e) {
				
			response.setContentType(e.getMensagem());
		}
			
		
	}

}
