package servicio.carrito.service;

import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicio.carrito.repository.CarritoRepository;

@Service
public class CarritoServiceImpl extends ServiceCommonsImpl<Carrito, CarritoRepository> implements CarritoService {
    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrito> encontrarCarroPorUsuarioId(Long id) {
        return repository.encontrarCarroPorUsuarioId(id);
    }

    @Override
    @Transactional
    public void limpiarCarrito(Long id) {
        repository.limpiarCarrito(id);
    }

}
