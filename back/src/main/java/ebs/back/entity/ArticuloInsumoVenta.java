package ebs.back.entity;

public class ArticuloInsumoVenta extends ArticuloVenta {

	private Long id;
	private String unidadMedida;
	private OrdenCompra ordenCompra;
	private Stock stock;
	private RubroInsumo rubro;

	public ArticuloInsumoVenta() {
		super();
	}

	public ArticuloInsumoVenta(Long id, String denomiacion, String descripcion, float precioVenta, String imagen,
			boolean enVenta, DetallePedido detalle, HistorialVentas ventas, String unidadMedida,
			OrdenCompra ordenCompra, Stock stock, RubroInsumo rubro) {
		super(id, denomiacion, descripcion, precioVenta, imagen, enVenta, detalle, ventas);
		this.id = id;
		this.unidadMedida = unidadMedida;

		this.ordenCompra = ordenCompra;
		this.stock = stock;
		this.rubro = rubro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public RubroInsumo getRubro() {
		return rubro;
	}

	public void setRubro(RubroInsumo rubro) {
		this.rubro = rubro;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

}
