package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private Receta receta;
	private RecetaSugerida recetaSugerida;
	private HistorialCompraAProveedores historialCompra;

	public Insumo() {
	}

	public Insumo(Long idInsumo, String unidadMedida, String denominacion, boolean esExtra, boolean baja,
			boolean esInsumo, Stock stock, RubroInsumo rubroInsumo, Receta receta, RecetaSugerida recetaSugerida,
			HistorialCompraAProveedores historialCompra) {
		this.idInsumo = idInsumo;
		this.unidadMedida = unidadMedida;
		this.denominacion = denominacion;
		this.esExtra = esExtra;
		this.baja = baja;
		this.esInsumo = esInsumo;
		this.stock = stock;
		this.rubroInsumo = rubroInsumo;
		this.receta = receta;
		this.recetaSugerida = recetaSugerida;
		this.historialCompra = historialCompra;
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

	@OneToOne
	@JoinColumn(name = "idRubro", nullable = false)
	public RubroInsumo getRubroInsumo() {
		return rubroInsumo;
	}

	public void setRubroInsumo(RubroInsumo rubroInsumo) {
		this.rubroInsumo = rubroInsumo;
	}

	@ManyToOne
	@JoinColumn(name = "idReceta", nullable = false)
	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	@ManyToOne
	@JoinColumn(name = "idRecetaSugerida", nullable = false)
	public RecetaSugerida getRecetaSugerida() {
		return recetaSugerida;
	}

	public void setRecetaSugerida(RecetaSugerida recetaSugerida) {
		this.recetaSugerida = recetaSugerida;
	}

	@OneToOne(mappedBy = "insumo")
	public HistorialCompraAProveedores getHistorialCompra() {
		return historialCompra;
	}

	public void setHistorialCompra(HistorialCompraAProveedores historialCompra) {
		this.historialCompra = historialCompra;
	}

}
