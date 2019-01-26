package com.example.demo;

import lombok.Value;

@Value(staticConstructor = "of")
class ProductPackage {
    private final long id;
    private final String name;
    private final String description;

}
