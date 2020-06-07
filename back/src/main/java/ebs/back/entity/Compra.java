package ebs.back.entity;

public class Compra {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
