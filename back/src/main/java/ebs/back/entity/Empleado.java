package ebs.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idEmpleado")
public class Empleado extends Persona {

    private String rol;

    public Empleado() {
        super();
    }

    public Empleado(String rol) {
        super();
        this.rol = rol;
    }

    public Empleado(Long id, String nombre, String apellido, String telefono, String email, String foto, String usuario, String contrasenia, boolean baja, List<Domicilio> domicilios, String rol) {
        super(id, nombre, apellido, telefono, email, foto, usuario, contrasenia, baja, domicilios);
        this.rol = rol;
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
