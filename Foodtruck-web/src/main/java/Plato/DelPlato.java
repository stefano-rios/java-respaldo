package Plato;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import data.PlatoDAO;
import entities.Plato;

/**
 * Servlet implementation class DelPlato
 */
@WebServlet("/deleteplato")
public class DelPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelPlato() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Plato platoBorrar = new Plato();
			platoBorrar.setId(id);
			PlatoDAO platoDAO = new PlatoDAO();
			platoDAO.delPlato(platoBorrar);

			response.sendRedirect("listadoplato");

		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("mensaje", "No se puede eliminar un plato con un pedido asociado.");
			request.setAttribute("servlet", "listadoplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadoplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}