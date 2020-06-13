package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SugerenciaChef implements Serializable {

	private Long id;
	private int tiempoCocina;
	private boolean aptoCeliaco;
	private boolean vegano;
	private boolean vegetariano;
	private String denominacion;
	private String descripcion;
	private String imagen;
	private List<RecetaSugerida> recetasSugeridas;

	public SugerenciaChef() {

	}

	public SugerenciaChef(Long id, int tiempoCocina, boolean aptoCeliaco, boolean vegano, boolean vegetariano,
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSugerencia")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false)
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@OneToMany(mappedBy = "sugerenciaChef", cascade = CascadeType.PERSIST)
	public List<RecetaSugerida> getRecetasSugeridas() {
		return recetasSugeridas;
	}

	public void setRecetasSugeridas(List<RecetaSugerida> recetasSugeridas) {
		this.recetasSugeridas = recetasSugeridas;
	}

}
