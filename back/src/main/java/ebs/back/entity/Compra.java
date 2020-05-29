package ebs.back.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Compra {

	private long id;

	private float precioUnitario;
	private float cantidad;
	private OrdenCompra ordenCompra;

	public Compra(long id, float precioUnitario, float cantidad, OrdenCompra ordenCompra) {
		this.id = id;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.ordenCompra = ordenCompra;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

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
