package aplication.tienda.producto.factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.persistence.Entity;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"application.entity.models.productos","application.entity.models.EntityServiceProducto"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
