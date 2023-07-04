package aplication.tienda.producto.factory.service;

import aplication.tienda.producto.factory.repository.ProductoRopaRepository;
import application.entity.models.EntityServiceProducto.Producto;
import application.entity.models.productos.ProductoRopa;
import application.entity.serviceCommons.ServiceCommons;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductoRopaServiceImpl extends ServiceCommonsImpl<ProductoRopa, ProductoRopaRepository>  implements ProductoRopaService{
    @Override
    public Iterable<ProductoRopa> listarTodo() {
        return repository.listarTodo();
    }
}
