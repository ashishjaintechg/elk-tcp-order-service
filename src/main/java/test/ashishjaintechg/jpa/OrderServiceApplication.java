package test.ashishjaintechg.jpa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OrderServiceApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceApplication.class);
	
	@GetMapping("/order")
	public String orderDetails() {
		String orderId="1001";
		LOGGER.info("{'hello':1001}");
		return orderId;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
