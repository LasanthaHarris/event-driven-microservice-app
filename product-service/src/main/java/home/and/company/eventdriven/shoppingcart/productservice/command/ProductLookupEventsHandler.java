package home.and.company.eventdriven.shoppingcart.productservice.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductLookupEntity;
import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductLookupRepository;
import home.and.company.eventdriven.shoppingcart.productservice.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

	private ProductLookupRepository repository;

	public ProductLookupEventsHandler(ProductLookupRepository repository) {

		this.repository = repository;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) {

		ProductLookupEntity entity = new ProductLookupEntity(event.getProductId(), event.getTitle());
		repository.save(entity);

	}

}
