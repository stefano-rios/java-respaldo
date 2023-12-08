package Cliente;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ClienteDAO;
import data.EmpleadoDAO;
import data.RolDAO;
import entities.Cliente;
import entities.Empleado;
import entities.Rol;


/**
 * Servlet implementation class UpdateCliente
 */
@WebServlet("/clienteeditar")
public class UpdateCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente c = new Cliente();
		try {
		//me traigo el id
		String dni = request.getParameter("dni");
		
		ClienteDAO cdao = new ClienteDAO();
		
		c.setDni(dni);
		c = cdao.getCliente(c);
		
		if(c == null) {
			throw new Exception();
		}
		
		request.setAttribute("cli", c);
		if (request.getParameter("mensaje") == null) {
			request.setAttribute("mensaje", " ");
		} else {
			request.setAttribute("mensaje", "Complete los datos correctamente.");
		}

		request.getRequestDispatcher("WEB-INF/updateCliente.jsp").forward(request, response);
		}
	
	
		
	 catch (Exception e) {
		request.setAttribute("mensaje", "Ha ocurrido un error.");
		request.setAttribute("servlet", "listadoclientes");
		request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
	}
		
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente c = new Cliente();
		String dni = request.getParameter("dni");
		ClienteDAO cdao = new ClienteDAO();
		try {
		
		
		String nom = request.getParameter("nombre");
		String dir = request.getParameter("direccion");
		
		if ( nom.equals("") || dir.equals("") || dni.equals("") ) {
			throw new IllegalArgumentException();
		}
		
		c = new Cliente(dni,nom,dir);
	
		cdao.updateCliente(c);
		
		LinkedList<Cliente> clientes = cdao.getAll();
		request.setAttribute("listaclientes", clientes);
		
		request.getRequestDispatcher("WEB-INF/listadoClientes.jsp").forward(request, response);
	}
		
		catch(IllegalArgumentException e ){	
			try {
				
			c.setDni(dni);
			c = cdao.getCliente(c);
			request.setAttribute("cli", c);
			request.setAttribute("mensaje", "Complete los datos correctamente");
			request.getRequestDispatcher("WEB-INF/updateCliente.jsp").forward(request, response);
			}
			catch(Exception en) {
				request.setAttribute("mensaje", "Ha ocurrido un error.");
				request.setAttribute("servlet", "clienteeditar?dni=" + c.getDni());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
			
		}
		
		catch(Exception e){
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "clienteeditar?dni="+  c.getDni());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
	
	

	}
	}
}
