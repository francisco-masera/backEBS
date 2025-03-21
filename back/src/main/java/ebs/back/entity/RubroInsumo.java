package ebs.back.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RubroInsumo implements Serializable {

	private Long id;
	private String denominacion;
	private List<Insumo> insumos = new ArrayList<>();

	public RubroInsumo() {

	}

	public RubroInsumo(Long id, String denominacion, List<Insumo> insumos) {
		this.id = id;
		this.denominacion = denominacion;
		this.insumos = insumos;

	}

	@Id
	@Column(name = "idRubroInsumo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "rubroInsumo", cascade = CascadeType.PERSIST)
	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	public void agregarArticulo(Insumo articulo) {

	}

}
