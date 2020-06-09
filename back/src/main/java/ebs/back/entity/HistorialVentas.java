package ebs.back.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HistorialVentas implements Serializable {

	private Long id;
	private LocalDate fechaVenta;
	private float precioVenta;
	private float costo;
	private double total;
	private List<ArticuloVenta> articulos = new ArrayList<>();

	public HistorialVentas() {

	}

	public HistorialVentas(Long id, LocalDate fechaVenta, float precioVenta, float costo, double total,
			List<ArticuloVenta> articulos) {
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.precioVenta = precioVenta;
		this.costo = costo;
		this.total = total;
		this.articulos = articulos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idHistorial", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date convertirFecha() {
		return Date.valueOf(this.fechaVenta);
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

	@Column(nullable = false, updatable = false)
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@OneToMany(mappedBy = "ventas")
	@JsonIgnore
	public List<ArticuloVenta> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticuloVenta> articulos) {
		this.articulos = articulos;
	}

	public void calcularTotal() {

	}

	public void agregarArticulo(ArticuloManufacturado articulo) {

	}

	public void agregarArticulo(ArticuloInsumoVenta agregarArticulo) {

	}
}
