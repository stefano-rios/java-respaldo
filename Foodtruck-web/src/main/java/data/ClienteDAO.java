package data;

import java.sql.*;
import java.util.LinkedList;

import entities.Cliente;

public class ClienteDAO {

	public LinkedList<Cliente> getAll() throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Cliente> clientes = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT * FROM Cliente");

			if (rs != null) {
				while (rs.next()) {

					Cliente c = new Cliente();

					c.setDni(rs.getString("dniCliente"));
					c.setNombre(rs.getString("nombre"));
					c.setDireccion(rs.getString("direccion"));

					clientes.add(c);
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

		return clientes;
	}

	public Cliente getCliente(Cliente cli)  throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cliente c = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("Select * from Cliente WHERE dniCliente=?");

			stmt.setString(1, cli.getDni());

			rs = stmt.executeQuery();

			if (rs != null & rs.next()) {

				c = new Cliente();
				c.setDni(rs.getString("dniCliente"));
				c.setNombre(rs.getString("nombre"));
				c.setDireccion(rs.getString("direccion"));
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

		return c;
	}

	public void newCliente(Cliente c) throws SQLException{
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("INSERT INTO Cliente (dniCliente,nombre, direccion) VALUES (?,?,?)");

			stmt.setString(1, c.getDni());
			stmt.setString(2, c.getNombre());
			stmt.setString(3, c.getDireccion());

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

	public void updateCliente(Cliente c) throws SQLException{

		PreparedStatement stmt = null;
		try {

			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE Cliente SET dniCliente=?, nombre=?, direccion=? where dniCliente=?");
			stmt.setString(1, c.getDni());
			stmt.setString(2, c.getNombre());
			stmt.setString(3, c.getDireccion());
			stmt.setString(4, c.getDni());

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

	public void deleteCliente(Cliente c)  throws SQLException{

		PreparedStatement stmt = null;
		try {

			stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE from Cliente WHERE dniCliente=?");
			stmt.setString(1, c.getDni());
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
