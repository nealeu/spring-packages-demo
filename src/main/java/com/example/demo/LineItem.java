package com.example.demo;

import lombok.Value;

@Value(staticConstructor = "of")
class LineItem {
    int count;
    String productId;
}
