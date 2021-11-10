package home.and.company.eventdriven.shoppingcart.productservice.command.rest;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.and.company.eventdriven.shoppingcart.productservice.command.CreateProductCommand;
import home.and.company.eventdriven.shoppingcart.productservice.request.dto.CreateProductDto;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductCommandController {
	
	
	private final CommandGateway commandGateway;
	
	@Autowired
	public ProductCommandController(CommandGateway commandGateway) {
		
		this.commandGateway = commandGateway;
	}

	
	@PostMapping
	public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
		
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
		.title(createProductDto.getTitle())
		.price(createProductDto.getPrice())
		.quntity(createProductDto.getQuantity())
		.productId(UUID.randomUUID().toString()).build();
		
		String returnValue = commandGateway.sendAndWait(createProductCommand);
		
		return new ResponseEntity<String>(returnValue, HttpStatus.CREATED);
	}

}
