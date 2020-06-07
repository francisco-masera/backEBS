package ebs.back.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private Long id;
	private List<TarjetaDebito> tarjetas;
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nombre, String apellido, int telefono, String email, String foto,
			List<Domicilio> domicilios, List<TarjetaDebito> tarjetas, List<Pedido> pedidos) {
		super(id, nombre, apellido, telefono, email, foto, domicilios);
		this.id = id;
		this.tarjetas = tarjetas;
		this.pedidos = pedidos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<TarjetaDebito> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaDebito> tarjetas) {
		this.tarjetas = tarjetas;
	}

	@Override
	protected void agregarDomicilio(Domicilio domicilio) {
		super.agregarDomicilio(domicilio);
	}

}
