package ebs.back.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private long id;
	private List<TarjetaDebito> tarjetas;
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(long id, String nombre, String apellido, int telefono, String email, String foto,
			List<Domicilio> domicilios, List<TarjetaDebito> tarjetas, List<Pedido> pedidos) {
		super(id, nombre, apellido, telefono, email, foto, domicilios);
		this.id = id;
		this.tarjetas = tarjetas;
		this.pedidos = pedidos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
