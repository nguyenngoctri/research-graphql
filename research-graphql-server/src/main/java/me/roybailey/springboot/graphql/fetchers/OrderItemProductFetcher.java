package me.roybailey.springboot.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.OrderItemDto;
import me.roybailey.data.schema.ProductDto;
import me.roybailey.springboot.ApplicationContextProvider;
import me.roybailey.springboot.service.ProductAdaptor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
@Component
public class OrderItemProductFetcher implements DataFetcher<ProductDto> {

    ProductAdaptor productAdaptor;

    public OrderItemProductFetcher() {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        productAdaptor = context.getBean(ProductAdaptor.class);
    }


    @Override
    public ProductDto get(DataFetchingEnvironment environment) {
        log.info("getSource {}", (Object)environment.getSource());
        OrderItemDto orderItemDto = environment.getSource();
        ProductDto product = productAdaptor.getProduct(orderItemDto.getProductId());
        return product;
    }
}