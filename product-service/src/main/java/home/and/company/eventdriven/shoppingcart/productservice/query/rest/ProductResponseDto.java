package home.and.company.eventdriven.shoppingcart.productservice.query.rest;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductResponseDto {

	private String productId;
	private String title;
	private BigDecimal price;
	private Integer quntity;
}
