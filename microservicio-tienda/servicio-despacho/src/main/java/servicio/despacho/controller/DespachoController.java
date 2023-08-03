package servicio.despacho.controller;

import application.entity.controllerCommons.ControllerCommons;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.despacho.entity.Despacho;
import servicio.despacho.entity.Rutas;
import servicio.despacho.service.DespachoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/despacho")
public class DespachoController extends ControllerCommons<Despacho, DespachoService> {

    @Override
    @GetMapping("/listar-despacho")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-despacho-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/listar-despacho-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/generar-despacho-salida")
    public ResponseEntity<?> crear(@Valid @RequestBody Despacho entity, BindingResult result) {



        Iterable<Long> idsOrden = entity.getOrdenes().stream().map(idOrden->{
            return idOrden.getId();
        }).collect(Collectors.toList());


        service.habilitar(idsOrden);

        return super.crear(entity, result);
    }

    @PutMapping("/editar-despacho/{id}")
    public ResponseEntity<?> editarDespacho(@Valid @RequestBody Despacho despacho, BindingResult result, @PathVariable Long id){



        try {
            if (result.hasErrors()){
                return this.validar(result);
            }

            if (despacho == null || id == null){
                throw  new IllegalArgumentException("los parametros no pueden ser nulos");
            }

            Optional<Despacho> o = service.findById(id);

            if (o.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            List<Long> idsOrden = despacho.getOrdenes().stream().map(idOrden->{
                return idOrden.getId();
            }).collect(Collectors.toList());

            List<Long> idsOrdenAnterior = o.get().getOrdenes().stream().map(idOrden->{
                return idOrden.getId();
            }).collect(Collectors.toList());

            List<Long> desHabilitarOrdenes = idsOrdenAnterior.stream().filter(idOrdenAnterior->!idsOrden.contains(idOrdenAnterior))
                            .collect(Collectors.toList());

            o.get().getRutas().clear();
            o.get().getOrdenes().clear();
            Despacho despacho1 = o.get();
            despacho1.setVehiculo(despacho.getVehiculo());
            despacho1.setChofer(despacho.getChofer());
            despacho1.setRutas(despacho.getRutas());
            despacho1.setFecha(despacho.getFecha());
            despacho1.setOrdenes(despacho.getOrdenes());
            despacho1.setEstadoEntrega(despacho.getEstadoEntrega());

            if (!desHabilitarOrdenes.isEmpty()){
                service.desHabilitar(desHabilitarOrdenes);
            }

            if (!idsOrden.isEmpty()){
                service.habilitar(idsOrden);
            }


            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(despacho1));
        }catch (Exception e){
            if (e instanceof IllegalArgumentException){
                return ResponseEntity.badRequest().body(" los parámetros no pueden ser nulos o vacios");
            }

            if (e instanceof  NumberFormatException){
                return ResponseEntity.badRequest().body("el parámetro id debe ser de tipo numerico");
            }else {
                return ResponseEntity.internalServerError().body(" error en la solicitud");
            }
        }

    }



    @Override
    @DeleteMapping("/eliminar-despacho/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }
}
