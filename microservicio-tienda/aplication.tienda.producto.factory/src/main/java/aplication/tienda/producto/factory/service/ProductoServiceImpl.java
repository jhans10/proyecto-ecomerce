package aplication.tienda.producto.factory.service;

import aplication.tienda.producto.factory.repository.ProductoRepository;
import application.entity.models.EntityServiceProducto.Producto;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServiceImpl extends ServiceCommonsImpl<Producto, ProductoRepository>  implements ProductoService {


    @Override
    public Iterable<Producto> listarTodo() {
        return repository.listarTodo();
    }

    @Override
    @Transactional
    public void actualizarStockProducto(int valor, Long id) {
        repository.actualizarStockProducto(valor, id);
    }

    @Override
    public int consultarStockProducto(Long id) {
        return repository.consultarStcok(id);
    }


}
