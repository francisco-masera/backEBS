package ebs.back.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Empleado")
public class Empleado extends Persona {

	private String usuario;
	private String contrasenia;
	private String rol;

	public Empleado() {
		super();
	}

	public Empleado(String usuario, String contrasenia, String rol) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
	}

	@Column(nullable = false)
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(nullable = false, unique = true)
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Column(nullable = false)
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public void agregarDomicilio(Domicilio domicilio) {
		this.domicilios.add(domicilio);
	}
}
