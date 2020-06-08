package ebs.back.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TarjetaDebito implements Serializable {

	private Long id;
	private long numero;
	private LocalDate vecimiento;
	private String nombreTitular;
	private Cliente cliente;

	public TarjetaDebito() {
	}

	public TarjetaDebito(Long id, long numero, LocalDate vecimiento, MonthDay diaVencimiento, String nombreTitular,
			Cliente cliente) {
		this.id = id;
		this.numero = numero;
		this.vecimiento = vecimiento;
		this.nombreTitular = nombreTitular;
		this.cliente = cliente;
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

	public LocalDate getVecimiento() {
		return vecimiento;
	}

	public void setVecimiento(LocalDate vecimiento) {
		this.vecimiento = vecimiento;
	}

	@Temporal(TemporalType.DATE)
	private Date convertirFecha() {
		return Date.from(this.vecimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean tarjetaVencida() {
		return false;
	}

}
