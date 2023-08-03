package aplication.tienda.producto.factory.repository;

import application.entity.models.EntityServiceProducto.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface RepositoryCategoria extends PagingAndSortingRepository<Categoria, Long> {



}
