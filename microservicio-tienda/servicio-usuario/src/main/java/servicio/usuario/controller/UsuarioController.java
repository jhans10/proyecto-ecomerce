package servicio.usuario.controller;

import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityServiceUsuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends ControllerCommons<Usuario, UsuarioService> {


    @Override
    @GetMapping("/listar-usuarios")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-usuarios-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-usuario")
    public ResponseEntity<?> crear(@RequestBody Usuario entity, BindingResult result) {
        return super.crear(entity, result);
    }



    @Override
    @GetMapping("/eliminar-usuario/{id}")
    public ResponseEntity<?> eliminar (@PathVariable Long id) {
        return super.eliminar(id);
    }
}
