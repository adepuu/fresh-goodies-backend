package com.adepuu.freshGoodiesBackend.products.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "product")
@SQLRestriction("deleted_at IS NULL")
public class Product {
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
  private Metadata metadata;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "deleted_at")
  private Date deletedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
    updatedAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();
  }
}