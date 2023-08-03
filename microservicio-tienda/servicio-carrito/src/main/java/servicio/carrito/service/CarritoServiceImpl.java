package servicio.carrito.service;

import application.entity.models.EntityServiceCarrito.Carrito;
import application.entity.serviceCommons.ServiceCommonsImpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicio.carrito.productoFeignClient.ProductoFeignClient;
import servicio.carrito.repository.CarritoRepository;

@Service
public class CarritoServiceImpl extends ServiceCommonsImpl<Carrito, CarritoRepository> implements CarritoService {

    @Autowired
    ProductoFeignClient productoFeignClient;

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

    @Override
    @Transactional
    public void actualizarStock(int valor, Long id) {

        productoFeignClient.actualizarStock(valor, id);
    }

    @Override
    @Transactional(readOnly = true)
    public int consultarSotckproducto(Long id) {
        return productoFeignClient.consultarStockProducto(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Carrito> listarCarritosUsuarioIdPageable(Long id, Pageable pageable) {
        return repository.encontrarCarroPorUsuarioIdPageable(id, pageable);
    }


}
