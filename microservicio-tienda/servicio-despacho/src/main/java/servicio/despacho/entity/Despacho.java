package servicio.despacho.entity;


import application.entity.models.EntityOrdenService.Orden;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "despachos")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToOne
    @NotNull
    private Vehiculos vehiculo;

    @OneToOne
    @NotNull
    private Chofer chofer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
    @JoinTable(name = "despacho_rutas", joinColumns = @JoinColumn(name = "despacho_id"),
    inverseJoinColumns = @JoinColumn(name = "ruta_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"despacho_id", "ruta_id"}))
   @NotNull
    private List<Rutas> rutas;

    @Temporal(TemporalType.DATE)
    private Date fecha;


    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "despacho_registro", joinColumns = @JoinColumn(name = "id_despacho")
    , inverseJoinColumns = @JoinColumn(name = "id_orden"), uniqueConstraints = @UniqueConstraint(columnNames = {"id_orden"}))
    @NotNull
    private List<Orden> ordenes;


    private Boolean estadoEntrega;

    public Despacho(){


        this.rutas = new ArrayList<>();
        this.ordenes = new ArrayList<>();

    }

    @PrePersist
    public void prePersist(){
        this.fecha = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.fecha = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public List<Rutas> getRutas() {
        return rutas;
    }

    public void setRutas(List<Rutas> rutas) {
        this.rutas = rutas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public Boolean getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(Boolean estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }
}
