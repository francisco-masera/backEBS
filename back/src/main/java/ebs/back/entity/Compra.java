package ebs.back.entity;

public class Compra extends BaseEntity {

	private float precioUnitario;
	private float cantidad;
	private OrdenCompra ordenCompra;

	public Compra() {
		super();
	}

	public Compra(float precioUnitario, float cantidad, OrdenCompra ordenCompra) {
		super();

		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.ordenCompra = ordenCompra;
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
