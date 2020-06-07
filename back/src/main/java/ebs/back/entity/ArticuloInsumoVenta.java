package ebs.back.entity;

public class ArticuloInsumoVenta extends ArticuloVenta {

	private String unidadMedida;
	private OrdenCompra ordenCompra;
	private Stock stock;
	private RubroInsumo rubro;

	public ArticuloInsumoVenta() {
		super();
	}

	public ArticuloInsumoVenta(String unidadMedida, OrdenCompra ordenCompra, Stock stock, RubroInsumo rubro) {
		super();
		this.unidadMedida = unidadMedida;
		this.ordenCompra = ordenCompra;
		this.stock = stock;
		this.rubro = rubro;
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
