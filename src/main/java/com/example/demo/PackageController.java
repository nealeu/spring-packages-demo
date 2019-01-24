package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

public class PackageController {

    @GetMapping("packages")
    Flux<ProductPackage> listPackages() {
        return Flux.just(new ProductPackage(1L, "package name", "package description"));
    }
}
