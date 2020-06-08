package ebs.back.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrdenCompra implements Serializable {

	private Long id;
	private long numero;
	private LocalDateTime fechaHora;
	private Compra compra;
	private List<ArticuloInsumo> insumos;
	private List<ArticuloInsumoVenta> insumosVenta;

	public OrdenCompra() {

	}

	public OrdenCompra(Long id, long numero, LocalDateTime fechaHora, Compra compra, List<ArticuloInsumo> insumos,
			List<ArticuloInsumoVenta> insumosVenta) {
		this.id = id;
		this.numero = numero;
		this.fechaHora = fechaHora;
		this.compra = compra;
		this.insumos = insumos;
		this.insumosVenta = insumosVenta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date convertirFecha() {
		return Timestamp.valueOf(this.fechaHora);
	}

	@OneToOne(mappedBy = "ordenCompra")
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	@OneToMany(mappedBy = "ordenCompra")
	public List<ArticuloInsumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ArticuloInsumo> insumos) {
		this.insumos = insumos;
	}

	@OneToMany(mappedBy = "ordenCompra")
	public List<ArticuloInsumoVenta> getInsumosVenta() {
		return insumosVenta;
	}

	public void setInsumosVenta(List<ArticuloInsumoVenta> insumosVenta) {
		this.insumosVenta = insumosVenta;
	}
}
