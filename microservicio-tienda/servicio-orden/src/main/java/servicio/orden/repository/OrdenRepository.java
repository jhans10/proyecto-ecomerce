package servicio.orden.repository;

import application.entity.models.EntityOrdenService.Orden;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdenRepository extends PagingAndSortingRepository<Orden, Long> {
}
