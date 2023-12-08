package Empleado;


import java.io.IOException;
import java.util.LinkedList;

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
 * Servlet implementation class UpdateEmpleado
 */
@WebServlet("/empleadoeditar")
public class UpdateEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		request.setAttribute("mensaje", "  ");
		String dni = request.getParameter("dni");
		EmpleadoDAO edao = new EmpleadoDAO();
		Empleado e = new Empleado();
		e.setDni(dni);
		e = edao.getEmpleado(e);
		request.setAttribute("emp", e);
		if(e == null) {
			throw new Exception();
		}
		request.getRequestDispatcher("WEB-INF/updateEmpleado.jsp").forward(request, response);
		}
		catch(Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadoempleados");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = request.getParameter("dni");
		EmpleadoDAO edao = new EmpleadoDAO();
		try {
		
		String nom = request.getParameter("nombre");
		String tur = request.getParameter("turno");
		String pass = Hash.doHashing(request.getParameter("password"));
		String rol = request.getParameter("rol");
		
		if(dni.equals("")||nom.equals("")||pass.equals("")||rol==null||tur==null) {
			throw new IllegalArgumentException();
		}
		
		Rol r = new Rol();
		r.setDesc(rol);
		
		RolDAO rdao = new RolDAO();
		r = rdao.getRolByDesc(r);
		
		Empleado e = new Empleado(dni,nom,tur,pass);
		e.addRol(r);
				
		edao.updateEmpleado(e);
		
		LinkedList<Empleado> empleados = edao.getAll();
		request.setAttribute("listaEmpleados", empleados);
		
		request.getRequestDispatcher("WEB-INF/listadoEmpleados.jsp").forward(request, response);
	}
		catch(IllegalArgumentException e){
			try {
			Empleado e1 = new Empleado();
			Empleado e2 = new Empleado();
			e1.setDni(dni);
			e2 =edao.getEmpleado(e1);
			request.setAttribute("emp", e2);
			request.setAttribute("mensaje", "Complete los datos correctamente.");
			request.getRequestDispatcher("WEB-INF/updateEmpleado.jsp").forward(request, response);
			}
			catch(Exception en) {
				request.setAttribute("mensaje", "Ha ocurrido un error.");
				request.setAttribute("servlet", "empleadoeditar");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
		}
		catch(Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "empleadoeditar");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

		}
	}
	

}
