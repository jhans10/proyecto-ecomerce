package servicio.orden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EntityScan({"application.entity.models.EntityServiceUsuario","application.entity.models.EntityServiceCarrito","application.entity.models.EntityServiceProducto",  "application.entity.models.EntityMetodoPago", "application.entity.models.EntityOrdenService"})
public class ServicioOrdenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioOrdenApplication.class, args);
	}

}
