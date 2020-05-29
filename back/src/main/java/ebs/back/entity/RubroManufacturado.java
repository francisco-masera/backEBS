package ebs.back.entity;

public class RubroManufacturado {
	private long id;
	private String denominacion;
	private ArticuloManufacturado manufacturado;

	public RubroManufacturado() {
	}

	public RubroManufacturado(long id, String deominacion, ArticuloManufacturado manufacturado) {
		this.id = id;
		this.denominacion = deominacion;
		this.manufacturado = manufacturado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
