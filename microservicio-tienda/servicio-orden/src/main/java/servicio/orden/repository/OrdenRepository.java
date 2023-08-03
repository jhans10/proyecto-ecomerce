package servicio.orden.repository;

import application.entity.models.EntityOrdenService.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends PagingAndSortingRepository<Orden, Long> {

    @Modifying
    @Query("UPDATE Orden o SET o.estado = true WHERE o.id IN :ids")
    public void habilitarOrden(@Param("ids") Iterable<Long> ids);

    @Modifying
    @Query("UPDATE Orden o SET o.estado = false WHERE o.id IN :ids")
    public void desHabilitarOrden(@Param("ids") List<Long> ids);

    @Query(value = "SELECT * FROM orden where usuario_id=?", nativeQuery = true)
    public Page<Orden> ordenesPorUsuarioIdPage(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM orden where usuario_id=?", nativeQuery = true)
    public Iterable<Orden> ordenesPorUsuarioId(Long id);


//    @Query("SELECT o FROM Orden o " + "JOIN o.usuario u " + "WHERE u.distrito LIKE CONCAT('%', :distrito, '%') " + "AND u.region LIKE CONCAT('%', :region, '%')")
//    public Page<Orden> ordenesPorDistrtitoRegionestado(@Param("distrito") String distrito,@Param("region") String region, Pageable pageable);




    @Query("SELECT o FROM Orden o JOIN o.usuario u " + "WHERE u.distrito LIKE CONCAT('%', :distrito, '%') " + "AND u.region LIKE CONCAT('%', :region, '%') " + "AND o.estado = :estado")
    public Iterable<Orden> ordenesPorDistrtitoRegionestado(@Param("distrito") String distrito,@Param("region") String region,@Param("estado") boolean estado);
}
