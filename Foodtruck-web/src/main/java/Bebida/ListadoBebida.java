package Bebida;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import data.BebidaDAO;

import entities.Bebida;


/**
 * Servlet implementation class AltaBebida
 */
@WebServlet("/listadobebida")
@MultipartConfig
public class ListadoBebida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoBebida() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try {
    		BebidaDAO listadoBebida = new BebidaDAO();
    		LinkedList<Bebida> bebidas = listadoBebida.getAll();
    		request.setAttribute("listadoBebida", bebidas);
    		request.getRequestDispatcher("WEB-INF/listadoBebida.jsp").forward(request, response);
    	}
    	catch(Exception e) {
    		request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "menubebidaplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
    	}
		
		
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    

}
