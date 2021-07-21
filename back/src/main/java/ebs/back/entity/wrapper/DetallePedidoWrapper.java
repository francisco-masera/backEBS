package ebs.back.entity.wrapper;

public class DetallePedidoWrapper {

    private Long id;
    private int cantidad;
    private ArticuloVentaWrapper articulo;

    public DetallePedidoWrapper() {
    }

    public DetallePedidoWrapper(Long id, int cantidad, ArticuloVentaWrapper articulo) {
        this.id = id;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArticuloVentaWrapper getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloVentaWrapper articulo) {
        this.articulo = articulo;
    }
}
