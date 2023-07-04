package application.entity.models.EntityServiceUsuario;

import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityServiceProducto.Producto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 30)
    @Column(unique = true)
    private String nombre;

    @NotEmpty
    @Size(min = 4, max = 30)
    private String apellido;

    @NotNull
    private Long edad;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Size(min = 4, max = 15)
    @Column(unique = true)
    private String password;



    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Orden> ordenes;

//    @Column(name = "fecha")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date fecha;



    public Usuario(){
      this.ordenes = new ArrayList<>();
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Date getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }


    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public void addProducto(Orden orden){
        this.ordenes.add(orden);
        orden.setUsuario(this);
    }

    public void eliminar(Orden orden){
        this.ordenes.remove(orden);
        orden.setUsuario(null);
    }
}
