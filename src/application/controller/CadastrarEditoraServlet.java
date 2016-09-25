package application.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.model.Editora;
import application.process.EditoraProcess;
import application.util.NegocioException;

@WebServlet("/cadastrarEditoraServlet")
public class CadastrarEditoraServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {		
			
			String obj = request.getParameter("editora");
			
			Editora editora = new Gson().fromJson(obj, Editora.class);			
			
			new EditoraProcess().persistir(editora);
			
		} catch (NegocioException e) {
			
			e.printStackTrace();
		}
	}

}
