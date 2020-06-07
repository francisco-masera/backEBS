package ebs.back.entity;

public class ArticuloInsumo extends BaseEntity {

	private String unidadMedida;
	private String denominacion;
	private boolean esExtra;
	private RubroInsumo rubro;
	private Receta receta;
	private RecetaSugerida recetaSugerida;
	private Stock stock;
	private OrdenCompra ordenCompra;

	public ArticuloInsumo() {
		super();
	}

	public ArticuloInsumo(String unidadMedida, String denominacion, boolean esExtra, RubroInsumo rubro, Receta receta,
			RecetaSugerida recetaSugerida, Stock stock, OrdenCompra ordenCompra) {
		super();
		this.unidadMedida = unidadMedida;
		this.denominacion = denominacion;
		this.esExtra = esExtra;
		this.rubro = rubro;
		this.receta = receta;
		this.recetaSugerida = recetaSugerida;
		this.stock = stock;
		this.ordenCompra = ordenCompra;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public boolean isEsExtra() {
		return esExtra;
	}

	public void setEsExtra(boolean esExtra) {
		this.esExtra = esExtra;
	}

	public RubroInsumo getRubro() {
		return rubro;
	}

	public void setRubro(RubroInsumo rubro) {
		this.rubro = rubro;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public RecetaSugerida getRecetaSugerida() {
		return recetaSugerida;
	}

	public void setRecetaSugerida(RecetaSugerida recetaSugerida) {
		this.recetaSugerida = recetaSugerida;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

}
