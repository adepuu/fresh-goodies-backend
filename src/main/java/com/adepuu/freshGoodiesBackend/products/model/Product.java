package com.adepuu.freshGoodiesBackend.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private String category;
    private String imageUrl;
    private double price;
    private int weight;
    private Metadata metadata;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private String unit;
        private int weight;
        private int calorie;
        private double proteins;
        private double fats;
        private int increment;
        private int carbs;
    }
}