package ebs.back.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "idArticuloManufacturado")
public class ArticuloManufacturado extends InformacionArticuloVenta {

	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private String denominacion;
	private boolean baja;
	private RubroManufacturado rubro;
	private List<Receta> recetas;

	public ArticuloManufacturado() {
		super();
	}

	public ArticuloManufacturado(int tiempoCocina, boolean aptoCeliaco, boolean vegano, boolean vegetariano,
			String denominacion, boolean baja, RubroManufacturado rubro, List<Receta> recetas) {
		super();
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.denominacion = denominacion;
		this.baja = baja;
		this.rubro = rubro;
		this.recetas = recetas;
	}

	public ArticuloManufacturado(Long id, String descripcion, float precioVenta, String imagen,
			List<DetallePedido> detalles, List<HistorialVentas> historialPrecios, int tiempoCocina, boolean aptoCeliaco,
			boolean vegano, boolean vegetariano, String denominacion, boolean baja, RubroManufacturado rubro,
			List<Receta> recetas) {
		super(id, descripcion, precioVenta, imagen, detalles, historialPrecios);
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.denominacion = denominacion;
		this.baja = baja;
		this.rubro = rubro;
		this.recetas = recetas;
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

	@Column(nullable = false, columnDefinition = "boolean default false")
	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	@ManyToOne
	@JoinColumn(name = "idRubro", nullable = false)
	public RubroManufacturado getRubro() {
		return rubro;
	}

	public void setRubro(RubroManufacturado rubro) {
		this.rubro = rubro;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "manufacturado", cascade = CascadeType.PERSIST)
	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

}
