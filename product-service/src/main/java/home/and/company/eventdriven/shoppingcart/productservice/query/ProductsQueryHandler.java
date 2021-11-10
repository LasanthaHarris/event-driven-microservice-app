package home.and.company.eventdriven.shoppingcart.productservice.query;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductEntity;
import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductsRepository;
import home.and.company.eventdriven.shoppingcart.productservice.query.rest.ProductResponseDto;

@Component
public class ProductsQueryHandler {

	private final ProductsRepository productsRepository;

	public ProductsQueryHandler(ProductsRepository productsRepository) {

		this.productsRepository = productsRepository;
	}
	
	@QueryHandler
	public List<ProductResponseDto> findProducts(FindProductsQuery query) {
		
		List<ProductResponseDto> productsDtos = Collections.emptyList();
		
		List<ProductEntity> productEntities = productsRepository.findAll();
		
		productsDtos = productEntities.stream()
			.map(entity -> {
				ProductResponseDto productDto = new ProductResponseDto();
				BeanUtils.copyProperties(entity, productDto);
				return productDto;
			})
			.collect(Collectors.toList());
		
		
		return productsDtos;
	}

}
