package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = ArticuloManufacturado.class), @Type(value = InformacionInsumoVenta.class) })
public abstract class InformacionArticuloVenta implements Serializable {

	protected Long id;
	protected String descripcion;
	protected float precioVenta;
	protected String imagen;
	protected List<DetallePedido> detalles;
	protected List<HistorialVentas> historialPrecios;

	public InformacionArticuloVenta() {
	}

	public InformacionArticuloVenta(Long id, String descripcion, float precioVenta, String imagen,
			List<DetallePedido> detalles, List<HistorialVentas> historialPrecios) {
		this.id = id;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.imagen = imagen;
		this.detalles = detalles;
		this.historialPrecios = historialPrecios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idArticuloVenta")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@JsonIgnore
	@OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
	public List<HistorialVentas> getHistorialPrecios() {
		return historialPrecios;
	}

	public void setHistorialPrecios(List<HistorialVentas> historialPrecios) {
		this.historialPrecios = historialPrecios;
	}

}
