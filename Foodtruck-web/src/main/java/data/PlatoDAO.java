package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class PlatoDAO {
	///////////////////////////////////////////////////////////////////////////////
	public LinkedList<Plato> getAll() throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Plato> platos= new LinkedList<>();	
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idPlato, nombrePlato, precioPlato, descripcion, imagen from plato order by nombrePlato");
			
			if(rs!=null) {
				while(rs.next()) {
					Plato p = new Plato();
					p.setId(rs.getInt("idPlato"));
					p.setNombre(rs.getString("nombrePlato"));
					
					p.setPrecio(rs.getFloat("precioPlato"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setFoto(rs.getString("imagen"));
					
					platos.add(p);
				}
			}
			
		} catch (SQLException e) {
			throw e;
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return platos;
	}
	////////////////////////////////////////////////////
	
public void newPlato(Plato newPlato) throws SQLException {
		
		PreparedStatement stmt=null;
		
		try {			
			
			
			stmt = DbConnector.getInstancia().getConn()
			.prepareStatement("insert into plato (nombrePlato, precioPlato, descripcion, imagen)" + "values(?,?,?,?)");
			
			stmt.setString(1, newPlato.getNombre());
			stmt.setFloat(2, newPlato.getPrecio());
			stmt.setString(3, newPlato.getDescripcion());
			stmt.setString(4, newPlato.getFoto());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
////////////////////////////////////////////////////////////////////

public void delPlato(Plato delPlato) throws SQLException {
	
	PreparedStatement stmt = null;
	try {

		stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from plato where idPlato=?");
		stmt.setInt(1, delPlato.getId());
		stmt.executeUpdate();

	} catch (SQLException ex) {
		ex.printStackTrace();
		throw ex;
	} finally {
		try {
			if (stmt != null) {
				stmt.close();
			}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException ex) {
			throw ex;
		}
	}

}

////////////////////

public void updatePlato(Plato updPlato) throws SQLException {
	PreparedStatement stmt = null;
	try {
		stmt = DbConnector.getInstancia().getConn()
				.prepareStatement("UPDATE plato SET nombrePlato=?, precioPlato=?, descripcion=?, imagen=? where idPlato=?");
	stmt.setString(1, updPlato.getNombre());
	stmt.setFloat(2, updPlato.getPrecio());
	stmt.setString(3, updPlato.getDescripcion());
	stmt.setString(4, updPlato.getFoto());
	stmt.setInt(5, updPlato.getId());
	stmt.executeUpdate();
	

	} catch (SQLException ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (stmt != null) {
				stmt.close();
			}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException ex) {
			throw ex;
		}
	}
 }
	
	//////////////////////////////////////


public Plato getPlato(Plato p) throws SQLException {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Plato pl = null;
	try {
		stmt = DbConnector.getInstancia().getConn().prepareStatement("Select * from Plato where idPlato=?");

		stmt.setInt(1, p.getId());

		rs = stmt.executeQuery();

		if (rs != null & rs.next()) {

			pl = new Plato();
			pl.setId(rs.getInt("idPlato"));
			pl.setNombre(rs.getString("nombrePlato"));
			pl.setPrecio(rs.getFloat("precioPlato"));
			pl.setDescripcion(rs.getString("descripcion"));
			pl.setFoto(rs.getString("imagen"));
			
		}

	} catch (SQLException e) {
		e.printStackTrace();

	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
	}

	return pl;
}


public void setPlatos(Pedido p) throws SQLException {
	
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	try {
		stmt=DbConnector.getInstancia().getConn().prepareStatement
		("select * from pedido p inner join pedido_plato pp on p.idPedido = pp.idPedido inner join plato pl on pl.idPlato = pp.idPlato where p.idPedido = ?");
		stmt.setInt(1, p.getId());
		rs= stmt.executeQuery();
		if(rs!=null) {
			
			while(rs.next()) {
				
				LineaPedido lp = new LineaPedido();
				Plato plato = new Plato();
				plato.setId(rs.getInt("idPlato"));
				plato.setNombre(rs.getString("nombrePlato"));
				plato.setPrecio(rs.getInt("precioPlato"));
				plato.setDescripcion(rs.getString("descripcion"));
				plato.setFoto(rs.getString("imagen"));
				
				lp.setProducto(plato);
				lp.setCantidad(rs.getInt("cantidad"));
				
				
				p.addLineaPedido(lp);
			}
		}
		
	} catch (SQLException e) {
		throw e;
	}finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




}