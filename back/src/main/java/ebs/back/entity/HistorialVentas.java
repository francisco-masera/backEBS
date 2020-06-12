package ebs.back.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HistorialVentas implements Serializable {

	private Long id;
	private LocalDate fechaVenta;
	private float precioVenta;
	private float costo;
	private InformacionArticuloVenta articulo;

	public HistorialVentas() {

	}

	public HistorialVentas(Long id, LocalDate fechaVenta, float precioVenta, float costo,
			InformacionArticuloVenta articulo) {
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.precioVenta = precioVenta;
		this.costo = costo;

		this.articulo = articulo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idHistorial")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	@Column(nullable = false, updatable = false)
	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	@Column(nullable = false, updatable = false)
	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	@ManyToOne
	@JoinColumn(name = "idArticulo", nullable = false)
	public InformacionArticuloVenta getArticulo() {
		return articulo;
	}

	public void setArticulo(InformacionArticuloVenta articulo) {
		this.articulo = articulo;
	}

	public void calcularTotal() {

	}

	public void agregarArticulo(InformacionArticuloVenta articulo) {

	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date convertirFecha() {
		return Date.valueOf(this.fechaVenta);
	}
}
