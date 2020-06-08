package ebs.back.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {

	private Long id;
	private long numero;
	private String estado;
	private LocalTime hora;
	private boolean tipoEntrega;
	private Factura factura;
	private Cliente cliente;
	private List<DetallePedido> detalles = new ArrayList<>();

	public Pedido() {

	}

	public Pedido(Long id, long numero, String estado, LocalTime hora, boolean tipoEntrega, Factura factura,
			Cliente cliente, List<DetallePedido> detalles) {
		this.id = id;
		this.numero = numero;
		this.estado = estado;
		this.hora = hora;
		this.tipoEntrega = tipoEntrega;
		this.factura = factura;
		this.cliente = cliente;
		this.detalles = detalles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalTime getHora() {
		return hora;
	}

	@Temporal(TemporalType.TIME)
	private Time convertirHora() {
		return Time.valueOf(this.getHora());
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean isTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(boolean tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	@OneToOne(mappedBy = "pedido")
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToMany(mappedBy = "pedido")
	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public float calularTotal() {
		return 0.0f;
	}

	public void agregarDetalle(DetallePedido detalle) {

	}

	public void cambiarEstado(String estado) {

	}

}
