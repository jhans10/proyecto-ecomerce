package servicio.usuario.repository;


import application.entity.models.EntityServiceUsuario.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
}
