package home.and.company.eventdriven.shoppingcart.productservice;

import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import home.and.company.eventdriven.shoppingcart.productservice.command.interceptors.CreateProductCommandInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
public class EventDrivenProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenProductServiceApplication.class, args);
	}
	
	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		
		commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
	}

}
