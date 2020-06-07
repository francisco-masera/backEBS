package ebs.back.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Factura extends BaseEntity {

	private LocalDateTime fechaHora;
	private long numero;
	private double total;
	private boolean formaPago;
	private Pedido pedido;

	public Factura() {
		super();
	}

	public Factura(LocalDateTime fechaHora, long numero, double total, boolean formaPago, Pedido pedido) {
		super();
		this.fechaHora = fechaHora;
		this.numero = numero;
		this.total = total;
		this.formaPago = formaPago;
		this.pedido = pedido;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	private Date convertirFechaHora() {
		return Timestamp.valueOf(this.fechaHora);
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isFormaPago() {
		return formaPago;
	}

	public void setFormaPago(boolean formaPago) {
		this.formaPago = formaPago;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean esDebito() {
		return false;
	}

	public void calcularTotal() {

	}

}
