package data;
import java.sql.*;
import java.util.LinkedList;

import entities.Empleado;
import entities.Rol;

public class RolDAO {

	public LinkedList<Rol> getAll(){
		
		Statement st = null;
		ResultSet rs = null;
		
		LinkedList<Rol> roles = new LinkedList<Rol>();
		
		try {
			st = DbConnector.getInstancia().getConn().createStatement();
			rs = st.executeQuery("SELECT * FROM Rol");
			
			if (rs != null) {
				while (rs.next()) {
					Rol r = new Rol();
					r.setId(rs.getInt("idRol"));
					r.setDesc(rs.getString("rol"));
					roles.add(r);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return roles;
		
	}
	
	public Rol getRol(Rol rol) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Rol r = null;
		
		try {
			st = DbConnector.getInstancia().getConn().prepareStatement("Select * from Rol WHERE idRol=?");
			st.setInt(1, rol.getId());

			rs = st.executeQuery();

			if (rs != null & rs.next()) {
				
				r = new Rol();
				r.setId(rs.getInt("idRol"));
				r.setDesc(rs.getString("rol"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return r;
	}
	
	public void newRol(Rol rol) {
		
		PreparedStatement st = null;
		
		try {
			st = DbConnector.getInstancia().getConn()
					.prepareStatement("INSERT INTO Rol (rol) VALUES (?)");
			st.setString(1, rol.getDesc());
			st.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void updateRol(Rol rol) {
		PreparedStatement st = null;
		
		try {
			 DbConnector.getInstancia().getConn()
				.prepareStatement("UPDATE Rol SET rol=? WHERE idRol=?");
			 st.setString(1, rol.getDesc());
			 st.setInt(2, rol.getId());
			 
			 st.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteRol(Rol rol) {
		
		PreparedStatement st = null;
		try {

			st = DbConnector.getInstancia().getConn().prepareStatement("DELETE from Rol WHERE idRol=?");
			st.setInt(1, rol.getId());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
public void deleteRolEmpleado(Empleado emp) {
		
		PreparedStatement st = null;
		try {

			st = DbConnector.getInstancia().getConn().prepareStatement("DELETE from empleado_rol WHERE dniEmpleado=?");
			st.setString(1, emp.getDni());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void setRoles(Empleado emp) {
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement
			("select * from rol r inner join empleado_rol er on r.idRol = er.idRol where dniEmpleado = ?");
			stmt.setString(1, emp.getDni());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setId(rs.getInt("idRol"));
					r.setDesc(rs.getString("rol"));
					emp.addRol(r);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	
public Rol getRolByDesc(Rol rol) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Rol r = null;
		
		try {
			st = DbConnector.getInstancia().getConn().prepareStatement("Select * from Rol WHERE rol=?");
			st.setString(1, rol.getDesc());

			rs = st.executeQuery();

			if (rs != null & rs.next()) {
				
				r = new Rol();
				r.setId(rs.getInt("idRol"));
				r.setDesc(rs.getString("rol"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return r;
	}
	
}
