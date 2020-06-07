package ebs.back.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrdenCompra extends BaseEntity {

	private long numero;
	private LocalDateTime fechaHora;
	private Compra compra;
	private List<ArticuloInsumo> insumos;
	private List<ArticuloInsumoVenta> insumosVenta;

	public OrdenCompra() {
		super();
	}

	public OrdenCompra(long numero, LocalDateTime fechaHora, Compra compra, List<ArticuloInsumo> insumos,
			List<ArticuloInsumoVenta> insumosVenta) {
		super();
		this.numero = numero;
		this.fechaHora = fechaHora;
		this.compra = compra;
		this.insumos = insumos;
		this.insumosVenta = insumosVenta;
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

	private Date convertirFecha() {
		return Timestamp.valueOf(this.fechaHora);
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<ArticuloInsumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ArticuloInsumo> insumos) {
		this.insumos = insumos;
	}

	public List<ArticuloInsumoVenta> getInsumosVenta() {
		return insumosVenta;
	}

	public void setInsumosVenta(List<ArticuloInsumoVenta> insumosVenta) {
		this.insumosVenta = insumosVenta;
	}
}
