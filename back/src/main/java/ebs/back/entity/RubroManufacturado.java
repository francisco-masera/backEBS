package ebs.back.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RubroManufacturado implements Serializable {

	private Long id;
	private String denominacion;
	private List<ArticuloManufacturado> manufacturados = new ArrayList<>();

	public RubroManufacturado() {

	}

	public RubroManufacturado(Long id, String denominacion, List<ArticuloManufacturado> manufacturados) {
		this.id = id;
		this.denominacion = denominacion;
		this.manufacturados = manufacturados;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRubroManufacturado", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String deominacion) {
		this.denominacion = deominacion;
	}

	@OneToMany(mappedBy = "rubro")
	// @JsonIgnore
	public List<ArticuloManufacturado> getManufacturados() {
		return manufacturados;
	}

	public void setManufacturados(List<ArticuloManufacturado> manufacturados) {
		this.manufacturados = manufacturados;
	}

}
