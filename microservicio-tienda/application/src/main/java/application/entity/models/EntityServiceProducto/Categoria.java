package application.entity.models.EntityServiceProducto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer"})
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 15)
    private String nombre;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria(){
        this.productos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto){
    this.productos.add(producto);
    producto.setCategoria(this);
    }

    public void eliminar(Producto producto){
        this.productos.remove(producto);
        producto.setCategoria(null);
    }




}
