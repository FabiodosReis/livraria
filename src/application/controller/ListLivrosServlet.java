package application.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.model.Livro;
import application.process.LivroProcess;

@WebServlet(asyncSupported = true, urlPatterns = { "/ListLivrosServlet"})
public class ListLivrosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ListLivrosServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Livro> livros = new LivroProcess().listar();
		
		String json = new Gson().toJson(livros);
		
		
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
		
	}

}
