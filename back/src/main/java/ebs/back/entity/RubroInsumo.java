package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RubroInsumo implements Serializable {

	private Long id;
	private String denominacion;
	private RubroInsumo rubroPadre;
	private List<RubroInsumo> rubros;
	private List<ArticuloInsumoVenta> insumosVenta;
	private List<ArticuloInsumo> insumos;

	public RubroInsumo() {

	}

	public RubroInsumo(Long id, String denominacion, RubroInsumo rubroPadre, List<RubroInsumo> rubros,
			List<ArticuloInsumoVenta> insumosVenta, List<ArticuloInsumo> insumos) {
		this.id = id;
		this.denominacion = denominacion;
		this.rubroPadre = rubroPadre;
		this.rubros = rubros;
		this.insumosVenta = insumosVenta;
		this.insumos = insumos;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRubroInsumo", nullable = false, insertable = false, updatable = false)
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

	@ManyToOne
	@JoinColumn(name = "rubroPadre", nullable = true)
	public RubroInsumo getRubroPadre() {
		return rubroPadre;
	}

	public void setRubroPadre(RubroInsumo rubroPadre) {
		this.rubroPadre = rubroPadre;
	}

	@OneToMany(mappedBy = "rubroPadre")
	public List<RubroInsumo> getRubros() {
		return rubros;
	}

	public void setRubros(List<RubroInsumo> rubros) {
		this.rubros = rubros;
	}

	@OneToMany(mappedBy = "rubro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ArticuloInsumoVenta> getInsumosVenta() {
		return insumosVenta;
	}

	public void setInsumosVenta(List<ArticuloInsumoVenta> insumosVenta) {
		this.insumosVenta = insumosVenta;
	}

	@OneToMany(mappedBy = "rubro")
	public List<ArticuloInsumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ArticuloInsumo> insumos) {
		this.insumos = insumos;
	}

	public void agregarArticulo(ArticuloInsumo articulo) {

	}

	public void agregarArticulo(ArticuloInsumoVenta articulo) {

	}
}
