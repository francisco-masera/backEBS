package ebs.back.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class RecetaSugerida {

	private long id;
	private float cantidadInsumo;
	private boolean estado;
	private List<ArticuloInsumo> insumos = new ArrayList<>();
	private SugerenciaChef sugerenciaChef;

	public RecetaSugerida(long id, float cantidadInsumo, boolean estado, List<ArticuloInsumo> insumos,
			SugerenciaChef sugerenciaChef) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.estado = estado;
		this.insumos = insumos;
		this.sugerenciaChef = sugerenciaChef;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<ArticuloInsumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ArticuloInsumo> insumos) {
		this.insumos = insumos;
	}

	public SugerenciaChef getSugerenciaChef() {
		return sugerenciaChef;
	}

	public void setSugerenciaChef(SugerenciaChef sugerenciaChef) {
		this.sugerenciaChef = sugerenciaChef;
	}

}
