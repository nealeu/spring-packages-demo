package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequiredArgsConstructor
public class PackageController {

    private final PackageTransformer packagesTransformer;


    @GetMapping(value = "packages", produces = APPLICATION_JSON_UTF8_VALUE)
    Mono<ProductPackageDto> listPackages() {


        Mono<ProductPackage> packageFromDb = Mono.just(ProductPackage.of(1L, "package name", "package description",
                new LineItem[]{
                        LineItem.of(2, "7Hv0hA2nmci7"),
                        LineItem.of(1, "PKM5pGAh9yGm")
                }));

        return packageFromDb.transform(packagesTransformer);
    }
}
