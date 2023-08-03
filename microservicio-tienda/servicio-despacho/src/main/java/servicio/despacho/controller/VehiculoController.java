package servicio.despacho.controller;

import application.entity.controllerCommons.ControllerCommons;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.despacho.entity.Vehiculos;
import servicio.despacho.service.VehiculoService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController  extends ControllerCommons<Vehiculos, VehiculoService> {

    @Override
    @GetMapping("/listar-vehiculos")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-vehiculos-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/buscar-vehiculo/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/registrar-vehiculo")
    public ResponseEntity<?> crear(@Valid  @RequestBody Vehiculos entity, BindingResult result) {
        return super.crear(entity, result);
    }

    @Override
    @DeleteMapping("/eliminar-vehiculo/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }

  @PutMapping("/editar/{id}")
  public ResponseEntity<?> editar(@Valid @PathVariable Long id, @RequestBody Vehiculos vehiculo, BindingResult result){


        try {


            if (id == null) {
                return ResponseEntity.badRequest().body("el id no puede ser nulo");
            }
            if (result.hasErrors()) {
                this.validar(result);
            }

            Optional<Vehiculos> o = service.findById(id);
            if (o.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el vehiculo no se encontro en la base de datos");
            }
            Vehiculos vehiculo1 = o.get();
            vehiculo1.setPlaca(vehiculo.getPlaca());
            vehiculo1.setMarca(vehiculo.getMarca());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(vehiculo1));

        }catch (Exception e){
            if (e instanceof  NumberFormatException){
                return ResponseEntity.badRequest().body("el id debe ser de tipo numerico");
            }
            return ResponseEntity.internalServerError().body("error en la solicitud");
        }

  }

}
