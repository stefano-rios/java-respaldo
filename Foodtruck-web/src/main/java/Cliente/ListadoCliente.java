package Cliente;


import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ClienteDAO;
import entities.Cliente;


/**
 * Servlet implementation class AltaCliente
 */
@WebServlet("/listadoclientes")
public class ListadoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoCliente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			
		ClienteDAO cdao = new ClienteDAO();
		LinkedList<Cliente> clientes = cdao.getAll();
		
		if (request.getParameter("mensaje") == null) {
			request.setAttribute("mensaje", " ");
		} else {
			request.setAttribute("mensaje", "Complete los datos correctamente.");
		}
		request.setAttribute("listaclientes", clientes);
		request.getRequestDispatcher("WEB-INF/listadoClientes.jsp").forward(request, response);
		}
		catch(Exception e){
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "menuadmin");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
