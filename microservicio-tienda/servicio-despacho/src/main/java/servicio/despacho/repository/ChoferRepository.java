package servicio.despacho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import servicio.despacho.entity.Chofer;

public interface ChoferRepository extends PagingAndSortingRepository<Chofer, Long> {
}
