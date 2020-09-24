package ebs.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Insumo implements Serializable {

	private Long idInsumo;
	private String unidadMedida;
	private String denominacion;
	private boolean esExtra;
	private boolean baja;
	private boolean esInsumo;
	private Stock stock = new Stock();
	private RubroInsumo rubroInsumo;
	private List<Receta> recetas;
	private List<RecetaSugerida> recetasSugeridas;
	private List<HistorialCompraAProveedores> historialCompras;

	public Insumo() {
	}

	public Insumo(Long idInsumo, String unidadMedida, String denominacion, boolean esExtra, boolean baja,
			boolean esInsumo, Stock stock, RubroInsumo rubroInsumo, List<Receta> recetas,
			List<RecetaSugerida> recetasSugeridas, List<HistorialCompraAProveedores> historialCompras) {
		this.idInsumo = idInsumo;
		this.unidadMedida = unidadMedida;
		this.denominacion = denominacion;
		this.esExtra = esExtra;
		this.baja = baja;
		this.esInsumo = esInsumo;
		this.stock = stock;
		this.rubroInsumo = rubroInsumo;
		this.recetas = recetas;
		this.recetasSugeridas = recetasSugeridas;
		this.historialCompras = historialCompras;
	}

	public Insumo(Long idInsumo) {
		this.idInsumo = idInsumo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInsumo")
	public Long getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Long idInsumo) {
		this.idInsumo = idInsumo;
	}

	@Column(nullable = false)
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Column(nullable = false)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@Column(nullable = false)
	public boolean isEsExtra() {
		return esExtra;
	}

	public void setEsExtra(boolean esExtra) {
		this.esExtra = esExtra;
	}

	@Column(nullable = false, columnDefinition = "boolean default false")
	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	@Column(nullable = false)
	public boolean isEsInsumo() {
		return esInsumo;
	}

	public void setEsInsumo(boolean esInsumo) {
		this.esInsumo = esInsumo;
	}

	@OneToOne
	@JoinColumn(name = "idStock", nullable = false)
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@ManyToOne
	@JoinColumn(name = "idRubro", nullable = false)
	public RubroInsumo getRubroInsumo() {
		return rubroInsumo;
	}

	public void setRubroInsumo(RubroInsumo rubroInsumo) {
		this.rubroInsumo = rubroInsumo;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "insumo", cascade = CascadeType.PERSIST)
	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "insumo", cascade = CascadeType.PERSIST)
	public List<RecetaSugerida> getRecetasSugeridas() {
		return recetasSugeridas;
	}

	public void setRecetasSugeridas(List<RecetaSugerida> recetasSugeridas) {
		this.recetasSugeridas = recetasSugeridas;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "insumo", cascade = CascadeType.PERSIST)
	public List<HistorialCompraAProveedores> getHistorialCompras() {
		return historialCompras;
	}

	public void setHistorialCompras(List<HistorialCompraAProveedores> historialCompras) {
		this.historialCompras = historialCompras;
	}
	

}
