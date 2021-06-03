package ebs.back.entity.wrapper;

public class ItemPedidoPendiente {

    private Long idItem;
    private Long idArticuloVenta;
    private Integer cantidad;
    private Long idPedido;
    private Float precioVenta;
    private String denominacion;


    public ItemPedidoPendiente() {
    }

    public ItemPedidoPendiente(Long idItem, Long idArticuloVenta, Integer cantidad, Long idPedido, Float precioVenta, String denominacion) {
        this.idItem = idItem;
        this.idArticuloVenta = idArticuloVenta;
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.precioVenta = precioVenta;
        this.denominacion = denominacion;
    }

    public Long getIdArticuloVenta() {
        return idArticuloVenta;
    }

    public void setIdArticuloVenta(Long idArticuloVenta) {
        this.idArticuloVenta = idArticuloVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }
}
