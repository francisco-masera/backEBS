package ebs.back.entity.wrapper;

public class ItemPedidoPendiente {

    private Long idItem;
    private Long idArticuloVenta;
    private Integer cantidad;
    private Float precioVenta;
    private String denominacion;


    public ItemPedidoPendiente() {
    }

    public ItemPedidoPendiente(Long idItem, Long idArticuloVenta, Integer cantidad, Float precioVenta, String denominacion) {
        this.idItem = idItem;
        this.idArticuloVenta = idArticuloVenta;
        this.cantidad = cantidad;
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
