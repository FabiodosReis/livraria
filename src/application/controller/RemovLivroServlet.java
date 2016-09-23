package application.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.process.LivroProcess;

@WebServlet(asyncSupported = true, urlPatterns = { "/RemovLivroServlet" })
public class RemovLivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RemovLivroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long codigo = Long.parseLong(request.getParameter("codigo"));
		
		new LivroProcess().excluir(codigo);;
	}

}
