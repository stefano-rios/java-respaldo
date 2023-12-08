package Bebida;

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

import data.BebidaDAO;
import data.PlatoDAO;
import entities.Bebida;

/**
 * Servlet implementation class editBebida
 */
@WebServlet("/editbebida")
@MultipartConfig
public class EditBebida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] extens = { ".ico", ".png", ".jpg", ".jpeg" };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditBebida() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("IDGET=" + id);
			Bebida be = new Bebida();
			be.setId(id);
			
			BebidaDAO bdao = new BebidaDAO();
			be = bdao.getBebida(be);
			
			if(be == null) {
				throw new Exception();
			}
			
			request.setAttribute("be1", be);
			
			if (request.getParameter("mensaje") == null) {
				request.setAttribute("mensaje", " ");
			} else {
				request.setAttribute("mensaje", "Complete los datos correctamente.");
			}

			request.getRequestDispatcher("WEB-INF/editBebida.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("mensaje", "Ha ocurrido un error.");
			request.setAttribute("servlet", "listadobebida");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Bebida nuevaBebida = new Bebida();
		BebidaDAO bdao = new BebidaDAO();
		
		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			nuevaBebida.setId(id);
			
			String nombre = request.getParameter("nombre");
			Float precio = Float.parseFloat(request.getParameter("precio"));
			Float litros = Float.parseFloat(request.getParameter("litros"));
			
			
			nuevaBebida.setLitros(litros);
			nuevaBebida.setNombre(nombre);
			nuevaBebida.setPrecio(precio);

			String pathFiles = request.getServletContext().getRealPath("") + File.separator + "img";
			File uploads = new File(pathFiles);

			if (nombre.equals("") | litros == null | precio == null) {
				throw new IllegalArgumentException();
			}
			
			Part part = request.getPart("imagen");

			if (part == null) {
				System.out.println("No ha seleccionado un archivo");
				return;
			}

			if (isExtension(part.getSubmittedFileName(), extens)) {
				String photo = saveFile(part, uploads);
				nuevaBebida.setFoto(photo);
			}

			
			bdao.updateBebida(nuevaBebida);
			response.sendRedirect("listadobebida");
		}
		 catch (IllegalArgumentException e) {

			 System.out.println("ID=" + nuevaBebida.getId());
				response.sendRedirect("editbebida?id=" + nuevaBebida.getId() + "&mensaje=true");

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Ha ocurrido un error.");
				request.setAttribute("servlet", "listadobebida");
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
