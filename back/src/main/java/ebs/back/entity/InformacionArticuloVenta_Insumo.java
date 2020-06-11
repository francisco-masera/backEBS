package ebs.back.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idInsumoVenta")
public class InformacionArticuloVenta_Insumo extends InformacionArticuloVenta {

	private Insumo insumo;

	public InformacionArticuloVenta_Insumo() {
		super();
	}

	public InformacionArticuloVenta_Insumo(Insumo insumo) {
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
