package ebs.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idCliente")
public class Cliente extends Persona {

    private int token;
    private List<TarjetaDebito> tarjetas;
    private List<Pedido> pedidos;

    public Cliente() {
        super();
    }

    public Cliente(List<TarjetaDebito> tarjetas, List<Pedido> pedidos) {
        super();
        this.tarjetas = tarjetas;
        this.pedidos = pedidos;
    }

    public Cliente(Long id, String nombre, String apellido, String telefono, String email, String foto, String usuario, String contrasenia, boolean baja, List<Domicilio> domicilios, List<TarjetaDebito> tarjetas, List<Pedido> pedidos) {
        super(id, nombre, apellido, telefono, email, foto, usuario, contrasenia, baja, domicilios);
        this.tarjetas = tarjetas;
        this.pedidos = pedidos;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    public List<TarjetaDebito> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaDebito> tarjetas) {
        this.tarjetas = tarjetas;
    }

    @Override
    public void agregarDomicilio(Domicilio domicilio) {
        this.domicilios.add(domicilio);
    }


}
