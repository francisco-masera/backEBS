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
	private boolean baja;
	private List<ArticuloManufacturado> manufacturados = new ArrayList<>();

	public RubroManufacturado() {

	}

	public RubroManufacturado(Long id, String denominacion, boolean baja, List<ArticuloManufacturado> manufacturados) {
		this.id = id;
		this.denominacion = denominacion;
		this.baja = baja;
		this.manufacturados = manufacturados;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRubroManufacturado")
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

	@Column(nullable = false, columnDefinition = "boolean default false")
	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
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
