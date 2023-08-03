package servicio.despacho.controller;

import application.entity.controllerCommons.ControllerCommons;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.despacho.entity.Chofer;
import servicio.despacho.service.ChoferService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/chofer")
public class ChoferController extends ControllerCommons<Chofer, ChoferService> {

    @Override
    @GetMapping("/listar-choferes")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-choferes-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/chofer-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/registar-chofer")
    public ResponseEntity<?> crear(@Valid @RequestBody Chofer entity, BindingResult result) {
        return super.crear(entity, result);
    }

    @Override
    @DeleteMapping("/eliminar-chofer/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }


    @PutMapping("/editar-chofer/{id}")
    public ResponseEntity<?> editarChofer(@Valid @RequestBody Chofer chofer, BindingResult result, @PathVariable Long id){

        try {
            if (id == null){
                return ResponseEntity.badRequest().build();
            }

            if (result.hasErrors()){
                this.validar(result);
            }

            Optional<Chofer> o = service.findById(id);
            if (o.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encontro el chofer");
            }

            Chofer chofer1 = o.get();
            chofer1.setNombres(chofer.getNombres());
            chofer1.setApellidos(chofer.getApellidos());
            chofer1.setDni(chofer.getDni());
            chofer1.setEdad(chofer.getEdad());
            chofer1.setLicencia(chofer.getLicencia());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(chofer1));

        }catch (Exception e){
            if (e instanceof NumberFormatException){
                return ResponseEntity.badRequest().body("el id debe ser de tipo numerico");
            }
            return ResponseEntity.internalServerError().body("error en la solicitud");
        }

    }

}
