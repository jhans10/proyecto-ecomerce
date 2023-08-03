package servicio.despacho.ClientHttpOrden;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-ordenes")
public interface OrdenFeignClient {

    @PutMapping("/orden/habilitar-ordenes")
    public void habilitar(@RequestBody Iterable<Long> id);

    @PutMapping("/orden/desHabilitar-ordenes")
    public void desHabilitar(@RequestBody List<Long> id);

}
