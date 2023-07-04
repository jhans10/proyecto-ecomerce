package aplication.tienda.producto.factory.controller;

import aplication.tienda.producto.factory.service.ProductoMovilService;
import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.productos.ProductoMovil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/movil")
public class ProductoMovilController extends ControllerCommons<ProductoMovil, ProductoMovilService> {

    @Override
    @GetMapping("/listar-movil")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-movilP")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/movilId/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-movil")
    public ResponseEntity<?> crear(@RequestBody  ProductoMovil entity, BindingResult result) {
        return super.crear(entity, result);
    }

    @Override
    @DeleteMapping("/eliminar-movil/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }

    @PutMapping("/movil/{ide}")
    public ResponseEntity<?> editar(@Valid @RequestBody ProductoMovil productoMovil, BindingResult resul, @PathVariable Long ide){

        try{
            if(resul.hasErrors()){
                return this.validar(resul);
            }

            if (productoMovil == null || ide == null){
                throw  new IllegalArgumentException("los parametros no pueden ser nulos o vacio");
            }


            Optional<ProductoMovil> o = service.findById(ide);
            if (o.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            ProductoMovil productoMovil1 = o.get();
            productoMovil1.setNombre(productoMovil.getNombre());
            productoMovil1.setSku(productoMovil.getSku());
            productoMovil1.setMarca(productoMovil.getMarca());
            productoMovil1.setPrecio(productoMovil.getPrecio());
            productoMovil1.setCategoria(productoMovil.getCategoria());
            productoMovil1.setColor(productoMovil.getColor());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoMovil1));

        }catch (Exception e){
            if (e instanceof  IllegalArgumentException){
                return ResponseEntity.badRequest().body("los parametros no pueden ser nulos o vacios");

            }
            if (e instanceof NumberFormatException){
                return ResponseEntity.badRequest().body("el parámetro id de ser de tipo numerico");
            }else {
                return ResponseEntity.internalServerError().body("error en la solicitud");
            }

        }



    }
}
