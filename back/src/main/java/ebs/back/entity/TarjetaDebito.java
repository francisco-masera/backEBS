package ebs.back.entity;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.Date;

public class TarjetaDebito {

	private long id;
	private long numero;
	private LocalDate vecimiento;
	private String nombreTitular;
	private Cliente cliente;

	public TarjetaDebito() {
	}

	public TarjetaDebito(long id, long numero, LocalDate vecimiento, MonthDay diaVencimiento, String nombreTitular,
			Cliente cliente) {
		this.id = id;
		this.numero = numero;
		this.vecimiento = vecimiento;
		this.nombreTitular = nombreTitular;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	private Date convertirFecha() {
		return Date.from(this.vecimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

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
