package ebs.back.entity;

public abstract class ArticuloVenta extends BaseEntity {

	protected String denominacion;
	protected String descripcion;
	protected float precioVenta;
	protected String imagen;
	protected boolean enVenta;
	protected DetallePedido detalle;
	protected HistorialVentas ventas;

	public ArticuloVenta() {
		super();
	}

	public ArticuloVenta(String denominacion, String descripcion, float precioVenta, String imagen, boolean enVenta,
			DetallePedido detalle, HistorialVentas ventas) {
		super();

		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.imagen = imagen;
		this.enVenta = enVenta;
		this.detalle = detalle;
		this.ventas = ventas;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isEnVenta() {
		return enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	public DetallePedido getDetalle() {
		return detalle;
	}

	public void setDetalle(DetallePedido detalle) {
		this.detalle = detalle;
	}

	public HistorialVentas getVentas() {
		return ventas;
	}

	public void setVentas(HistorialVentas ventas) {
		this.ventas = ventas;
	}

}
