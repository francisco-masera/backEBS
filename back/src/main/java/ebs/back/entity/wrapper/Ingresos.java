package ebs.back.entity.wrapper;

import java.time.LocalDate;

public class Ingresos {

    private LocalDate fechaMin;
    private LocalDate fechaMax;
    private Double ingresos;

    public Ingresos() {
    }

    public Ingresos(LocalDate fechaMin, LocalDate fechaMax, Double ingresos) {
        this.fechaMin = fechaMin;
        this.fechaMax = fechaMax;
        this.ingresos = ingresos;
    }

    public LocalDate getFechaMin() {
        return fechaMin;
    }

    public void setFechaMin(LocalDate fechaMin) {
        this.fechaMin = fechaMin;
    }

    public LocalDate getFechaMax() {
        return fechaMax;
    }

    public void setFechaMax(LocalDate fechaMax) {
        this.fechaMax = fechaMax;
    }

    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }
}
