package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
	private boolean baja;
	private ArticuloManufacturado manufacturado;
	private List<ArticuloInsumo> insumos;

	public Receta() {

	}

	public Receta(Long id, float cantidadInsumo, boolean baja, ArticuloManufacturado manufacturado,
			List<ArticuloInsumo> insumos) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.baja = baja;
		this.manufacturado = manufacturado;
		this.insumos = insumos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idReceta", nullable = false, insertable = false, updatable = false)
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
