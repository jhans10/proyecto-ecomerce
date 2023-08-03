package servicio.despacho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"application.entity.models.EntityOrdenService","application.entity.models.EntityMetodoPago" ,"application.entity.models.EntityServiceUsuario","application.entity.models.EntityServiceProducto","servicio.despacho.entity"})
public class ServicioDespachoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioDespachoApplication.class, args);
	}

}
