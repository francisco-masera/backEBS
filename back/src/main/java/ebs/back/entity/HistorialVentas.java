package ebs.back.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HistorialVentas {

	private long id;
	private LocalDate fechaVenta;
	private float precioVenta;
	private float costo;
	private double total;
	private List<ArticuloVenta> articulos = new ArrayList<>();

	public HistorialVentas(long id, LocalDate fechaVenta, float precioVenta, float costo, double total,
			List<ArticuloVenta> articulos) {
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.precioVenta = precioVenta;
		this.costo = costo;
		this.total = total;
		this.articulos = articulos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	private Date convertirFecha() {
		return Date.valueOf(this.fechaVenta);
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<ArticuloVenta> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticuloVenta> articulos) {
		this.articulos = articulos;
	}

	public void calcularTotal() {

	}

	public void agregarArticulo(ArticuloManufacturado manufacturado) {

	}
}
