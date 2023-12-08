package Bebida;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.BebidaDAO;
import entities.Bebida;

/**
 * Servlet implementation class DeleteBebida
 */
@WebServlet("/deletebebida")
public class DeleteBebida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBebida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Bebida be = new Bebida();
			be.setId(id);
			BebidaDAO bdao = new BebidaDAO();
			
			bdao.delteBebida(be);
			LinkedList<Bebida> bebidas = bdao.getAll();
			request.setAttribute("listadoBebida", bebidas);
			
			request.getRequestDispatcher("WEB-INF/listadoBebida.jsp").forward(request, response);
			
		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("mensaje", "No se puede eliminar una bebida con un pedido asociado.");
			request.setAttribute("servlet", "listadobebida");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadobebida");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}