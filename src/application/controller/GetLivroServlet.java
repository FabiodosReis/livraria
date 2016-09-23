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

@WebServlet("/getLivroServlet")
public class GetLivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public GetLivroServlet() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long codigo = Long.parseLong(request.getParameter("codigo"));
		
		Livro livro = new LivroProcess().get(codigo);
		
		String json = new Gson().toJson(livro);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}	

}
