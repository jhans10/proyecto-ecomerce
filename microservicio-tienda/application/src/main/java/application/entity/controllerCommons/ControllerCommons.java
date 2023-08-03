package application.entity.controllerCommons;

import application.entity.serviceCommons.ServiceCommons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControllerCommons <E, S extends ServiceCommons<E>>{

    @Autowired
    protected  S service;

   // @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }

    //@GetMapping("/pagina")
    public  ResponseEntity<?>listarPage(Pageable pageable){
        return ResponseEntity.ok().body(service.findAllPage(pageable));
    }

    //@GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable Long id){

        try {
            Optional<E> o = service.findById(id);
            if (o.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(o.get());
        } catch (NumberFormatException n){
            return ResponseEntity.badRequest().body("El parametro 'id' debe ser numérico "+n.getMessage() +" "+n.getCause());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Error en la solicitud");
        }



    }

   // @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){


     try {
         if (result.hasErrors()){
             return this.validar(result);
         }

         if (entity == null){

            throw new IllegalArgumentException("el objeto entity no puede ser nulo");

         }
         return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));

     }catch ( IllegalArgumentException e){
//         if (e instanceof  IllegalArgumentException){
//             return ResponseEntity.badRequest().body("el objeto entity no puede ser nulo");
//         }
        return ResponseEntity.badRequest().body("el objeto entity no puede ser nulo");
     }

    }

   // @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            if (id == null){
                throw new IllegalArgumentException("el parametro id no puede ser nulo");
            }
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            if (e instanceof IllegalArgumentException){
                return ResponseEntity.badRequest().body("el parametro id no puede ser nulo");
            }else if (e instanceof NumberFormatException){
                return ResponseEntity.badRequest().body("el id debe ser Formato Numerico");
            }else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }


    protected  ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(error->{
            errores.put(error.getField(), "el campo "+error.getField()+" "+error.getDefaultMessage());

        });
        return ResponseEntity.badRequest().body(errores);
    }


}
