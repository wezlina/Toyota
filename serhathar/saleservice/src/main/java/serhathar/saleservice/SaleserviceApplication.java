package serhathar.saleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import serhathar.saleservice.inventory.client.ProductFeignClient;

@SpringBootApplication
@EnableFeignClients
public class SaleserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(SaleserviceApplication.class, args);
	}
}
