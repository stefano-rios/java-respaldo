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
 * Servlet implementation class NuevoPedido
 */
@WebServlet("/altapedido")
public class AltaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaPedido() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/altapedido.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			
			String servicio = request.getParameter("servicio");
			String[] cantidades = request.getParameterValues("cantidad");
			
			LinkedList<LineaPedido> lineas = (LinkedList<LineaPedido>) session.getAttribute("lineas");
			Pedido p = new Pedido();
				
			int i = 0;
			for(LineaPedido lp : lineas) {
					
				lp.setCantidad(Integer.parseInt(cantidades[i]));
				p.addLineaPedido(lp);
				i++;
			}
				
			
			Empleado e = (Empleado) request.getSession().getAttribute("empleado");
			Cliente c = new Cliente(); //null
			p.setEstado("En preparación");
			p.setCliente(c);
			p.setEmpleado(e);
			
			
			if(servicio.equalsIgnoreCase("delivery")) {
				
				p.setTipoPedido("Delivery");
				session.setAttribute("pedido", p);
				
				request.getRequestDispatcher("WEB-INF/altaPedidoDelivery.jsp").forward(request, response);
		
			
			
			} else {
			PedidoDAO pdao = new PedidoDAO();
			
			p.setTipoPedido("Presencial");
			pdao.newPedido(p);
			session.setAttribute("pedido", p);
			response.sendRedirect("listadopedido");
			
			
			}
			
		} catch(Exception e) {
			
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "iniciarpedido");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		
	}

}
