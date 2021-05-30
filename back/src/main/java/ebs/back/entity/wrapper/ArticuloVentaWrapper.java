package ebs.back.entity.wrapper;

public class ArticuloVentaWrapper {

    private Long idArticuloVenta;
    private boolean aptoCeliaco;
    private boolean vegano;
    private boolean vegetariano;
    private String denominacion;
    private float precioVenta;
    private boolean esInsumo;
    private String descripcion;

    public ArticuloVentaWrapper() {
    }

    public ArticuloVentaWrapper(Long idArticuloVenta, boolean aptoCeliaco, boolean vegano, boolean vegetariano, String denominacion, float precioVenta, boolean esInsumo, String descripcion) {
        this.idArticuloVenta = idArticuloVenta;
        this.aptoCeliaco = aptoCeliaco;
        this.vegano = vegano;
        this.vegetariano = vegetariano;
        this.denominacion = denominacion;
        this.precioVenta = precioVenta;
        this.esInsumo = esInsumo;
        this.descripcion = descripcion;
    }

    public Long getIdArticuloVenta() {
        return idArticuloVenta;
    }

    public void setIdArticuloVenta(Long idArticuloVenta) {
        this.idArticuloVenta = idArticuloVenta;
    }

    public boolean isAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    public boolean isVegano() {
        return vegano;
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isEsInsumo() {
        return esInsumo;
    }

    public void setEsInsumo(boolean esInsumo) {
        this.esInsumo = esInsumo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
