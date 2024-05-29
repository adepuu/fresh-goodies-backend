package com.adepuu.freshGoodiesBackend.products.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "metadata")
@SQLRestriction("deleted_at IS NULL")
public class Metadata {
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