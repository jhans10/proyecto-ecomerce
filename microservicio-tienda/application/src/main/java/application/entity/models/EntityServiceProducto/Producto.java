package application.entity.models.EntityServiceProducto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "productos")
@Inheritance(strategy = InheritanceType.JOINED) //este es para tema de herencia
 public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 30)
    private String nombre;
    @NotEmpty
    @Size(min = 3, max = 4)
    private String sku;
    @NotEmpty
    @Size(min = 4, max = 10)
    private String marca;





    @NotNull
    private Long precio;

    @NotNull
    @JsonIgnoreProperties(value = {"productos","handler", "hibernateLazyInitializer"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Producto(){
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(sku, producto.sku) && Objects.equals(precio, producto.precio) && Objects.equals(categoria, producto.categoria) && Objects.equals(marca, producto.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, sku, precio, categoria);
    }
}
