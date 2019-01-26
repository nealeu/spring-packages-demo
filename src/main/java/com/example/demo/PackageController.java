package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PackageController {

    @GetMapping("packages")
    Mono<ProductPackage> listPackages() {
        return Mono.just(ProductPackage.of(1L, "package name", "package description"));
    }
}
