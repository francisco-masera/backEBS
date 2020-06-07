package ebs.back.entity;

public class SugerenciaChef extends ArticuloVenta {

	private Long id;
	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private RecetaSugerida recetaSugerida;

	public SugerenciaChef() {
		super();
	}

	public SugerenciaChef(Long id, String denomiacion, String descripcion, float precioVenta, String imagen,
			boolean enVenta, DetallePedido detalle, HistorialVentas ventas, int tiempoCocina, boolean aptoCeliaco,
			boolean vegano, boolean vegetariano, RecetaSugerida recetaSugerida) {
		super(id, denomiacion, descripcion, precioVenta, imagen, enVenta, detalle, ventas);
		this.id = id;
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.recetaSugerida = recetaSugerida;
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

	public RecetaSugerida getRecetaSugerida() {
		return recetaSugerida;
	}

	public void setRecetaSugerida(RecetaSugerida recetaSugerida) {
		this.recetaSugerida = recetaSugerida;
	}

}
