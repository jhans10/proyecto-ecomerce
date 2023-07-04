package application.entity.models.productos;

import application.entity.models.EntityServiceProducto.Producto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "producto_movil")
public class ProductoMovil extends Producto implements Serializable {

    @NotEmpty
    @Size(min = 4, max = 10)
    private String color;
    public ProductoMovil(){
      super();

  }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
