package ebs.back.entity;

public class ArticuloInsumoVenta extends ArticuloVenta {

	private long id;
	private int unidadMedida;
	private float costo;
	private OrdenCompra ordenCompra;
	private Stock stock;
	private RubroInsumo rubro;

	public ArticuloInsumoVenta() {
		super();
	}

	public ArticuloInsumoVenta(long id, String denomiacion, String descripcion, float precioVenta, String imagen,
			boolean enVenta, float costo, DetallePedido detalle, HistorialVentas ventas, int unidadMedida,
			OrdenCompra ordenCompra, Stock stock, RubroInsumo rubro) {
		super(id, denomiacion, descripcion, precioVenta, imagen, enVenta, detalle, ventas);
		this.id = id;
		this.unidadMedida = unidadMedida;
		this.costo = costo;
		this.ordenCompra = ordenCompra;
		this.stock = stock;
		this.rubro = rubro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(int unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
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
