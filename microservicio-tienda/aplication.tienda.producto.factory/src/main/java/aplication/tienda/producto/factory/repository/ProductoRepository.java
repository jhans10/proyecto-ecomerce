package aplication.tienda.producto.factory.repository;

import application.entity.models.EntityServiceProducto.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long> {

    @Query(value = "SELECT productos.id, productos.nombre, sku, marca, precio, productos.categoria_id , categorias.nombre, producto_ropa.talla, producto_ropa.color FROM productos INNER JOIN categorias ON productos.categoria_id=categorias.id INNER JOIN producto_ropa ON productos.id = producto_ropa.id", nativeQuery = true)
    public Iterable<Producto> listarTodo();

    @Modifying
    @Query("UPDATE Producto SET stock = stock - :valor WHERE id = :id")
    public void actualizarStockProducto(@Param("valor") int valor, @Param("id") Long id);


    @Query("SELECT p.stock FROM Producto p WHERE p.id = :id")
    public int consultarStcok(@Param("id") Long id);

}
