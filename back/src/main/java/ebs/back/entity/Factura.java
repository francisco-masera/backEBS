package ebs.back.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Factura implements Serializable {

	private Long id;
	private LocalDateTime fechaHora;
	private long numero;
	private double total;
	private boolean formaPago;
	private Pedido pedido;

	public Factura() {
	
	}

	public Factura(Long id, LocalDateTime fechaHora, long numero, double total, boolean formaPago, Pedido pedido) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.numero = numero;
		this.total = total;
		this.formaPago = formaPago;
		this.pedido = pedido;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
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

	@OneToOne
	@JoinColumn(name = "idPedido", nullable = false)
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
