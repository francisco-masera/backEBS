package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ArticuloInsumo implements Serializable {

	private Long id;
	private String unidadMedida;
	private String denominacion;
	private boolean esExtra;
	private RubroInsumo rubro;
	private Receta receta;
	private RecetaSugerida recetaSugerida;
	private Stock stock;
	private OrdenCompra ordenCompra;

	public ArticuloInsumo() {

	}

	public ArticuloInsumo(Long id, String unidadMedida, String denominacion, boolean esExtra, RubroInsumo rubro,
			Receta receta, RecetaSugerida recetaSugerida, Stock stock, OrdenCompra ordenCompra) {
		this.id = id;
		this.unidadMedida = unidadMedida;
		this.denominacion = denominacion;
		this.esExtra = esExtra;
		this.rubro = rubro;
		this.receta = receta;
		this.recetaSugerida = recetaSugerida;
		this.stock = stock;
		this.ordenCompra = ordenCompra;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public boolean isEsExtra() {
		return esExtra;
	}

	public void setEsExtra(boolean esExtra) {
		this.esExtra = esExtra;
	}

	@ManyToOne
	@JoinColumn(name = "idRubro", nullable = false)
	public RubroInsumo getRubro() {
		return rubro;
	}

	public void setRubro(RubroInsumo rubro) {
		this.rubro = rubro;
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

	@OneToOne
	@JoinColumn(name = "idStock", nullable = false)
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@ManyToOne
	@JoinColumn(name = "idOrden", nullable = false)
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

}
