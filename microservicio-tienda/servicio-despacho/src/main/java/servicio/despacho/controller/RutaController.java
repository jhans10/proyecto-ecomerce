package servicio.despacho.controller;

import application.entity.controllerCommons.ControllerCommons;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import servicio.despacho.entity.Rutas;
import servicio.despacho.service.RutaService;

import javax.validation.Valid;

@RestController
@RequestMapping("/rutas")
public class RutaController extends ControllerCommons<Rutas, RutaService> {

    @Override
    @GetMapping("/listar-rutas")
    public ResponseEntity<?> listar() {
        return super.listar();
    }

    @Override
    @GetMapping("/listar-rutas-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/listar-ruta-id/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/registrar-ruta")
    public ResponseEntity<?> crear(@Valid  @RequestBody Rutas entity, BindingResult result) {
        return super.crear(entity, result);
    }

    @Override
    @DeleteMapping("/eliminar-ruta/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return super.eliminar(id);
    }
}
