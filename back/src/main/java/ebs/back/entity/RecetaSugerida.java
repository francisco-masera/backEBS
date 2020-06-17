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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RecetaSugerida implements Serializable {

	private Long id;
	private float cantidadInsumo;
	private Insumo insumo;
	private SugerenciaChef sugerenciaChef;

	public RecetaSugerida() {

	}

	public RecetaSugerida(Long id, float cantidadInsumo, boolean estado, Insumo insumo, SugerenciaChef sugerenciaChef) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.insumo = insumo;
		this.sugerenciaChef = sugerenciaChef;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSugerencia")
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

	@ManyToOne
	@JoinColumn(name = "idInsumo", nullable = false)
	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	
	@ManyToOne
	@JoinColumn(name = "idRecetaSugerida", nullable = false)
	public SugerenciaChef getSugerenciaChef() {
		return sugerenciaChef;
	}

	public void setSugerenciaChef(SugerenciaChef sugerenciaChef) {
		this.sugerenciaChef = sugerenciaChef;
	}

}
