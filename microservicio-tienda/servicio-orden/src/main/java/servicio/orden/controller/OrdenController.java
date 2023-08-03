package servicio.orden.controller;

import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityOrdenService.OrdenDetalle;
import application.entity.models.EntityServiceCarrito.Carrito;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.orden.service.OrdenService;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/orden")
public class OrdenController extends ControllerCommons<Orden, OrdenService> {



    @Override
    @GetMapping("/listar-ordenes")
    public ResponseEntity<?> listar() {
        return super.listar();
    }


    @GetMapping("/filtrar-distrito-region-estado/{distrito}/{region}/{estado}")
    public ResponseEntity<?> filtrarPorDistritoRegionEstado(@PathVariable String distrito,
                                                            @PathVariable String region,
                                                            @PathVariable  boolean estado){

        Map<String, String> errors = new HashMap<>();
        if (distrito.isEmpty()){
            errors.put("distrito", "el parámetro distrito no puede ser vacio");
        }
        if (region.isEmpty()){
            errors.put("region", "el parámetro region no puede ser vacio");
        }

//        result.getFieldErrors().forEach(err->{
//            errors.put(err.getField(), " el campo "+err.getField()+" "+err.getDefaultMessage());
//        });


//
        if (!errors.isEmpty()){
            return ResponseEntity.badRequest().body(errors);


        }
        return ResponseEntity.ok(service.ordenesPorDistrtitoRegionestado(distrito, region,estado));


    }

    @Override
    @GetMapping("/listar-ordenes-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }


    @GetMapping("/ordenes-por-usuario-page/{id}")
    public ResponseEntity<?> ordenesPorUsuarioPage(@Valid @PathVariable Long id, Pageable pageable) {
        if (id== null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error en la solicitdad");
        }
        try {
            return ResponseEntity.ok(service.ordenesPorUsuarioIdPage(id, pageable));

        }catch (NumberFormatException n){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegurese de que el id sea de valor númerico");
        }
    }

    @GetMapping("/ordenes-por-usuario/{id}")
    public ResponseEntity<?> ordenesPorUsuario(@Valid @PathVariable Long id) {
        if (id == null){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error en la solicitud");
        }
        try{
            return ResponseEntity.ok(service.ordenesPorUsuarioId(id));

        }catch (NumberFormatException n){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegurese de que el valor sea de tipo numérico");
        }
    }


    @Override
    @GetMapping("/orden-id/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable  Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/generar-orden")
    public ResponseEntity<?> crear(@Valid @RequestBody Orden entity, BindingResult result) {


       Orden ord = service.save(entity);
          List<Carrito> carritos = (List<Carrito>) service.carritosPorUsuario(entity.getUsuario().getId());
       carritos.forEach(carr->{
           OrdenDetalle ordenDetalle = new OrdenDetalle();
           ordenDetalle.setId(ord.getId());
           ordenDetalle.setCantidad(carr.getCantidad());
           ordenDetalle.setProducto(carr.getProducto());
           ord.addOrdenesDetalles(ordenDetalle);
       });
        service.limpiarCarrito(ord.getUsuario().getId());
     return super.crear(ord, result);

    }

    @PutMapping("/habilitar-ordenes")
   public ResponseEntity<?> habilitar(@RequestBody Iterable<Long> id){
        service.habilitarOrdenes(id);
        return ResponseEntity.ok().build();

   }


    //segun mi logica la orden no se debe eliminar

//    @Override
//    @DeleteMapping("/eliminar-orden/{id}")
//    public ResponseEntity<?> eliminar(@PathVariable  Long id) {
//        return super.eliminar(id);
//    }


    //obtener la lista de carritos de otro microservicio con un FeignClientHtttp

//    @GetMapping("/carritos-usuario/{id}")
//    public  ResponseEntity<?> listarCarritos(@PathVariable Long id){
//        return ResponseEntity.ok().body(service.carritosPorUsuario(id));
//    }

    @PutMapping("/desHabilitar-ordenes")
    public ResponseEntity<?> habilitar(@Valid @RequestBody List<Long> id){
        service.desHabilitarOrdenes(id);
        return ResponseEntity.ok().build();

    }
}
