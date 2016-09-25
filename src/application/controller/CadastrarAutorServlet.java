package application.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.model.Autor;
import application.process.AutorProcess;
import application.util.NegocioException;

@WebServlet("/cadastrarAutorServlet")
public class CadastrarAutorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//converte a data para padrão americano
			Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			String json = request.getParameter("autor");			
	
			Autor autor = gSon.fromJson(json, Autor.class);		
			
			new AutorProcess().persistir(autor);
			
		} catch (NegocioException e) {
			
			System.out.println(e.getMensagem());
		}
	}

}
