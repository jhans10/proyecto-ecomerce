package aplication.tienda.producto.factory.repository;

import application.entity.models.productos.ProductoElectronico;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductoElectronicoRepository extends PagingAndSortingRepository<ProductoElectronico, Long> {
}
