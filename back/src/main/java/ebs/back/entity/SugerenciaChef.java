package ebs.back.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

	public SugerenciaChef(int tiempoCocina, boolean aptoCeliaco, boolean vegano, boolean vegetariano,
			RecetaSugerida recetaSugerida) {
		super();
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.recetaSugerida = recetaSugerida;
	}

	@Column(nullable = false)
	public int getTiempoCocina() {
		return tiempoCocina;
	}

	public void setTiempoCocina(int tiempoCocina) {
		this.tiempoCocina = tiempoCocina;
	}

	@Column(nullable = false)
	public boolean isAptoCeliaco() {
		return aptoCeliaco;
	}

	public void setAptoCeliaco(boolean aptoCeliaco) {
		this.aptoCeliaco = aptoCeliaco;
	}

	@Column(nullable = false)
	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	@Column(nullable = false)
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
