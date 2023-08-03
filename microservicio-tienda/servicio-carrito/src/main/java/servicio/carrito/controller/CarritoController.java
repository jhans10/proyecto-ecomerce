package servicio.carrito.controller;

import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityServiceCarrito.Carrito;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.carrito.service.CarritoService;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

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


    @GetMapping("/listar-carrito-usuario-id-paginable/{id}")
    public ResponseEntity<?> listarCarritosUsuarioIdPage(@PathVariable Long id, Pageable pageable) {
        if (id== null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error en la solicitdad");
        }
        try{
            return ResponseEntity.ok(service.listarCarritosUsuarioIdPageable(id, pageable));

        }catch (NumberFormatException n){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("asegurese que el id se de tipo númerico");
        }
    }



    @Override
    @GetMapping("/carrito/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id) {
        return super.findById(id);
    }


    @PostMapping("/crear-carro")
    public ResponseEntity<?> crear(@Valid @RequestBody  Carrito entity, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            this.validar(result);
        }


        int sotckProducto = service.consultarSotckproducto(entity.getProducto().getId());

        if (sotckProducto >=  entity.getCantidad()){
            service.actualizarStock(entity.getCantidad(), entity.getProducto().getId());
           return  ResponseEntity.ok().body(service.save(entity));
        }else {
            response.put("mensaje", "producto agotado" );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @Override
    @DeleteMapping("/eliminar-carro/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }



//creando un  metodo para feign client http en el microservicio orden

    @GetMapping("/Carros-Usuario-id/{id}")
    public ResponseEntity<?> listarCarroPorUsuarioId(@Valid  @PathVariable Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error en la solicitud");
        }
        try{
            return ResponseEntity.ok().body(service.encontrarCarroPorUsuarioId(id));

        }catch (NumberFormatException n){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("asegurese de que el valor id sea de tipo númerico");
        }
    }

    @DeleteMapping("/limpiar-carrito/{id}")
    public ResponseEntity<?> limpiarCarritos(@Valid @PathVariable Long id){


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
