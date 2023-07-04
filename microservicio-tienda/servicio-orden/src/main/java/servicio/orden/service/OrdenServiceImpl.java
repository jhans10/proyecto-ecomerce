package servicio.orden.service;

import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
