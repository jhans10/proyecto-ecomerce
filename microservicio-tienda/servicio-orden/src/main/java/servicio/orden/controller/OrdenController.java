package servicio.orden.controller;

import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityOrdenService.Orden;
import application.entity.models.EntityOrdenService.OrdenDetalle;
import application.entity.models.EntityServiceCarrito.Carrito;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.orden.service.OrdenService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    @GetMapping("/listar-ordenes-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/orden-id/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/generar-orden")
    public ResponseEntity<?> crear(@RequestBody Orden entity, BindingResult result) {


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


    //segun mi logica la orden no se debe eliminar

//    @Override
//    @DeleteMapping("/eliminar-orden/{id}")
//    public ResponseEntity<?> eliminar(@PathVariable  Long id) {
//        return super.eliminar(id);
//    }


    //obtener la lista de carritos de otro microservicio con un FeignClientHtttp

    @GetMapping("/carritos-usuario/{id}")
    public  ResponseEntity<?> listarCarritos(@PathVariable Long id){
        return ResponseEntity.ok().body(service.carritosPorUsuario(id));
    }

}
