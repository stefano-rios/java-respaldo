package entities;

import java.sql.Timestamp;
import java.util.LinkedList;

public class Pedido {

	private int id;
	private Timestamp fechaHora;
	private String estado;
	private String tipoPedido;
	private Empleado empleado;
	private Cliente cliente;
	private LinkedList<LineaPedido> lineaPedidos = new LinkedList<LineaPedido>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void addLineaPedido(LineaPedido lp) {

		this.lineaPedidos.add(lp);

	}

	public LinkedList<LineaPedido> getLineas() {
		return this.lineaPedidos;
	}

	public float getTotal() {
		int total = 0;

		for (LineaPedido lp : lineaPedidos) {

			if (lp.getProducto() instanceof Plato) {
				Plato p = (Plato) lp.getProducto();
				total += p.getPrecio() * lp.getCantidad();
			}

			if (lp.getProducto() instanceof Bebida) {
				Bebida b = (Bebida) lp.getProducto();
				total += b.getPrecio() * lp.getCantidad();
			}

		}

		return total;

	}

}
