package servicio.carrito.service;

import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommons;

public interface CarritoService extends ServiceCommons<Carrito> {
    public Iterable<Carrito> encontrarCarroPorUsuarioId(Long id);

    public void limpiarCarrito(Long id);

}
