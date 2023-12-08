package Pedido;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.ClienteDAO;
import data.PedidoDAO;
import entities.Cliente;
import entities.Empleado;
import entities.LineaPedido;
import entities.Pedido;

/**
 * Servlet implementation class PedidoDeliveri
 */
@WebServlet("/altapedidodelivery")
public class AltaPedidoDelivery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AltaPedidoDelivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.getRequestDispatcher("WEB-INF/altaPedidoDelivery.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();

			String nombre = request.getParameter("nombre");
			String dni = request.getParameter("dni");
			String direccion = request.getParameter("direccion");

			PedidoDAO pdao = new PedidoDAO();
			ClienteDAO cdao = new ClienteDAO();
			Cliente c = new Cliente();
			Cliente busquedaC = new Cliente();

			c.setDni(dni);
			c.setDireccion(direccion);
			c.setNombre(nombre);

			busquedaC = cdao.getCliente(c);

			Pedido pedido = (Pedido) session.getAttribute("pedido");

			if (busquedaC != null) {

				pedido.setCliente(c);
				cdao.updateCliente(c);
				pdao.newPedido(pedido);

			} else {
				cdao.newCliente(c);
				pedido.setCliente(c);
				pdao.newPedido(pedido);
			}

			response.sendRedirect("listadopedido");
			
			
		} catch(Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "iniciarpedido");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		

	}

}
