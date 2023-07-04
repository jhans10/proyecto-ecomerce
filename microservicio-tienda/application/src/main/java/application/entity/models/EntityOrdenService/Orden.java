package application.entity.models.EntityOrdenService;

import application.entity.models.EntityMetodoPago.MetodoPago;
import application.entity.models.EntityServiceUsuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orden")
public class Orden implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @JsonIgnoreProperties(value = {"ordenes","handler", "hibernateLazyInitializer"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    private MetodoPago metodoPago;



    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orden_id")
    private List<OrdenDetalle> ordenDetallesdetalles;

//    private Date fecha;

    public Orden(){
        this.ordenDetallesdetalles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }



    public List<OrdenDetalle> getOrdenDetallesdetalles() {
        return ordenDetallesdetalles;
    }

    public void setOrdenDetallesdetalles(List<OrdenDetalle> ordenDetallesdetalles) {
        this.ordenDetallesdetalles = ordenDetallesdetalles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orden orden = (Orden) o;
        return Objects.equals(id, orden.id) && Objects.equals(usuario, orden.usuario) && Objects.equals(metodoPago, orden.metodoPago);
    }

    public void addOrdenesDetalles(OrdenDetalle ordenDetalle){
        this.ordenDetallesdetalles.add(ordenDetalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, metodoPago);
    }

    public Double getTotal() {

        Double total =0.00;

        for (OrdenDetalle ordenDetalle : ordenDetallesdetalles){
            total += ordenDetalle.getImporte();
        }
        return total;
    }


}
