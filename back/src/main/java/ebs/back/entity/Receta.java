package ebs.back.entity;

import java.util.List;

public class Receta {

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
