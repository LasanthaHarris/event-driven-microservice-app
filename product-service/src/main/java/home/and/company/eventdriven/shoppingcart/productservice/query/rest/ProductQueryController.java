package home.and.company.eventdriven.shoppingcart.productservice.query.rest;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.and.company.eventdriven.shoppingcart.productservice.query.FindProductsQuery;

@RestController
@RequestMapping("/api/v1/products")
public class ProductQueryController {
	
	@Autowired
	private QueryGateway queryGateway;
	
	@GetMapping
	public List<ProductResponseDto> getProducts() {

		FindProductsQuery findProductsQuery = new FindProductsQuery();
		List<ProductResponseDto> products = queryGateway.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductResponseDto.class)).join();
		
		return products;
		
		
	}

}
