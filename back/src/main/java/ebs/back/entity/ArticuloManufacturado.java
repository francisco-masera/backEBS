package ebs.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idArticulo")
public class ArticuloManufacturado extends InformacionArticuloVenta {

	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private String denominacion;
	private boolean baja;
	private RubroManufacturado rubro;
	private Receta receta;

	public ArticuloManufacturado() {
		super();
	}

	public ArticuloManufacturado(int tiempoCocina, boolean aptoCeliaco, boolean vegano, boolean vegetariano,
			String denominacion, boolean baja, RubroManufacturado rubro, Receta receta) {
		super();
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.denominacion = denominacion;
		this.baja = baja;
		this.rubro = rubro;
		this.receta = receta;
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

	@Column(nullable = false)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	@OneToOne
	@JoinColumn(name = "idRubro", nullable = false)
	public RubroManufacturado getRubro() {
		return rubro;
	}

	public void setRubro(RubroManufacturado rubro) {
		this.rubro = rubro;
	}

	@OneToOne(mappedBy = "manufacturado")
	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}
