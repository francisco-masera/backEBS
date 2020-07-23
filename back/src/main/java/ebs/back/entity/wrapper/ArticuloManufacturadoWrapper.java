package ebs.back.entity.wrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ebs.back.entity.Receta;
import ebs.back.entity.RubroManufacturado;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticuloManufacturadoWrapper {
	private Long idArticuloManufacturado;
	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private String denominacion;
	private boolean baja;
	private RubroManufacturado rubro;
	private List<Receta> recetas;

	public ArticuloManufacturadoWrapper() {
	}

	public Long getId() {
		return idArticuloManufacturado;
	}

	public void setId(Long idArticuloManufacturado) {
		this.idArticuloManufacturado = idArticuloManufacturado;
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

	public RubroManufacturado getRubro() {
		return rubro;
	}

	public void setRubro(RubroManufacturado rubro) {
		this.rubro = rubro;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

}
