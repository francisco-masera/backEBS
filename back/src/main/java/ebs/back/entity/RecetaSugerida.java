package ebs.back.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RecetaSugerida implements Serializable {

	private Long id;
	private float cantidadInsumo;
	private boolean estado;
	private List<ArticuloInsumo> insumos = new ArrayList<>();
	private SugerenciaChef sugerenciaChef;

	public RecetaSugerida() {

	}

	public RecetaSugerida(Long id, float cantidadInsumo, boolean estado, List<ArticuloInsumo> insumos,
			SugerenciaChef sugerenciaChef) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.estado = estado;
		this.insumos = insumos;
		this.sugerenciaChef = sugerenciaChef;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSugerencia", nullable = false, updatable = false, insertable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	@Column(nullable = false)
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@OneToMany(mappedBy = "recetaSugerida")
	public List<ArticuloInsumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ArticuloInsumo> insumos) {
		this.insumos = insumos;
	}

	@OneToOne
	@JoinColumn(name = "idSugerencia", nullable = false)
	public SugerenciaChef getSugerenciaChef() {
		return sugerenciaChef;
	}

	public void setSugerenciaChef(SugerenciaChef sugerenciaChef) {
		this.sugerenciaChef = sugerenciaChef;
	}

}
