package ebs.back.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "idInsumoVenta")
@Table(name = "informacionarticuloventa_insumo")
public class InformacionInsumoVenta extends InformacionArticuloVenta {

	private Insumo insumo;

	public InformacionInsumoVenta() {
		super();
	}

	public InformacionInsumoVenta(Long id, String descripcion, float precioVenta, String imagen,
			List<DetallePedido> detalles, List<HistorialVentas> historialPrecios, Insumo insumo) {
		super(id, descripcion, precioVenta, imagen, detalles, historialPrecios);
		this.insumo = insumo;
	}

	public InformacionInsumoVenta(Insumo insumo) {
		super();
		this.insumo = insumo;
	}

	@OneToOne
	@JoinColumn(name = "idInsumo", nullable = false)
	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

}
