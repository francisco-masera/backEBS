package ebs.back.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private List<TarjetaDebito> tarjetas;
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(List<TarjetaDebito> tarjetas, List<Pedido> pedidos) {
		super();

		this.tarjetas = tarjetas;
		this.pedidos = pedidos;
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
