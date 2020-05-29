package ebs.back.entity;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TarjetaDebito {

	private long id;
	private long numero;
	private LocalDate vecimiento;
	private String nombreTitular;
	// private Cliente cliente;

	public TarjetaDebito(long id, long numero, LocalDate vecimiento, MonthDay diaVencimiento, String nombreTitular,
			Cliente cliente) {
		this.id = id;
		this.numero = numero;
		this.vecimiento = vecimiento;
		this.nombreTitular = nombreTitular;
		// this.cliente = cliente;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	@Column(nullable = false)
	public LocalDate getVecimiento() {
		return vecimiento;
	}

	public void setVecimiento(LocalDate vecimiento) {
		this.vecimiento = vecimiento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vencimiento", nullable = false)
	private Date convertirFecha() {
		return Date.from(this.vecimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Column(nullable = false)
	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/*
	 * public Cliente getCliente() { return cliente; }
	 * 
	 * public void setCliente(Cliente cliente) { this.cliente = cliente; }
	 */
	public boolean tarjetaVencida() {
		return false;
	}

}
