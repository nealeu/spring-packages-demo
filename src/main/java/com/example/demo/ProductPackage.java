package com.example.demo;

import lombok.Value;

@Value // or Data
public class ProductPackage {
    private final long id;
    private final String name;
    private final String description;

}
