package home.and.company.eventdriven.shoppingcart.productservice.request.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductDto {
	
	@NotBlank(message = "Product title cann't be empty")
	private String title;
	
	@Min(value = 1, message = "Price cann't be lower than 1")
	private BigDecimal price;
	
	@Min(value = 1, message = "Quantity cann't be lower than 1")
	private Integer quantity;
	

}
