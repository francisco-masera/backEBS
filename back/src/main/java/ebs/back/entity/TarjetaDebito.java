package ebs.back.entity;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.Date;

public class TarjetaDebito extends BaseEntity {

	private long numero;
	private LocalDate vecimiento;
	private String nombreTitular;
	private Cliente cliente;

	public TarjetaDebito() {
		super();
	}

	public TarjetaDebito(long numero, LocalDate vecimiento, String nombreTitular, Cliente cliente) {
		super();
		this.numero = numero;
		this.vecimiento = vecimiento;
		this.nombreTitular = nombreTitular;
		this.cliente = cliente;
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
