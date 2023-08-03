package application.entity.models.EntityOrdenService;

import application.entity.models.EntityServiceProducto.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "orden_detalle")
public class OrdenDetalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private  Integer cantidad;

    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getImporte(){
        if (producto != null) {
            return cantidad.doubleValue() * producto.getPrecio();
        } else {
            return 0.0; // O cualquier otro valor predeterminado que desees asignar en caso de que el producto sea nulo
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenDetalle that = (OrdenDetalle) o;
        return Objects.equals(id, that.id) && Objects.equals(cantidad, that.cantidad) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, producto);
    }
}
