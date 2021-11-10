package home.and.company.eventdriven.shoppingcart.productservice.command.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import home.and.company.eventdriven.shoppingcart.productservice.command.interceptors.CreateProductCommandInterceptor;
import home.and.company.eventdriven.shoppingcart.productservice.request.dto.CreateProductDto;

@WebMvcTest
class ProductCommandControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CommandGateway commandGateway;
	
	@MockBean
	private CommandBus commandBus;
	
	@MockBean
	private CreateProductCommandInterceptor createProductCommandInterceptor;

	@BeforeEach
	void setUp() throws Exception {
		
		CreateProductDto.builder()
			.title("OnePlus 9T Pro")
			.quantity(1)
			.price(new BigDecimal(20000))
			.build();
	}

	@Test
	void testCreateProduct() {
		fail("Not yet implemented");
	}

}
 