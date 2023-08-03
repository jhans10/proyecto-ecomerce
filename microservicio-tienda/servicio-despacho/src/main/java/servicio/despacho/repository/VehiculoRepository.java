package servicio.despacho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import servicio.despacho.entity.Vehiculos;

public interface VehiculoRepository extends PagingAndSortingRepository<Vehiculos, Long> {
}
