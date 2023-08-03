package servicio.carrito.repository;

import application.entity.models.EntityServiceCarrito.Carrito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends PagingAndSortingRepository<Carrito, Long> {


    @Query(value = "SELECT * FROM carrito where usuario_id=?", nativeQuery = true)
    public Iterable<Carrito> encontrarCarroPorUsuarioId(Long id);

    @Query(value = "SELECT * FROM carrito where usuario_id=?", nativeQuery = true)
    public Page<Carrito> encontrarCarroPorUsuarioIdPageable(Long id, Pageable pageable);

//investigar porque sale error nullPointerException usando consulta nativa en este

//    @Modifying
//    @Query(value = "DELETE FROM carrito WHERE usuario_id=?", nativeQuery = true)
//    public void limpiarCarrito(Long ide);

    @Modifying
    @Query("DELETE FROM Carrito ca where ca.usuario.id=?1")
    public void limpiarCarrito(Long ide);







}
