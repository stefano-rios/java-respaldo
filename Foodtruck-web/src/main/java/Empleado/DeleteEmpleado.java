package Empleado;


import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.EmpleadoDAO;
import entities.Empleado;

/**
 * Servlet implementation class DeleteEmpleado
 */
@WebServlet("/empleadoborrar")
public class DeleteEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String dni = request.getParameter("dni");
		
		Empleado e = new Empleado();
		e.setDni(dni);
		EmpleadoDAO edao = new EmpleadoDAO();
		
		edao.deleteEmpleado(e);
		LinkedList<Empleado> empleados = edao.getAll();
		request.setAttribute("listaEmpleados", empleados);
		
		request.getRequestDispatcher("WEB-INF/listadoEmpleados.jsp").forward(request, response);
		} 
		 catch (SQLIntegrityConstraintViolationException e) {
				request.setAttribute("mensaje", "No se puede eliminar un empleado con un pedido asociado.");
				request.setAttribute("servlet", "listadoempleados");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

			}
		catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadoempleados");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
