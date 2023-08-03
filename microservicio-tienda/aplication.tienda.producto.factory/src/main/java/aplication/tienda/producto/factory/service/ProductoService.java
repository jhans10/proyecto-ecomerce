package aplication.tienda.producto.factory.service;


import application.entity.models.EntityServiceProducto.Categoria;
import application.entity.models.EntityServiceProducto.Producto;
import application.entity.serviceCommons.ServiceCommons;

import java.util.List;

public interface ProductoService extends ServiceCommons<Producto> {
    public Iterable<Producto> listarTodo();
    public void actualizarStockProducto(int valor, Long id);

    public int consultarStockProducto(Long id);


}
