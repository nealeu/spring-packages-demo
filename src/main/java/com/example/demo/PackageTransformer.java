package com.example.demo;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Takes a package containing line items and reactively enhances the line items with product price and name
 */
@Component
public class PackageTransformer implements Function<Mono<ProductPackage>, Mono<ProductPackageDto>> {

    private final Mono<Map<String, Product>> cachedProductMap;


    public PackageTransformer(ProductApi productApi) {
        cachedProductMap = productApi.getProducts()
                .map(this::indexProducts)
                .cache(Duration.ofSeconds(10));
    }

    @Override
    public Mono<ProductPackageDto> apply(Mono<ProductPackage> productPackage) {
        return cachedProductMap.zipWith(productPackage).map(tuple -> toDto(tuple.getT1(), tuple.getT2()));
    }

    private Map<String, Product> indexProducts(Product[] products) {
        return Stream.of(products).collect(toMap(Product::getId, Function.identity()));
    }

    private ProductPackageDto toDto(Map<String, Product> products, ProductPackage aPackage) {
        return ProductPackageDto.of(
                aPackage.getId(),
                aPackage.getName(),
                aPackage.getDescription(),
                Stream.of(aPackage.getProducts())
                        .map(lineItem -> toLineItemDto(lineItem, products))
                        .toArray(LineItemDto[]::new));
    }

    private LineItemDto toLineItemDto(LineItem lineItem, Map<String, Product> products) {
        int itemPrice = products.get(lineItem.getProductId()).getUsdPrice();
        return LineItemDto.of(lineItem.getCount(),
                lineItem.getProductId(),
                products.get(lineItem.getProductId()).getName(),
                itemPrice,
                itemPrice * lineItem.getCount());
    }
}
