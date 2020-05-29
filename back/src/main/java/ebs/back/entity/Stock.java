package ebs.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Stock {

	private long id;
	private long actual;
	private int minimo;
	private long maximo;
	private ArticuloInsumo articuloInsumo;
	private ArticuloInsumoVenta articuloInsumoVenta;

	public Stock(long id, long actual, int minimo, long maximo, ArticuloInsumo articuloInsumo,
			ArticuloInsumoVenta articuloInsumoVenta) {
		this.id = id;
		this.actual = actual;
		this.minimo = minimo;
		this.maximo = maximo;
		this.articuloInsumo = articuloInsumo;
		this.articuloInsumoVenta = articuloInsumoVenta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getActual() {
		return actual;
	}

	public void setActual(long actual) {
		this.actual = actual;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public long getMaximo() {
		return maximo;
	}

	public void setMaximo(long maximo) {
		this.maximo = maximo;
	}

	public ArticuloInsumo getArticuloInsumo() {
		return articuloInsumo;
	}

	public void setArticuloInsumo(ArticuloInsumo articuloInsumo) {
		this.articuloInsumo = articuloInsumo;
	}

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
