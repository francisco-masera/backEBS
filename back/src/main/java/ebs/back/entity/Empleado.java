package ebs.back.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Empleado")
public class Empleado extends Persona {

	private String usuario;
	private String contrasenia;
	private String rol;

	public Empleado() {
		super();
	}

	public Empleado(String nombre, String apellido, int telefono, String email, String foto, List<Domicilio> domicilios,
			String usuario, String contrasenia, String rol) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	protected void agregarDomicilio(Domicilio domicilio) {
		this.domicilios.add(domicilio);
	}
}
