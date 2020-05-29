package ebs.back.entity;

import java.beans.Transient;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class Receta {

	private long id;
	private float cantidadInsumo;
	private ArticuloManufacturado manufacturado;
	private List<ArticuloInsumo> insumos;

	public Receta(long id, float cantidadInsumo, ArticuloManufacturado manufacturado, List<ArticuloInsumo> insumos) {
		super();
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.manufacturado = manufacturado;
		this.insumos = insumos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	public ArticuloManufacturado getManufacturado() {
		return manufacturado;
	}

	public void setManufacturado(ArticuloManufacturado manufacturado) {
		this.manufacturado = manufacturado;
	}

	public List<ArticuloInsumo> getInsumo() {
		return insumos;
	}

	public void setInsumo(List<ArticuloInsumo> insumo) {
		this.insumos = insumo;
	}

}
