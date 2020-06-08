package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
	protected boolean baja;
	protected DetallePedido detalle;
	protected HistorialVentas ventas;

	public ArticuloVenta() {
	}

	public ArticuloVenta(Long id, String denominacion, String descripcion, float precioVenta, String imagen,
			boolean enVenta, boolean baja, DetallePedido detalle, HistorialVentas ventas) {
		this.id = id;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.imagen = imagen;
		this.enVenta = enVenta;
		this.baja = baja;
		this.detalle = detalle;
		this.ventas = ventas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idArticuloVenta", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false)
	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	@Column(nullable = false)
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column(nullable = false)
	public boolean isEnVenta() {
		return enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	@Column(nullable = false, columnDefinition = "boolean default false")
	public boolean getBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	@OneToOne(mappedBy = "articulo")
	public DetallePedido getDetalle() {
		return detalle;
	}

	public void setDetalle(DetallePedido detalle) {
		this.detalle = detalle;
	}

	@ManyToOne
	@JoinColumn(name = "idHistorial", nullable = false, updatable = false)
	public HistorialVentas getVentas() {
		return ventas;
	}

	public void setVentas(HistorialVentas ventas) {
		this.ventas = ventas;
	}

}
