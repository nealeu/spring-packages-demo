package com.example.demo;

import lombok.Value;

@Value(staticConstructor = "of")
class LineItemDto {
    int count;
    String productId;
    String name;
    // Assumed to be in cents
    int usdPriceEach;
    int usdPriceTotal;
}
