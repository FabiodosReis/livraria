package application.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.model.Autor;
import application.process.AutorProcess;

@WebServlet("/getAutorServlet")
public class GetAutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long codigo = Long.parseLong(request.getParameter("codigo"));
		
		Autor autor = new AutorProcess().get(codigo);
		
		String json = new Gson().toJson(autor);
		
		response.setCharacterEncoding("UTF-8");	
		
		response.getWriter().write(json);
	}

}
