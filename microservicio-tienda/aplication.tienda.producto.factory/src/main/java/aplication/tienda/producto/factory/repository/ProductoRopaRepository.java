package aplication.tienda.producto.factory.repository;

import application.entity.models.EntityServiceProducto.Producto;
import application.entity.models.productos.ProductoRopa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRopaRepository extends PagingAndSortingRepository<ProductoRopa, Long> {

    @Query(value = "SELECT productos.id, productos.nombre, sku, marca, precio, productos.categoria_id , categorias.nombre, producto_ropa.talla, producto_ropa.color FROM productos INNER JOIN categorias ON productos.categoria_id=categorias.id INNER JOIN producto_ropa ON productos.id = producto_ropa.id", nativeQuery = true)
    public Iterable<ProductoRopa> listarTodo();
}
