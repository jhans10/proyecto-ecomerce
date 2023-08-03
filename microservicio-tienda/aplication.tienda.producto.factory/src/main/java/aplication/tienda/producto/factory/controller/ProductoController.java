package aplication.tienda.producto.factory.controller;

import aplication.tienda.producto.factory.service.ProductoService;
import aplication.tienda.producto.factory.service.ServiceCategoria;
import application.entity.controllerCommons.ControllerCommons;
import application.entity.models.EntityServiceProducto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoController extends ControllerCommons<Producto, ProductoService> {


    @Autowired
    ServiceCategoria serviceCategoria;


    @GetMapping("/listar")
    public ResponseEntity<?> listarT() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    @GetMapping("/listar-page")
    public ResponseEntity<?> listarPage(Pageable pageable) {
        return super.listarPage(pageable);
    }

    @Override
    @GetMapping("/producto/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
        if(id == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error en la solicitud");
        }
        return super.findById(id);
    }

    @Override
    @PostMapping("/crear-producto")
    public ResponseEntity<?> crear(@Valid @RequestBody Producto producto, BindingResult result) {

        return super.crear(producto, result);
    }



    @DeleteMapping("/eliminar-producto/{id}")
    public ResponseEntity<?> eliminarProducto(@Valid @PathVariable  Long id) {

        if(id == null){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("el id no puede ser nulo");
        }

        try {
            Optional<Producto> producto = service.findById(id);

            if (producto.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el producto no existe en la base de datos");
            }

            String nombreFotoAnterior = producto.get().getImage();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length()>0){
                Path rutaFotoAnterior = Paths.get("C:\\Users\\hp\\Desktop\\proyecto-ecomerce-backend\\microservicio-tienda\\aplication.tienda.producto.factory\\images").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }
            service.deleteById(id);

        }catch (DataAccessException d){
            return ResponseEntity.badRequest().body(" error al eliminar el cliente en la base de datos");
        }

        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id){

        try{
            if(result.hasErrors()){
                return this.validar(result);
            }

            if (producto == null || id == null){
                throw  new IllegalArgumentException("los parametros no pueden ser nulos o vacio");
            }


            Optional<Producto> o = service.findById(id);
            if (o.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            Producto producto1 = o.get();
            producto1.setNombre(producto.getNombre());
            producto1.setSku(producto.getSku());
            producto1.setMarca(producto.getMarca());
            producto1.setPrecio(producto.getPrecio());
            producto1.setCategoria(producto.getCategoria());


            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));


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


    // CREAR PRODUCTO CON FOTO
    @PostMapping("/producto-foto")
    public ResponseEntity<?> upload(@Valid @RequestParam MultipartFile archivo, @RequestParam Long id){

        Map<String, Object> response = new HashMap<>();
        Optional<Producto> productoRopa = service.findById(id);
        if (!archivo.isEmpty()){
            String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace("", "");
            Path rutaImagen = Paths.get("C:\\Users\\hp\\Desktop\\proyecto-ecomerce-backend\\microservicio-tienda\\aplication.tienda.producto.factory\\images").resolve(nombreArchivo).toAbsolutePath();

            try{

                var arch= archivo.getInputStream();
                Files.copy(arch, rutaImagen);
                arch.close();
            }catch (IOException e){
                e.printStackTrace();
                response.put("mensaje", "error al subir la imagen del cliente"+nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = productoRopa.get().getImage();
            if (nombreFotoAnterior !=null && nombreFotoAnterior.length()>0){
                Path rutaFotoAnterior = Paths.get("C:\\Users\\hp\\Desktop\\proyecto-ecomerce-backend\\microservicio-tienda\\aplication.tienda.producto.factory\\images").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior =  rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }
            productoRopa.get().setImage(nombreArchivo);
            service.save(productoRopa.get());
            response.put("producto", productoRopa);
            response.put("mensaje", "has subido correctamente la imagem");


        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @GetMapping("/ver-foto-producto/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@Valid @PathVariable String nombreFoto){
        Path rutaArchivo = Paths.get("C:\\Users\\hp\\Desktop\\proyecto-ecomerce-backend\\microservicio-tienda\\aplication.tienda.producto.factory\\images").resolve(nombreFoto).toAbsolutePath();
        Resource resource = null;
        try{
            resource = new UrlResource(rutaArchivo.toUri());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        if (!resource.exists() && !resource.isReadable()){
            rutaArchivo = Paths.get("C:\\Users\\hp\\Desktop\\proyecto-ecomerce-backend\\microservicio-tienda\\aplication.tienda.producto.factory\\images").resolve("productoNot").toAbsolutePath();
       try {
           resource= new UrlResource(rutaArchivo.toUri());

       }catch (MalformedURLException e){
           e.printStackTrace();
       }
        }
        HttpHeaders cabeceras = new HttpHeaders();
        cabeceras.setContentType(MediaType.IMAGE_JPEG);
//        cabeceras.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename\""+resource.getFilename()+"\"");
//         cabeceras.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, cabeceras, HttpStatus.OK);
    }




    @PutMapping("/actualizar-stock/{valor}/{id}")
    public ResponseEntity<?> actualizarStock(@Valid @PathVariable int valor, @PathVariable Long id){
        service.actualizarStockProducto(valor, id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/consultar-stock/{id}")
    public ResponseEntity<?> consultarStock(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarStockProducto(id));
    }


    @GetMapping("/listar-categorias")
    ResponseEntity<?> listarCategorias(){
        return ResponseEntity.ok(serviceCategoria.listarCategoria());
    }


}
