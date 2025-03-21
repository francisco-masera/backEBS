package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetallePedido implements Serializable {

	private Long id;
	private int cantidad;
	private InformacionArticuloVenta articulo;
	private Pedido pedido;

	public DetallePedido() {

	}

	public DetallePedido(Long id, int cantidad, InformacionArticuloVenta articulo, Pedido pedido) {
		this.id = id;
		this.cantidad = cantidad;
		this.articulo = articulo;
		this.pedido = pedido;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDetalle")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@ManyToOne
	@JoinColumn(name = "idArticulo", nullable = false)
	public InformacionArticuloVenta getArticulo() {
		return articulo;
	}

	public void setArticulo(InformacionArticuloVenta articulo) {
		this.articulo = articulo;
	}

	@ManyToOne
	@JoinColumn(name = "idPedido", nullable = false, unique = true)
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
