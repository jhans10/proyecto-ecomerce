package aplication.tienda.producto.factory.service;


import application.entity.models.EntityServiceProducto.Producto;
import application.entity.models.productos.ProductoRopa;
import application.entity.serviceCommons.ServiceCommons;

public interface ProductoRopaService extends ServiceCommons<ProductoRopa> {
    public Iterable<ProductoRopa> listarTodo();
}
