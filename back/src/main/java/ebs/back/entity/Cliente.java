package ebs.back.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Persona {

	private List<TarjetaDebito> tarjetas;
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(String nombre, String apellido, int telefono, String email, String foto, List<Domicilio> domicilios,
			List<TarjetaDebito> tarjetas, List<Pedido> pedidos) {
		super();
		this.tarjetas = tarjetas;
		this.pedidos = pedidos;
	}

	@OneToMany(mappedBy = "cliente")
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@OneToMany(mappedBy = "cliente")
	public List<TarjetaDebito> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaDebito> tarjetas) {
		this.tarjetas = tarjetas;
	}

	@Override
	protected void agregarDomicilio(Domicilio domicilio) {
		this.domicilios.add(domicilio);
	}

}
