package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RubroManufacturado implements Serializable {

	private Long id;
	private String denominacion;
	private ArticuloManufacturado manufacturado;

	public RubroManufacturado() {

	}

	public RubroManufacturado(Long id, String deominacion, ArticuloManufacturado manufacturado) {
		this.id = id;
		this.denominacion = deominacion;
		this.manufacturado = manufacturado;
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

	@OneToOne(mappedBy = "rubro")
	public ArticuloManufacturado getManufacturado() {
		return manufacturado;
	}

	public void setManufacturado(ArticuloManufacturado manufacturado) {
		this.manufacturado = manufacturado;
	}

}
