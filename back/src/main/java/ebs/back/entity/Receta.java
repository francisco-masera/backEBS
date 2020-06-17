package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Receta implements Serializable {

	private Long id;
	private float cantidadInsumo;
	private boolean baja;
	private ArticuloManufacturado manufacturado;
	private Insumo insumo;

	public Receta() {

	}

	public Receta(Long id, float cantidadInsumo, boolean baja, ArticuloManufacturado manufacturado, Insumo insumo) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.baja = baja;
		this.manufacturado = manufacturado;
		this.insumo = insumo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idReceta")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	@Column(nullable = false, columnDefinition = "boolean default false")
	public boolean getBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	
	@ManyToOne
	@JoinColumn(name = "idManufacturado", nullable = false)
	public ArticuloManufacturado getManufacturado() {
		return manufacturado;
	}

	public void setManufacturado(ArticuloManufacturado manufacturado) {
		this.manufacturado = manufacturado;
	}

	@ManyToOne
	@JoinColumn(name = "idInsumo", nullable = false)
	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

}
