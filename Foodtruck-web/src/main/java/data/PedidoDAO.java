package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.Timestamp;

import entities.Bebida;
import entities.Cliente;
import entities.Empleado;
import entities.LineaPedido;
import entities.Pedido;
import entities.Plato;



public class PedidoDAO {

	
	public LinkedList<Pedido> getAll() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Pedido> pedidos = new LinkedList<Pedido>();
		PlatoDAO pdao = new PlatoDAO();
		BebidaDAO bdao = new BebidaDAO();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT * FROM Pedido");

			if (rs != null) {
				while (rs.next()) {

					Pedido p = new Pedido();
					Empleado e = new Empleado();
					Cliente c = new Cliente();
					
					p.setId(rs.getInt("idPedido"));
					p.setEstado(rs.getString("estadoPedido"));
					p.setTipoPedido(rs.getString("tipoPedido"));
					p.setFechaHora(rs.getTimestamp("fechaHoraPedido"));
					String dniEmpleado = rs.getString("dniEmpleado");
					String dniCliente = rs.getString("dniCliente");
					
					e.setDni(dniEmpleado);
					c.setDni(dniCliente);
					
					if (dniCliente != null) {
						ClienteDAO cdao = new ClienteDAO();
						c = cdao.getCliente(c);
					}
					
					
					p.setEmpleado(e);
					p.setCliente(c);
		
					pdao.setPlatos(p);
					bdao.setBebidas(p);
					
					
					pedidos.add(p);
					
					
				}
				
				
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

		return pedidos;
	}
	
	
	
	public LinkedList<Pedido> getPedidoByFecha(String fecha) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Pedido> pedidos = new LinkedList<Pedido>();
		PlatoDAO pdao = new PlatoDAO();
		BebidaDAO bdao = new BebidaDAO();

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(" Select * from pedido where fechaHoraPedido like Concat(?,'%') order by fechaHoraPedido ");

			stmt.setString(1, fecha);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					
					
					
					Pedido p = new Pedido();
					Empleado e = new Empleado();
					Cliente c = new Cliente();
					
					p.setId(rs.getInt("idPedido"));
					p.setEstado(rs.getString("estadoPedido"));
					p.setTipoPedido(rs.getString("tipoPedido"));
					p.setFechaHora(rs.getTimestamp("fechaHoraPedido"));
					String dniEmpleado = rs.getString("dniEmpleado");
					String dniCliente = rs.getString("dniCliente");
					
					e.setDni(dniEmpleado);
					c.setDni(dniCliente);
					
					if (dniCliente != null) {
						ClienteDAO cdao = new ClienteDAO();
						c = cdao.getCliente(c);
					}
					
					
					p.setEmpleado(e);
					p.setCliente(c);
		
					pdao.setPlatos(p);
					bdao.setBebidas(p);
					
					
					pedidos.add(p);
					
					
				}
				
				
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

		return pedidos;
	}
	
	
	
	
	
	public Pedido getPedido(Pedido p)  throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Pedido pe = null;
		EmpleadoDAO edao = new EmpleadoDAO();
		ClienteDAO cdao = new ClienteDAO();
		PlatoDAO pdao = new PlatoDAO();
		BebidaDAO bdao = new BebidaDAO();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("Select * from Pedido WHERE idPedido=?");

			stmt.setInt(1, p.getId());

			rs = stmt.executeQuery();

			if (rs != null & rs.next()) {

				pe = new Pedido();
				pe.setId(rs.getInt("idPedido"));
				pe.setFechaHora(rs.getTimestamp("fechaHoraPedido"));
				
				pe.setEstado(rs.getString("estadoPedido"));
				pe.setTipoPedido(rs.getString("tipoPedido"));
				
		
				Empleado e = new Empleado();
				e.setDni(rs.getString("dniEmpleado"));
				
				Cliente c = new Cliente();
				c.setDni(rs.getString("dniCliente"));
				
				pe.setEmpleado(edao.getEmpleado(e));
				pe.setCliente(cdao.getCliente(c));
				
				pdao.setPlatos(pe);
				bdao.setBebidas(pe);
				
				
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

		return pe;
	}
	
	public void newPedido(Pedido p)  throws SQLException {
		PreparedStatement stmt = null;
		ResultSet keyRS=null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("INSERT INTO Pedido (estadoPedido, tipoPedido, dniEmpleado, dniCliente,total) VALUES (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1,p.getEstado());
			stmt.setString(2, p.getTipoPedido());
			stmt.setString(3, p.getEmpleado().getDni());
			
			if(p.getCliente().getDni() != null) {
				stmt.setString(4, p.getCliente().getDni());
			} else {
				stmt.setString(4, null);
			}
			
			stmt.setFloat(5, p.getTotal());
			
			
			
			stmt.executeUpdate(); // Se activa un trigger que inicializa la fecha y hora en la db
			keyRS= stmt.getGeneratedKeys();
			
			if(keyRS != null && keyRS.next()) {
				p.setId(keyRS.getInt(1)); //
				
			}
			
			
			for (LineaPedido lp : p.getLineas()) {
				
				if(lp.getProducto() instanceof Plato ) {
					
					stmt = DbConnector.getInstancia().getConn()
							.prepareStatement("INSERT INTO pedido_Plato (idPedido,idPlato,fechaHoraPedido,cantidad) VALUES (?,?,?,?)");
					
					Plato pl = (Plato) lp.getProducto();
					
					stmt.setInt(1, p.getId());
					stmt.setInt(2, pl.getId());
					Timestamp hora = this.getPedido(p).getFechaHora();
					stmt.setTimestamp(3, hora);
					stmt.setInt(4, lp.getCantidad());
					
				}
				
				else {
					
					if(lp.getProducto() instanceof Bebida ) {
						
						stmt = DbConnector.getInstancia().getConn()
								.prepareStatement("INSERT INTO pedido_bebida (idPedido,idBebida,fechaHoraPedido,cantidad) VALUES (?,?,?,?)");
						
						Bebida b = (Bebida) lp.getProducto();
						
						stmt.setInt(1, p.getId());
						stmt.setInt(2, b.getId());
						Timestamp hora = this.getPedido(p).getFechaHora();
						stmt.setTimestamp(3, hora);
						stmt.setInt(4, lp.getCantidad());
				}
	

				
			}

				stmt.executeUpdate();
				
			}
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
	
	public void updateEstadoPedido(Pedido p) throws SQLException {

		PreparedStatement stmt = null;
		try {

			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE pedido SET estadoPedido=?, dniEmpleado=? where idpedido=?");
			stmt.setString(1, p.getEstado());
			stmt.setString(2, p.getEmpleado().getDni());
			stmt.setInt(3, p.getId());
			

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
}