package ebs.back.entity;

import java.util.List;

public class Receta extends BaseEntity {

	private float cantidadInsumo;
	private ArticuloManufacturado manufacturado;
	private List<ArticuloInsumo> insumos;

	public Receta() {
		super();
	}

	public Receta(float cantidadInsumo, ArticuloManufacturado manufacturado, List<ArticuloInsumo> insumos) {
		super();

		this.cantidadInsumo = cantidadInsumo;
		this.manufacturado = manufacturado;
		this.insumos = insumos;
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
