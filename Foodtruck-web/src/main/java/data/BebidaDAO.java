package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.*;

public class BebidaDAO {
	
	public LinkedList<Bebida> getAll() throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Bebida> bebidas= new LinkedList<>();	
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idBebida, precioBebida, nombreBebida, litrosBebida, imagen from bebida order by nombreBebida");
			
			if(rs!=null) {
				while(rs.next()) {
					Bebida b = new Bebida();
					b.setId(rs.getInt("idBebida"));
					b.setPrecio(rs.getFloat("precioBebida"));
					b.setNombre(rs.getString("nombreBebida"));
					b.setLitros(rs.getFloat("litrosBebida"));
					b.setFoto(rs.getString("imagen"));
					bebidas.add(b);
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
				throw e;
			}
		}
		
		
		return bebidas;
	}
	
	//------------------------------------------------
	//agregar
	
	public void newBebida(Bebida newBebida) throws SQLException {
		
		ResultSet keyRS=null;
		PreparedStatement stmt=null;
		
		try {			
			
			
			stmt = DbConnector.getInstancia().getConn()
			.prepareStatement("insert into bebida (precioBebida, nombreBebida, litrosBebida, imagen)" + "values(?,?,?,?)");
			
			stmt.setFloat(1, newBebida.getPrecio());
			stmt.setString(2, newBebida.getNombre());
			stmt.setFloat(3, newBebida.getLitros());
			stmt.setString(4, newBebida.getFoto());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
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
	
	public void delteBebida(Bebida delBeb) throws SQLException {
		
		PreparedStatement stmt = null;
		try {

			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from bebida where idBebida=?");
			stmt.setInt(1, delBeb.getId());
			stmt.executeUpdate();

		} catch (SQLException ex) {
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
		
	
	public void updateBebida(Bebida updBebida) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE bebida SET precioBebida=?, nombreBebida=?, litrosBebida=?, imagen=? where idBebida=?");
		stmt.setFloat(1, updBebida.getPrecio());
		stmt.setString(2, updBebida.getNombre() );
		stmt.setFloat(3, updBebida.getLitros());
		stmt.setString(4, updBebida.getFoto());
		stmt.setInt(5, updBebida.getId());
		stmt.executeUpdate();
		

		} catch (SQLException ex) {
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
	
	public Bebida getBebida(Bebida b) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Bebida be = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("Select * from Bebida where idBebida=?");

			stmt.setInt(1, b.getId());

			rs = stmt.executeQuery();

			if (rs != null & rs.next()) {

				be = new Bebida();
				be.setId(rs.getInt("idBebida"));
				be.setNombre(rs.getString("nombreBebida"));
				be.setPrecio(rs.getFloat("precioBebida"));
				be.setLitros(rs.getFloat("litrosBebida"));
				be.setFoto(rs.getString("imagen"));
			}

		} catch (SQLException e) {
			throw e;

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

		return be;
	}
	
	public void setBebidas(Pedido p) throws SQLException {
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement
			("select * from pedido p inner join pedido_bebida pb on p.idPedido = pb.idPedido inner join bebida b on b.idBebida = pb.idBebida where p.idPedido = ?");
			stmt.setInt(1, p.getId());
			rs= stmt.executeQuery();
			if(rs!=null) {
				
				while(rs.next()) {
					
					LineaPedido lp = new LineaPedido();
					Bebida bebida = new Bebida();
					bebida.setId(rs.getInt("idBebida"));
					bebida.setNombre(rs.getString("nombreBebida"));
					bebida.setPrecio(rs.getInt("precioBebida"));
					bebida.setLitros(rs.getFloat("litrosBebida"));
					bebida.setFoto(rs.getString("imagen"));
					
					
					lp.setProducto(bebida);
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
				throw e;
			}
		}
	}
	
	
}