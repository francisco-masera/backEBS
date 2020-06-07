package ebs.back.entity;

public class DetallePedido {

	private Long id;
	private int cantidad;
	private ArticuloVenta articulo;
	private Pedido pedido;

	public DetallePedido() {
	}

	public DetallePedido(Long id, int cantidad, ArticuloVenta articulo, Pedido pedido) {
		this.id = id;
		this.cantidad = cantidad;
		this.articulo = articulo;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
