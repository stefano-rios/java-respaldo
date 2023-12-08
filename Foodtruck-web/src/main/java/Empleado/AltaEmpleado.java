package Empleado;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.EmpleadoDAO;
import data.RolDAO;
import entities.Empleado;
import entities.Hash;
import entities.Rol;

/**
 * Servlet implementation class AltaEmpleado
 */
@WebServlet("/altaempleado")
public class AltaEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mensaje", "  ");
		request.getRequestDispatcher("WEB-INF/altaEmpleado.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//podriamos borrar ROL, ya que nunca lo usamos hablo de rol y empleadorol, ya está como atributo
		String dni = request.getParameter("dni");
		String nom = request.getParameter("nombre");
		String tur = request.getParameter("turno");
		String pass = Hash.doHashing(request.getParameter("password"));
		String rol = request.getParameter("rol");
		
		if (dni.equals("")||nom.equals("")||tur.equals("")||pass.equals("")||rol==null) {
			throw new IllegalArgumentException();
		}
		
		Rol r = new Rol();
		r.setDesc(rol);
		
		RolDAO rdao = new RolDAO();
		r = rdao.getRolByDesc(r);
		
		EmpleadoDAO edao = new EmpleadoDAO();
		Empleado e = new Empleado(dni,nom,tur,pass);
		e.addRol(r);
		edao.newEmpleado(e);
		
		response.sendRedirect("listadoempleados");
	}
		
		catch(IllegalArgumentException e){
			request.setAttribute("mensaje", "Complete los datos correctamente");
			request.getRequestDispatcher("WEB-INF/altaEmpleado.jsp").forward(request, response);
		}
		
		catch(Exception e ) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadoempleados");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

		}
	}
	

}
