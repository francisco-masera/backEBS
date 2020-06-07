package ebs.back.entity;

public class Config extends BaseEntity {


	private int cantidadCocineros;
	private String emailEmpresa;

	public Config() {
		super();
	}


	public Config(int cantidadCocineros, String emailEmpresa) {
		super();

		this.cantidadCocineros = cantidadCocineros;
		this.emailEmpresa = emailEmpresa;
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
