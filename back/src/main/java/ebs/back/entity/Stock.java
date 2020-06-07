package ebs.back.entity;

public class Stock {

	private Long id;
	private long actual;
	private int minimo;
	private long maximo;
	private ArticuloInsumo articuloInsumo;
	private ArticuloInsumoVenta articuloInsumoVenta;

	public Stock() {
	}

	public Stock(Long id, long actual, int minimo, long maximo, ArticuloInsumo articuloInsumo,
			ArticuloInsumoVenta articuloInsumoVenta) {
		this.id = id;
		this.actual = actual;
		this.minimo = minimo;
		this.maximo = maximo;
		this.articuloInsumo = articuloInsumo;
		this.articuloInsumoVenta = articuloInsumoVenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActual() {
		return actual;
	}

	public void setActual(long actual) {
		this.actual = actual;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public long getMaximo() {
		return maximo;
	}

	public void setMaximo(long maximo) {
		this.maximo = maximo;
	}

	public ArticuloInsumo getArticuloInsumo() {
		return articuloInsumo;
	}

	public void setArticuloInsumo(ArticuloInsumo articuloInsumo) {
		this.articuloInsumo = articuloInsumo;
	}

	public ArticuloInsumoVenta getArticuloInsumoVenta() {
		return articuloInsumoVenta;
	}

	public void setArticuloInsumoVenta(ArticuloInsumoVenta articuloInsumoVenta) {
		this.articuloInsumoVenta = articuloInsumoVenta;
	}

	public boolean stockBajo(ArticuloInsumoVenta articulo) {
		return false;
	}

	public boolean stockBajo(ArticuloInsumo articulo) {
		return false;
	}

	public boolean stockAlto(ArticuloInsumoVenta articulo) {
		return false;
	}

	public boolean stockAlto(ArticuloInsumo articulo) {
		return false;
	}

	public void pedirStock(ArticuloInsumoVenta articulo) {

	}

	public void pedirStock(ArticuloInsumo articulo) {

	}

}
