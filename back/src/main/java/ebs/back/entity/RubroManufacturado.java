package ebs.back.entity;

public class RubroManufacturado extends BaseEntity {

	private String denominacion;
	private ArticuloManufacturado manufacturado;

	public RubroManufacturado() {
		super();
	}

	public RubroManufacturado(String denominacion, ArticuloManufacturado manufacturado) {
		super();
		this.denominacion = denominacion;
		this.manufacturado = manufacturado;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String deominacion) {
		this.denominacion = deominacion;
	}

	public ArticuloManufacturado getManufacturado() {
		return manufacturado;
	}

	public void setManufacturado(ArticuloManufacturado manufacturado) {
		this.manufacturado = manufacturado;
	}

}
