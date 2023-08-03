package servicio.orden.service;

import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdenService extends ServiceCommons<Orden> {


    public Iterable<Carrito> carritosPorUsuario(Long id);

    public void limpiarCarrito(Long id);

    public void habilitarOrdenes(Iterable<Long> id);
    public void desHabilitarOrdenes(List<Long> id);

    public Page<Orden> ordenesPorUsuarioIdPage(Long id, Pageable pageable);

    public Iterable<Orden> ordenesPorUsuarioId(Long id);


    public Iterable<Orden> ordenesPorDistrtitoRegionestado(String distrito, String region,boolean estado);

}
