package ebs.back.entity.wrapper;

public class RecetaSugeridaWrapper {

	private Long id;
	private float cantidadInsumo;
	private Long idInsumo;
	private Long idSugerencia;

	public RecetaSugeridaWrapper(Long id, float cantidadInsumo, Long idInsumo, Long idSugerencia) {
		this.id = id;
		this.cantidadInsumo = cantidadInsumo;
		this.idInsumo = idInsumo;
		this.idSugerencia = idSugerencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	public Long getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Long idInsumo) {
		this.idInsumo = idInsumo;
	}

	public Long getIdSugerencia() {
		return idSugerencia;
	}

	public void setIdSugerencia(Long idSugerencia) {
		this.idSugerencia = idSugerencia;
	}

}
