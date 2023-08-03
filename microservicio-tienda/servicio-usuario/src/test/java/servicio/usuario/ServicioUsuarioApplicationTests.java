package servicio.usuario;

import static org.mockito.Mockito.*;

import application.entity.models.EntityServiceUsuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.Fuseable;
import servicio.usuario.controller.UsuarioController;
import servicio.usuario.repository.UsuarioRepository;
import servicio.usuario.service.UsuarioService;
import servicio.usuario.service.UsuarioServiceImpl;

import java.util.Optional;

@SpringBootTest
class ServicioUsuarioApplicationTests {









	@BeforeEach
	void setUp(){



	}



	@Test
	void contextLoads() {

	}

}
