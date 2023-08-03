package servicio.despacho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import servicio.despacho.entity.Rutas;

public interface RutaRepository  extends PagingAndSortingRepository<Rutas, Long> {
}
