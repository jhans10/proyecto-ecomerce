package servicio.orden.service;

import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommons;

import java.util.List;

public interface OrdenService extends ServiceCommons<Orden> {


    public Iterable<Carrito> carritosPorUsuario(Long id);

    public void limpiarCarrito(Long id);
}
