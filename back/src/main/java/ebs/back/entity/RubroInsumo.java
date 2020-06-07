package ebs.back.entity;

import java.util.List;

public class RubroInsumo {

	private Long id;
	private String denominacion;
	private RubroInsumo rubro;
	private List<RubroInsumo> rubros;
	private List<ArticuloInsumoVenta> insumosVenta;
	private List<ArticuloInsumo> insumos;

	public RubroInsumo() {
	}

	public RubroInsumo(Long id, String denominacion, RubroInsumo rubro, List<RubroInsumo> rubros,
			List<ArticuloInsumoVenta> insumosVenta, List<ArticuloInsumo> insumos) {
		this.id = id;
		this.denominacion = denominacion;
		this.rubro = rubro;
		this.rubros = rubros;
		this.insumosVenta = insumosVenta;
		this.insumos = insumos;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public RubroInsumo getRubro() {
		return rubro;
	}

	public void setRubro(RubroInsumo subRubro) {
		this.rubro = subRubro;
	}

	public List<RubroInsumo> getRubros() {
		return rubros;
	}

	public void setRubros(List<RubroInsumo> rubros) {
		this.rubros = rubros;
	}

	public List<ArticuloInsumoVenta> getInsumosVenta() {
		return insumosVenta;
	}

	public void setInsumosVenta(List<ArticuloInsumoVenta> insumosVenta) {
		this.insumosVenta = insumosVenta;
	}

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
