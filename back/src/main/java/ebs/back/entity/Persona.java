package ebs.back.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Persona {

	protected long id;
	protected String nombre;
	protected String apellido;
	protected int telefono;
	protected String email;
	protected String foto;
	protected List<Domicilio> domicilios = new ArrayList<>();

	public Persona() {
	}

	public Persona(long id, String nombre, String apellido, int telefono, String email, String foto,
			List<Domicilio> domicilios) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.foto = foto;
		this.domicilios = domicilios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Domicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	protected void agregarDomicilio(Domicilio domicilio) {
		this.domicilios.add(domicilio);
	}
}
