package ebs.back.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Sugerencia")
public class SugerenciaChef extends ArticuloVenta {

	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private RecetaSugerida recetaSugerida;

	public SugerenciaChef() {
		super();
	}

	public SugerenciaChef(String denominacion, String descripcion, float precioVenta, String imagen, boolean enVenta,
			DetallePedido detalle, HistorialVentas ventas, int tiempoCocina, boolean aptoCeliaco, boolean vegano,
			boolean vegetariano, RecetaSugerida recetaSugerida) {
		super();
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.recetaSugerida = recetaSugerida;
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

	@OneToOne(mappedBy = "sugerenciaChef")
	public RecetaSugerida getRecetaSugerida() {
		return recetaSugerida;
	}

	public void setRecetaSugerida(RecetaSugerida recetaSugerida) {
		this.recetaSugerida = recetaSugerida;
	}

}
