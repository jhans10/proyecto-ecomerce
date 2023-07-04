package servicio.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"application.entity.models.EntityServiceUsuario","application.entity.models.EntityServiceProducto","application.entity.models.EntityServiceCarrito","application.entity.models.EntityOrdenService","application.entity.models.EntityMetodoPago","application.entity.models.EntityServiceUsuario"})
public class ServicioUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioUsuarioApplication.class, args);
	}

}
