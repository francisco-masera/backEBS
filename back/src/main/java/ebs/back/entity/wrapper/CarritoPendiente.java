package ebs.back.entity.wrapper;

import ebs.back.entity.DetallePedido;

import java.time.LocalDateTime;
import java.util.List;

public class CarritoPendiente {

    private Long id;
    private Long idCliente;
    private Float envio;
    private Float descuento;
    private Float total;
    private Boolean esTarjeta;
    private Boolean esDelivery;
    private String estado;
    private Long numero;
    private LocalDateTime horaEstimada;
    private List<DetallePedido> detalles;

    public CarritoPendiente() {
    }


    public CarritoPendiente(Long id, Long idCliente, Float envio,
                            Float descuento, Float total, Boolean esTarjeta, Boolean esDelivery, String estado,
                            Long numero, LocalDateTime horaEstimada, List<DetallePedido> detalles) {
        this.id = id;
        this.idCliente = idCliente;
        this.envio = envio;
        this.descuento = descuento;
        this.total = total;
        this.esTarjeta = esTarjeta;
        this.esDelivery = esDelivery;
        this.estado = estado;
        this.numero = numero;
        this.horaEstimada = horaEstimada;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEnvio(Float envio) {
        this.envio = envio;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getEsTarjeta() {
        return esTarjeta;
    }

    public void setEsTarjeta(Boolean esTarjeta) {
        this.esTarjeta = esTarjeta;
    }

    public Boolean getEsDelivery() {
        return esDelivery;
    }

    public void setEsDelivery(Boolean esDelivery) {
        this.esDelivery = esDelivery;
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

    public LocalDateTime getHoraEstimada() {
        return horaEstimada;
    }

    public void setHoraEstimada(LocalDateTime horaEstimada) {
        this.horaEstimada = horaEstimada;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
