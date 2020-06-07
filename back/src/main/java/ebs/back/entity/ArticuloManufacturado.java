package ebs.back.entity;

public class ArticuloManufacturado extends ArticuloVenta {

	private Long id;
	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private RubroManufacturado rubro;
	private Receta receta;

	public ArticuloManufacturado() {
		super();
	}

	public ArticuloManufacturado(Long id, int tiempoCocina, boolean aptoCeliaco, boolean vegano, boolean vegetariano,
			RubroManufacturado rubro, Receta receta) {
		super();
		this.id = id;
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.rubro = rubro;
		this.receta = receta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
