package application.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.model.Autor;
import application.process.AutorProcess;

@WebServlet(asyncSupported = true, urlPatterns = { "/listAutoresServlet" })
public class ListAutoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
      public ListAutoresServlet() {
        super();
       
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			List<Autor> autores = new AutorProcess().listar();
			
			Gson gSon = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
			
			String json = gSon.toJson(autores);
			
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write(json);
	}	

}
