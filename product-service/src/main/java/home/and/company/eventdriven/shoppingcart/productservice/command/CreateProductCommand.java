package home.and.company.eventdriven.shoppingcart.productservice.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductCommand {
	
	@TargetAggregateIdentifier
	private String productId;
	private final String title;
	private final BigDecimal price;
	private final Integer quntity;

}
