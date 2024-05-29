package com.adepuu.freshGoodiesBackend.products.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @NotBlank(message = "Product name is required")
  private String name;

  @NotBlank(message = "Category is required")
  private String category;

  @NotBlank(message = "Image URL is required")
  private String imageUrl;

  @Min(value = 0, message = "Price must be non-negative")
  private double price;

  @Min(value = 0, message = "Weight must be non-negative")
  private int weight;

  @NotNull(message = "Metadata is required")
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "metadata_id", referencedColumnName = "id")
  private MetadataEntity metadata;
}