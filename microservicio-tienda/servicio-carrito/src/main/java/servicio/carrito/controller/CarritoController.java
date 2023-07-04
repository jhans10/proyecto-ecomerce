package servicio.carrito.controller;

import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityServiceCarrito.Carrito;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.carrito.service.CarritoService;

import javax.ws.rs.Path;

@RestController
@RequestMapping("/carrito")
public class CarritoController extends ControllerCommons<Carrito, CarritoService> {

    @Override
    @GetMapping("/listar-carrito")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-carrito-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/carrito/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-carro")
    public ResponseEntity<?> crear(@RequestBody  Carrito entity, BindingResult result) {
        return super.crear(entity, result);
    }

    @Override
    @DeleteMapping("/eliminar-carro/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }



//creando un  metodo para feign client http en el microservicio orden

    @GetMapping("/Carros-Usuario-id/{id}")
    public ResponseEntity<?> listarCarroPorUsuarioId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.encontrarCarroPorUsuarioId(id));
    }

    @DeleteMapping("/limpiar-carrito/{id}")
    public ResponseEntity<?> limpiarCarritos(@PathVariable Long id){


        try {
            if (id==null){
                throw new IllegalArgumentException("el parametro id no puede ser nulo");
            }
            service.limpiarCarrito(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){

            if (e instanceof  IllegalArgumentException){
                return ResponseEntity.badRequest().body("el parámetro id no puede ser nulo");
            }else if (e instanceof NumberFormatException){
                return ResponseEntity.badRequest().body("el parámetro id debe ser de tipo númerico");
            }else {
                return ResponseEntity.internalServerError().build();
            }

        }
    }


}
