package Cliente;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

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
@WebServlet("/altacliente")
public class AltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mensaje", "  ");
		request.getRequestDispatcher("WEB-INF/altaCliente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = "";
	try {
		
		dni = request.getParameter("dni");
		String nom = request.getParameter("nombre");
		String dir = request.getParameter("direccion");
		
		//ex
		if (nom.equals("") || ( dni.length()!=8) || dir.equals("")) {
			throw new IllegalArgumentException();
		}
		
		Cliente c = new Cliente(dni,nom,dir);
		
		ClienteDAO cdao = new ClienteDAO();
		
		cdao.newCliente(c);
		
		response.sendRedirect("listadoclientes");
	}
	
	catch(IllegalArgumentException e){
		request.setAttribute("mensaje", "Complete los datos correctamente");
		request.getRequestDispatcher("WEB-INF/altaCliente.jsp").forward(request, response);
	}
	catch(SQLIntegrityConstraintViolationException e) {
		request.setAttribute("mensaje", "El cliente con el DNI " + dni + " ya existe.");
		request.setAttribute("servlet", "altacliente");
		request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
	}
	catch(Exception e) {
		request.setAttribute("mensaje", "Ha ocurrido un error.");
		request.setAttribute("servlet", "listadoclientes");
		request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
	}
	
}

}
