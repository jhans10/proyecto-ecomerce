package application.entity.models.productos;

import application.entity.models.EntityServiceProducto.Producto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "producto_ropa")
public class ProductoRopa extends Producto   implements Serializable {

    @NotEmpty
    @Size(min = 1, max = 3)
    protected String talla;
    @NotEmpty
    @Size(min = 4, max = 10)
    protected String color;
    public ProductoRopa(){
        super();
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
