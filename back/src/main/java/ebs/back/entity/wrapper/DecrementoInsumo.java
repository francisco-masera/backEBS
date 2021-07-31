package ebs.back.entity.wrapper;

import java.math.BigDecimal;

public class DecrementoInsumo {

    private Long idInsumo;
    private BigDecimal cantidad;
    private Integer cantidadDetalle;

    public DecrementoInsumo() {
    }

    public DecrementoInsumo(Long idInsumo, BigDecimal cantidad, Integer cantidadDetalle) {
        this.idInsumo = idInsumo;
        this.cantidad = cantidad;
        this.cantidadDetalle = cantidadDetalle;
    }

    public Integer getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(Integer cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
