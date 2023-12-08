package Plato;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import data.PlatoDAO;
import entities.Plato;

/**
 * Servlet implementation class editPlato
 */
@WebServlet("/editplato")
@MultipartConfig
public class EditPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] extens = { ".ico", ".png", ".jpg", ".jpeg" };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPlato() {
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
			int id = Integer.parseInt(request.getParameter("id"));

			Plato plato = new Plato();
			plato.setId(id);

			PlatoDAO pdao = new PlatoDAO();
			plato = pdao.getPlato(plato);

			if (request.getParameter("mensaje") == null) {
				request.setAttribute("mensaje", " ");
			} else {
				request.setAttribute("mensaje", "Complete los datos correctamente.");
			}

			request.setAttribute("plato", plato);
			request.getRequestDispatcher("WEB-INF/editPlato.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadoplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PlatoDAO pdao = new PlatoDAO();
		Plato plato = new Plato();
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			plato.setId(id);

			String nombre = request.getParameter("nombre");
			Float precio = Float.parseFloat(request.getParameter("precio"));
			String descripcion = request.getParameter("descripcion");

			plato.setDescripcion(descripcion);
			plato.setNombre(nombre);
			plato.setPrecio(precio);

			if (nombre.equals("") | descripcion.equals("") | precio == null) {
				throw new IllegalArgumentException();
			}

			String pathFiles = request.getServletContext().getRealPath("") + File.separator + "img";
			File uploads = new File(pathFiles);

			Part part = request.getPart("imagen");

			if (part == null) {
				System.out.println("No ha seleccionado un archivo");
				return;
			}

			if (isExtension(part.getSubmittedFileName(), extens)) {
				String photo = saveFile(part, uploads);
				plato.setFoto(photo);
			}

			pdao.updatePlato(plato);
			response.sendRedirect("listadoplato");

		} catch (IllegalArgumentException e) {

			response.sendRedirect("editplato?id=" + plato.getId() + "&mensaje=true");

		} catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadoplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}

	}

	private String saveFile(Part part, File pathUploads) {

		String pathAbsolute = "";
		String fileName = "";

		try {

			Path path = Paths.get(part.getSubmittedFileName());
			Random random = new Random();
			fileName = (random.nextInt(1000) + 1) + path.getFileName().toString();

			InputStream input = part.getInputStream();

			if (input != null) {
				File file = new File(pathUploads, fileName);
				pathAbsolute = file.getAbsolutePath();

				// Guardamos el archivo
				Files.copy(input, file.toPath());
			}

		} catch (Exception e) {

		}

		return "img/" + fileName;

	}

	private boolean isExtension(String fileName, String[] extensions) {
		for (String et : extensions) {
			if (fileName.toLowerCase().endsWith(et)) {
				return true;
			}
		}
		return false;
	}

}