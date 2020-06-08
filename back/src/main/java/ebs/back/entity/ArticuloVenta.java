package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ArticuloVenta implements Serializable {

	protected Long id;
	protected String denominacion;
	protected String descripcion;
	protected float precioVenta;
	protected String imagen;
	protected boolean enVenta;
	protected DetallePedido detalle;
	protected HistorialVentas ventas;

	public ArticuloVenta() {
	}

	public ArticuloVenta(Long id, String denominacion, String descripcion, float precioVenta, String imagen,
			boolean enVenta, DetallePedido detalle, HistorialVentas ventas) {
		this.id = id;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.imagen = imagen;
		this.enVenta = enVenta;
		this.detalle = detalle;
		this.ventas = ventas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@OneToOne(mappedBy = "articulo")
	public DetallePedido getDetalle() {
		return detalle;
	}

	public void setDetalle(DetallePedido detalle) {
		this.detalle = detalle;
	}

	@ManyToOne()
	@JoinColumn(name = "idHistorial")
	public HistorialVentas getVentas() {
		return ventas;
	}

	public void setVentas(HistorialVentas ventas) {
		this.ventas = ventas;
	}

}
