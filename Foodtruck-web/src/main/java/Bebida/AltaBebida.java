package Bebida;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import data.BebidaDAO;
import entities.Bebida;

/**
 * Servlet implementation class AltaBebida
 */
@WebServlet("/altabebida")
@MultipartConfig
public class AltaBebida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] extens = {".ico", ".png", ".jpg", ".jpeg"};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaBebida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mensaje", "  ");
		request.getRequestDispatcher("WEB-INF/altaBebida.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
		
		
		
		float precio = Float.parseFloat(request.getParameter("precio"));
		String nombre = request.getParameter("nombre");
		float litros = Float.parseFloat(request.getParameter("litros"));
		
		Bebida b = new Bebida(precio, nombre, litros);
		
		String pathFiles = request.getServletContext().getRealPath("") + File.separator + "img";
		File uploads = new File(pathFiles);
		
		if (nombre.equals("") | litros == 0 | precio == 0) {
			throw new IllegalArgumentException();
		}
		
		Part part = request.getPart("imagen");
		
		if(part == null) {
			System.out.println("No ha seleccionado un archivo");
			return;
		}
		
		if(isExtension(part.getSubmittedFileName(), extens)) {
			String photo = saveFile(part,uploads);
			b.setFoto(photo);
		}
		
		
		BebidaDAO bdao = new BebidaDAO();
		
		bdao.newBebida(b);
		
		response.sendRedirect("listadobebida");
	}
	catch (IllegalArgumentException e) {
		request.setAttribute("mensaje", "Complete los datos correctamente");
		request.getRequestDispatcher("WEB-INF/altaBebida.jsp").forward(request, response);
	} catch (Exception e) {
		request.setAttribute("mensaje", "Ha ocurrido un error.");
		request.setAttribute("servlet", "altabebida");
		request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
	}
		
		
	}
    
    	private String saveFile(Part part, File pathUploads) {
		
		String pathAbsolute = "";
		String fileName = "";
		
		try {
			
			Path path = Paths.get(part.getSubmittedFileName());
			Random random = new Random();
			fileName = random.nextInt(1000) + path.getFileName().toString();
			
			InputStream input = part.getInputStream();
			
			if(input != null) {
				File file = new File(pathUploads, fileName);
				pathAbsolute = file.getAbsolutePath();
				
				System.out.println(pathAbsolute);
				
				// Guardamos el archivo
				Files.copy(input, file.toPath()); 
			}
			
			
		} catch (Exception e) {
			
		}
		
		return "img/" + fileName;
		
	}
	
	private boolean isExtension(String fileName, String[] extensions) {
		for(String et : extensions) {
			if(fileName.toLowerCase().endsWith(et)) {
				return true;
			}
		}
		return false;
	}

}
