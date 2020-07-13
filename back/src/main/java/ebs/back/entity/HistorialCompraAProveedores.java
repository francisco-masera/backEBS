package ebs.back.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HistorialCompraAProveedores implements Serializable {

	private Long id;
	private float precioUnitario;
	private float cantidad;
	private LocalDateTime fechaCompra;
	private Insumo insumo;

	public HistorialCompraAProveedores() {

	}

	public HistorialCompraAProveedores(Long id, float precioUnitario, float cantidad, LocalDateTime fechaCompra,
			Insumo insumo) {
		this.id = id;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.fechaCompra = fechaCompra;
		this.insumo = insumo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCompra")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, updatable = false)
	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Column(nullable = false, updatable = false)
	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@ManyToOne
	@JoinColumn(name = "idInsumo", nullable = false)
	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public void calcularTotal() {

	}

	public void comprarArticulo(Insumo insumo) {

	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date convertirFecha() {
		return Timestamp.valueOf(this.fechaCompra);
	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {

		Instant instant = dateToConvert.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDateTime date = zdt.toLocalDateTime();
		return date;
	}
}
