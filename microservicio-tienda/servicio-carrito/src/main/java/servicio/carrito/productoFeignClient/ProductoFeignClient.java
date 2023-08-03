package servicio.carrito.productoFeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-productos")
public interface ProductoFeignClient {
    @PutMapping("/productos/actualizar-stock/{valor}/{id}")
    public void actualizarStock(@PathVariable int valor, @PathVariable Long id);

    @GetMapping("/productos/consultar-stock/{id}")
    public int consultarStockProducto(@PathVariable Long id);
}
