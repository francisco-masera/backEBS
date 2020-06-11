package ebs.back.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "rubroInsumo")
	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	public void agregarArticulo(Insumo articulo) {

	}

}
