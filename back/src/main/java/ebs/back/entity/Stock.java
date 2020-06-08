package ebs.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stock implements Serializable {

	private Long id;
	private long actual;
	private int minimo;
	private long maximo;
	private ArticuloInsumo articuloInsumo;
	private ArticuloInsumoVenta articuloInsumoVenta;

	public Stock() {

	}

	public Stock(Long id, long actual, int minimo, long maximo, ArticuloInsumo articuloInsumo,
			ArticuloInsumoVenta articuloInsumoVenta) {
		this.id = id;
		this.actual = actual;
		this.minimo = minimo;
		this.maximo = maximo;

		this.articuloInsumo = articuloInsumo;
		this.articuloInsumoVenta = articuloInsumoVenta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idStock", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Long getActual() {
		return actual;
	}

	public void setActual(long actual) {
		this.actual = actual;
	}

	@Column(nullable = false)
	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	@Column(nullable = false)
	public long getMaximo() {
		return maximo;
	}

	public void setMaximo(long maximo) {
		this.maximo = maximo;
	}

	@OneToOne(mappedBy = "stock")
	public ArticuloInsumo getArticuloInsumo() {
		return articuloInsumo;
	}

	public void setArticuloInsumo(ArticuloInsumo articuloInsumo) {
		this.articuloInsumo = articuloInsumo;
	}

	@OneToOne(mappedBy = "stock")
	public ArticuloInsumoVenta getArticuloInsumoVenta() {
		return articuloInsumoVenta;
	}

	public void setArticuloInsumoVenta(ArticuloInsumoVenta articuloInsumoVenta) {
		this.articuloInsumoVenta = articuloInsumoVenta;
	}

	public boolean stockBajo(ArticuloInsumoVenta articulo) {
		return false;
	}

	public boolean stockBajo(ArticuloInsumo articulo) {
		return false;
	}

	public boolean stockAlto(ArticuloInsumoVenta articulo) {
		return false;
	}

	public boolean stockAlto(ArticuloInsumo articulo) {
		return false;
	}

	public void pedirStock(ArticuloInsumoVenta articulo) {

	}

	public void pedirStock(ArticuloInsumo articulo) {

	}

}
