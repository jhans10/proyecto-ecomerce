package servicio.despacho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import servicio.despacho.entity.Despacho;

public interface DespachoRepository extends PagingAndSortingRepository<Despacho, Long> {
}
