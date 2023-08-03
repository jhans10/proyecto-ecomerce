package servicio.usuario.controller;

import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityServiceUsuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.usuario.service.UsuarioService;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-usuario")
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario entity, BindingResult result) {
        return super.crear(entity, result);
    }



    @Override
    @GetMapping("/eliminar-usuario/{id}")
    public ResponseEntity<?> eliminar (@Valid @PathVariable Long id) {
        return super.eliminar(id);
    }

    @PutMapping("/editar-usuario/{id}")
    public ResponseEntity<?> editarPorId(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        try{
            if (result.hasErrors()){
                return this.validar(result);
            }

            if (usuario == null || id == null){
                throw  new IllegalArgumentException("los parámetros no pueden ser nulos o vacios");
            }

            Optional<Usuario> o = service.findById(id);
            if (o.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Usuario usuario1 = o.get();
            usuario1.setNombre(usuario.getNombre());
            usuario1.setApellido(usuario.getApellido());
            usuario1.setEdad(usuario.getEdad());
            usuario1.setEmail(usuario.getEmail());
            usuario1.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario1));
        }catch (Exception e){
           if (e instanceof IllegalArgumentException){
               return ResponseEntity.badRequest().body(" los parámetros no pueden ser nulos o vacios");
           }

           if (e instanceof  NumberFormatException){
               return ResponseEntity.badRequest().body("el parámetro id debe ser de tipo numerico");
           }else {
               return ResponseEntity.internalServerError().body(" error en la solicitud");
           }


        }
    }

}
