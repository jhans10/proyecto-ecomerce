package application.entity.models.productos;

import application.entity.models.EntityServiceProducto.Producto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "producto_electronico")
public class ProductoElectronico extends Producto implements Serializable {



    public ProductoElectronico(){
        super();
    }
}
