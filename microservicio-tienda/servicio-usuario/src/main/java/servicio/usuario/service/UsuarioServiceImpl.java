package servicio.usuario.service;

import application.entity.models.EntityServiceUsuario.Usuario;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;
import servicio.usuario.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl extends ServiceCommonsImpl<Usuario, UsuarioRepository> implements UsuarioService {
}
