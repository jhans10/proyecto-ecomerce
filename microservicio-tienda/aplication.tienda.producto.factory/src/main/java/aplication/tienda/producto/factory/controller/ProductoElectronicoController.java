package aplication.tienda.producto.factory.controller;

import aplication.tienda.producto.factory.service.ProductoElectronicoService;
import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.productos.ProductoElectronico;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/electronico")
public class ProductoElectronicoController extends ControllerCommons<ProductoElectronico, ProductoElectronicoService> {

    @Override
    @GetMapping("/listar-electronicos")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-electronicosP")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/electronicoId/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-electronico")
    public ResponseEntity<?> crear(@RequestBody ProductoElectronico entity, BindingResult result) {
        return super.crear(entity, result);
    }

    @Override
    @DeleteMapping("/eliminar-electronico/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }

    @PutMapping("/electronico/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody ProductoElectronico productoElectronico, BindingResult result, @PathVariable Long id){

        try{
            if(result.hasErrors()){
                return this.validar(result);
            }

            if (productoElectronico == null || id == null){
                throw  new IllegalArgumentException("los parametros no pueden ser nulos o vacio");
            }


            Optional<ProductoElectronico> o = service.findById(id);
            if (o.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            ProductoElectronico productoElectronico1 = o.get();
            productoElectronico1.setNombre(productoElectronico.getNombre());
            productoElectronico1.setSku(productoElectronico.getSku());
            productoElectronico1.setMarca(productoElectronico.getMarca());
            productoElectronico1.setPrecio(productoElectronico.getPrecio());
            productoElectronico1.setCategoria(productoElectronico.getCategoria());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoElectronico1));

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
