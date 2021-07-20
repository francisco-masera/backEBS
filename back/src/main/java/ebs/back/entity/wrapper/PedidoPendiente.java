package ebs.back.entity.wrapper;

import java.time.LocalTime;
import java.util.List;

public class PedidoPendiente {


    private final static Float envio = 50.0F;
    private final static Float descuento = 0.1F;
    private Long idPedido;
    private Long idCliente;
    private Boolean formaPago;
    private Boolean tipoEntrega;
    private String estado;
    private Long numero;
    private LocalTime horaEstimada;
    private List<ItemPedidoPendiente> items;
    private int tiempoEstimado;


    public PedidoPendiente() {
    }

    public PedidoPendiente(Long idPedido, Long idCliente, Boolean formaPago, Boolean tipoEntrega, String estado,
                           Long numero, LocalTime horaEstimada, List<ItemPedidoPendiente> items) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.formaPago = formaPago;
        this.tipoEntrega = tipoEntrega;
        this.estado = estado;
        this.numero = numero;
        this.horaEstimada = horaEstimada;
        this.items = items;
    }

    public PedidoPendiente(Long idPedido, Long idCliente, Boolean formaPago, Boolean tipoEntrega, String estado,
                           Long numero, LocalTime horaEstimada, List<ItemPedidoPendiente> items, int tiempoEstimado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.formaPago = formaPago;
        this.tipoEntrega = tipoEntrega;
        this.estado = estado;
        this.numero = numero;
        this.horaEstimada = horaEstimada;
        this.items = items;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Float getEnvio() {
        return envio;
    }

    public Float getDescuento() {
        return descuento;
    }

    public Boolean getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Boolean formaPago) {
        this.formaPago = formaPago;
    }

    public Boolean getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(Boolean tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalTime getHoraEstimada() {
        return horaEstimada;
    }

    public void setHoraEstimada(LocalTime horaEstimada) {
        this.horaEstimada = horaEstimada;
    }

    public List<ItemPedidoPendiente> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoPendiente> items) {
        this.items = items;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
}
