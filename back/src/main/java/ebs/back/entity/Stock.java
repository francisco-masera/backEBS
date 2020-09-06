package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stock implements Serializable {

	private Long id;
	private Float actual;
	private Float minimo;
	private Float maximo;
	private Insumo insumo;

	public Stock() {

	}

	public Stock(Long id, Float actual, Float minimo, Float maximo, Insumo insumo) {
		this.id = id;
		this.actual = actual;
		this.minimo = minimo;
		this.maximo = maximo;
		this.insumo = insumo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idStock")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Float getActual() {
		return actual;
	}

	public void setActual(Float actual) {
		this.actual = actual;
	}

	@Column(nullable = false)
	public Float getMinimo() {
		return minimo;
	}

	public void setMinimo(Float minimo) {
		this.minimo = minimo;
	}

	@Column(nullable = false)
	public Float getMaximo() {
		return maximo;
	}

	public void setMaximo(Float maximo) {
		this.maximo = maximo;
	}

	@JsonIgnore
	@OneToOne(mappedBy = "stock")
	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public boolean stockBajo(Insumo insumo) {
		return false;
	}

	public boolean stockAlto(Insumo insumo) {
		return false;
	}

	public void pedirStock(Insumo insumo) {

	}

}
