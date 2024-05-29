package com.adepuu.freshGoodiesBackend.products.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MetadataEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "Unit is required")
  private String unit;

  @Min(value = 0, message = "Weight must be non-negative")
  private int weight;

  @Min(value = 0, message = "Calorie count must be non-negative")
  private int calorie;

  @Min(value = 0, message = "Proteins must be non-negative")
  private double proteins;

  @Min(value = 0, message = "Fats must be non-negative")
  private double fats;

  @Min(value = 0, message = "Increment must be non-negative")
  private int increment;

  @Min(value = 0, message = "Carbs must be non-negative")
  private int carbs;
}