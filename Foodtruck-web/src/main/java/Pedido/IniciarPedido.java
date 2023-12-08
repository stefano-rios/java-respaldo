package Pedido;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.BebidaDAO;
import data.PlatoDAO;
import entities.Bebida;
import entities.LineaPedido;
import entities.Plato;

/**
 * Servlet implementation class IniciarPedido
 */
@WebServlet("/iniciarpedido")
public class IniciarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IniciarPedido() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		PlatoDAO pdao = new PlatoDAO();
		BebidaDAO bdao = new BebidaDAO();
		LinkedList<Plato> platos = pdao.getAll();
		LinkedList<Bebida> bebidas = bdao.getAll();
		request.setAttribute("listadoplatos", platos);
		request.setAttribute("listadobebidas", bebidas);
		request.getRequestDispatcher("WEB-INF/iniciarPedido.jsp").forward(request, response);
		}
		catch(Exception e) {
			
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
		String[] checkboxplato = request.getParameterValues("checkboxplato");
		String[] checkboxbebida = request.getParameterValues("checkboxbebida");
		LinkedList<LineaPedido> lineas = new LinkedList<LineaPedido>();

		PlatoDAO pdao = new PlatoDAO();
		BebidaDAO bdao = new BebidaDAO();

		if (checkboxplato != null) {
			for (String valor : checkboxplato) {

				Plato p = new Plato();
				p.setId(Integer.parseInt(valor));
				p = pdao.getPlato(p);
				LineaPedido lp = new LineaPedido();
				lp.setProducto(p);
				lp.setCantidad(1);
				lineas.add(lp);

			}
		}

		if (checkboxbebida != null) {
			for (String valor1 : checkboxbebida) {

				Bebida b = new Bebida();
				b.setId(Integer.parseInt(valor1));
				b = bdao.getBebida(b);
				LineaPedido lpedido = new LineaPedido();
				lpedido.setProducto(b);
				lpedido.setCantidad(1);
				lineas.add(lpedido);
			}
		}

		
		if(checkboxbebida == null && checkboxplato == null) {
			request.setAttribute("mensaje", "Debe seleccionar al menos un producto");
			request.setAttribute("servlet", "iniciarpedido");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("lineas", lineas);

		response.sendRedirect("altapedido");
		}
		catch(Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}

	}

}
