package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Receta implements Serializable {

	private Long id;
	private float cantidadInsumo;
	private ArticuloManufacturado manufacturado;
	private List<ArticuloInsumo> insumos;

	public Receta() {
	
	}

	public Receta(Long id, float cantidadInsumo, ArticuloManufacturado manufacturado, List<ArticuloInsumo> insumos) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.manufacturado = manufacturado;
		this.insumos = insumos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	@OneToOne
	@JoinColumn(name = "idManufacturado", nullable = false)
	public ArticuloManufacturado getManufacturado() {
		return manufacturado;
	}

	public void setManufacturado(ArticuloManufacturado manufacturado) {
		this.manufacturado = manufacturado;
	}

	@OneToMany(mappedBy = "receta")
	public List<ArticuloInsumo> getInsumo() {
		return insumos;
	}

	public void setInsumo(List<ArticuloInsumo> insumo) {
		this.insumos = insumo;
	}

}
