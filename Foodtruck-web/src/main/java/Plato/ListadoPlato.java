package Plato;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PlatoDAO;
import entities.Plato;

/**
 * Servlet implementation class AltaPlato
 */
@WebServlet("/listadoplato")
public class ListadoPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoPlato() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			PlatoDAO listadoPlato = new PlatoDAO();
			LinkedList<Plato> platos = listadoPlato.getAll();
			request.setAttribute("listadoPlato", platos);
			request.getRequestDispatcher("WEB-INF/listadoPlatos.jsp").forward(request, response);

		} catch (Exception e) {

			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "menubebidaplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
