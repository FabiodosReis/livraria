package application.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.process.AutorProcess;

@WebServlet(asyncSupported = true, urlPatterns = { "/removAutorServlet" })
public class RemovAutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemovAutorServlet() {
        super();
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long codigo = Long.parseLong(request.getParameter("codigo"));
		
		new AutorProcess().excluir(codigo);
	}

}
