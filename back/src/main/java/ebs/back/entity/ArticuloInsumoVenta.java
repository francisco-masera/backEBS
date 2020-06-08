package ebs.back.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Insumo")
public class ArticuloInsumoVenta extends ArticuloVenta {

	private String unidadMedida;
	private OrdenCompra ordenCompra;
	private Stock stock;
	private RubroInsumo rubro;

	public ArticuloInsumoVenta() {
		super();
	}

	public ArticuloInsumoVenta(String unidadMedida, OrdenCompra ordenCompra, Stock stock, RubroInsumo rubro) {
		super();
		this.unidadMedida = unidadMedida;
		this.ordenCompra = ordenCompra;
		this.stock = stock;
		this.rubro = rubro;
	}

	@Column(nullable = false)
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
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
	public RubroInsumo getRubro() {
		return rubro;
	}

	public void setRubro(RubroInsumo rubro) {
		this.rubro = rubro;
	}

	@OneToOne
	@JoinColumn(name = "idOrden", nullable = false)
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

}
