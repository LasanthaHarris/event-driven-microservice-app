package home.and.company.eventdriven.shoppingcart.productservice.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductEntity;
import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductsRepository;
import home.and.company.eventdriven.shoppingcart.productservice.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

	private final ProductsRepository repository;
	
	public ProductEventsHandler(ProductsRepository repository) {
		
		this.repository = repository;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		
		repository.save(productEntity);
		
	}
}
