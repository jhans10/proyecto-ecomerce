package servicio.usuario.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import application.entity.models.EntityServiceUsuario.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import servicio.usuario.repository.UsuarioRepository;
import servicio.usuario.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@Transactional
public class UsuarioRepositoryTest {


 //la anotacion sirve para probar componentes solo para la capa de persistencia
//    esta anotacion solo buscara las clases de entidad o sea las clases con anotacion entity
//    y los repositorio de spring data jpa

    @Autowired
    UsuarioRepository usuarioRepository;



    private Usuario usuario;

    @BeforeEach
    void guardarUsuarios(){
        usuario = new Usuario();
        usuario.setNombre("pedro marin");
        usuario.setApellido("gonzales ganoza");
        usuario.setEmail("pedroGonzales@gmail.com");
        usuario.setEdad(24L);
        usuario.setPassword("1234347");

        usuarioRepository.save(usuario);

    }

    @DisplayName("test para obtener un usuario por id")
    @Test
    void testObtenerUsuarioPorId(){
        Usuario  usuario2 = new Usuario();

        usuario2.setNombre("lucas");
        usuario2.setApellido("prato fernandez");
        usuario2.setEmail("pratoFer@gmail.com");
        usuario2.setEdad(44L);
        usuario2.setPassword("4234347");
        usuarioRepository.save(usuario2);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(1L);

//        assertThat(Optional.of(usuarioEncontrado)).isNotNull();
//        assertThat(usuarioEncontrado.getNombre()).isEqualTo("lucas");

        Assertions.assertTrue(usuarioEncontrado.isPresent());
        Assertions.assertEquals("pedro marin", usuarioEncontrado.get().getNombre());

    }

//    @DisplayName("test para guardar usuario")
//    @Test
//    void testGuardarUsuario(){
////          usaremos la terminologia BDD, se divide en 3 partes
////            given : dado o condicion previa o configuracion
////            when: accion o el comportamiento que vamos a probar
////            then: verificar la salida
//
//
//        Usuario usuario3 = new Usuario();
//
//        usuario3.setNombre("jhans jhonnatan");
//        usuario3.setApellido("timana juarez");
//        usuario3.setEmail("timanajhans@gmail.com");
//        usuario3.setEdad(28L);
//        usuario3.setPassword("1234567");
//
//
//        Usuario usuarioGuardado2 = usuarioRepository.save(usuario3);
//
//        assertThat(usuarioGuardado2).isNotNull();
//        assertThat(usuarioGuardado2.getId()).isGreaterThan(0);
//
//    }

//    @DisplayName("test para listar a los usuarios")
//    @Test
//    void testListarUsuarios(){
//
//        Usuario usuario3 = new Usuario();
//        usuario3.setNombre("jhans jhonnatan");
//        usuario3.setApellido("timana juarez");
//        usuario3.setEmail("timanajhans@gmail.com");
//        usuario3.setEdad(28L);
//        usuario3.setPassword("1234567");
//
//
//        Usuario usuarioGuardado2 = usuarioRepository.save(usuario3);
//
//        List<Usuario> lista = (List<Usuario>) usuarioRepository.findAll();
//
//        assertThat(lista).isNotNull();
//        assertThat(lista.size()).isGreaterThan(0);
//        assertThat(lista.size()).isEqualTo(2);
//
//
//
//    }





//    @DisplayName("test para actualizar un empleado")
//    @Test
//    void testActualizarEmpleado(){
////        usuarioRepository.save(usuario);
//        Optional<Usuario> o = usuarioRepository.findById(1L);
//        if (o.isPresent()){
//            o.get().setNombre("pepe");
//            usuarioRepository.save(o.get());
//        }
//
//        Optional<Usuario> e = usuarioRepository.findById(1L);
//
//        assertThat(e).isNotNull();
//        assertThat(e.get().getNombre()).isEqualTo("pepe");
//
//
//    }
//
//    @DisplayName("test para eliminar un usuario")
//    @Test
//    void testEliminarUsuario(){
////        usuarioRepository.save(usuario);
////        usuarioRepository.save(usuario1);
//
//        usuarioRepository.deleteById(1L);
//        List<Usuario> lista = (List<Usuario>) usuarioRepository.findAll();
//
//
////        assertThat(lista).isNotNull();
////        assertThat(lista.size()).isEqualTo(2);
//
//
//
//        assertThat(lista.size()).isEqualTo(1);
//
//
//    }




}
