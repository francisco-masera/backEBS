package ebs.back.entity;

public class Config {

	private Long id;
	private int cantidadCocineros;
	private String emailEmpresa;

	public Config() {
	}

	public Config(Long id, int cantidadCocineros, String emailEmpresa) {
		this.id = id;
		this.cantidadCocineros = cantidadCocineros;
		this.emailEmpresa = emailEmpresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidadCocineros() {
		return cantidadCocineros;
	}

	public void setCantidadCocineros(int cantidadCocineros) {
		this.cantidadCocineros = cantidadCocineros;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

}
