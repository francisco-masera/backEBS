package ebs.back.entity.wrapper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ebs.back.entity.Cliente;
import ebs.back.entity.Factura;

public class PedidoWrapper {

    private Long id;
    private long numero;
    private String estado;
    private LocalTime hora;
    private boolean formaPago;
    private boolean tipoEntrega;
    private Factura factura;
    private Cliente cliente;
    private List<DetallePedidoWrapper> detalles = new ArrayList<>();
    private Float total;

    public PedidoWrapper() {

    }

    public PedidoWrapper(Long id, long numero, String estado, LocalTime hora, boolean formaPago, boolean tipoEntrega,
                         Factura factura, Cliente cliente, List<DetallePedidoWrapper> detalles, Float total) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.hora = hora;
        this.formaPago = formaPago;
        this.tipoEntrega = tipoEntrega;
        this.factura = factura;
        this.cliente = cliente;
        this.detalles = detalles;
        this.total = total;
    }

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

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isFormaPago() {
        return formaPago;
    }

    public void setFormaPago(boolean formaPago) {
        this.formaPago = formaPago;
    }

    public boolean isTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(boolean tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedidoWrapper> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoWrapper> detalles) {
        this.detalles = detalles;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
