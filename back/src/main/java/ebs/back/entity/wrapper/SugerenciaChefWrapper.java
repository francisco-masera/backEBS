package ebs.back.entity.wrapper;

import java.util.List;

import ebs.back.entity.RecetaSugerida;

public class SugerenciaChefWrapper {

	private Long id;
	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private String denominacion;
	private String descripcion;
	private String imagen;
	private List<RecetaSugerida> recetasSugeridas;

	public SugerenciaChefWrapper() {

	}

	public SugerenciaChefWrapper(Long id, int tiempoCocina, boolean aptoCeliaco, boolean vegano, boolean vegetariano,
			String denominacion, String descripcion, String imagen, List<RecetaSugerida> recetasSugeridas) {
		this.id = id;
		this.tiempoCocina = tiempoCocina;
		this.aptoCeliaco = aptoCeliaco;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.recetasSugeridas = recetasSugeridas;
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

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<RecetaSugerida> getRecetasSugeridas() {
		return recetasSugeridas;
	}

	public void setRecetasSugeridas(List<RecetaSugerida> recetasSugeridas) {
		this.recetasSugeridas = recetasSugeridas;
	}

}
