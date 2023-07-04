package servicio.orden.clientHttp;

import application.entity.models.EntityServiceCarrito.Carrito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="microservicio-carrito")
public interface CarritoFeignClient {

    @GetMapping("/carrito/Carros-Usuario-id/{id}")
    public Iterable<Carrito> carritos(@PathVariable Long id);

    @DeleteMapping("/carrito/limpiar-carrito/{id}")
    public void limpiarCarrito(@PathVariable Long id);
}
