package Pedido;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PedidoDAO;
import entities.Pedido;

/**
 * Servlet implementation class ListadoPedidoPorFecha
 */
@WebServlet("/listadopedidoporfecha")
public class ListadoPedidoPorFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoPedidoPorFecha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
		String fecha = request.getParameter("fecha");
		if(request.getParameter("fecha") == null) {
			
			fecha = "";
		}
		
		PedidoDAO pdao = new PedidoDAO();
		LinkedList<Pedido> pedidos  = pdao.getPedidoByFecha(fecha);
		
		request.setAttribute("pedidos", pedidos);
		request.getRequestDispatcher("WEB-INF/listadoPedidoPorFecha.jsp").forward(request, response);
		}
	catch(Exception e) {
		request.setAttribute("mensaje", "Ha ocurrido un error.");
		request.setAttribute("servlet", "iniciarpedido");
		request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
