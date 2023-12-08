package Login;


import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.EmpleadoDAO;
import entities.Empleado;
import entities.Hash;
import entities.Rol;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Empleado e = new Empleado();
		Rol r = new Rol();
		r.setDesc("Invitado");
		e.getColeccionRoles().add(r);
		
		
		request.getSession().setAttribute("empleado", e);
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String dni = request.getParameter("dni");
			String pass = request.getParameter("password");
			
			Empleado e = new Empleado();
			e.setDni(dni);
			e.setPassword(Hash.doHashing(pass));
			
			EmpleadoDAO edao = new EmpleadoDAO();
			Empleado emp = edao.login(e);
			
			String roles = emp.getRoles();
			
			
			if (emp != null && roles.equalsIgnoreCase("Administrador")) {
				
				request.getSession().setAttribute("empleado", emp);
				response.sendRedirect("menuadmin");
			} 
			
			else {
				request.getSession().setAttribute("empleado", emp);
				response.sendRedirect("listadopedido");
			}
			
			
		} 
		
		//Si empleado es nulo
		catch(NullPointerException e) {
					request.setAttribute("mensaje", "Las credenciales no coinciden con nuestros datos.");
					request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "login");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			
		}
		
		
	}
	
}
