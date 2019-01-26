package com.example.demo;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductPackageDto {

    private final long id;
    private final String name;
    private final String description;
    private final LineItemDto[] products;
}
