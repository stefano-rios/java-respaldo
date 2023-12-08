package Plato;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
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
 * Servlet implementation class AltaPlato
 */
@WebServlet("/altaplato")
@MultipartConfig
public class AltaPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] extens = { ".ico", ".png", ".jpg", ".jpeg" };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AltaPlato() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("mensaje", "  ");
		request.getRequestDispatcher("WEB-INF/altaPlato.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String nombre = request.getParameter("nombre");
			float precio = Float.parseFloat(request.getParameter("precio"));
			String descripcion = request.getParameter("descripcion");

			Plato p = new Plato(nombre, precio, descripcion);

			// carpeta para alojar arch subidos
			String pathFiles = request.getServletContext().getRealPath("") + File.separator + "img";

			// referencio la ruta de la carpeta
			File uploads = new File(pathFiles);

			if (nombre.equals("") | descripcion.equals("") | precio == 0) {
				throw new IllegalArgumentException();
			}

			// recepciono imagen
			Part part = request.getPart("imagen");

			if (part == null) {
				System.out.println("No ha seleccionado un archivo");
				return;
			}

			if (isExtension(part.getSubmittedFileName(), extens)) {
				String photo = saveFile(part, uploads);
				p.setFoto(photo);
			}

			PlatoDAO pdao = new PlatoDAO();
			pdao.newPlato(p);

			response.sendRedirect("listadoplato");

		} catch (IllegalArgumentException e) {
			request.setAttribute("mensaje", "Complete los datos correctamente");
			request.getRequestDispatcher("WEB-INF/altaPlato.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "altaplato");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}

	}

	private String saveFile(Part part, File pathUploads) {

		String pathAbsolute = "";
		String fileName = "";

		try {
			// obtengo nombre del archivo subido
			Path path = Paths.get(part.getSubmittedFileName());
			Random random = new Random();

			// Creo variable con ese nombre(se guarda en db)
			fileName = random.nextInt(1000) + path.getFileName().toString();

			// archivo en si(se guarda en la carpeta)
			InputStream input = part.getInputStream();

			if (input != null) {
				// obtengo ruta absoluta, pasando ruta de descargas y nombre de archivo
				File file = new File(pathUploads, fileName);
				pathAbsolute = file.getAbsolutePath();

				System.out.println(pathAbsolute);

				// Guardamos el archivo en carpeta img, con el archivo que subimos en input y
				// fileToPath
				Files.copy(input, file.toPath());
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
		// le agrego img al nombre de archivo para guardarlo
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