package servicio.orden.service;

import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicio.orden.clientHttp.CarritoFeignClient;
import servicio.orden.repository.OrdenRepository;

import java.util.List;

@Service
public class OrdenServiceImpl extends ServiceCommonsImpl<Orden, OrdenRepository> implements OrdenService {

    @Autowired
    CarritoFeignClient carritoFeignClient;


    @Override
    public Iterable<Carrito> carritosPorUsuario(Long id) {

        return carritoFeignClient.carritos(id);
    }

    @Override
    public void limpiarCarrito(Long id) {
        carritoFeignClient.limpiarCarrito(id);
    }

    @Override
    @Transactional
    public void habilitarOrdenes(Iterable<Long> id) {

        repository.habilitarOrden(id);
    }

    @Override
    @Transactional
    public void desHabilitarOrdenes(List<Long> id) {
       repository.desHabilitarOrden(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Orden> ordenesPorUsuarioIdPage(Long id, Pageable pageable) {
        return repository.ordenesPorUsuarioIdPage(id, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Orden> ordenesPorUsuarioId(Long id) {
        return repository.ordenesPorUsuarioId(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Orden> ordenesPorDistrtitoRegionestado(String distrito, String region,boolean estado) {
        return repository.ordenesPorDistrtitoRegionestado(distrito, region,estado);
    }
}
