package home.and.company.eventdriven.shoppingcart.productservice.command.interceptors;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import home.and.company.eventdriven.shoppingcart.productservice.command.CreateProductCommand;
import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductLookupEntity;
import home.and.company.eventdriven.shoppingcart.productservice.core.data.ProductLookupRepository;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	private static final Logger Logger = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

	private ProductLookupRepository productLookupRepository;

	public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {

		this.productLookupRepository = productLookupRepository;
	}

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {

		return (index, command) -> {
			Logger.info("Intercepted command : " + command.getPayloadType());

			if (CreateProductCommand.class.equals(command.getPayloadType())) {

				CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
				ProductLookupEntity productEntity = productLookupRepository
						.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

				if (Objects.nonNull(productEntity)) {
					throw new IllegalStateException(
							String.format("Product with productId %s or title %s already exist.",
									productEntity.getProductId(), productEntity.getTitle()));
				}
			}
			return command;
		};
	}

}
