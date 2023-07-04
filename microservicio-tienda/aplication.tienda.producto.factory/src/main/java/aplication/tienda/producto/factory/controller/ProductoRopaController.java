package aplication.tienda.producto.factory.controller;

import aplication.tienda.producto.factory.service.ProductoRopaService;
import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityServiceProducto.Producto;
import application.entity.models.productos.ProductoRopa;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/ropa")
public class ProductoRopaController extends ControllerCommons<ProductoRopa, ProductoRopaService> {


    @GetMapping("/listar")
    public ResponseEntity<?> listarT() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    @GetMapping("/listar-ropaP")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/ropaId/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-ropa")
    public ResponseEntity<?> crear(@RequestBody ProductoRopa productoRopa, BindingResult result) {

        return super.crear(productoRopa, result);
    }

    @Override
    @DeleteMapping("/eliminar-ropa/{id}")
    public ResponseEntity<?> eliminar(@PathVariable  Long id) {
        return super.eliminar(id);
    }

    @PutMapping("/ropa/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody ProductoRopa productoRopa, BindingResult result, @PathVariable Long id){

        try{
            if(result.hasErrors()){
                return this.validar(result);
            }

            if (productoRopa == null || id == null){
                throw  new IllegalArgumentException("los parametros no pueden ser nulos o vacio");
            }


            Optional<ProductoRopa> o = service.findById(id);
            if (o.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            ProductoRopa productoRopa1 = o.get();
            productoRopa1.setNombre(productoRopa.getNombre());
            productoRopa1.setSku(productoRopa.getSku());
            productoRopa1.setMarca(productoRopa.getMarca());
            productoRopa1.setPrecio(productoRopa.getPrecio());
            productoRopa1.setCategoria(productoRopa.getCategoria());
            productoRopa1.setTalla(productoRopa.getTalla());
            productoRopa1.setColor(productoRopa.getColor());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoRopa1));

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
