package application.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.model.Editora;
import application.process.EditoraProcess;

@WebServlet("/listEditorasServlet")
public class ListEditorasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Editora> editoras = new EditoraProcess().listar();
		
		String json = new Gson().toJson(editoras);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
