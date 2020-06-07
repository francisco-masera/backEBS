package ebs.back.entity;

import java.util.List;

public class Empleado extends Persona {

	private Long id;
	private String usuario;
	private String contrasenia;
	private String rol;

	public Empleado() {
		super();
	}

	public Empleado(Long id, String nombre, String apellido, int telefono, String email, String foto,
			List<Domicilio> domicilios, String usuario, String contrasenia, String rol) {
		super(id, nombre, apellido, telefono, email, foto, domicilios);
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		super.agregarDomicilio(domicilio);
	}
}
