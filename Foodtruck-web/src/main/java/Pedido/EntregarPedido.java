package Pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PedidoDAO;
import entities.Empleado;
import entities.Pedido;

/**
 * Servlet implementation class EntregarPedido
 */
@WebServlet("/entregarpedido")
public class EntregarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntregarPedido() {
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
			int id = Integer.parseInt(request.getParameter("nro"));
			PedidoDAO pdao = new PedidoDAO();

			Pedido p = new Pedido();
			Empleado e = (Empleado) request.getSession().getAttribute("empleado");
			p.setEmpleado(e);
			p.setId(id);
			p.setEstado("Entregado");
			pdao.updateEstadoPedido(p);
			response.sendRedirect("listadopedido");

		} catch (Exception e) {

			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}

	}

}
