package servicio.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EntityScan({"application.entity.models.EntityServiceUsuario","application.entity.models.EntityOrdenService","application.entity.models.EntityMetodoPago","application.entity.models.EntityServiceProducto" ,"application.entity.models.EntityServiceCarrito"})
public class ServicioCarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioCarritoApplication.class, args);
	}

}
