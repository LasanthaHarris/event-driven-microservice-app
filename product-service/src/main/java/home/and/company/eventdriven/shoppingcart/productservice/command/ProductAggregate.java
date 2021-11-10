package home.and.company.eventdriven.shoppingcart.productservice.command;

import java.math.BigDecimal;
import java.util.Objects;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import home.and.company.eventdriven.shoppingcart.productservice.core.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	@AggregateIdentifier
	private String productId;
	private String title;
	private BigDecimal price;
	private Integer quntity;

	public ProductAggregate() {

	}

	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
		
		if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Price cann't be less than zero");
		}
		
		if(Objects.isNull(createProductCommand.getTitle()) || createProductCommand.getTitle().isBlank()) {
			throw new IllegalArgumentException("'Title' canno't be empty");
		}
		
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
		
		AggregateLifecycle.apply(productCreatedEvent);

	}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		
		this.productId = productCreatedEvent.getProductId();
		this.title = productCreatedEvent.getTitle();
		this.price = productCreatedEvent.getPrice();
		this.quntity = productCreatedEvent.getQuntity();

	}

}
