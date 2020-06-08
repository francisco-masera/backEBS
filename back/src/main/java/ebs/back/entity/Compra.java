package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Compra implements Serializable {

	private Long id;
	private float precioUnitario;
	private float cantidad;
	private OrdenCompra ordenCompra;

	public Compra() {

	}

	public Compra(Long id, float precioUnitario, float cantidad, OrdenCompra ordenCompra) {
		this.id = id;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.ordenCompra = ordenCompra;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCompra", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, updatable = false)
	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Column(nullable = false, updatable = false)
	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@OneToOne
	@JoinColumn(name = "idOrden", nullable = false)
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public void calcularTotal() {

	}

	public void comprarArticulo(ArticuloInsumo articulo) {

	}

	public void comprarArticulo(ArticuloInsumoVenta articulo) {

	}
}
