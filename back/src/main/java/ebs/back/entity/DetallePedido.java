package ebs.back.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DetallePedido {

	private long id;
	private int cantidad;
	private ArticuloVenta articulo;
	private Pedido pedido;

	public DetallePedido(long id, int cantidad, ArticuloVenta articulo, Pedido pedido) {
		this.id = id;
		this.cantidad = cantidad;
		this.articulo = articulo;
		this.pedido = pedido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArticuloVenta getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloVenta articulo) {
		this.articulo = articulo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public float calcularSubtotal() {
		return 0.0f;
	}

}
