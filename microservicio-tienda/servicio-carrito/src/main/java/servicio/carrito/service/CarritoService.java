package servicio.carrito.service;

import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarritoService extends ServiceCommons<Carrito> {
    public Iterable<Carrito> encontrarCarroPorUsuarioId(Long id);

    public void limpiarCarrito(Long id);

    public void actualizarStock(int valor, Long id);

    public int consultarSotckproducto(Long id);

    public Page<Carrito> listarCarritosUsuarioIdPageable(Long id, Pageable pageable);

}
