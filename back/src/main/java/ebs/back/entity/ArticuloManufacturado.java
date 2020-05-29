package ebs.back.entity;

public class ArticuloManufacturado extends ArticuloVenta {

	private long id;
	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private RubroManufacturado rubro;
	private Receta receta;

	public ArticuloManufacturado(long id, String denomiacion, String descripcion, float precioVenta, String imagen,
			boolean enVenta, DetallePedido detalle, HistorialVentas ventas, int tiempoCocina, boolean aptoCeliaco,
			boolean vegano, boolean vegetariano, RubroManufacturado rubro, Receta receta) {
		super(id, denomiacion, descripcion, precioVenta, imagen, enVenta, detalle, ventas);
		this.id = id;
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.rubro = rubro;
		this.receta = receta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTiempoCocina() {
		return tiempoCocina;
	}

	public void setTiempoCocina(int tiempoCocina) {
		this.tiempoCocina = tiempoCocina;
	}

	public boolean isAptoCeliaco() {
		return aptoCeliaco;
	}

	public void setAptoCeliaco(boolean aptoCeliaco) {
		this.aptoCeliaco = aptoCeliaco;
	}

	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	public boolean isVegetariano() {
		return vegetariano;
	}

	public void setVegetariano(boolean vegetariano) {
		this.vegetariano = vegetariano;
	}

	public RubroManufacturado getRubro() {
		return rubro;
	}

	public void setRubro(RubroManufacturado rubro) {
		this.rubro = rubro;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}
